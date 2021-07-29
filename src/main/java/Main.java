import entity.Student;
import repo.AbstractRepository;
import repo.StudentRepository;


public class Main {
    public static void main(String[] args) throws Exception {
        Student student;
        Student studentAdd;
        Student studentUpdate;
        Student studentDelete;

        Class.forName("org.h2.Driver");

        AbstractRepository<Student> repository = new StudentRepository();

        student = new Student("student1", 1);

        studentAdd = repository.add(student);

        System.out.printf("added: %s%n", studentAdd);

        student = new Student("student11", 11);
        student.setId(studentAdd.getId());

        studentUpdate = repository.update(student);

        System.out.printf("updated: %s%n", studentUpdate);

        student = repository.getByName("student11");

        studentDelete = repository.deleteById(student.getId());

        System.out.printf("deleted: %s%n", studentDelete);
        System.out.printf("all: %s%n", repository.getAll());

        for (int i = 0; i < 100; i++) {
            student = new Student("student#" + i, i);
            repository.add(student);
        }
    }
}
