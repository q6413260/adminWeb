package com.xiaoming.controller;

import com.xiaoming.dto.ApiAttributeDTO;
import com.xiaoming.dto.ApiDTO;
import com.xiaoming.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<ApiDTO> list = null;
        ApiDTO apiDTO = new ApiDTO();
        apiDTO.setServiceName(serviceName);
        apiDTO.setInterfaceName(interfaceName);
        apiDTO.setMethodName(methodName);
        list = apiService.queryApi(apiDTO);
        return list;
    }

    @RequestMapping(value = "/attribute/query", method = RequestMethod.GET)
    public @ResponseBody List<ApiAttributeDTO> queryApiAttribute(@RequestParam("apiId") Long apiId){
        List<ApiAttributeDTO> list = apiService.queryAttrByApiId(apiId);
        return list;
    }

    @RequestMapping(value = "/attribute/add", method = RequestMethod.POST)
    public @ResponseBody Map addAttribute(@RequestBody ApiAttributeDTO apiAttributeDTO){
        Map map = new HashMap();
        apiService.addAttribute(apiAttributeDTO);
        map.put("resultCode", "00");
        return map;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Map createApi(@RequestBody ApiDTO apiDTO){
        Map map = new HashMap();
        try {
            validateApiReq(apiDTO);
        } catch (IllegalArgumentException e) {
            map.put("resultCode", "01");
            map.put("resultMsg", e.getMessage());
            return map;
        }
        apiService.insert(apiDTO);
        map.put("resultCode", "00");
        return map;
    }

    private void validateApiReq(ApiDTO apiDTO) {

    }
}
