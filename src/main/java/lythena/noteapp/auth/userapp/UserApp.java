package lythena.noteapp.auth.userapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {
    private String username;
    private String password;
    private Roles role;
}
