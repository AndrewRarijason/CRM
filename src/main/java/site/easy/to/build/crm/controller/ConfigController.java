package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @GetMapping
    public Map<String, String> getDatabaseConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("db_url", dbUrl);
        config.put("db_username", dbUsername);
        config.put("db_password", dbPassword);
        return config;
    }
}