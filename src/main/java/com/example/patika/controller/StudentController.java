package com.example.patika.controller;


import com.example.patika.entity.Student;
import com.example.patika.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequestScope
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get")
    public List<Student> getAll(){
        return this.studentService.getStudents();
    }

    @PostMapping("/save")
    public ResponseEntity<?> add(@RequestBody Student student){
        this.studentService.create(student);
        return ResponseEntity.ok("Student created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try{
            this.studentService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(this.studentService.findById(id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                    @RequestParam(required = false) LocalDate birthDate, @PathVariable("id") Long id){
        try {
            this.studentService.update(name,surname,birthDate,id);
            return ResponseEntity.ok("Student is updated");
        }catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    }




}
