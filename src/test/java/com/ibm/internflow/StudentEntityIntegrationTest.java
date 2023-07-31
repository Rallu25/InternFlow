package com.ibm.internflow;

import com.ibm.internflow.entity.StudentEntity;
import com.ibm.internflow.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StudentEntityIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    public void testSaveStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName("Ana");
        studentEntity.setLastName("Maria");
        studentEntity.setEmail("ana.maria@gmail.com");

        StudentEntity savedStudent = studentRepository.save(studentEntity);

        assertNotNull(savedStudent.getStudentId());
        assertEquals("Ana", savedStudent.getFirstName());
        assertEquals("Maria", savedStudent.getLastName());
        assertEquals("ana.maria@gmail.com", savedStudent.getEmail());
    }

}
