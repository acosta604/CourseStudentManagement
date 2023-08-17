package com.mindunits.coursestudentmanager.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorRepository {

    @PersistenceContext
    EntityManager dataBase;


}
