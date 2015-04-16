package erp.customer.control;

import erp.customer.entity.Customer;
import erp.customer.entity.CustomerInfoDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @PersistenceContext
    protected EntityManager em;

    public List<Customer> getCustomers(String term) {
        TypedQuery<Customer> q = em.createQuery(
                "SELECT c FROM Customer c WHERE c.lastname LIKE :term ORDER BY c.lastname, c.firstname",
                Customer.class);
        q.setParameter("term", term + "%");
        return q.getResultList();
    }

    public List<CustomerInfoDTO> getCustomersWithConstructorExpression(String term) {
        Query q = em.createQuery(
                "SELECT NEW erp.customer.entity.CustomerInfoDTO(c.id, c.lastname, c.firstname, SUM(i.product.price)) "
                + "FROM Customer c JOIN c.orders o JOIN o.items i "
                + "WHERE c.lastname LIKE :term "
                + "GROUP BY c.id "
                + "ORDER BY c.lastname, c.firstname");
        q.setParameter("term", term + "%");
        return q.getResultList();
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
