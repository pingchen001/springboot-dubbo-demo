package top.snailclimb.dubboconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.snailclimb.service.OrderService;

@RestController
public class HelloController {
    @Reference
    private OrderService orderService;

    @RequestMapping("/hello")
    public String hello() {
        String hello = orderService.orderQuery("world");
        System.out.println("result:" + hello);
        return hello;
    }
}
