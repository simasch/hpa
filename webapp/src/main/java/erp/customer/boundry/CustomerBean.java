package erp.customer.boundry;

import erp.customer.control.CustomerService;
import erp.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
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
        customers = customerService.getCustomersWithConstructorExpression(searchText);
    }

    public void searchV3() {
        customers = customerService.getCustomersWithSqlFromFile(searchText);
    }

    public void searchV4() {
        customers = customerService.getCustomersWithJooq(searchText);
    }

    public void clear() {
        customers = null;
    }

    public String edit(Long id) {
        this.customer = customerService.find(Customer.class, id);

        return "/customers/edit.xhtml";
    }

    public String save() {
        customerService.save(customer);

        return "/customers/list.xhtml";
    }

    public String cancel() {
        return "/customers/list.xhtml";
    }

    public String back() {
        customers = null;

        return "/template.xhtml";
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
        return customers == null ? 0 : customers.size();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
