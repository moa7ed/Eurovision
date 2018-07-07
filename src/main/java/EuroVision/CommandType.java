package EuroVision;

public enum CommandType {
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
