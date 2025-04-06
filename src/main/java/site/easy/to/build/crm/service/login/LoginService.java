package site.easy.to.build.crm.service.login;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.easy.to.build.crm.repository.UserRepository;
import site.easy.to.build.crm.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoginService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    
    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User checkLogin(String username, String password) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsername(username);
        if (!users.isEmpty()) {
            User user = users.get(0);
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            } // Utiliser matches()
        }
        return null;
    }
    
}
