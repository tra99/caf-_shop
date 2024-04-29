package shop.coffee.backend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private PaymentMethod paymentMethod;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Order> orders;

    // constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // public List<Order> getOrders() {
    //     return orders;
    // }

    // public void setOrders(List<Order> orders) {
    //     this.orders = orders;
    // }

    public enum PaymentMethod {
        ABA, Wing, Aclida
    }
}




    // @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
    // private List<Address> address;

    // public List<Address> getAddress() {
    //     return address;
    // }

    // public void setAddress(List<Address> address) {
    //     this.address = address;
    // }

    // @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL)
    // private List<Order> orders;

    // public List<Order> getOrders() {
    //     return orders;
    // }

    // public void setOrders(List<Order> orders) {
    //     this.orders = orders;
    // }