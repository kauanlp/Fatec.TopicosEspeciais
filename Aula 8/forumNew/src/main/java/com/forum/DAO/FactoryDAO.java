package com.forum.DAO;

public class FactoryDAO {

    public static QuestionDAO getQuestionDAO() {

        return new QuestionDAOPostgresSQL();
    }

    public static AnswerDAO getAnswerDAO() {

        return new AnswerDAOPostgresSQL(getQuestionDAO());
    }
}
