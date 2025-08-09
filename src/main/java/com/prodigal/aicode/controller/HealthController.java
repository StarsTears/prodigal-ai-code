package com.prodigal.aicode.controller;

import com.prodigal.aicode.common.BaseResult;
import com.prodigal.aicode.common.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lang
 * @project prodigal-ai-code
 * @Version: 1.0
 * @description 健康检测
 * @since 2025/8/1
 */
@RestController
@RequestMapping("/health")
public class HealthController {
    @RequestMapping(path = "/check",method = RequestMethod.POST)
    public BaseResult<String> check() {
        return ResultUtils.success("ok");
    }
}
