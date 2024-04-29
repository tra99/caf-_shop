// package shop.coffee.backend.entity;

// import jakarta.persistence.*;



// @Entity
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     private Long qty;

//     @Enumerated(EnumType.STRING)
//     private DeliveryOption deliveryOption;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private MyUser user;

//     // @ManyToOne
//     // @JoinColumn(name = "address_id")
//     // private Address address;

//     // public Address getAddress() {
//     //     return address;
//     // }

//     // public void setAddress(Address address) {
//     //     this.address = address;
//     // }

//     // @ManyToOne
//     // @JoinColumn(name = "item_id")
//     // private Item item;

//     // public Item getItem() {
//     //     return item;
//     // }

//     // public void setItem(Item item) {
//     //     this.item = item;
//     // }


//     public Long getQty() {
//         return qty;
//     }

//     public void setQty(Long qty) {
//         this.qty = qty;
//     }

//     public DeliveryOption getDeliveryOption() {
//         return deliveryOption;
//     }

//     public void setDeliveryOption(DeliveryOption deliveryOption) {
//         this.deliveryOption = deliveryOption;
//     }

//     public MyUser getUser() {
//         return user;
//     }

//     public void setUser(MyUser user) {
//         this.user = user;
//     }

//     public enum DeliveryOption {
//         PickUp, Rider
//     }
// }