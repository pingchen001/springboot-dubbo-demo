package top.snailclimb.service.impl;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.snailclimb.service.OrderService;
import top.snailclimb.vo.OrderVO;

@Component
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderVO queryOrder(Long orderId) {
        Assert.notNull(orderId, "orderId can not be null");
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setOrderSn("201912150001");
        orderVO.setAmount(1000L);
        orderVO.setCustomerName("张三");
        orderVO.setCustomerPhone("135456789xx");
        orderVO.setCustomerAddress("武汉市");
        return orderVO;
    }

    @Override
    public String orderQuery(String id) {
        return "OrderInfo" + id;
    }

}
