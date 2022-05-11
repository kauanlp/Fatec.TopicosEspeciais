package com.forum.DAO;

import com.forum.domain.Question;
import com.forum.persistence.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOPostgresSQL implements QuestionDAO {

    @Override
    public void save(Question question) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("INSERT INTO _questions(qti_title, qti_description, qti_tag, qti_registerDate) VALUES(?, ?, ?, ?)");
            sql.setString(1, question.getTitle());
            sql.setString(2, question.getDescription());
            sql.setString(3, question.getTag());
            sql.setTimestamp(4, new Timestamp(question.getRegisterDate().getTime()));
            sql.executeUpdate();
        }
    }

    @Override
    public void update(Question question) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("UPDATE _questions SET qti_title=?, qti_description=?, qti_tag=? WHERE qti_id=?");
            sql.setString(1, question.getTitle());
            sql.setString(2, question.getDescription());
            sql.setString(3, question.getTag());
            sql.setInt(4, question.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("DELETE FROM _questions WHERE qti_id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public void delete(Question question) throws ClassNotFoundException, SQLException {
        delete(question.getId());
    }

    @Override
    public List<Question> findAll() throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT qti_id, qti_title, qti_description, qti_tag, qti_registerDate FROM _questions");
            ResultSet resultado = sql.executeQuery();
            List<Question> questions = new ArrayList<>();
            while (resultado.next()) {
                Question question = new Question(resultado.getInt("qti_id"),
                        resultado.getString("qti_title"),
                        resultado.getString("qti_description"),
                        resultado.getString("qti_tag"),
                        new Date(resultado.getTimestamp("qti_registerDate").getTime()));
                questions.add(question);
            }
            return questions;
        }
    }

    @Override
    public Question findOne(int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT qti_id, qti_title, qti_description, qti_tag, qti_registerDate FROM _questions WHERE qti_id=?");
            sql.setInt(1, id);

            Question question = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                question = new Question(resultado.getInt("qti_id"),
                        resultado.getString("qti_title"),
                        resultado.getString("qti_description"),
                        resultado.getString("qti_tag"),
                        resultado.getDate("qti_registerDate"));
            }
            return question;
        }
    }
}
