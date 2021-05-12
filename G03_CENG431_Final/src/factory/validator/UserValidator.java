package factory.validator;

import factory.RandomFactory;
import fileio.DatabaseResult;
import fileio.UserRepository;

public class UserValidator {

	public static ValidationResult validateUser(String userName, String password, String userId) {
		ValidationResult userNameResult = validateUserName(userName);
		ValidationResult passwordResult = validatePassword(password);
		ValidationResult userIdResult = validateUserId(userId);

		boolean areValidationsOK = userNameResult.isValid && passwordResult.isValid && userIdResult.isValid;
		ValidationResult validationResult = new ValidationResult("Not validated.");
		if (areValidationsOK) {
			validationResult = new ValidationResult(true, "Validated.");
			return validationResult;
		}

		return validationResult;
	}

	private static ValidationResult validateUserName(String userName) {
		ValidationResult validationResult = new ValidationResult("Username is already in User Repository");
		DatabaseResult resultUserName = (new UserRepository()).getUserByName(userName);

		if (resultUserName.getObject() == null) {
			validationResult = new ValidationResult(true, "");
		}
		return validationResult;

	}

	private static ValidationResult validatePassword(String password) {
		boolean isAboveLength = password.length() >= 6;
		return new ValidationResult(isAboveLength, "Password is not valid");
	}

	private static ValidationResult validateUserId(String userId) {
		UserRepository userRepository = new UserRepository();
		DatabaseResult userIdResult = userRepository.getUserById(userId);
		if (userIdResult.getObject() == null) {
			return new ValidationResult(true, "Validated");
		}
		String new_id = RandomFactory.randomId();
		while (userRepository.getUserById(new_id).getObject() != null) {
			new_id = RandomFactory.randomId();
		}
		return new ValidationResult(new_id);
	}

}
