package EuroVision;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Program {
	
	public void runCommand(String[] args) throws IOException, ParseException {
		Command command = new Command(args[0], Arrays.copyOfRange(args, 1, args.length));
		if (command.isValid()) {
			switch(command.commandType) {
			   case LOAD :
				   System.out.println(JSONHelper.loadJSONFile(args[1], args[2]));
				   break;
			   case RESULTS :
				   printVotesFor(args[1], args[2]);
			}
			
		}
	}
	
	public static void printVotesFor(String country, String year) {
		JSONArray jsonArray = JSONHelper.readJSONData(JSONHelper.JSONStore + year + ".json");
		if (jsonArray == null) {
			System.out.println("No data available for these parameters");
			return;
		}
	    System.out.println(country+" "+ year+" voting results:");
		HashMap<String, Integer> votesCounts = new HashMap<String, Integer>();
		for (Object jsonObject : jsonArray) {
			String countryValue = (String) ((JSONObject) jsonObject).get(JSONHelper.COUNTRY);
		    String voterForValue = (String) ((JSONObject) jsonObject).get(JSONHelper.VOTEDFOR);
		    if(countryValue.equalsIgnoreCase(country)){
		    	votesCounts.put(voterForValue, votesCounts.getOrDefault(voterForValue, 0)+1);
		    }
	    }
		for (String key : votesCounts.keySet()) {
			String printStatement = votesCounts.get(key) + " ";
		    if (votesCounts.get(key) == 1)
		    	printStatement += "point goes to ";
		    else
		    	printStatement += "points go to ";
		    printStatement += key;
		    System.out.println(printStatement);
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		Program program = new Program();
		program.runCommand(args);
	}

}
