package by.iba.service;

import by.iba.domain.User;

public interface AccountService {


	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	User create(User user);

}
