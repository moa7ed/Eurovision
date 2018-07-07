package EuroVision;


public class Command {
	
	
	public CommandType commandType;
	private String[] parameters;

	public Command(String commandType, String[] parameters) {
		this.commandType = CommandType.commandForString(commandType);
		this.parameters = parameters;
		checkIfValid();
	}
	
	public boolean isValid() {
		if(commandType == CommandType.INVALID || parameters.length != 2)
			return false;
		for (String param : parameters) {
			if (param == null || param.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	private void checkIfValid() {
		if (!isValid()) {
			commandType = CommandType.INVALID;
		}
	}
}
