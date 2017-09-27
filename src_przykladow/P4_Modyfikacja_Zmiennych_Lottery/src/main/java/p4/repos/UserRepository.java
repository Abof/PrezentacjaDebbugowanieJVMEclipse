package p4.repos;

import java.util.Arrays;
import java.util.List;

import p4.user.User;

public class UserRepository {

	public List<User> getUsers() {
		return Arrays.asList( 
				new User(10, true, true, false, false),
				new User(11, true, true, false, true),
				new User(12, true, true, false, false),
				new User(13, true, false, true, true), // <-- Nasz pechowiec!
				new User(14, true, true, false, true));
	};
}
