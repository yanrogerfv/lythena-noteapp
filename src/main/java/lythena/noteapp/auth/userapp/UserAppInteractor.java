package lythena.noteapp.auth.userapp;

import java.util.List;

public class UserAppInteractor {

    private final UserAppRepository repository;

    public UserAppInteractor(UserAppRepository repository) {
        this.repository = repository;
    }

    public List<String> listUsers(){
        return repository.listUsers();
    }

    public void createUser(UserAppInput input){
        repository.addUserFromInput(input);
    }

    public void removeUser(String username, String password){
        repository.removeUser(repository.getUser(username), password);
    }

}
