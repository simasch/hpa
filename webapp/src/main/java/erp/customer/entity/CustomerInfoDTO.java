package erp.customer.entity;

public class CustomerInfoDTO {

    private final Long id;
    private final String lastname;
    private final String firstname;
    private final double revenue;

    public final static String SELECT =
            "SELECT NEW erp.customer.entity.CustomerInfoDTO" +
            "(c.id, c.lastname, c.firstname, SUM(i.product.price)) " +
            "FROM Customer c JOIN c.orders o JOIN o.items i " +
            "WHERE c.lastname LIKE :term " +
            "GROUP BY c.id " +
            "ORDER BY c.lastname, c.firstname";

    public CustomerInfoDTO(Long id, String lastname, String firstname, double revenue) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.revenue = revenue;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public double getRevenue() {
        return revenue;
    }

}
