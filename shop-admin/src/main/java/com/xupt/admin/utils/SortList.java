package com.xupt.admin.utils;

import com.xupt.domain.content.ContentCategory;
import com.xupt.domain.item.ItemCategory;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/2
 */
public class SortList {

    public static void  sort(List<ContentCategory> sources,List<ContentCategory> target,Long parentId) {
        for (ContentCategory contentCategory : sources) {
            if(contentCategory.getParentId().equals(parentId)){
                target.add(contentCategory);

                // 判断有没有子节点，如果有，则继续追加
                if (contentCategory.getIsParent()) {
                    for (ContentCategory source : sources) {
                        if (source.getParentId().equals(contentCategory.getId())) {
                            sort(sources, target, contentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void  sortItem(List<ItemCategory> sources, List<ItemCategory> target, Long parentId) {
        for (ItemCategory item : sources) {
            if(item.getParentId().equals(parentId)){
                target.add(item);
                // 判断有没有子节点，如果有，则继续追加
                if (item.getIsParent()) {
                    for (ItemCategory source : sources) {
                        if (source.getParentId().equals(item.getId())) {
                            sortItem(sources, target, item.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
