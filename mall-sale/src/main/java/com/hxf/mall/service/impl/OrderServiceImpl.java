package com.hxf.mall.service.impl;

import com.hxf.mall.bean.T_MALL_ADDRESS;
import com.hxf.mall.bean.T_MALL_ORDER;
import com.hxf.mall.bean.T_MALL_ORDER_INFO;
import com.hxf.mall.exception.OverSaleException;
import com.hxf.mall.mapper.CartMapper;
import com.hxf.mall.mapper.OrderMapper;
import com.hxf.mall.model.OBJECT_T_MALL_FLOW;
import com.hxf.mall.model.OBJECT_T_MALL_ORDER;
import com.hxf.mall.service.OrderService;
import com.hxf.mall.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS addr) {
        List<Integer> list_id = new ArrayList<Integer>();//用来存储要删除的购物车id
        //保存order订单对象
        order.setDzh_id(addr.getId());
        order.setDzh_mch(addr.getYh_dz());
        order.setShhr(addr.getShjr());
        orderMapper.insert_order(order);
        //保存flow送货清单
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
        for(OBJECT_T_MALL_FLOW flow : list_flow){
            flow.setDd_id(order.getId());
            flow.setMdd(order.getDzh_mch());
            orderMapper.insert_flow(flow);
            //保存info订单信息
            list_info = flow.getList_info();
            Map<Object, Object> map_info = new HashMap<Object, Object>();
            map_info.put("list_info", list_info);
            map_info.put("dd_id", order.getId());
            map_info.put("flow_id", flow.getId());
            orderMapper.insert_infos(map_info);
            for(T_MALL_ORDER_INFO info : list_info){
                list_id.add(info.getGwch_id());
            }
        }
        //删除购物车信息
        //除了根据info订单里的购物车id来删除之外，还有另一种方式：就是直接删除该userID对应的购物车中shfxz为0的购物车，
        // 因为提交订单时，是根据用户勾选的东西来提交的。
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("list_id", list_id);
        cartMapper.delete_carts(map);
    }

    @Override
    public T_MALL_ORDER select_order(Integer id) {
        return orderMapper.select_order(id);
    }

    @Override
    public void pay(OBJECT_T_MALL_ORDER order) throws OverSaleException {
        //修改订单状态 ---已支付
        order.setJdh(2);
        orderMapper.update_order(order);
        //修改物流信息
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        for(OBJECT_T_MALL_FLOW flow : list_flow){
            // 修改物流的业务
            flow.setPsmsh("商品正在出库");
            flow.setPsshj(MyDateUtil.getMyDate(1));//当前时间一天后开始配送
            flow.setYwy("小红雨");
            flow.setLxfsh("1324824");
            orderMapper.update_flow(flow);

            List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
            //修改sku信息，销量和库存等
            for(T_MALL_ORDER_INFO info : list_info){
                //查询库存（加锁）
                //并发量大的时候，加锁会影响效率，这里加个阀门，到kc到达警戒线时才去加锁
                long kc = 0;
                int count = orderMapper.select_count_kc(info.getSku_id());//查询该sku库存是否大于10
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("count", count);
                map.put("sku_id", info.getSku_id());
                kc = orderMapper.select_kc(map);
                if (kc >= info.getSku_shl()) {
                    orderMapper.update_kc(info);
                } else {
                    throw new OverSaleException("库存不足");
                }
            }
        }

        //修改订单状态 ---出库
        order.setYjsdshj(MyDateUtil.getMyDate(3));//当前时间三天后预计送达
        orderMapper.update_order(order);
    }
}
