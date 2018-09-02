package dao;

import java.util.List;

public interface DAO<Entity, Key> {
    void init();
    void add(Entity entity);
    List<Entity> getAll();
    void delete(Key key);
}
