package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.JpaStudent;
import io.lao.alloutjpa.model.Student;

import java.util.List;

public interface StudentService {

    void saveNewStudent(JpaStudent student);

    void updateStudentRecordById(Integer studentId);

    List<Student> getAllStudents();

    void deleteStudentById(Integer studentId);
}
