package com.bytehonor.server.demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.base.spring.response.JsonResponse;
import com.bytehonor.server.demo.spring.remote.FeignRemoteService;

@RestController
@RequestMapping("/feign")
public class FeignController {

    private static final Logger LOG = LoggerFactory.getLogger(FeignController.class);

    @Autowired
    private FeignRemoteService feignRemoteService;

    @GetMapping("/time")
    public DataString getTime() {
        LOG.info("getTime");
        JsonResponse<DataString> response = feignRemoteService.getTime("test");
        return DataString.of(JsonResponse.dataString(response));
    }

    @GetMapping("/error")
    public DataString getError() {
        LOG.info("getError");
        JsonResponse<DataString> response = feignRemoteService.getError("test");
        return DataString.of(JsonResponse.dataString(response));
    }
}
