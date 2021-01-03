package pbouda.jfr.cgroups.spring;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "notes")
public class NotesController {

    private final NotesRepository repository;

    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Note> list() {
        List<Note> notes = repository.findAll(Sort.by(Sort.Order.desc("$natural")));
        System.out.println("Retrieved notes: " + notes.size());
        return notes;
    }

    @PostMapping
    public String post(@RequestBody Note note) {
        Note saved = repository.save(note);
        System.out.println("Saved note: " + saved.getId());
        return note.getSubject();
    }
}
