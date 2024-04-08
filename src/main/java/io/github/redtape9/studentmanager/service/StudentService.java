package io.github.redtape9.studentmanager.service;

import io.github.redtape9.studentmanager.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(Student student);

    List<Student> getAllStudents();

    void deleteStudent(Long id);

    void updateStudent(Long id, Student student);

    Student getStudent(Long id);
}
