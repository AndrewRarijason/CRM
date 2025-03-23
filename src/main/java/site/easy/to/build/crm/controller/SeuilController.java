package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import site.easy.to.build.crm.service.seuil.SeuilService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.*;
import site.easy.to.build.crm.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employee/seuils")
public class SeuilController {

    private final SeuilService seuilService;
    private final UserService userService;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public SeuilController(SeuilService seuilService, UserService userService, AuthenticationUtils authenticationUtils) {
        this.seuilService = seuilService;
        this.userService = userService;
        this.authenticationUtils = authenticationUtils;

    }

    @GetMapping
    public String listSeuils(Model model) {
        List<Seuil> seuils = seuilService.getAllSeuils();
        model.addAttribute("seuils", seuils);
        return "seuils/list"; 
    }

    @GetMapping("/create")
    public String showCreatingForm(Model model,Authentication authentication) {
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user.isInactiveUser()) {
            return "error/account-inactive";
        }

    
        model.addAttribute("seuil", new Seuil());
        return "seuil/create-seuil"; // Correspond à la vue `add.html`
    }

    @GetMapping("/showSeuilActual")
    public String showSeuilActual(Model model,Authentication authentication) {
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user.isInactiveUser()) {
            return "error/account-inactive";
        }

        Seuil seuilActual=seuilService.getSeuilActuel();

    
        model.addAttribute("seuil",seuilActual);
        return "seuil/actual-seuil"; // Correspond à la vue `add.html`
    }

   
    // @PostMapping("/add")
    // public String addSeuil(@ModelAttribute Seuil seuil) {
    //     seuilService.addSeuil(seuil);
    //     return "redirect:/seuils";
    // }

     @PostMapping("/add")
    public String createSeuil(@ModelAttribute("seuil") @Validated Seuil seuil, BindingResult bindingResult,
                              Authentication authentication, Model model) throws JsonProcessingException {

        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user == null) {
            return "error/500";
        }
        if (user.isInactiveUser()) {
            return "error/account-inactive";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("seuil", seuil);
            return "seuil/create-seuil";
        }

        seuil.setDateSeuil(LocalDateTime.now());

        // Sauvegarde du seuil
         seuilService.addSeuil(seuil);

        // Redirection après la création
        return "redirect:/employee/seuils/showSeuilActual";
    }

  
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Seuil> seuil = seuilService.getSeuilById(id);
        if (seuil.isPresent()) {
            model.addAttribute("seuil", seuil.get());
            return "seuils/edit"; 
        } else {
            return "redirect:/seuils";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateSeuil(@PathVariable int id, @ModelAttribute Seuil seuil) {
        seuilService.updateSeuil(id, seuil);
        return "redirect:/seuils";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeuil(@PathVariable int id) {
        seuilService.deleteSeuil(id);
        return "redirect:/seuils";
    }
}
