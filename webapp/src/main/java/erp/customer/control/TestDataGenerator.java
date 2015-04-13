package erp.customer.control;

import erp.customer.entity.Customer;
import erp.customer.entity.Order;
import erp.customer.entity.OrderItem;
import erp.product.entity.Product;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.soqqo.datagen.RandomDataGenerator;
import org.soqqo.datagen.config.DataTypes.Name;
import org.soqqo.datagen.config.GenConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestDataGenerator {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void generate() {
        Random random = new Random();
        Product product = new Product();
        product.setName("Bleistift");
        product.setPrice(2.5d);
        em.persist(product);

        RandomDataGenerator rdg = new RandomDataGenerator();
        List<Customer> customers = rdg.generateList(400,
                new GenConfig()
                .name(Name.Firstname, "firstname")
                .name(Name.Lastname, "lastname"), Customer.class);

        for (Customer customer : customers) {
            em.persist(customer);

            List<Order> orders = rdg.generateList(random.nextInt(10),
                    new GenConfig()
                    .dateTimeRandom("orderDate"), Order.class);

            for (Order order : orders) {
                customer.getOrders().add(order);

                List<OrderItem> orderItems = rdg.generateList(random.nextInt(20),
                        new GenConfig(), OrderItem.class);

                for (OrderItem item : orderItems) {
                    item.setProduct(product);
                    item.setQuantity(random.nextInt(5) + 1);

                    order.getItems().add(item);
                }
            }
        }
    }

}
