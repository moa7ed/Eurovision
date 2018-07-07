package EuroVision;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Program {
	
	public void runCommand(String[] args) throws IOException, ParseException {
		if (args.length == 0) {
			System.out.println("Please use \"load\" or \"results\" commands");
			return;
		}
		Command command = new Command(args[0], Arrays.copyOfRange(args, 1, args.length));
		switch(command.commandType) {
		   case LOAD :
			   System.out.println(JSONHelper.loadJSONFile(args[1], args[2]));
			   break;
		   case RESULTS :
			   printVotesFor(args[1], args[2]);
			   break;
			case INVALID:
				System.out.println("Invalid command to run, please run either: \n"
						+ "   eurovision load <filePath> <year>\n"
						+ "or eurovision results <country> <year>");
		}
	}
	
	public static void printVotesFor(String country, String year) {
		JSONArray jsonArray = JSONHelper.readJSONData(JSONHelper.JSONStore + year + ".json");
		if (jsonArray == null || jsonArray.isEmpty()) {
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
		if (votesCounts.isEmpty()) {
			System.out.println("No votes from this country");
			return;
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
