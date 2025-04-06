package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.login.LoginService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth") // Meilleure convention pour une API REST d'authentification
public class LoginRestController {

    private final LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        User user = loginService.checkLogin(username, password);

        if (user != null) {
            // Créer une réponse DTO pour ne pas exposer l'entité User directement
            return ResponseEntity.ok(user);
        }

        // Retourner une erreur standard avec un statut HTTP 401 (Unauthorized)
        return ResponseEntity.status(401).body(Map.of("error", "Nom d'utilisateur ou mot de passe incorrect"));
    }
}
