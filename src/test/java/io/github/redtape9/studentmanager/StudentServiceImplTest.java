package io.github.redtape9.studentmanager;
import io.github.redtape9.studentmanager.exception.NoStudentsFoundException;
import io.github.redtape9.studentmanager.exception.StudentNotFoundException;
import io.github.redtape9.studentmanager.model.Student;
import io.github.redtape9.studentmanager.repo.StudentRepository;
import io.github.redtape9.studentmanager.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllStudentsReturnsListOfStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        assertEquals(2, studentService.getAllStudents().size());
    }

    @Test
    public void getAllStudentsThrowsNoStudentsFoundExceptionWhenNoStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList());
        assertThrows(NoStudentsFoundException.class, () -> studentService.getAllStudents());
    }

    @Test
    public void getStudentReturnsStudentWhenStudentExists() {
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        assertEquals(student, studentService.getStudent(1L));
    }

    @Test
    public void getStudentThrowsStudentNotFoundExceptionWhenStudentDoesNotExist() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudent(1L));
    }

    @Test
    public void deleteStudentDeletesStudentWhenStudentExists() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteStudentThrowsStudentNotFoundExceptionWhenStudentDoesNotExist() {
        when(studentRepository.existsById(1L)).thenReturn(false);
        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(1L));
    }
}