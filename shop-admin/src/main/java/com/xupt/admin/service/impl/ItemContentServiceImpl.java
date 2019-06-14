package com.xupt.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.admin.mapper.ItemContentMapper;
import com.xupt.admin.service.ItemContentService;
import com.xupt.common.constant.Consts;
import com.xupt.common.dto.ResultMap;
import com.xupt.domain.item.Item;
import com.xupt.domain.item.ItemForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author maxu
 * @date 2019/6/11
 */
@Service
public class ItemContentServiceImpl implements ItemContentService {

    @Autowired
    private ItemContentMapper itemContentMapper;
    @Override
    public ResultMap saveContent(ItemForm content) {
        ResultMap resultMap = new ResultMap();
        Item item = new Item();
        BeanUtils.copyProperties(content, item);
        Date date = new Date();
        item.setStatus(Consts.ITEM_NORMAL);
        item.setCreated(date);
        item.setUpdated(date);
        itemContentMapper.saveContent(item);
        return resultMap;
    }

    @Override
    public ResultMap listContents(Integer start, Integer length, Integer draw) {
        ResultMap result = new ResultMap();
        PageHelper.offsetPage(start, length);
        List<Item> contents = itemContentMapper.searchContents();
        PageInfo<Item> pages = PageInfo.of(contents);
        result.payload(pages.getList());
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotal());
        result.put("recordsFiltered",pages.getTotal());
        result.put("error","");
        return result;
    }
}
