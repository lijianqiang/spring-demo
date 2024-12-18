package com.bytehonor.server.demo.spring.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bytehonor.sdk.base.spring.response.DataString;
import com.bytehonor.sdk.base.spring.response.JsonResponse;

@FeignClient(name = "health-monitor-server")
public interface FeignRemoteService {

    @GetMapping("/time")
    JsonResponse<DataString> getTime(@RequestParam(name = "from") String from);

    @GetMapping("/test")
    JsonResponse<DataString> getError(@RequestParam(name = "from") String from);
}
