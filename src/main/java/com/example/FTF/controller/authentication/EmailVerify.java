package com.example.FTF.controller.authentication;

import com.example.FTF.units.core.result.HttpResult;
import com.example.FTF.units.email.EmailServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/authentication")
public class EmailVerify {

    @Resource
    private EmailServer emailServer;

    @GetMapping("/send/{email}")
    public HttpResult sendEmail(@PathVariable String email) {
        emailServer.sendEmail(email);
        return HttpResult.success();
    }
}
