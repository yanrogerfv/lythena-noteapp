package lythena.noteapp.notes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("v0/notes")
@Tag(name = "v0/Notes Controller")
public class NoteController {

    private final NoteInteractor noteInteractor;

    public NoteController(NoteInteractor noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    @GetMapping
    @Operation(summary = "List notes.")
    public List<String> listNotes(){
        return noteInteractor.listNotes();
    }

    @GetMapping("/tags")
    @Operation(summary = "List notes by Tag.")
    public List<Note> listNotesByString(String tag){
        return noteInteractor.findNotesByTags(tag);
    }

    @GetMapping("/view")
    @Operation(summary = "View one note by its ID.")
    public Note viewNote(@RequestParam UUID id){
        return noteInteractor.getNote(id);
    }

    @GetMapping("/find")
    @Operation(summary = "Find a note by its Title.")
    public List<Note> findNote(@RequestParam String title){
        return noteInteractor.findNotes(title);
    }

    @PostMapping
    @Operation(summary = "Create a new note.")
    public String createNote(@RequestBody NoteInput noteInput){
        return noteInteractor.createNote(noteInput);
    }

    @PatchMapping
    @Operation(summary = "Favorite a note.")
    public String favoriteNote(@RequestParam UUID id){
        return noteInteractor.favoriteNote(id);
    }

    @PutMapping
    @Operation(summary = "Edit a note.")
    public String editNote(@RequestBody NoteInput noteInput){
        return noteInteractor.editNote(noteInput);
    }

    @DeleteMapping
    @Operation(summary = "Delete a note")
    public String deleteNote(@RequestParam UUID id){
        return noteInteractor.deleteNote(id);
    }

    @GetMapping("/export")
    @Operation(summary = "Export a note to .txt.")
    public File exportNote(@RequestParam UUID id){
        return noteInteractor.exportNote(id);
    }
}
