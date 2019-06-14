package com.xupt.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xupt.api.constant.Constants;
import com.xupt.api.service.CartService;
import com.xupt.api.service.OrderService;
import com.xupt.common.dto.ResultMap;
import com.xupt.common.untils.MapperUtils;
import com.xupt.domain.buyer.BuyerCart;
import com.xupt.domain.buyer.BuyerItem;
import com.xupt.domain.item.Item;
import com.xupt.domain.order.Order;
import com.xupt.domain.user.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author maxu
 * @date 2019/6/12
 */
@RestController
@RequestMapping(Constants.VERSION +"/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ApiOperation("添加购物车")
    public ResponseEntity buyerCart(@RequestParam("id") Long itemId,
                                    @RequestParam("count") Integer amount,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        log.info("---------------------》》进入添加购物车");
        ResultMap resultMap = new ResultMap();
        //将对象转换成json字符串/json字符串转成对象
        BuyerCart buyerCart = null;
        //1,获取Cookie中的购物车
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (Constants.BUYER_CART.equals(cookie.getName())) {
                    //购物车 对象 与json字符串互转
                    buyerCart = MapperUtils.json2pojo(URLDecoder.decode(cookie.getValue(),"utf-8"), BuyerCart.class);
                    break;
                }
            }
        }
        //2,Cookie中没有购物车, 创建购物车对象
        if (null == buyerCart) {
            buyerCart = new BuyerCart();
        }
        //3, 将当前款商品追加到购物车
        if (null != itemId && null != amount) {
            Item item = new Item();
            item.setId(itemId);
            BuyerItem buyerItem = new BuyerItem();
            buyerItem.setItem(item);
            //设置数量
            buyerItem.setAmount(amount);
            //添加购物项到购物车
            buyerCart.addItem(buyerItem);
        }
        List<BuyerItem> items = buyerCart.getItems();
        Collections.sort(items, new Comparator<BuyerItem>() {
            @Override
            public int compare(BuyerItem o1, BuyerItem o2) {
                return -1;
            }
        });

        //前三点 登录和非登录做的是一样的操作, 在第四点需要判断,判断用户是否登陆
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(Constants.USER_SESSION);
        if (null != user) {
            //登录了
            //4, 将购物车追加到Redis中
            cartService.insertBuyerCartToRedis(buyerCart, user.getUsername());
            //5, 清空Cookie 设置存活时间为0, 立马销毁
            Cookie cookie = new Cookie(Constants.BUYER_CART, null);
            cookie.setPath("/");
            cookie.setMaxAge(-0);
            response.addCookie(cookie);
        } else {
            //未登录
            //4, 保存购物车到Cookie中
            //将对象转换成json格式
            Writer w = new StringWriter();
            MapperUtils.getInstance().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValue(w, buyerCart);
            String encode = URLEncoder.encode(w.toString(), "utf-8");
            Cookie cookie = new Cookie(Constants.BUYER_CART, encode);
            //设置path是可以共享cookie
            cookie.setPath("/");
            //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
            cookie.setMaxAge(24 * 60 * 60);
            //5,Cookie写会浏览器
            response.addCookie(cookie);
        }
        ResultMap success = resultMap.success();
        success.message("添加成功");
        return  ResponseEntity.status(success.getCode()).body(success);
    }


    @GetMapping
    @ApiOperation("去展示购物车的内容")
    public ResponseEntity toCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将对象转换成json字符串/json字符串转成对象
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        BuyerCart buyerCart = null;
        //1,获取Cookie中的购物车
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // 获取cookie中的购物车
                if (Constants.BUYER_CART.equals(cookie.getName())) {
                    //购物车 对象 与json字符串互转
                    buyerCart = om.readValue(URLDecoder.decode(cookie.getValue(),"utf-8"), BuyerCart.class);
                    break;
                }
            }
        }
        //判断是否登录
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(Constants.USER_SESSION);
        if (null != user) {
            //登录了
            //2, 购物车 有东西, 则将购物车的东西保存到Redis中
            if (null != buyerCart) {
                cartService.insertBuyerCartToRedis(buyerCart, user.getUsername());
                //清空Cookie 设置存活时间为0, 立马销毁
                Cookie cookie = new Cookie(Constants.BUYER_CART, null);
                cookie.setPath("/");
                cookie.setMaxAge(-0);
                response.addCookie(cookie);
            }
            //3, 取出Redis中的购物车
            buyerCart = cartService.selectBuyerCartFromRedis(user.getUsername());
        }
        //4, 没有 则创建购物车
        if (null == buyerCart) {
            buyerCart = new BuyerCart();
        }
        //5, 将购物车装满, 前面只是将itemId装进购物车, 这里还需要查出item详情
        List<BuyerItem> items = buyerCart.getItems();
        if (items.size() > 0) {
            //只有购物车中有购物项, 才可以将item相关信息加入到购物项中
            for (BuyerItem buyerItem : items) {
                buyerItem.setItem(cartService.selectItemById(buyerItem.getItem().getId()));
            }
        }
        //5,上面已经将购物车装满了, 这里直接回显页面
        //跳转购物页面
        ResultMap resultMap = new ResultMap().success().payload(buyerCart);
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
    //去结算
    @ApiOperation("下单")
    @PostMapping("/trueBuy")
    public ResponseEntity trueBuy(@RequestBody List<String> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1, 购物车必须有商品,
        //取出用户名  再取出购物车
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(Constants.USER_SESSION);
        //取出所有购物车
        BuyerCart buyerCart = cartService.selectBuyerCartFromRedisByItemIds(ids, user.getUsername());
        List<BuyerItem> items = buyerCart.getItems();
        ResultMap resultMap = new ResultMap();
        if (items.size() > 0) {
            //购物车中有商品
            //判断所勾选的商品是否都有货, 如果有一件无货, 那么就刷新页面.
            Boolean flag = true;
            //2, 购物车中商品必须有库存 且购买大于库存数量时视为无货. 提示: 购物车原页面不动. 有货改为无货, 加红提醒.
            for (BuyerItem buyerItem : items) {
                //装满购物车的购物项, 当前购物项只有item_Id这一个东西, 我们还需要购物项的数量去判断是否有货
                buyerItem.setItem(cartService.selectItemById((buyerItem.getItem().getId())));
                //校验库存
                if (buyerItem.getAmount() > buyerItem.getItem().getNum()) {
                    //无货
                    buyerItem.setIsHave(false);
                    flag = false;
                }
                if (!flag) {
                    //无货, 原页面不动, 有货改成无货, 刷新页面.
                    resultMap.fail().message("商品暂时无货").payload(buyerCart);
                    // model.addAttribute("buyerCart", buyerCart);
                    // return "cart";
                    return ResponseEntity.status(resultMap.getCode()).body(resultMap);
                }
            }
        } else {
            //购物车没有商品
            //没有商品: 1>原购物车页面刷新(购物车页面提示没有商品)
            resultMap.fail().message("购物车没有商品");
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
            //return "redirect:/shopping/toCart";
        }
        //3, 成功下单，将订单信息发送给 RabbitMQ
        Date date = new Date();
        Order order = new Order();
        order.setStatus(1);
        // TODO 这里订单ID应该是 RedisHash ID
        order.setId(String.valueOf(UUID.randomUUID()).replaceAll("-",""));
        order.setMessageId(String.valueOf(UUID.randomUUID()).replaceAll("-", ""));
        order.setCreated(date);
        order.setUpdated(date);
        order.setUserId(user.getId());
        order.setPayment(buyerCart.getTotalPrice());
        orderService.createOrder(order);
        resultMap.success().message("下单成功，请支付");
        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
    }
}
