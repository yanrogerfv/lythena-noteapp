package lythena.noteapp.auth.userapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAppInput {
    private String username;
    private String password;
    private Integer role;
}
