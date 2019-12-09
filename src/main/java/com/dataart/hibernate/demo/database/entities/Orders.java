package com.dataart.hibernate.demo.database.entities;

import com.dataart.hibernate.demo.model.OrderModel;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import java.time.LocalDateTime;

import static com.dataart.hibernate.demo.database.entities.Orders.GET_ALL_ORDERS;
import static com.dataart.hibernate.demo.database.entities.Orders.ORDERS_MAPPING;
import static lombok.AccessLevel.PRIVATE;


@NamedNativeQuery(
        name = GET_ALL_ORDERS,
        query = "SELECT o.id as id, o.order_name as orderName from orders o",
        resultSetMapping = ORDERS_MAPPING
)
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ORDERS_MAPPING,
                classes = @ConstructorResult(
                        targetClass = OrderModel.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "orderName", type = String.class)
                        })),
})

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 10)
//@Immutable
//@SQLInsert(sql = "insert into orders (id, order_name, created, updated) VALUES (?,?,?,?)")
//@SQLUpdate(sql = "update orders set order_name = ?, updated = ? where id = ?")
//@SQLDelete(sql = "delete from orders where id = ? and updated < DATEADD(Year, -1, now())")
public class Orders {

    public static final String GET_ALL_ORDERS = "orders.GetAllOrders";
    public static final String ORDERS_MAPPING = "orders.orderModelMapping";

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "Order{" +
                this.getClass() +
                '}';
    }
}
