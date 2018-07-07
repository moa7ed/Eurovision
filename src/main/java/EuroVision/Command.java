package EuroVision;


public class Command {
	enum CommandType {
		LOAD,RESULTS,INVALID;
		static CommandType commandForString(String command) {
			switch(command) {
			   case "load" :
			      return LOAD;
			   case "results" :
			      return RESULTS;
			   default : 
				  return INVALID;
			}
		}
	}
	
	private CommandType commandType;
	private String[] parameters;

	public Command(String commandType, String[] parameters) {
		this.commandType = CommandType.commandForString(commandType);
		this.parameters = parameters;
	}

	private boolean isValidParameters() {
		if(commandType == CommandType.INVALID || parameters.length != 2)
			return false;
		for (String param : parameters) {
			if (param == null || param.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
