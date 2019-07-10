package com.shopping.shoppingMall.controller.pc;

import com.shopping.shoppingMall.common.entity.PageVO;
import com.shopping.shoppingMall.common.entity.ResponseVO;
import com.shopping.shoppingMall.service.ClassificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/class/")
public class ClassificationController {

    @Autowired
    ClassificationService classificationService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO userList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        try {
            PageVO vo = classificationService.findListHasAccountPage(0, pageVO);
            responseVO = responseVO.ok(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseVO;
    }
}
