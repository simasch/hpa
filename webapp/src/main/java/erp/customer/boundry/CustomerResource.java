package erp.customer.boundry;

import erp.customer.control.CustomerService;
import erp.customer.entity.Customer;
import erp.customer.control.TestDataGenerator;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerResource implements Serializable {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TestDataGenerator testDataGenerator;

    @RequestMapping(value = "/api/customers")
    @ResponseBody
    public List<Customer> list(@RequestParam(value = "term", required = false) String term) {
        return customerService.getCustomers(term == null ? "" : term);
    }

    @RequestMapping(value = "/api/customers", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete() {
        int number = customerService.deleteCustomers();
        return number + " customers deleted";
    }

    @RequestMapping("/api/customers/generate")
    @ResponseBody
    public void generate() {
        testDataGenerator.generate();
    }
}
