package lythena.noteapp.auth;

import lythena.noteapp.auth.userapp.UserApp;
import lythena.noteapp.auth.userapp.UserAppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfiguration {

    private final UserAppRepository userAppRepository;

    public SecurityConfiguration(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Bean
    public UserDetailsService users() {
        List<UserDetails> users = new ArrayList<>();
        for (int i = 0; i < userAppRepository.getList().size(); i++) {
            UserApp userApp = userAppRepository.getList().get(i);
            users.add(User.builder()
                    .username(userApp.getUsername())
                    .password("{bcrypt}".concat(userApp.getPassword()))
                    .roles(userApp.getRole().getName())
                    .build());
        }
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("v0/login/*");
    }


}
