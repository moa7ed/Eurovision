# EuroVison

It is a gradle java project to support command line interface for votes counting system which load votes for european countries and print these votes per country.

## Technologies Used
- Gradle
- JDK 8
- Eclipse Mars 2
- Mac OSX

## Structure, 
It contains 1 package with 4 files 
  - `CommandType.java`, which contains available types for command
  - `Command.java`, which present a model for command
  - `JSONHelper.java`, which contains all logic related to read write and parse json files
  - `Program.java`, which represents the main file to runCommand and it contains the main function

It also contains the jar executable file to be run as command line application in `jar` folder and called `eurovision.jar`

## Instructions to run it as command line application
- use unix-based machine, `it is a mandatory step as I use /tmp directory to save files`
- run this command `alias eurovision='java -jar <location to eurovision.jar>'`, `eurovision.jar` is under `jar` folder
- start use it, examples:
    - `eurovision load <filePath.json> <year>` or
    - `eurovision results <country> <year>`
  
    
## Testing
  I skipped testing to save time (as it is not menntioned in the requirements), but it should has testing for 
  - test `Command.java` for creation and valdiating the command
  - test JSON Reading, Writing and Validation
  - integration test for `RunCommand` to test most of possibilities, example:
    - valid commands and parameters
    - wrong command
    - wrong parameters
    - load wrong format or wrong data
    - Print empty output, wrong output or very big output
