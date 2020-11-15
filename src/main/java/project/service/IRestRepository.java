package project.service;

import project.model.BaseEntity;
import project.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IRestRepository<T extends BaseEntity> {

    List<T> select() throws SQLException;
    T select(Integer id) throws SQLException;
    T insert(T entity) throws SQLException;
    T update(Integer id, T entity) throws SQLException;
    T delete(Integer id) throws SQLException;

//    void create(User user) throws SQLException;
//
//
//    /**
//     * Возвращает список всех имеющихся клиентов
//     * @return список клиентов
//     */
//
//    List<User> readAll() throws SQLException;
//
//
//    /**
//     * Возвращает клиента по его ID
//     * @param id - ID клиента
//     * @return - объект клиента с заданным ID
//     */
//
//    User read(int id);
//
//
//    /**
//     * Обновляет клиента с заданным ID,
//     * в соответствии с переданным клиентом
//     * @param user - клиент в соответсвии с которым нужно обновить данные
//     * @param id - id клиента которого нужно обновить
//     * @return - true если данные были обновлены, иначе false
//     */
//
//    boolean update(User user, int id);
//
//
//    /**
//     * Удаляет клиента с заданным ID
//     * @param id - id клиента, которого нужно удалить
//     * @return - true если клиент был удален, иначе false
//     */
//
//    boolean delete(int id);
}
