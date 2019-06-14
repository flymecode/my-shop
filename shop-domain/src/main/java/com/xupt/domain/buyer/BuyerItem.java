package com.xupt.domain.buyer;

import com.xupt.domain.item.Item;
import lombok.Data;

import java.io.Serializable;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Data
public class BuyerItem implements Serializable {

    // 商品
    private Item item;

    // 是否有货
    private Boolean isHave = true;

    // 购买数量
    private Integer amount = 1;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) //比较地址
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuyerItem other = (BuyerItem) obj;
        if (item == null) {
            if (other.item != null)
            return false;
        } else if (!item.getId().equals(other.item.getId()))
            return false;
        return true;
    }
}
