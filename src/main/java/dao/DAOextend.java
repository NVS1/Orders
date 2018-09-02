package dao;

public interface DAOextend <Entity, Key1, Key2> extends DAO<Entity, Key1> {
    boolean isPresent (Key2 key2);
    Entity get (Key2 key1);
    Entity getById (Key1 key1);
}
