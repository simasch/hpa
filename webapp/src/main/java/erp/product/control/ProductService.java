package erp.product.control;

import erp.product.entity.Product;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @PersistenceContext
    protected EntityManager em;

    public List<Product> getProducts(String term) {
        String queryString = "SELECT p FROM Product p ORDER BY p.name";

        TypedQuery<Product> q = em.createQuery(queryString, Product.class);
        List<Product> list = q.getResultList();

        return list;
    }

    public <T> T find(Class<T> c, Serializable id) {
        return em.find(c, id);
    }

    @Transactional
    public <T> T save(T t) {
        return em.merge(t);
    }

}
