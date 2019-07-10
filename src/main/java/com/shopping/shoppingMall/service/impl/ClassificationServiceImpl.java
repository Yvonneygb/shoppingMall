package com.shopping.shoppingMall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.dao.ClassificationVOMapper;
import com.shopping.shoppingMall.entity.ClassificationVO;
import com.shopping.shoppingMall.service.ClassificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Resource
    ClassificationVOMapper classificationVOMapper;

    @Override
    public PageVO findListHasAccountPage(int flag, PageVO pageVO) {
        PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        List<ClassificationVO> list = classificationVOMapper.findListHasAccount(flag);
        PageInfo<ClassificationVO> pageInfo = new PageInfo<>( list);
        PageHelper.clearPage();
        PageVO vo = new PageVO();
        vo.setPageSize(pageVO.getPageSize());
        vo.setPageNum(pageVO.getPageNum());
        vo.setRows(pageInfo.getList());
        vo.setTotal(pageInfo.getTotal());
        return vo;
    }
}
