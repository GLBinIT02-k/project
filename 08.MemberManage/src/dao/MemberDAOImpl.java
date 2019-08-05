package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Member;

public class MemberDAOImpl extends baseDAO implements MemberDAO {

	private static final String MEMBER_SELECT_BY_ID_SQL = "SELECT * FROM MEMBER WHERE ID = ?";

	@Override
	public Member selectById(String id) {
		Member member = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(MEMBER_SELECT_BY_ID_SQL);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				member = new Member();
				member.setId(resultSet.getString("id"));
				member.setPassword(resultSet.getString("password"));
				member.setName(resultSet.getString("name"));
				member.setGender(resultSet.getString("gender"));
				member.setBirth(resultSet.getString("birth"));
				member.setNo(resultSet.getInt("no"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			closeDBObject(resultSet, preparedStatement, connection);
		}

		return member;
	}

}
