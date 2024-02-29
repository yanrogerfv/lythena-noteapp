package lythena.noteapp.notes;

import lythena.noteapp.config.RogueException;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class NoteRepository {

    private final List<Note> notes;
    private final File summary = new File("summary/summary.txt");

    public NoteRepository(){
        notes = new ArrayList<Note>();
        if(summary.exists()) {
            try {
                FileReader fileReader = new FileReader(summary);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                List<String> specs = bufferedReader.lines().toList();
                List<Note> aux = new ArrayList<>();
                for (int i = 0; i < specs.size(); i++) {
                    String noteSpecs = specs.get(i);
                    String[] split = noteSpecs.split(";");
                    UUID finalId = UUID.fromString(split[1]);
                    String title = split[0];
                    LocalDate createDate = LocalDate.parse(split[2]);
                    String modDate = split[3];

                    File auxfile = new File("notefiles/".concat(noteSpecs).concat(".txt"));
                    if (auxfile.exists()) {

                        StringBuilder body = new StringBuilder(Files.readString(auxfile.toPath()));

                        Note note = new Note(finalId, title, body.toString(), List.of("tags"), modDate, createDate);
                        notes.add(note);
                    } else {
                        throw new RogueException("A nota ".concat(noteSpecs).concat(" não existe."));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                summary.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public List<String> listNotes(){
        return notes.stream().map(Note::getNoteSpecs).toList();
    }
    public List<Note> findNotes(String title){
        return notes.stream().filter(n -> n.getTitle().contains(title)).toList();
    }
    public List<Note> findNotesByTags(String tag){
        return notes.stream().filter(n -> n.getTags().contains(tag)).toList();
    }
    public Note getNote(UUID id) {
        return notes.stream().filter(n -> id.equals(n.getId())).findFirst().orElseThrow(() -> new RogueException("Nota não encontrada."));
    }

    public void postNote(Note note) {
        try {

            StringBuilder string = new StringBuilder(Files.readString(summary.toPath()));
            File file = new File("notefiles/"+note.getNoteSpecs()+".txt");
            //noinspection ResultOfMethodCallIgnored
            if(!file.createNewFile())
                throw new RogueException("Não foi possível criar ".concat(note.getNoteSpecs()));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(note.getBody());

            FileWriter summaryWriter = new FileWriter(summary);

            String content = string.append(note.getNoteSpecs().concat("\n")).toString();
            summaryWriter.write(content);

            fileWriter.close();
            summaryWriter.close();
            notes.add(note);
            System.out.println("Nota "+note.getTitle()+" criada com sucesso!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void favoriteNote(UUID id){
//        Note note = getNote(id);
//        note.setFavorite(true);
//        save(note);
//    }

    public void editNote(NoteInput input){
        Note note = getNote(input.getId());
        String baseSpecs = note.getNoteSpecs();
        File file = new File("notefiles/".concat(baseSpecs.concat(".txt")));
        if(input.getTitle() != null)
            note.setTitle(input.getTitle());
        if(input.getBody() != null)
            note.setBody(input.getBody());
        if(input.getTags() != null)
            note.setTags(input.getTags());
        note.setModificationDate(LocalDate.now().toString());
        save(note);

        try {
            File newFile = new File("notefiles/".concat(note.getNoteSpecs()).concat(".txt"));
            file.renameTo(newFile);
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(note.getBody());
            fileWriter.close();
//
            List<String> list = Files.readAllLines(summary.toPath());
            int index = list.indexOf(list.stream().filter(s -> s.equals(baseSpecs)).findAny().orElseThrow(
                    () -> new RogueException("Nota não encontrada.")));

            list.set(index, note.getNoteSpecs());

            FileWriter summaryWriter = new FileWriter(summary);
            StringBuilder string = new StringBuilder();

            for (String s : list)
                string.append(s).append("\n");

            summaryWriter.write(string.toString());
            summaryWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNote(UUID id){
        Note note = notes.stream().filter(n -> id.equals(n.getId())).findAny().orElseThrow(
                () -> new RogueException("Nota não encontrada."));
        notes.remove(note);
        File file = new File("notefiles/".concat(note.getNoteSpecs().concat(".txt")));
        if(!file.delete())
            throw new RogueException("Ocorreu um erro ao excluir o arquivo da nota.");

        try {
            List<String> list = Files.readAllLines(summary.toPath());
            list.remove(list.stream().filter(s -> s.equals(note.getNoteSpecs())).findAny().orElseThrow(
                    () -> new RogueException("Nota não indexada.")));

            FileWriter summaryWriter = new FileWriter(summary);
            StringBuilder string = new StringBuilder();

            for (String s : list)
                string.append(s).append("\n");

            summaryWriter.write(string.toString());
            summaryWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void save(Note note){
        notes.remove(note);
        notes.add(note);
    }
}