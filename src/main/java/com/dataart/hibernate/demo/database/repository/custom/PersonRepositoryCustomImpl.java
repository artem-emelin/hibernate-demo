package com.dataart.hibernate.demo.database.repository.custom;

import com.dataart.hibernate.demo.database.entities.Person;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@PersistenceContext
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    private final EntityManager em;

    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    public Person findByName(String firstName) {
        return em.createQuery("FROM Person p WHERE p.firstName = :firstName", Person.class)
                .setParameter("firstName", firstName)
                .setHint(QueryHints.HINT_READONLY, true)
                .getSingleResult();
    }

    public List<Person> findAllWithGraph() {
        return em.createQuery("FROM Person p", Person.class)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), em.getEntityGraph(Person.GRAPH_ACCOUNTS))
                .getResultList();
    }

    // Test to save entity in read only Tx
    @Transactional
    public void saveCustom(Person person) {
        Session session = em.unwrap(Session.class);
        session.setDefaultReadOnly(false);
        session.setReadOnly(person, false);
        session.merge(person);
        session.setReadOnly(person, false);
        session.setFlushMode(FlushModeType.AUTO);
        em.flush();
    }
}
