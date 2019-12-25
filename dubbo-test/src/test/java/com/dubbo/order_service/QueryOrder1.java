package com.dubbo.order_service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.Assert;
import org.testng.annotations.Test;
import top.snailclimb.service.OrderService;
import top.snailclimb.vo.OrderVO;

@SpringBootApplication
@EnableDubboConfiguration
public class QueryOrder1 extends UpdateOrder {

    @Reference
    private OrderService orderService;

    @Test(description = "正常场景：传入正确的orderId，调用接口，返回OrderInfo，检查返回字段")
    public void queryOrderByCorrectId(){

        OrderVO result = orderService.queryOrder(1l);
        Assert.assertEquals(result.getCustomerAddress().toString(),"武汉市");
        Assert.assertEquals(result.getCustomerName().toString(),"张三");
        Assert.assertEquals(result.getOrderId().toString(),"1");

    }

    @Test(description = "异常场景：传入非法的orderId，调用接口，返回errorCode")
    public void queryOrderByValidId(){
        OrderVO result = orderService.queryOrder(-1l);
        System.out.println("Response result is:" + result.getCustomerAddress().toString());
        Assert.assertNull(result.getCustomerAddress());

    }

}
