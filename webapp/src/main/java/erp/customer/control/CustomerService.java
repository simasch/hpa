package erp.customer.control;

import erp.customer.entity.Customer;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
