package io.github.redtape9.studentmanager.controller;

import io.github.redtape9.studentmanager.model.Student;
import io.github.redtape9.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
    }

}
