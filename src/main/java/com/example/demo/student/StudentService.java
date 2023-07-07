package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String getHello(){
        return "Hello World";
    }

    public List<String> getListing(){
        return List.of("Hello", "Listing");
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
        System.out.println(student);
    }

    public String deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(String.format("Student with ID %s does not exist", studentId));
        }
        studentRepository.deleteById(studentId);
        return String.format("Student with ID %s has been deleted", studentId);

    }

    @Transactional
    public void updateStudent(long studentId, String name, String email) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student does not exist");
        }
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        Student student = studentOptional.get();
        Student student = studentRepository.findById(studentId).get();
        System.out.println(student);

        if ((name != null) && (name.length() > 0)
                && (name != student.getName())){
            student.setName(name);
        }

        if ((email != null) && (email.length() > 0)
                && (email != student.getEmail())){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
