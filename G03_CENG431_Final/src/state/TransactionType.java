package state;

public class TransactionType {
	private State state; // one of state objects Completed,InProgress,NotStarted

	/**
	 * The constructor for Product state object.
	 * 
	 * @param state = one of state objects Completed,InProgress,NotStarted
	 */
	public TransactionType(State state) {
		this.state = state;
	}

	/**
	 * The default constructor for Product state object whose state defined as
	 * NotStarted
	 */
	public TransactionType() {
		this.state = new Pending();
	}

	/**
	 * The function updates the state as next state.
	 */
	public void Approve() {
		this.state.nextState(this);
	}

	public String getState() {
		return this.state.getState();
	}

	protected void setState(State state) {
		this.state = state;
	}
}