# Lythena Noteapp

This is a personal project of mine to make a note-taking application, 
starting with the backend made in Java and Spring.

## Summary
1. [Authorization](#Authorization)
2. [Notes](#Notes)
3. [Concepts](#Concepts)
10. [Outro](#Outro)

## Authorization

The security aspect of the Lythena comes from Spring Security and its 
athorizations. First, you shall not access any part of the application without 
logging in, the only exceptions are the windows that allows someone to create a 
proper login for the user to start. Some restrictions are:

- You can't use an username that already exists;
- Your username must have between 4 and 16 characters;
- Your password must have at least 8 characters;
- The username and the password can have any letter, number or
symbol, with the ";" character being the only unusable one);

Once created a new user, it is stored in the repository aswell as locally, in 
an `usuarios.txt` file with the crypted keys of that user. 

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
- Ambient music;


Creation of a "summary.txt" file, that the code uses as reference to indexize
every existing local note.

## Outro

Contact: [yanrogerfv@gmail.com](mailto:yanrogerfv@gmail.com)