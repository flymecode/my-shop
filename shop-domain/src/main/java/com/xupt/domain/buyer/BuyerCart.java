package com.xupt.domain.buyer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Data
public class BuyerCart implements Serializable {
    private List<BuyerItem> items = new ArrayList<>();

    public void addItem(BuyerItem item) {
        if (items.contains(item)) {
            for (BuyerItem buyerItem : items) {
                if (buyerItem.equals(item)) {
                    buyerItem.setAmount(item.getAmount() + buyerItem.getAmount());
                }
            }
        } else {
            items.add(item);
        }
    }

    //商品数量
    @JsonIgnore
    public Integer getProductAmount() {
        Integer result = 0;
        //计算
        for (BuyerItem buyerItem : items) {
            result += buyerItem.getAmount();
        }
        return result;
    }

    @JsonIgnore
    public Double getProductPrice() {
        Double result = 0d;
        // 计算
        for (BuyerItem buyerItem : items) {
            result += buyerItem.getAmount() * buyerItem.getItem().getPrice();
        }
        return result;
    }

    @JsonIgnore
    public Double getFee() {
        Double result = 0d;
        // 计算
        if (getProductPrice() < 79) {
            result = 5d;
        }
        return result;
    }

    @JsonIgnore
    public Double getTotalPrice(){
        return getProductPrice() + getFee();
    }
}
