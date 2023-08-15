package com.mindunits.coursestudentmanager.controllers;

import com.mindunits.coursestudentmanager.models.Note;
import com.mindunits.coursestudentmanager.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class NoteController {


    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> agregarNota(@RequestBody Note note) {
        noteService.agregarNota(note.getStudentId(), note.getCourseId(), note.getValor());
        return ResponseEntity.ok("Nota agregada con éxito");
    }

    @PutMapping("/{notaId}")
    public ResponseEntity<?> actualizarNota(@PathVariable Long notaId, @RequestBody Double valor) {
        noteService.actualizarNota(notaId, valor);
        return ResponseEntity.ok("Nota actualizada con éxito");
    }

    @GetMapping ("/student/{StudentId}")
    public ResponseEntity<?> obtenerNotasPorEstudiante(@PathVariable  Long StudentId) {
        List<Nota> notas = noteService.obtainerNotesforstudents(studentId);
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<?> obtenerNotasPorCurso(@PathVariable Long courseId) {
        List<Note> notes = noteService.obtenerNotasPorCurso(courseId);
        if (!notes.Empty()) {
            return ResponseEntity.ok(notes);
        }
        return ResponseEntity.noContent().build();
    }


}
