package com.xupt.admin.controller;

import com.xupt.admin.service.OrderService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.order.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @ResponseBody
    @GetMapping("/page")
    public ResponseEntity listContentPage(@RequestParam(value = "start",defaultValue = "0") Integer start,
                                          @RequestParam(value = "length",defaultValue = "10") Integer length,
                                          @RequestParam(value = "draw",defaultValue = "1") Integer draw,
                                          OrderForm orderForm) {
        ResultMap resultMap = orderService.listOrders(start, length, draw,orderForm);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
