package customer.boundry;

import customer.control.CustomerService;
import customer.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class CustomerBean {

    @Autowired
    private CustomerService customerService;

    private List customers;
    private Customer customer;

    private String searchText = "";

    public void searchV1() {
        customers = customerService.getCustomers(searchText);
    }

    public void searchV2() {
    }

    public void clear() {
        customers = null;
    }

    public String edit(Long id) {
        this.customer = customerService.find(Customer.class, id);

        return "customer_edit.xhtml";
    }

    public String save() {
        customerService.save(customer);

        return "customer_list.xhtml";
    }

    public String cancel() {
        return "customer_list.xhtml";
    }

    public String back() {
        customers = null;

        return "index.xhtml";
    }

    public List getCustomers() {
        return customers;
    }

    public void setCustomers(List customers) {
        this.customers = customers;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getNumberOfCustomers() {
        if (customers == null) {
            return 0;
        } else {
            return customers.size();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
