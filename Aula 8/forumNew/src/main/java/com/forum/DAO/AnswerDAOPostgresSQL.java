package com.forum.DAO;

import com.forum.domain.Answer;
import com.forum.persistence.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOPostgresSQL implements AnswerDAO {
    QuestionDAO questionDAO;

    public AnswerDAOPostgresSQL(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public void save(Answer answer) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("INSERT INTO _answers(awr_description, awr_qti_id) VALUES(?, ?)");
            sql.setString(1, answer.getDescription());
            sql.setInt(2, answer.getQuestion().getId());

            sql.executeUpdate();
        }
    }

    @Override
    public void update(Answer answer) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("UPDATE _answers SET awr_description=?, awr_qti_id=? WHERE awr_id=?");
            sql.setString(1, answer.getDescription());
            //sql.setInt(2, answer.getQuestion());
            sql.setInt(3, answer.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("DELETE FROM _answers WHERE awr_id=?");
            sql.setInt(1, id);
            sql.executeUpdate();
        }
    }

    @Override
    public void delete(Answer answer) throws ClassNotFoundException, SQLException {
        delete(answer.getId());
    }

    @Override
    public List<Answer> findAll() throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT awr_id, awr_description, awr_qti_id FROM _answers");
            ResultSet resultado = sql.executeQuery();
            List<Answer> answers = new ArrayList<>();
            while (resultado.next()) {
                Answer answer = new Answer(resultado.getInt("awr_id"),
                        resultado.getString("awr_description"),
                        questionDAO.findOne(resultado.getInt("awr_qti_id")));
                answers.add(answer);
            }
            return answers;
        }
    }

    @Override
    public Answer findOne(int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT awr_id, awr_description, awr_qti_id FROM _answers WHERE awr_id=?");
            sql.setInt(1, id);

            Answer answer = null;
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                answer = new Answer(resultado.getInt("awr_id"),
                        resultado.getString("awr_description"),
                        questionDAO.findOne(Integer.parseInt("awr_qti_id")));
            }
            return answer;
        }

    }

    @Override
    public List<Answer> findAllByQuestionId(int id) throws ClassNotFoundException, SQLException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT awr_id, awr_description, awr_qti_id FROM _answers WHERE awr_qti_id=?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            List<Answer> answers = new ArrayList<>();
            while (resultado.next()) {
                Answer answer = new Answer(resultado.getInt("awr_id"),
                        resultado.getString("awr_description"),
                        questionDAO.findOne(resultado.getInt("awr_qti_id")));
                answers.add(answer);
            }
            return answers;
        }
    }
}
