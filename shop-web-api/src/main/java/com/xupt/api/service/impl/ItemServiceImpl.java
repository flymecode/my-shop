package com.xupt.api.service.impl;

import com.xupt.api.mapper.ItemCategoryMapper;
import com.xupt.api.mapper.ItemMapper;
import com.xupt.api.service.ItemService;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.ContentVO;
import com.xupt.domain.item.Item;
import com.xupt.domain.item.ItemCategory;
import com.xupt.domain.item.ItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/12
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;
    @Override
    @Transactional
    public ResultMap findItems(Long cid) {
        ResultMap resultMap = new ResultMap();
        ItemCategory itemCategory = itemCategoryMapper.search(cid);
        List<Item> all = itemMapper.search(cid);
        List<ItemVO> itemVOS = new ArrayList<>();
        for (Item item : all) {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(item,itemVO);
            itemVOS.add(itemVO);
        }
        ContentVO contentVO = new ContentVO();
        contentVO.setData(itemVOS);
        contentVO.setName(itemCategory.getName());
        List<ContentVO> contentVOS = new ArrayList<>();
        contentVOS.add(contentVO);
        return resultMap.success().payload(contentVOS);
    }

}
