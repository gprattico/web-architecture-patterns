package domain.user;

public class UserProxy implements UserInterface{

	private long id;
	private User user =null;
	
	public UserProxy(int id) {
		this.id = id;
	}

	@Override
	public int getVersion(){
		return checkExists().getVersion();
	}

	@Override
	public void setVersion(int version) {
		checkExists().setVersion(version);
	}

	@Override
	public String getUsername() {
		return checkExists().getUsername();
	}

	@Override
	public void setUsername(String username) {
		checkExists().setUsername(username);
	}

	@Override
	public String getPassword() {
		return checkExists().getPassword();
	}

	@Override
	public void setPassword(String password) {
		checkExists().setPassword(password);
	}

	@Override
	public long getId() {
		return checkExists().getId();
	}
	public User checkExists() {
		try {
		if(user ==null) {
			user = UserInputMapper.find(id);
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Database error");
		}
		return user;
	}

}
