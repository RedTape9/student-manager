package io.github.redtape9.studentmanager.exception;

public class NoStudentsFoundException extends RuntimeException {
    public NoStudentsFoundException() {
        super("No students found");
    }
}