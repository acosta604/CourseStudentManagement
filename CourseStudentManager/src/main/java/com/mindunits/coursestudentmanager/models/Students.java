

package com.mindunits.coursestudentmanager.models;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "student" )
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id_student;

    @Column(name = "student_name")
    private String student_name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
}
