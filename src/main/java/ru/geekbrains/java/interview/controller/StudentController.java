package ru.geekbrains.java.interview.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.interview.entity.StudentDto;
import ru.geekbrains.java.interview.model.Student;
import ru.geekbrains.java.interview.repo.StudentRepository;
import ru.geekbrains.java.interview.repo.exception.NotFoundException;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = "all", produces = "application/json")
    public Iterable<StudentDto> getAll(){
        return studentRepository.findAll();
    }

    @PutMapping(value = "add", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student addStudent(@RequestBody Student student){
        StudentDto studentDto = new StudentDto(student);
        studentDto = studentRepository.save(studentDto);
        return new Student(studentDto);
    }

    @PostMapping(value = "update", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student) throws NotFoundException{
        Long id = student.getId();
        if (id == null){
            throw new NotFoundException();
        }
        StudentDto studentDto = studentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto = studentRepository.save(studentDto);
        return new Student(studentDto);
    }

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteStudent(@PathVariable long id) throws NotFoundException{
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new NotFoundException();
        }
    }
}
