package com.hxf.mall.ctrl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxf.mall.service.AttrService;
import com.hxf.mall.to.OBJECT_T_MALL_ATTR;
import com.hxf.mall.to.ResponseData;
import com.hxf.mall.util.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("attr-list")
    public ResponseData getAttrList(Integer flbh2, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OBJECT_T_MALL_ATTR> list = attrService.get_attr_list(flbh2);
        PageInfo<OBJECT_T_MALL_ATTR> page = new PageInfo<OBJECT_T_MALL_ATTR>(list);
        return ResponseDataUtil.buildSuccess(page);
    }
}
