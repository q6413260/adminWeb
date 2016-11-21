package com.xiaoming.controller;

import com.xiaoming.dto.ApiDTO;
import com.xiaoming.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoming on 21/11/2016.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public @ResponseBody List<ApiDTO> queryApis(@RequestParam(value = "serviceName", required = false) String serviceName,
                                                @RequestParam(value = "interfaceName", required = false) String interfaceName,
                                                @RequestParam(value = "methodName", required = false) String methodName) {
        List<ApiDTO> list = new ArrayList<ApiDTO>();
        return list;
    }
}
