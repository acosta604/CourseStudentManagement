package com.mindunits.coursestudentmanager.repository;

public class NoteRepository {
    void agregarNota(Long estudent, Long course, Double valor);
    void actualizarNota(Long note, Double valor);
    List Note obtenerNotasPorEstudiante(Long student);
    List Note obtenerNotasPorCurso(Long course)
}


