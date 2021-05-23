package service;


import fileio.repository.DatabaseResult;
import fileio.repository.UserRepository;
import model.User;

/**
 * This class responsible to login
 */
public class AuthService {

	private UserRepository userRepository;

	public AuthService() {
		this.userRepository = new UserRepository();
	}

	/**
	 * This function is logged in the user if credentials matches
	 * 
	 * @param userName typed
	 * @param password typed
	 * @return User if credentials matches
	 */
	public User login(String userName, char[] password) {
		DatabaseResult result = userRepository.getUserByName(userName);
		User found = (User) result.getObject();
		if (found != null) { // if user found, look his password
			final String psw = ((User) found).getPassword();
			if (!psw.equals(String.valueOf(password))) { // if password not matches
				found = null;
			}
			else{//this block is for writing all initialized entities for the user to see when he logins
				userRepository.saveChanges(); 
			}
		}
		return found;
	}

}