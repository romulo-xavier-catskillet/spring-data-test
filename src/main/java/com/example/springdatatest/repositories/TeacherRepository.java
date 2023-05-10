package com.example.springdatatest.repositories;

import com.example.springdatatest.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
