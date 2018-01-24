package com.demo.controller.other;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenzhi
 * @date 2018/1/24 0024
 * @description
 */
@RestController
@RequestMapping("/other")
public class TimeLineController {

    @RequestMapping("/toTimeLine")
    public ModelAndView toTimeLine() {
        ModelAndView modelAndView = new ModelAndView("other/timeLine");
        return modelAndView;
    }
}
