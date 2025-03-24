package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.service.login.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginRestController {
    LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping
    public boolean login(@RequestParam("username") String username,@RequestParam("password") String password) {
        return loginService.checkLogin(username,password);
    }
}
