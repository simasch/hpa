package customer.boundry;

import customer.control.CustomerService;
import customer.entity.Customer;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController implements Serializable {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    @ResponseBody
    public List<Customer> list(@RequestParam(value = "term", required = false) String term) {
        return customerService.getCustomers(term);
    }

}
