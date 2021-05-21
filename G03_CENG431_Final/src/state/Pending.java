package state;

public class Pending extends State{
/**
	 * The function updates the state of given product state object as InProgress
	 * state object.
	 * 
	 * @param state product state object
	 */
	@Override
	public void nextState(TransactionType state) {
		state.setState(new Approved());
	}

	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "Pending";
	}
}
