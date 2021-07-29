package repo;

import java.util.List;

public abstract class AbstractRepository<T> {
    public abstract List<T> getAll();

    public abstract T getById(long id);

    public abstract T getByName(String name);

    public abstract T update(T t);

    public abstract T add(T t);

    public abstract T deleteById(long id);
}
