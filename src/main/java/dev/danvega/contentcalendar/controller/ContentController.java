package dev.danvega.contentcalendar.controller;

import dev.danvega.contentcalendar.model.Content;
import dev.danvega.contentcalendar.repository.ContentCollectionRepository;
import dev.danvega.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
    private final ContentRepository repository;

    public ContentController(ContentRepository repository, ContentCollectionRepository repository1) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Integer id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!")));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if(!repository.existsById(id)) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}