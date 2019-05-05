package com.hxf.mall.controller;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_ORDER_INFO;
import com.hxf.mall.bean.T_MALL_SHOPPINGCAR;
import com.hxf.mall.bean.T_MALL_USER_ACCOUNT;
import com.hxf.mall.exception.OverSaleException;
import com.hxf.mall.model.OBJECT_T_MALL_FLOW;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;
import com.hxf.mall.service.CartService;
import com.hxf.mall.service.OrderService;
import com.hxf.mall.service.UserService;
import com.hxf.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@SessionAttributes(value = {"order"})
@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping("order-check")
    public Result orderPrepare(HttpSession session, Map map){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        if(user == null){
            return Result.fail("服务出错");
        }
        //session获取购物车列表
        list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        /* 一个订单对应多个物流flow
         * 一个库存地址对应一个物流flow
         * 一个选中的购物车对应一个订单信息order_info
         * 一个物流对应多个订单信息
         */
        OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
        //订单进度 1：已提交 2：已支付 3:运输中 4：已签收 5:完成
        order.setJdh(1);
        order.setYh_id(user.getId());
        order.setZje(getMoney(list_cart));
        List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
        //获取选中商品的去重后的库存地址
        Set<String> set_kcdz = new HashSet<>();
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            if(cart.getShfxz().equals("1")){
                set_kcdz.add(cart.getKcdz());
            }
        }
        //根据库存地址生成送货清单-->flow
        for(String kcdz : set_kcdz){
            OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
            flow.setMqdd(kcdz);
            flow.setPsfsh("小红快递");
            flow.setYh_id(user.getId());
            List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
            //根据购物车生成订单信息
            for(T_MALL_SHOPPINGCAR cart : list_cart){
                if(cart.getShfxz().equals("1") && cart.getKcdz().equals(kcdz)){
                    T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
                    info.setGwch_id(cart.getId());
                    info.setShp_tp(cart.getShp_tp());
                    info.setSku_id(cart.getSku_id());
                    info.setSku_jg(cart.getSku_jg());
                    info.setSku_kcdz(kcdz);
                    info.setSku_mch(cart.getSku_mch());
                    info.setSku_shl(cart.getTjshl());
                    list_info.add(info);
                }
            }
            flow.setList_info(list_info);
            list_flow.add(flow);
        }
        order.setList_flow(list_flow);
        map.put("order", order);//与sessionAttributes配合，将订单信息放入session中，实现跨请求访问

        List<T_MALL_ADDRESS> list_address = new ArrayList<>();
        try {
            list_address = userService.get_address_list(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务出错");
        }
        map.put("list_address", list_address);
        return Result.success(map);
    }

    private BigDecimal getMoney(List<T_MALL_SHOPPINGCAR> list_cart) {
        BigDecimal sum = new BigDecimal("0");
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            if(cart.getShfxz().equals("1")) {//选中的才统计金额
                sum = sum.add(new BigDecimal(cart.getHj() + ""));//添加前要先转换
            }
        }
        return sum;
    }

    @PostMapping("order")
    public Result saveOrder(@RequestBody T_MALL_ADDRESS address, @ModelAttribute("order") OBJECT_T_MALL_ORDER order, HttpSession session){
        try {
            //保存order订单对象
            orderService.save_order(order, address);
            //同步session
            T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
            session.setAttribute("list_cart_session", cartService.get_cart_list_by_user(user));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("fail");
        }
        return Result.success(order.getId());//将订单id传回给前端
    }

    @GetMapping("order/{id}")
    public Result getOrder(@PathVariable Integer id){
        return Result.success(orderService.select_order(id));
    }
}
