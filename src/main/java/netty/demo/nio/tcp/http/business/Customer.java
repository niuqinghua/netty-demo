package netty.demo.nio.tcp.http.business;

import java.util.List;

/**
 * Created by niuqinghua on 2015/7/13.
 */
public class Customer {

    private long customerNumber;

    private String firstName;

    private String lastName;

    private List<String> middleName;

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getMiddleName() {
        return middleName;
    }

    public void setMiddleName(List<String> middleName) {
        this.middleName = middleName;
    }
}
