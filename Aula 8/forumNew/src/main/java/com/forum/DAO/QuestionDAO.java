package com.forum.DAO;

import com.forum.domain.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    void save(Question question) throws ClassNotFoundException, SQLException;

    void update(Question question) throws ClassNotFoundException, SQLException;

    void delete(int id) throws ClassNotFoundException, SQLException;

    void delete(Question question) throws ClassNotFoundException, SQLException;

    List<Question> findAll() throws ClassNotFoundException, SQLException;

    Question findOne(int id) throws ClassNotFoundException, SQLException;
}
