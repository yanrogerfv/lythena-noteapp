package lythena.noteapp.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteInput {
    private UUID id;
    private String title;
    private String body;
    private List<String> tags;
}
