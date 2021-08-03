package ru.geekbrains.java.interview.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import ru.geekbrains.java.interview.model.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class StudentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true, length = 30)
    private String name;

    @NonNull
    private Integer age;

    public StudentDto(@NotNull Student student) {
        id = student.getId();
        name = student.getName();
        age = student.getAge();
    }
}
