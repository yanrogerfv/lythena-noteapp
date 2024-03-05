package lythena.noteapp.auth.userapp;

import lythena.noteapp.config.RogueException;

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
        if(input.getUsername().length() > 16 || input.getUsername().length() < 4)
            throw new RogueException("O nome de usuário deve conter entre 4 e 16 caracteres!");
        if(repository.listUsers().contains(input.getUsername()))
            throw new RogueException("Já existe um usuário com este nome.");
        if(input.getPassword().length() < 8)
            throw new RogueException("A senha deve conter no mínimo 8 caracteres.");
        if(input.getUsername().contains(";") || input.getPassword().contains(";"))
            throw new RogueException("Não se deve utilizar do caractere ';'.");
        if(Roles.values()[input.getRole()] == null)
            throw new RogueException("Cargo inexistente.");
        repository.addUserFromInput(input);
    }

    public void removeUser(String username, String password){
        repository.removeUser(repository.getUser(username), password);
    }

}
