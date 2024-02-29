package lythena.noteapp.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private UUID id;
    private String title;
    private String body;
    private List<String> tags;
    private String modificationDate;
    private LocalDate createDate;
//    private boolean favorite;

    public String getNoteSpecs(){
        StringBuilder stringBuilder = new StringBuilder(title+";"+id+";"+createDate+";"+modificationDate);
        return stringBuilder.toString();
    }

}
