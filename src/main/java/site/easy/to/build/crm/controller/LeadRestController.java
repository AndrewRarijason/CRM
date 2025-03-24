package site.easy.to.build.crm.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/leads")
public class LeadRestController {

    @GetMapping("/text")
    public String getText() {
        return "i love you";
    }
}
