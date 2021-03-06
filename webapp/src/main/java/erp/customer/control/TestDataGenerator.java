package erp.customer.control;

import erp.customer.entity.Customer;
import erp.customer.entity.Order;
import erp.customer.entity.OrderItem;
import erp.product.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.person.Person;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestDataGenerator {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void generate() {
        Fairy fairy = Fairy.create();

        Random random = new Random();
        Product product = new Product();
        product.setName("Bleistift");
        product.setPrice(2.5d);
        em.persist(product);

        for (int i = 0; i < 400; i++) {
            Person person = fairy.person();
            Customer customer = new Customer();
            customer.setFirstname(person.getFirstName());
            customer.setLastname(person.getLastName());
            em.persist(customer);

            DateProducer dateProducer = fairy.dateProducer();
            for (int j = 0; j < random.nextInt(10) + 1; j++) {
                Order order = new Order();
                order.setOrderDate(dateProducer.randomDateInThePast(10).toDate());
                customer.getOrders().add(order);

                for (int k = 0; k < random.nextInt(20) + 1; k++) {
                    OrderItem item = new OrderItem();
                    item.setProduct(product);
                    item.setQuantity(random.nextInt(5) + 1);

                    order.getItems().add(item);
                }
            }
        }
    }

}
