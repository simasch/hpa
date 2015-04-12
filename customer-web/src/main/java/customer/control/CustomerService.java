package customer.control;

import customer.entity.Customer;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @PersistenceContext
    protected EntityManager em;

    public List<Customer> getCustomers(String term) {
        String queryString = "SELECT c FROM Customer c";
        
        TypedQuery<Customer> q = em.createQuery(queryString, Customer.class);
        List<Customer> list = q.getResultList();

        return list;
    }

    public <T> T find(Class<T> c, Serializable id) {
        return em.find(c, id);
    }

    public <T> T save(T t) {
        return em.merge(t);
    }

}
