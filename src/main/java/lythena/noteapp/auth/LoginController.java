package lythena.noteapp.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lythena.noteapp.auth.userapp.UserApp;
import lythena.noteapp.auth.userapp.UserAppInput;
import lythena.noteapp.auth.userapp.UserAppInteractor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v0/login")
@Tag(name = "v0/Login Controller")
public class LoginController {

    private final UserAppInteractor interactor;

    public LoginController(UserAppInteractor interactor) {
        this.interactor = interactor;
    }

    @GetMapping("/users")
    public List<String> listUsers(){
        return interactor.listUsers();
    }

    @PostMapping("/newuser")
    public void createUser(@RequestParam String username, @RequestParam String password, Integer role){
        interactor.createUser(new UserAppInput(username, password, role));
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam String username, @RequestParam String password){
        interactor.removeUser(username, password);
    }

}
