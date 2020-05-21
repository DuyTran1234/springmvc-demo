package com.project.dao;

import java.util.List;

import com.project.entity.User;

public interface UserDao {
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User getUserById(int id);
	public User getUserByName(String name, String password);
	public List<User> getAllUser();
}
