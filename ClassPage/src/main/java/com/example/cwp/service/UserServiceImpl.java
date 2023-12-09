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
		Optional<User> findUser = uRop.findById(user.getId());
		if(findUser.isPresent()) {
			return findUser.get();
		}
		else {
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		User userfind = uRop.findById(user.getId()).get();
		userfind.setSub1(user.getSub1());
		userfind.setSub2(user.getSub2());
		userfind.setSub3(user.getSub3());
		userfind.setSub4(user.getSub4());
		userfind.setSub5(user.getSub5());
		uRop.save(userfind);
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
