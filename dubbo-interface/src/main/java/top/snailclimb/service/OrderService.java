package top.snailclimb.service;

import top.snailclimb.vo.OrderVO;

public interface OrderService {

    public  String orderQuery(String id);

    /**
     * 查询订单信息
     *
     * @param orderId 订单ID
     * @return
     */
    OrderVO queryOrder(Long orderId);
}
