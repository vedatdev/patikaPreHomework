package com.example.patika.services;

import com.example.patika.entity.Student;
import com.example.patika.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void create(Student toAdd){
         studentRepository.save(toAdd);

        System.out.println("Eklendi " + toAdd.getName() );
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
        System.out.println("Silindi " + id);
    }

    public Student findById(Long id){

        return this.studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
    }
    @Transactional
    public void update(String name, String surname, LocalDate birthDate,Long id){

         Student studentToUpdate = this.studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
         if (Objects.nonNull(name) && name.length() > 0 && !studentToUpdate.getName().equals(name))
            studentToUpdate.setName(name);

        if (Objects.nonNull(surname) && surname.length() > 0 && !studentToUpdate.getSurname().equals(surname))
            studentToUpdate.setSurname(surname);

        if (Objects.nonNull(birthDate) && birthDate.isBefore(LocalDate.now()))
         studentToUpdate.setBirthDate(birthDate);

    }

}
