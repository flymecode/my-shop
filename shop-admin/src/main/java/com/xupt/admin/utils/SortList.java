package com.xupt.admin.utils;

import com.xupt.domain.ContentCategory;

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
}
