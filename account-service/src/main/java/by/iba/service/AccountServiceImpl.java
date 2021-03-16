package by.iba.service;

import by.iba.client.AuthServiceClient;
import by.iba.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());



	@Autowired
	private AuthServiceClient authClient;


	@Override
	public User create(User user) {


		authClient.createUser(user);

		log.info("new account has been created: " + user);

		return user;
	}


}
