package lythena.noteapp.auth.userapp;

public enum Roles {
    ADMIN("Administrador"),
    USER("Usuário");

    private final String name;

    Roles(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
