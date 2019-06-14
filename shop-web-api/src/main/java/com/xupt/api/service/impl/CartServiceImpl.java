package com.xupt.api.service.impl;

import com.xupt.api.mapper.ItemMapper;
import com.xupt.api.service.CartService;
import com.xupt.domain.buyer.BuyerCart;
import com.xupt.domain.buyer.BuyerItem;
import com.xupt.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private HashOperations hashOperations;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void insertBuyerCartToRedis(BuyerCart buyerCart, String username) {
        List<BuyerItem> items = buyerCart.getItems();
        if (items.size() > 0) {
            //redis中保存的是item_id 为key , amount 为value的Map集合
            Map<String, String> hash = new HashMap<String, String>();
            for (BuyerItem item : items) {
                //判断是否有同款
                if (hashOperations.hasKey("buyerCart:" + username, String.valueOf(item.getItem().getId()))) {
                    hashOperations.increment("buyerCart:" + username, String.valueOf(item.getItem().getId()), item.getAmount());
                } else {
                    hash.put(String.valueOf(item.getItem().getId()), String.valueOf(item.getAmount()));
                }
            }
            if (hash.size() > 0) {
                hashOperations.putAll("buyerCart:" + username, hash);
            }
        }
    }

    @Override
    public BuyerCart selectBuyerCartFromRedis(String username) {
        BuyerCart buyerCart = new BuyerCart();
        //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
        Map<String, String> hgetAll = hashOperations.entries("buyerCart:" + username);
        Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            //entry.getKey(): skuId
            Item item = new Item();
            item.setId(Long.parseLong(entry.getKey()));
            BuyerItem buyerItem = new BuyerItem();
            buyerItem.setItem(item);
            //entry.getValue(): amount
            buyerItem.setAmount(Integer.parseInt(entry.getValue()));
            //添加到购物车中
            buyerCart.addItem(buyerItem);
        }
        return buyerCart;
    }

    @Override
    public Item selectItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public BuyerCart selectBuyerCartFromRedisByItemIds(List<String> ids, String username) {
        // 新建一个购物车，保存 ids 中的购物项
        BuyerCart buyerCart = new BuyerCart();
        //获取所有商品, redis中保存的是item_Id 为key , amount 为value的Map集合
        Map<String, String> hgetAll = hashOperations.entries("buyerCart:" + username);
        if (null != hgetAll && hgetAll.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                for (String id : ids) {
                    if (id.equals(entry.getKey())) {
                        //entry.getKey(): Id
                        Item item = new Item();
                        item.setId(Long.parseLong(entry.getKey()));
                        BuyerItem buyerItem = new BuyerItem();
                        buyerItem.setItem(item);
                        //entry.getValue(): amount
                        buyerItem.setAmount(Integer.parseInt(entry.getValue()));
                        //添加到购物车中
                        buyerCart.addItem(buyerItem);
                    }
                }
            }
        }
        return buyerCart;
    }
}
