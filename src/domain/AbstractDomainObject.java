package domain;

public abstract class AbstractDomainObject {

	private long id;
	private int version;
	
	public long getId() {
		return this.id;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

}
