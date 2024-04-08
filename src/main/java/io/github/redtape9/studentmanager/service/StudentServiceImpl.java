package io.github.redtape9.studentmanager.service;

import io.github.redtape9.studentmanager.exception.NoStudentsFoundException;
import io.github.redtape9.studentmanager.exception.StudentNotFoundException;
import io.github.redtape9.studentmanager.model.Student;
import io.github.redtape9.studentmanager.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            throw new NoStudentsFoundException();
        }else {
            return students;
        }
    }

    public Student getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException(id);
        } else {
            studentRepository.deleteById(id);
        }
    }

    public void updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        existingStudent.setName(student.getName());
        existingStudent.setAddress(student.getAddress());
        studentRepository.save(existingStudent);
    }
}
