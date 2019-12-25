package top.snailclimb.dubboconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.snailclimb.service.OrderService;
import top.snailclimb.vo.OrderVO;

/**
 * OrderController
 *
 * @date 2019/12/15 8:31 下午
 */
@RestController()
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("{orderId}")
    public OrderVO queryOrder(@PathVariable(name = "orderId") Long orderId) {
        return orderService.queryOrder(orderId);
    }
}
