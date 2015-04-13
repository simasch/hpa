package customer.control;

import customer.entity.Customer;
import customer.entity.Order;
import customer.entity.OrderItem;
import customer.entity.Product;
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

            for (int j = 0; j < random.nextInt(50 - 1 + 1) + 1; j++) {
                Order order = new Order();
                customer.getOrders().add(order);

                for (int k = 0; k < random.nextInt(20 - 1 + 1) + 1; k++) {
                    OrderItem item = new OrderItem();
                    item.setProduct(product);
                    item.setQuantity(random.nextInt(10 - 1 + 1) + 1);

                    order.getItems().add(item);
                }
            }
        }
    }

}
