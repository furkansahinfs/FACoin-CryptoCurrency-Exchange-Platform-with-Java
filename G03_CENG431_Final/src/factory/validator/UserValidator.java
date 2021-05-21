package factory.validator;

import factory.RandomFactory;
import fileio.repository.DatabaseResult;
import fileio.repository.IRestrictedRepository;
import fileio.repository.UserRepository;
import model.User;

public class UserValidator {

	public static ValidationResult validateUser(String userName, String password) {
		ValidationResult userNameResult = validateUserName(userName);
		ValidationResult passwordResult = validatePassword(password);

		boolean areValidationsOK = userNameResult.isValid && passwordResult.isValid;
		ValidationResult validationResult = new ValidationResult("Not validated.");
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	private static ValidationResult validateUserName(String userName) {
		ValidationResult validationResult = new ValidationResult("Username is already in User Repository");
		IRestrictedRepository<User> userRepository = new UserRepository();
		
		DatabaseResult resultUserName = userRepository.getByName(userName);
		if (resultUserName == null) {
			validationResult = new ValidationResult(true, "");
		}

		
		return validationResult;

	}

	private static ValidationResult validatePassword(String password) {
		boolean isAboveLength = password.length() >= 6;
		return new ValidationResult(isAboveLength, "Password is not valid");
	}

	public static ValidationResult validateUserId(String userId) {
		if(userId==null) {
			return new ValidationResult(false, "Invalidated");
		}
		IRestrictedRepository<User> userRepository = new UserRepository();
		DatabaseResult userIdResult = userRepository.getById(userId);
		if (userIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
		String new_id = RandomFactory.randomId();
		while (userRepository.getById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}

}
