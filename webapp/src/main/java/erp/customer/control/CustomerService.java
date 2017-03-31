package erp.customer.control;

import erp.customer.entity.Customer;
import erp.customer.entity.CustomerInfoDTO;
import org.jooq.DSLContext;
import org.qlrm.executor.JpaQueryExecutor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

import static erp.database.Tables.*;
import static org.jooq.impl.DSL.sum;

@Service
public class CustomerService {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected DSLContext create;

    public List<Customer> getCustomers(String term) {
        TypedQuery<Customer> q = em.createQuery(
                "SELECT c FROM Customer c WHERE lower(c.lastname) LIKE lower(:term) ORDER BY c.lastname, c.firstname",
                Customer.class);
        q.setParameter("term", term + "%");
        return q.getResultList();
    }

    public List<CustomerInfoDTO> getCustomersWithConstructorExpression(String term) {
        //Query q = em.createQuery(CustomerInfoDTO.SELECT);
        Query q = em.createQuery(
                "SELECT NEW erp.customer.entity.CustomerInfoDTO" +
                        "(c.id, c.lastname, c.firstname, SUM(i.product.price)) " +
                        "FROM Customer c JOIN c.orders o JOIN o.items i " +
                        "WHERE lower(c.lastname) LIKE lower(:term) " +
                        "GROUP BY c.id, c.lastname, c.firstname " +
                        "ORDER BY c.lastname, c.firstname");

        q.setParameter("term", term + "%");
        return q.getResultList();
    }

    public List<CustomerInfoDTO> getCustomersWithSql(String term) {
        Query q = em.createNativeQuery(
                "SELECT C.ID, C.LASTNAME, C.FIRSTNAME, SUM(P.PRICE) " +
                        "FROM CUSTOMERS C " +
                        "JOIN ORDERS O ON C.ID = O.CUSTOMER_ID " +
                        "JOIN ORDERITEMS I ON O.ID = I.ORDER_ID " +
                        "JOIN PRODUCTS P ON I.PRODUCT_ID = P.ID " +
                        "WHERE LOWER(C.LASTNAME) LIKE LOWER(?) " +
                        "GROUP BY C.ID, C.LASTNAME, C.FIRSTNAME " +
                        "ORDER BY C.LASTNAME, C.FIRSTNAME");

        q.setParameter(1, term + "%");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return jpaResultMapper.list(q, CustomerInfoDTO.class);
    }

    public List<CustomerInfoDTO> getCustomersWithSqlFromFile(String term) {
        JpaQueryExecutor jpaQueryExecutor = new JpaQueryExecutor();
        return jpaQueryExecutor.executeSelect(em, CustomerInfoDTO.class, "sql/customer_revenue.sql", term + "%");
    }

    public List<CustomerInfoDTO> getCustomersWithJooq(String term) {
        return create.
                select(CUSTOMERS.ID, CUSTOMERS.LASTNAME, CUSTOMERS.FIRSTNAME, sum(PRODUCTS.PRICE)).
                from(CUSTOMERS).
                join(ORDERS).on(ORDERS.CUSTOMER_ID.eq(CUSTOMERS.ID)).
                join(ORDERITEMS).on(ORDERITEMS.ORDER_ID.eq(ORDERS.ID)).
                join(PRODUCTS).on(PRODUCTS.ID.eq(ORDERITEMS.PRODUCT_ID)).
                where(CUSTOMERS.LASTNAME.like(term + "%")).
                groupBy(CUSTOMERS.ID, CUSTOMERS.LASTNAME, CUSTOMERS.FIRSTNAME).
                orderBy(CUSTOMERS.LASTNAME, CUSTOMERS.FIRSTNAME).
                fetchInto(CustomerInfoDTO.class);
    }

    public <T> T find(Class<T> c, Serializable id) {
        return em.find(c, id);
    }

    @Transactional
    public <T> T save(T t) {
        return em.merge(t);
    }

    @Transactional
    public int deleteCustomers() {
        em.createQuery("DELETE FROM OrderItem o").executeUpdate();
        em.createQuery("DELETE FROM Order o").executeUpdate();
        return em.createQuery("DELETE FROM Customer c").executeUpdate();
    }

}
