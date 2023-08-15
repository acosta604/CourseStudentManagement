package com.mindunits.coursestudentmanager.services;

import com.mindunits.coursestudentmanager.models.Note;
import com.mindunits.coursestudentmanager.repository.CourseRepository;
import com.mindunits.coursestudentmanager.repository.NoteRepository;
import com.mindunits.coursestudentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public void agregarNota(Long estudianteId, Long cursoId, Double valor) {

    }

    @Override
    public void actualizarNote(Long notaId, Double valor
        // Implementa la lógica para actualizar el valor de una nota existente

    @Override
    public List<Note> obtenerNotasPorEstudiante(Long estudiantetId);
        // Implementa la lógica para obtener las notas de un estudiante
    @Override
    public List<Note>

    @Override
    public List<Note> obtenerNotasPorCurso(Long cursoId) {
        // Implementa la lógica para obtener las notas de un curso;
    }