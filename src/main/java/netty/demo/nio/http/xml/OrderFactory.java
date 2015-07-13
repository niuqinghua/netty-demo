package netty.demo.nio.http.xml;

import netty.demo.nio.http.business.Address;
import netty.demo.nio.http.business.Customer;
import netty.demo.nio.http.business.Order;
import netty.demo.nio.http.business.Shipping;

/**
 * Created by niuqinghua on 2015/7/14.
 */
public class OrderFactory {

    public static Order create(long orderId) {
        Order order = new Order();
        order.setOrderNumber(orderId);
        order.setTotal(9999.99f);

        Address address = new Address();
        address.setCity("beijing");
        address.setCountry("China");
        address.setPostCode("100000");
        address.setState("beijing");
        address.setStreet1("street1");
        address.setStreet2("street2");
        order.setBillTo(address);

        Customer customer = new Customer();
        customer.setCustomerNumber(orderId);
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        order.setCustomer(customer);

        order.setShipping(Shipping.INTERNATIONAL_MAIL);
        order.setShipTo(address);

        return order;
    }

}
