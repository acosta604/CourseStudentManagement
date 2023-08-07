package com.mindunits.coursestudentmanager.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
    private Long id;

    public Professor(int id) {
        this.id = (long) id;
    }

    /**
     * JPA requiere que las entidades tengan un constructor sin argumentos (un constructor por defecto)
     * para poder instanciar la entidad cuando se recuperan los datos de la base de datos.
     */
    public Professor() {}

    @Column(name = "professor_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;
}