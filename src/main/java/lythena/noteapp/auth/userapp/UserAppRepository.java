package lythena.noteapp.auth.userapp;

import lythena.noteapp.config.EntityNotFoundException;
import lythena.noteapp.config.RogueException;
import lythena.noteapp.notes.Note;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAppRepository {
    private final List<UserApp> users;
    private final File usersTxt = new File("/usuarios/usuarios.txt");

    public UserAppRepository() {
        users = new ArrayList<>();
        if(usersTxt.exists()) {
            try {
                FileReader fileReader = new FileReader(usersTxt);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                List<String> specs = bufferedReader.lines().toList();
                List<Note> aux = new ArrayList<>();
                for (int i = 0; i < specs.size(); i++) {

                    String[] split = specs.get(i).split(";");

                    String username = split[0];
                    String codedPassword = split[1];
                    Integer role = Integer.getInteger(split[2]);

                    addUserFromFile(username, codedPassword, role);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            addUserFromInput(new UserAppInput("user", "password", 1));
            addUserFromInput(new UserAppInput("admin", "password", 0));
            addUserFromInput(new UserAppInput("Atlas", "World", 0));
            try {
                //noinspection ResultOfMethodCallIgnored
                usersTxt.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean verifyCredentials(UserApp userApp){
        return users.contains(userApp);
    }

    public List<UserApp> getList(){
        return users;
    }
    public List<String> listUsers(){
        return users.stream().map(UserApp::getUsername).toList();
    }
    public UserApp getUser(String username){
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow(
                () -> new RogueException("Usuário não encontrado."));
    }
    public void addUserFromInput(UserAppInput input){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Roles role = Arrays.stream(Roles.values()).filter(
                r-> r.ordinal() == input.getRole()).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado"));

        UserApp userApp = new UserApp(input.getUsername(), input.getPassword(), role);
        userApp.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
        users.add(userApp);


    }

    private void addUserFromFile(String username, String codedPassword, Integer roleInput){
        Roles role = Arrays.stream(Roles.values()).filter(
                        r-> r.ordinal() == roleInput).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado"));
        UserApp userApp = new UserApp(username, codedPassword, role);
        users.add(userApp);
    }

    public void removeUser(UserApp userApp, String password){
        if (users.stream().anyMatch(u -> u.equals(userApp)))
            if(password.equals(userApp.getPassword()))
                users.remove(userApp);
    }

}
