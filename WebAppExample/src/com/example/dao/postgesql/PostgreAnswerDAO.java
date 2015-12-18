package com.example.dao.postgesql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.dao.AnswerDAO;
import com.example.dao.exception.DAOException;
import com.example.db.ConnectionHolder;
import com.example.entity.Answer;

public class PostgreAnswerDAO implements AnswerDAO {

	private static final String INSERT_ANSWER = "INSERT INTO answers(name, language) values(?,?);";
	private static final String SELECT_ALL_ANSWERS = "SELECT * FROM answers";

	private static final Logger log = LogManager.getLogger();

	@Override
	public Answer create(Answer answer) throws DAOException {
		log.entry(answer);
		Connection connection = ConnectionHolder.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, answer.getName());
			statement.setString(2, answer.getLanguage());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				answer.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			log.error("SQLException during anser insert query", ex);
			throw new DAOException(ex);
		}
		log.exit(answer);
		return answer;
	}

	@Override
	public Answer get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer update(Answer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Answer> getAll() throws DAOException {
		log.entry();
		Connection con = ConnectionHolder.getConnection();
		try {
			Statement statement = (Statement) con.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_ANSWERS);
			while(resultSet.next()){
				log.debug(resultSet.getString("name"));
			}
		} catch (SQLException ex) {
			log.error("SQLException during anser insert query", ex);
			throw new DAOException(ex);
		}
		log.exit();
		return null;
	}

	@Override
	public Answer getAnswerByUserName(String userName) {
		log.entry(userName);
		return null;
	}

	@Override
	public List<Answer> getAnswerByLanguage(String language) {
		// TODO Auto-generated method stub
		return null;
	}
}
