package erp.product.boundry;

import erp.product.control.ProductService;
import erp.product.entity.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class ProductBean {

    @Autowired
    private ProductService productService;

    private List products;
    private Product product;

    private String searchText = "";

    public void searchV1() {
        products = productService.getProducts(searchText);
    }

    public void searchV2() {
    }

    public void clear() {
        products = null;
    }

    public String edit(Long id) {
        this.product = productService.find(Product.class, id);

        return "/products/edit.xhtml";
    }

    public String save() {
        productService.save(product);

        return "/products/list.xhtml";
    }

    public String cancel() {
        return "/products/list.xhtml";
    }

    public String back() {
        products = null;

        return "/template.xhtml";
    }

    public int getNumberOfProducts() {
        if (products == null) {
            return 0;
        } else {
            return products.size();
        }
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

}
