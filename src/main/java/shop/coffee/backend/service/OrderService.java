// package shop.coffee.backend.service;

// import java.util.*;
// import org.springframework.stereotype.Service;
// import shop.coffee.backend.entity.Order;
// import shop.coffee.backend.repository.OrderRepository;

// @Service
// public class OrderService {
//     private final OrderRepository orderRepository;

//     public OrderService(OrderRepository orderRepository){
//         this.orderRepository=orderRepository;
//     }

//     public List<Order> getAllOrder(){
//         return orderRepository.findAll();
//     }

//     public Optional<Order> getOrderById(long id){
//         return orderRepository.findById(id);
//     }

//     public Order createOrUpdateOrder(Order order){
//         return orderRepository.save(order);
//     }

//     public void deleteOrderById(long id){
//         orderRepository.deleteById(id);
//     }
// }
