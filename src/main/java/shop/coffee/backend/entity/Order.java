// package shop.coffee.backend.entity;

// import com.fasterxml.jackson.annotation.JsonBackReference;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import java.util.*;

// @Entity
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long orderId;

//     private long qty;
//     private DeliverOption deliverOption;
//     public long getQty() {
//         return qty;
//     }

//     public void setQty(long qty) {
//         this.qty = qty;
//     }

//     public DeliverOption getDeliverOption() {
//         return deliverOption;
//     }

//     public void setDeliverOption(DeliverOption deliverOption) {
//         this.deliverOption = deliverOption;
//     }

//     private enum DeliverOption{
//         PickUp, Rider
//     }

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     @JsonBackReference
//     private MyUser myUser;

//     public MyUser getMyUser() {
//         return myUser;
//     }

//     public void setMyUser(MyUser myUser) {
//         this.myUser = myUser;
//     }

//     @ManyToOne
//     @JoinColumn(name = "address_id")
//     private Address address;

//     public Address getAddress() {
//         return address;
//     }

//     public void setAddress(Address address) {
//         this.address = address;
//     }

//     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//     private List<Item> items;
//     public List<Item> getItems() {
//         return items;
//     }

//     public void setItems(List<Item> items) {
//         this.items = items;
//     }
// }

