package technifutur.java.order_dispatch.controller;


import jakarta.ws.rs.PATCH;
import org.springframework.web.bind.annotation.*;
import technifutur.java.order_dispatch.model.dto.OrderDTO;
import technifutur.java.order_dispatch.model.form.OrderForm;
import technifutur.java.order_dispatch.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id:[0-9]+}")
    public OrderDTO getOne(@PathVariable long id) {

        return orderService.getOne(id);

    }

    @GetMapping("/all")
    public List<OrderDTO> getAll() {

        return orderService.getAll();

    }

    @PatchMapping("/update/{id:[0-9]+}")
    public void

    @DeleteMapping("/delete/{id:[0-9]+}")
    public void deleteOrder(@RequestBody @PathVariable long id) {
        this.orderService.delete(id);
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderForm orderForm) {
        this.orderService.create(orderForm);
    }
}
