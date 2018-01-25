package com.demo.controller.other;

import com.demo.model.other.TimeLine;
import com.demo.model.sys.User;
import com.demo.service.other.TimeLineService;
import com.demo.utils.common.PageEntity;
import com.demo.utils.common.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author chenzhi
 * @date 2018/1/24 0024
 * @description
 */
@RestController
@RequestMapping("/other")
public class TimeLineController {

    @Autowired
    private TimeLineService timeLineService;

    @RequestMapping("/toTimeLine")
    public ModelAndView toTimeLine() {
        ModelAndView modelAndView = new ModelAndView("other/timeLine");
        List<TimeLine> timeLineList = timeLineService.selectList();
        modelAndView.addObject("timeLineList",timeLineList);
        return modelAndView;
    }

    @RequestMapping("/toTimeLineList")
    public ModelAndView toTimeLineList(){
        ModelAndView modelAndView = new ModelAndView("other/timeLineList");
        return modelAndView;
    }

    @RequestMapping("/getPageTimeLineList")
    public PageEntity getPageTimeLineList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit",defaultValue = "10") Integer limit, TimeLine timeLine){
        PageEntity pageEntity = new PageEntity();
        try {
            PageInfo pageInfo = timeLineService.findPage(page,limit,timeLine);
            pageEntity.setCount(pageInfo.getTotal());
            pageEntity.setData(pageInfo.getList());
        }catch (Exception e){
            e.printStackTrace();
            pageEntity.setCode(PageEntity.CODE_ERROR);
            pageEntity.setMsg("获取时间线列表出错！");
        }
        return pageEntity;
    }

    @RequestMapping("/toTimeLineInfo")
    public ModelAndView toTimeLineInfo(@RequestParam(value = "id",required = false) String id) {
        ModelAndView model = new ModelAndView("other/timeLineInfo");
        TimeLine timeLine = new TimeLine();
        if(StringUtils.isNotEmpty(id)){
            timeLine = timeLineService.getById(id);
        }
        model.addObject("timeLine",timeLine);
        return model;
    }

    @RequestMapping(value = "/timeLineSave")
    public Result timeLineSave(TimeLine timeLine){
        Result result = new Result();
        try {
            if(StringUtils.isNotEmpty(timeLine.getId())){
                timeLineService.update(timeLine);
            }else{
                timeLineService.insert(timeLine);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("保存出错！");
        }
        return result;
    }

    @RequestMapping(value = "/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            timeLineService.delete(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("删除出错！");
            return result;
        }
    }

    @RequestMapping(value = "/batchDel")
    public Result batchDel(@RequestParam(value = "ids[]")String[] ids){
        Result result = new Result();
        try{
            timeLineService.batchDel(ids);
            return result;
        }catch (Exception e){
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("批量删除出错！");
            e.printStackTrace();
            return result;
        }
    }
}
