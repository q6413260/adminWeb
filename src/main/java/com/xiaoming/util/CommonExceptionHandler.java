package com.xiaoming.util;

import com.xiaoming.util.exception.UnLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaoming on 19/11/2016.
 */
@Controller
public class CommonExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object o, Exception e) {
        ModelAndView view = new ModelAndView();
        if(e instanceof UnLoginException){

            return view;
        }
        view = new ModelAndView("/page_500.jsp");
        return view;
    }
}
