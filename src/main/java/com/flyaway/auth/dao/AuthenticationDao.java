package com.flyaway.auth.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.flyaway.db.DBConnection;
import com.flyaway.auth.bean.AuthenticationBean;

public class AuthenticationDao {
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AuthenticationDao dao = new AuthenticationDao();
		
		//System.out.println(dao.getAllUsers().size());
		
		//System.out.println(dao.getUserById(2).getEmail());
		
		//dao.deleteUserById(3);
		
		AuthenticationBean bean = new AuthenticationBean();
		bean.setUserId(2);
		bean.setPassword("secret");
		dao.updateUserById(bean);
		
	}
	
	public List<AuthenticationBean> getAllUsers() throws ClassNotFoundException, SQLException {
		
		List<AuthenticationBean> listOfUsers = new ArrayList<AuthenticationBean>();		
		Statement stmt = DBConnection.getConnection().createStatement();
		String query = "select * from authentications";
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			AuthenticationBean bean = new AuthenticationBean();
			bean.setUserId(rs.getInt(1));
			bean.setAdmin(rs.getBoolean(2));
			bean.setName(rs.getString(3));
			bean.setEmail(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setGender(rs.getString(6));
			listOfUsers.add(bean);
		}
		
		return listOfUsers;
	}
	
	public AuthenticationBean getUserByEmail(String email) throws ClassNotFoundException, SQLException {
		
		AuthenticationBean bean = new AuthenticationBean();		
		Statement stmt = DBConnection.getConnection().createStatement();
		String query = "SELECT * FROM authentications WHERE email=\""+email+"\"";
		ResultSet rs = stmt.executeQuery(query);
		
		if (rs.next()) {
			bean.setUserId(rs.getInt(1));
			bean.setAdmin(rs.getBoolean(2));
			bean.setName(rs.getString(3));
			bean.setEmail(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setGender(rs.getString(6));
		} else {
			bean = null;
		}
		
		return bean;
	}
	
	public int insertUser(AuthenticationBean bean) throws ClassNotFoundException, SQLException {
		
		String query = "insert into authentications values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		
		pstmt.setInt(1, bean.getUserId());
		pstmt.setBoolean(2, bean.isAdmin());
		pstmt.setString(3, bean.getName());
		pstmt.setString(4, bean.getEmail());
		pstmt.setString(5, bean.getPassword());
		pstmt.setString(6, bean.getGender());
		
		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	
	public int deleteUserById(int userId) throws ClassNotFoundException, SQLException {
		
		String query = "delete from authentications where id = ?";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		
		pstmt.setInt(1, userId);
		
		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	
	public int updateUserById(AuthenticationBean bean) throws ClassNotFoundException, SQLException {

		String query = "update authentications set password=? where admin = true";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		pstmt.setString(1, bean.getPassword());

		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	

}
