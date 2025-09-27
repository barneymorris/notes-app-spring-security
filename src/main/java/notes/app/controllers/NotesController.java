package notes.app.controllers;

import notes.app.models.Note;
import notes.app.services.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@AuthenticationPrincipal UserDetails userDetails, @RequestBody String content)  {
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: " + username);
        return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: " + username);
        return noteService.getNotesForUser(username);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable("noteId") Long noteId, @RequestBody String content, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.updateNoteForUser(noteId, content, username);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, username);
    }
}
