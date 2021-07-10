package com.sample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sample.vo.User;

@Repository
public class UserDao {
	
	/*
	 * 만들려고 보니까 JdbcTemplate라는 쥑이는게 이미 있는거야
	 * 그래서 그걸 사용
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertUser(User user) {
		String sql = "insert into sample_users " 
					+	"(user_id, user_name, user_password, user_email, user_phone) "
					+	"values "
					+	"(?,?,?,?,?) ";
		jdbcTemplate.update(sql, user.getId(),
								 user.getName(),
								 user.getPassword(),
								 user.getEmail(),
								 user.getPhone());
	}
	public void updateUser(User user) {
		String sql = "update sample_users "
				   + "set "
				   + "user_name 	= ?, "
				   + "user_password = ?, "
				   + "user_email 	= ?, "
				   + "user_phone 	= ?, "
				   + "user_status 	= ?, "
				   + "user_point 	= ? "
				   + "where user_id = ? ";
		jdbcTemplate.update(sql, user.getName(),
								 user.getPassword(),
								 user.getEmail(),
								 user.getPhone(),
								 user.getStatus(),
								 user.getPoint(),
								 user.getId());
	}
	
	//queryForObject는 여러건
	//query는 한건
	public User getUserById(String userId) {
		String sql = "select * "
				   + "from sample_users "
				   + "where user_id = ? ";
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getNString("user_name"));
				user.setPassword(rs.getNString("user_password"));
				user.setEmail(rs.getString("user_email"));
				user.setPhone(rs.getNString("user_phone"));
				user.setStatus(rs.getNString("user_stauts"));
				user.setPoint(rs.getInt("user_point"));
				user.setCreatedDate(rs.getDate("user_created_date"));
			
				return user;
			}
		} , userId);
	}
	
	public List<User> getAllUsers() {
		String sql = "select * "
				   + "from sample_users ";
		
		return jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setEmail(rs.getString("user_email"));
				user.setPhone(rs.getString("user_phone"));
				user.setStatus(rs.getString("user_status"));
				user.setPoint(rs.getInt("user_point"));
				user.setCreatedDate(rs.getDate("user_created_date"));

				return user;
			}
		});
	}
}
