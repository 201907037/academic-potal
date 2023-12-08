package com.example.cwp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cwp.domain.User;
import com.example.cwp.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository uRop;
	
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		uRop.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		uRop.deleteById(user.getId());
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		
		return uRop.findById(user.getId()).get();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		User userfind = uRop.findById(user.getId()).get();
		
	}

	@Override
	public String getUserId(int id) {
		Optional<User> coId = uRop.findById(id);
		if(coId.isPresent()) {
			return "사용할 수 없는 아이디";
		}
		return "사용 가능한 아이디";
	}

}
