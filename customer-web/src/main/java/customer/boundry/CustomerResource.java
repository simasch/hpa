package customer.boundry;

import customer.control.CustomerService;
import customer.entity.Customer;
import customer.control.TestDataGenerator;
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

    @RequestMapping(value = "/customers")
    @ResponseBody
    public List<Customer> list(@RequestParam(value = "term", required = false) String term) {
        return customerService.getCustomers(term);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete() {
        int number = customerService.deleteCustomers();
        return number + " customers deleted";
    }

    @RequestMapping("/customers/generate")
    @ResponseBody
    public void generate() {
        testDataGenerator.generate();
    }
}
