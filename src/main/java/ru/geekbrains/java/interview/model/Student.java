package ru.geekbrains.java.interview.model;

import lombok.Data;
import lombok.NonNull;
import ru.geekbrains.java.interview.entity.StudentDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class Student {
    private Long id;

    @NonNull
    @Size(min = 5, max = 30)
    private final String name;

    @NonNull
    private final Integer age;

    public Student(@NotNull StudentDto studentDto){
        id = studentDto.getId();
        name = studentDto.getName();
        age = studentDto.getAge();
    }

    public Student(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}
