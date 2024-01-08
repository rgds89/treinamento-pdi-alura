package br.com.store.wholesale.ecommerce.kafka.producer.contoller;

import br.com.store.wholesale.ecommerce.kafka.producer.service.NewOrderService;
import br.com.store.wholesale.ecommerce.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/wholesale/ecommerce")
@RequiredArgsConstructor
public class OrderController {
    private final NewOrderService newOrderService;

    @RequestMapping("/newOrder")
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void newOrder(@RequestBody Order order) {
        newOrderService.newOrder(order);
    }
}
