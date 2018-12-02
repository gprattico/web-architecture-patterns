package domain.user;

import domain.DomainObjectInterface;

public interface UserInterface extends DomainObjectInterface {

	public int getVersion();

	public void setVersion(int version);

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public long getId();
	
}
