package lythena.noteapp.config;

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

}
