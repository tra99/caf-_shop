// package shop.coffee.backend.controller;

// import java.util.*;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import shop.coffee.backend.entity.Order;
// import shop.coffee.backend.service.OrderService;

// @RestController
// @RequestMapping("/api/order")
// public class OrderController {
//     private final OrderService orderService;

//     public OrderController(OrderService orderService){
//         this.orderService=orderService;
//     }

//     @GetMapping("/getAll")
//     public ResponseEntity<List<Order>> getAllOrders(){
//         List<Order> orders=orderService.getAllOrder();
//         return new ResponseEntity<>(orders,HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Order> getOrderById(@PathVariable long id){
//         Optional<Order> order=orderService.getOrderById(id);
//         return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @PostMapping("/add")
//     public ResponseEntity<Order> createOrder(@RequestBody Order order){
//         Order createOrder=orderService.createOrUpdateOrder(order);
//         return new ResponseEntity<>(createOrder, HttpStatus.OK);
//     }

//     @PutMapping("/update/{id}")
//     public ResponseEntity<Order> updateOrderById(@PathVariable long id, @RequestBody Order updatedOrder){
//         Optional<Order> existingOrderOptional=orderService.getOrderById(id);

//         if(existingOrderOptional.isPresent()){
//             Order existingOrder=existingOrderOptional.get();
//             existingOrder.setQty(updatedOrder.getQty());
//             existingOrder.setDeliverOption(updatedOrder.getDeliverOption());
//             existingOrder.setItem(updatedOrder.getItem());

//             Order savedOrder=orderService.createOrUpdateOrder(existingOrder);
//             return new ResponseEntity<>(savedOrder,HttpStatus.OK);
//         }else{
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping("/delete/{id}")
//     public ResponseEntity<String> deleteOrderById(@PathVariable long id){
//         Optional<Order> order=orderService.getOrderById(id);
    
//         if(order.isPresent()){
//             orderService.deleteOrderById(id);
//             String message= "Order with ID " + id + " is deleted"; // Added a space before "is deleted"
//             return new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
//         }
//         else{
//             String message="Order with ID " + id + " is not found";
//             return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
//         }
//     }
    
// }
