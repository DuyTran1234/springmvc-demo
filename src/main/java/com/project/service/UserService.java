package com.project.service;

import java.util.List;

import com.project.model.UserDTO;

public interface UserService {
	public void addUser(UserDTO userDTO);
	public void updateUser(UserDTO userDTO);
	public void deleteUser(int id);
	public UserDTO getUserById(int id);
	public UserDTO getUserByName(String name, String password);
	public List<UserDTO> getAllUser();
}
