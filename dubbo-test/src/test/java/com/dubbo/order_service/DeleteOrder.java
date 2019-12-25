package com.dubbo.order_service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.util.ReadExcel;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeTest;
import top.snailclimb.service.OrderService;

import java.util.HashMap;

@RunWith(DataProviderRunner.class)
public class DeleteOrder extends UpdateOrder {

    @Reference
    private OrderService orderService;

    //从excel读取数据
    @BeforeTest
    public Object[][] data() {
        ReadExcel testcase = new ReadExcel();
        return testcase.testData("TestSenarioData");
    }

    @Test
    @UseDataProvider("data")
    public void testCase(HashMap<String, String> data) {
        String id = data.get("id");
        String hello = orderService.orderQuery(id);
        System.out.println("result:" + orderService.orderQuery("1"));
        Assert.assertEquals(hello,"Hello 1");

    }

}
