package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private  final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String getHello(){
        return studentService.getHello();
    }

    @GetMapping("/listing")
    public List<String> getListing(){
        return studentService.getListing();
    }

    @GetMapping("/student")
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    @PostMapping("/student")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/student/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @PutMapping ("/student/{studentId}")
    public void updateStudent(@PathVariable("studentId") long studentId,
                              @RequestParam(required = false, name = "name") String name,
                              @RequestParam(required = false, name = "email") String email){
        studentService.updateStudent(studentId,name,email);
    }
}
