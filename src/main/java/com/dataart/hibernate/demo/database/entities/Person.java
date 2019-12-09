package com.dataart.hibernate.demo.database.entities;

import com.dataart.hibernate.demo.util.PersonGenderConverter;
import com.dataart.hibernate.demo.util.PersonStatusConverter;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "person")
@SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = Person.GRAPH_ACCOUNTS,
                attributeNodes = { @NamedAttributeNode("accounts") }
        )
})
//@DynamicInsert
//@DynamicUpdate
public class Person {

    public static final String GRAPH_ACCOUNTS = "Person.Graph[accounts]";

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "gender", length = 1, updatable = false)
    @Convert(converter = PersonGenderConverter.class)
    private PersonGender gender;

    @Column(name = "status", length = 1)
    @Builder.Default
    @Convert(converter = PersonStatusConverter.class)
    private PersonStatus status = PersonStatus.ACTIVE;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime updated;


    @Builder.Default
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    //@Fetch(value = FetchMode.SUBSELECT)
    //@LazyCollection(value = LazyCollectionOption.EXTRA)
    //@BatchSize(size = 2)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        //account.setOwner(this);
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        //account.setOwner(null);
        accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Person{" +
                this.getClass() +
                '}';
    }
}
