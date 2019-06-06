package com.wyu.infolib.controller.platform;

import com.wyu.infolib.common.entity.PageVO;
import com.wyu.infolib.common.entity.ResponseVO;
import com.wyu.infolib.entity.Dict;
import com.wyu.infolib.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 字典类控制
 * @Author RLinux
 * @Email RLinux_zwh@163.com
 * @Since 2019/4/3 10:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseVO dictList(PageVO pageVO){
        ResponseVO responseVO = new ResponseVO();
        List<Dict> list = null;
        try {
            list = dictService.dictList(pageVO);
            responseVO.setData(list);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEmsg("新增失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseVO addDict(Dict dict){
        ResponseVO responseVO = new ResponseVO();
        try {
            dictService.save(dict);
            responseVO.setEmsg("新增成功");
        } catch (Exception e) {
            responseVO.setEmsg("新增失败");
            responseVO.setEcode(1);
        }
        return responseVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseVO updateDict(Dict dict){
        ResponseVO responseVO = new ResponseVO();
        try {
            dictService.updateDict(dict);
            responseVO.setEmsg("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("更新失败");
        }
        return responseVO;
    }

    @PostMapping("/del")
    @ResponseBody
    public ResponseVO deleteDict(String ids){
        ResponseVO responseVO = new ResponseVO();
        try {
            dictService.deleteDict(ids);
            responseVO.setEmsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            responseVO.setEcode(1);
            responseVO.setEmsg("删除失败");
        }
        return responseVO;
    }
}
