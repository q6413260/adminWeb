package com.xiaoming.controller;

import com.google.common.base.Preconditions;
import com.xiaoming.dto.DeveloperApiDTO;
import com.xiaoming.dto.DeveloperDTO;
import com.xiaoming.dto.MenuDTO;
import com.xiaoming.service.DeveloperService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoming on 16/11/2016.
 */
@Controller
@RequestMapping("/developers")
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<DeveloperDTO> queryDevelopers(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "appKey", required = false) String appKey) {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setName(name);
        developerDTO.setAppKey(appKey);
        List<DeveloperDTO> list = developerService.find(developerDTO);
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map createNewDeveloper(@RequestBody DeveloperDTO developerDTO) {
        Map map = new HashMap();
        try {
            validateDeveloperReq(developerDTO);
        } catch (IllegalArgumentException e) {
            map.put("resultCode", "01");
            map.put("resultMsg", e.getMessage());
            return map;
        }
        developerService.insert(developerDTO);
        map.put("resultCode", "00");
        return map;
    }
    @RequestMapping(value = "{developerId}/apis", method = RequestMethod.GET)
    public @ResponseBody List<DeveloperApiDTO> queryAccessedApi(@PathVariable Long developerId) {
        List<DeveloperApiDTO> list = developerService.queryAccessedApi(developerId);
        return list;
    }

    @RequestMapping(value = "{developerId}/apis", method = RequestMethod.POST)
    public @ResponseBody Map insertDeveloperApi(@RequestBody DeveloperApiDTO developerApiDTO) {
        Map map = new HashMap();
        developerService.insertDeveloperApi(developerApiDTO);
        map.put("resultCode", "00");
        return map;
    }

    private void validateDeveloperReq(DeveloperDTO developerDTO) {
        Preconditions.checkArgument(!StringUtils.isEmpty(developerDTO.getName()), "开发者名称不能为空！");
        Preconditions.checkArgument(!StringUtils.isEmpty(developerDTO.getAppKey()), "appKey不能为空！");
        Preconditions.checkArgument(!StringUtils.isEmpty(developerDTO.getPublicKey()), "开发者公钥不能为空！");
    }
}
