package repo;

import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Function;

public class StudentRepository extends AbstractRepository<Student> {

    private final EntityManager em;

    public StudentRepository() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("my-unit");

        em = emf.createEntityManager();
    }

    @Override
    public Student getById(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student getByName(String name) {
        return (Student) em.createQuery(
                "SELECT s FROM Student s WHERE s.name = ?1")
                .setParameter(1, name)
                .getResultList().get(0);
    }

    @Override
    public List<Student> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> rootEntry = cq.from(Student.class);
        CriteriaQuery<Student> all = cq.select(rootEntry);
        TypedQuery<Student> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Student update(Student newStudent) {

        Function<Object, Object> function = (newStudentTemp) -> {
            Student newStudentInternal = (Student) newStudentTemp;
            Student oldStudent = getById(newStudentInternal.getId());
            oldStudent.setName(newStudentInternal.getName());
            oldStudent.setMark(newStudentInternal.getMark());
            em.persist(oldStudent);
            return oldStudent;
        };

        return (Student) runInTransaction(function, newStudent);
    }

    @Override
    public Student add(Student student) {

        Function<Object, Object> function = (studentTemp) -> {
            em.persist(studentTemp);
            return studentTemp;
        };

        return (Student) runInTransaction(function, student);
    }

    @Override
    public Student deleteById(long id) {

        Function<Object, Object> function = (idStudent) -> {
            Student student = getById((long) idStudent);
            em.remove(student);
            return student;
        };

        return (Student) runInTransaction(function, id);
    }

    private Object runInTransaction(Function<Object, Object> function, Object obj){
        em.getTransaction().begin();
        Object result = function.apply(obj);
        em.flush();
        em.getTransaction().commit();
        return result;
    }
}
