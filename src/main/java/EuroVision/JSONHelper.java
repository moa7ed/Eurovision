package EuroVision;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHelper {

	static final String COUNTRY = "country";
	static final String VOTEDFOR = "votedFor";
	static final String JSONStore = "/tmp/eurovision/";

	public static String loadJSONFile(String inputFilePath, String year) throws IOException, ParseException {
		JSONArray jsonArray = readJSONData(inputFilePath);
		
		if (jsonArray != null) {
			File file = new File(JSONStore + year + ".json");
			if (file.exists()) {
				appendVotesForYear(jsonArray, year);
			} else {
				createNewVotesForYear(jsonArray, year);
			}
			return "load completed!";
		}
		else {
			return "Exception happened while loading";
		}
	}

	static JSONArray readJSONData(String filePath) {
		try {
			JSONParser parser = new JSONParser();
			Reader fileReader = new FileReader(filePath);
			return (JSONArray) parser.parse(fileReader); 
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void createNewVotesForYear(JSONArray jsonArray, String year) {
		try {
			new File(JSONStore).mkdir();
			FileWriter file = new FileWriter(JSONStore + year + ".json");
			file.write(jsonArray.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void appendVotesForYear(JSONArray jsonArray, String year) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(JSONStore + year + ".json", "rw");

		long pos = randomAccessFile.length();
		while (randomAccessFile.length() > 0) {
			pos--;
			randomAccessFile.seek(pos);
			if (randomAccessFile.readByte() == ']') {
				randomAccessFile.seek(pos);
				break;
			}
		}

		String jsonElement = jsonArray.toJSONString();
		if (jsonElement.charAt(0) == '[' && jsonElement.charAt(jsonElement.length() - 1) == ']') {
			jsonElement = jsonElement.substring(1, jsonElement.length() - 1);
		}
		randomAccessFile.writeBytes("," + jsonElement + "]");

		randomAccessFile.close();
	}

}

