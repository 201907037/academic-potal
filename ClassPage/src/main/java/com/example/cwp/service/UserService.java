package com.example.cwp.service;

import com.example.cwp.domain.User;

public interface UserService {
	public void insertUser(User user);
	public void deleteUser(User user);
	public User getUser(User user);
	public void updateUser(User user);
	public String getUserId(int id);
}
