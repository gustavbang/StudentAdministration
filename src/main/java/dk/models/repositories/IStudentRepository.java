package dk.models.repositories;

import dk.models.entities.Student;

import java.util.ArrayList;


public interface IStudentRepository {

    // Create
    public void create(Student st);

    // Read All
    public ArrayList<Student> readAll();

    // Read
    public Student read(int studentId);

    // Update
    public void update(Student st);

    // Delete
    public void delete(int studentId);

    // Login
    public Student login(Student studentId);
}
