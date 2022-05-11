package com.forum.DAO;

import com.forum.domain.Answer;
import com.forum.domain.Question;

import java.sql.SQLException;
import java.util.List;

public interface AnswerDAO {
    void save(Answer answer) throws ClassNotFoundException, SQLException;

    void update(Answer answer) throws ClassNotFoundException, SQLException;

    void delete(int id) throws ClassNotFoundException, SQLException;

    void delete(Answer answer) throws ClassNotFoundException, SQLException;

    List<Answer> findAll() throws ClassNotFoundException, SQLException;

    Answer findOne(int id) throws ClassNotFoundException, SQLException;
}
