# Lythena Noteapp

This is a personal project of mine to make a note-taking application, 
starting with the backend made in Java and Spring.

## Summary
1. [Notes](#Notes)
2. [Concepts](#Concepts)
10. [Outro](#Outro)

## Notes:

Notes are the basic and essential feature of the application, they are made of a
title, a body (both `Strings`) and tags (list of `Strings`) for input. 
They also have within themselves an id (`UUID`), creation and modification 
dates (`LocalDate` and `String`, respectively).

```
public class Note {

    private UUID id;
    private String title;
    private String body;
    private List<String> tags;
    private String modificationDate;
    private LocalDate createDate;
    
}
```

Features implemented until now:
- Listing, creation, edition, deletion, 
services of view (by ID) and find (by title or tags).
- Saving notes locally as .txt's. 


## Concepts

Here are some ideas for future implementations, that may or may not be added 
to the final version of the application:

- Notebooks;
- Favoriting;
- Groups;
- Task notes;
- Sharing notes;
- Authentication by user and password;
- Ambient music;


Creation of a "summary.txt" file, that the code uses as reference to indexize
every existing local note.

## Outro

Contact: [yanrogerfv@gmail.com](mailto:yanrogerfv@gmail.com)