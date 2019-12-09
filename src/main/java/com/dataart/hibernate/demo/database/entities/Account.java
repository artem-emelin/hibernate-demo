package com.dataart.hibernate.demo.database.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "account")
@SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
public class Account {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Person owner;

    @Column(name = "amount", precision = 20, scale = 10)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime updated;

    @Version
    @Column(name = "version")
    private long version;

    @Override
    public String toString() {
        return "Account{" +
                this.getClass() +
                '}';
    }
}
