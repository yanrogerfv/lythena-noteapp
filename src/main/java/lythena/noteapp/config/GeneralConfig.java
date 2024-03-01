package lythena.noteapp.config;

import lythena.noteapp.auth.SecurityConfiguration;
import lythena.noteapp.auth.userapp.UserAppInteractor;
import lythena.noteapp.auth.userapp.UserAppRepository;
import lythena.noteapp.notes.NoteInteractor;
import lythena.noteapp.notes.NoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public NoteInteractor interactor(NoteRepository noteRepository){
        return new NoteInteractor(noteRepository);
    }

    @Bean
    public NoteRepository noteRepository(){
        return new NoteRepository();
    }

    @Bean
    public SecurityConfiguration passwordConfiguration(UserAppRepository userAppRepository){
        return new SecurityConfiguration(userAppRepository);
    }

    @Bean
    public UserAppRepository userAppRepository(){
        return new UserAppRepository();
    }

    @Bean
    public UserAppInteractor userAppInteractor(UserAppRepository userAppRepository){
        return new UserAppInteractor(userAppRepository());
    }

}
