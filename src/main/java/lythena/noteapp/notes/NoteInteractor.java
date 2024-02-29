package lythena.noteapp.notes;

import com.aspose.cells.Workbook;
import lythena.noteapp.config.RogueException;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class NoteInteractor {

    private final NoteRepository noteRepository;

    public NoteInteractor(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<String> listNotes(){
        return noteRepository.listNotes();
    }

    public Note getNote(UUID id){
        return noteRepository.getNote(id);
    }
    public List<Note> findNotes(String title){
        return noteRepository.findNotes(title);
    }
    public List<Note> findNotesByTags(String tag){
        return noteRepository.findNotesByTags(tag);
    }
    public String createNote(NoteInput noteInput){
        Note note = new Note();

        if(noteInput.getTitle() == null || noteInput.getTitle().isBlank()) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < 24; i++) {
                if(noteInput.getBody().charAt(i) == ';')
                    t.append('#');
                else
                    t.append(noteInput.getBody().charAt(i));
            }
            noteInput.setTitle(t.toString());
        } else
            note.setTitle(noteInput.getTitle());
        if(noteInput.getTitle().length() > 24)
            throw new RogueException("O tamanho máximo é de 24 caracteres.");

        for (int i = 0; i < noteInput.getTitle().length(); i++) {
            if(noteInput.getTitle().charAt(i) == ';')
                throw new RogueException("O título não deve conter ponto e vírgula(;).");
        }
        note.setTitle(noteInput.getTitle());
        note.setId(UUID.randomUUID());
        note.setBody(noteInput.getBody());
        note.setTags(noteInput.getTags());
        note.setCreateDate(LocalDate.now());
        note.setModificationDate("Unmodified");
        noteRepository.postNote(note);
        return "Nota salva com sucesso!";
    }
    public String favoriteNote(UUID id){
//        noteRepository.favoriteNote(id);
        return "Nota favoritada com sucesso!";
    }
    public String editNote(NoteInput input){
        noteRepository.editNote(input);
        return "Nota editada com sucesso!";
    }
    public String deleteNote(UUID id){
        noteRepository.deleteNote(id);
        return "Note deletada com sucesso!";
    }
    public File exportNote(UUID id){
        Note note = getNote(id);
        File file = new File("notefiles/".concat(note.getNoteSpecs().concat(".txt")));
        try {
            Workbook workbook = new Workbook(file.toString());
            workbook.save("exports/".concat(note.getNoteSpecs().concat(".pdf")));
            return new File("exports/".concat(note.getNoteSpecs().concat(".pdf")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
