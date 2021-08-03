package ru.geekbrains.java.interview.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.java.interview.entity.StudentDto;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentDto, Long> {
    @Override
    Optional<StudentDto> findById(Long aLong);

    @Override
    Iterable<StudentDto> findAll();

    @Override
    <S extends StudentDto> S save(S s);

    @Override
    void deleteById(Long aLong);
}
