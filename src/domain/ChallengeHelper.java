package domain;

public class ChallengeHelper {

	private long id;
	private long challenger;
	private long challengee;
	private int status;
	
	public ChallengeHelper(long id, long challenger, long challengee, int status){
		
		this.id = id;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}

	public long getChallenger() {
		return challenger;
	}

	public void setChallenger(long challenger) {
		this.challenger = challenger;
	}

	public long getChallengee() {
		return challengee;
	}

	public void setChallengee(long challengee) {
		this.challengee = challengee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

}
