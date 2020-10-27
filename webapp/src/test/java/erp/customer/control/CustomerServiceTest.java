package erp.customer.control;

import erp.customer.entity.CustomerInfoDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomersWithSql() {
        List<CustomerInfoDTO> customers = customerService.getCustomersWithSql(null);

        Assert.assertTrue(customers.isEmpty());
    }

}
