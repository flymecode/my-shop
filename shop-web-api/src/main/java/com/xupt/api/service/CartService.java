package com.xupt.api.service;

import com.xupt.domain.buyer.BuyerCart;
import com.xupt.domain.item.Item;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
public interface CartService {

    void insertBuyerCartToRedis(BuyerCart buyerCart, String username);

    BuyerCart selectBuyerCartFromRedis(String username);

    Item selectItemById(Long id);

    BuyerCart selectBuyerCartFromRedisByItemIds(List<String> ids, String username);
}
