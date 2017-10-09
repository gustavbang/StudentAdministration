package dk.models.repositories;

import dk.models.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

@Repository
public class StudentRepository implements IStudentRepository {

    @Autowired
    private JdbcTemplate jdbc;
    //private SqlRowSet sqlRowSet;
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    public void create(Student st) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(st.getEnrollmentDate());
        jdbc.update("INSERT INTO studentdb.student(firstName, lastName, enrollmentDate, password, cpr) " + "VALUES('" + st.getFirstName() + "', '" + st.getLastName() + "', '" +  currentTime + "', '" + st.getPassword() + "', '" + st.getCpr() + "') ");
    }

    @Override
    public ArrayList<Student> readAll() {

        ArrayList<Student> students = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM studentdb.student");

        while(sqlRowSet.next()){
            // indhold af sqlRowset ned i en arrayliste
           students.add(new Student(sqlRowSet.getInt("id"), sqlRowSet.getString("firstName"), sqlRowSet.getString("lastName"), sqlRowSet.getDate("enrollmentDate"), sqlRowSet.getString("password"), sqlRowSet.getString("cpr")));
        }

        return students;
    }

    @Override
    public Student read(int studentId) {


        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM studentdb.student WHERE id =" + studentId);

        if(sqlRowSet.next()){
            // indhold af sqlRowset ned i en arrayliste
            return new Student(sqlRowSet.getInt("id"), sqlRowSet.getString("firstName"), sqlRowSet.getString("lastName"), sqlRowSet.getDate("enrollmentDate"), sqlRowSet.getString("password"), sqlRowSet.getString("cpr"));
        }
        return new Student();
    }

    @Override
    public void update(Student st) {
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String currentTime = sdf.format(st.getEnrollmentDate());
    jdbc.update("UPDATE studentdb.student SET firstName = '" + st.getFirstName() + "', lastName = '" + st.getLastName() + "', enrollmentDate = '" + currentTime + "', password = '" + st.getPassword() + "', cpr = '" + st.getCpr() + "' WHERE  id = " + st.getStudentId());
    }

    @Override
    public void delete(int studentId) {
        jdbc.update("DELETE FROM studentdb.student WHERE id = " + studentId);
    }

    @Override
    public Student login(Student st) {
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM studentdb.student WHERE id = '" + st.getStudentId() + "', password = '" + st.getPassword());
        Student currentFucktard = new Student();
        if (sqlRowSet.next()) {
            int id = sqlRowSet.getInt("id");
            String firstName = sqlRowSet.getString("firstName");
            String lastName = sqlRowSet.getString("lastName");
            Date enrollmentDate = sqlRowSet.getDate("enrollmentDate");
            String password = sqlRowSet.getString("password");
            String cpr = sqlRowSet.getString("cpr");

            currentFucktard.setStudentId(id);
            currentFucktard.setFirstName(firstName);
            currentFucktard.setLastName(lastName);
            currentFucktard.setEnrollmentDate(enrollmentDate);
            currentFucktard.setPassword(password);
            currentFucktard.setCpr(cpr);


        }
        return currentFucktard;
    }
}
