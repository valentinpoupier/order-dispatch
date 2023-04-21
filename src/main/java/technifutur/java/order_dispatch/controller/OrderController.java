package technifutur.java.order_dispatch.controller;


import org.apache.catalina.servlets.DefaultServlet.SortManager.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technifutur.java.order_dispatch.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
private OrderService orderService;
public OrderController(OrderService orderService){
    this.orderService = orderService;
}

@DeleteMapping("/delete/{id:[0-9]+}")
public void deleteOrder(@RequestBody @PathVariable long id){
    this.orderService.delete(id);
}
}
