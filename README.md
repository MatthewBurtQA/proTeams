# proTeams

Full stack application (created in two intensive days due to previous versions just...not functioning as intended :( ). contents are a HTML/CSS/JSCRIPT set up for the basic creation functionality, and a back end with all the functioning REST related service stuff. Few more attempts and this will be a complete full stack functioning application! Huzzah!! 

## Getting Started
One would be advised, that checking the application.properties file will tell you which gate this project is mapped on. Once you have established that port is not in use on your own system, simply run the App within the IDE, or mvn package build it into a fat jar/war depending on how you wish to deploy! 

### Prerequisites
Maven is essential for the functionality of this project, as it handles the required dependencies. You will also need mockito/Junit related stuff, which Maven will handle.

A browser is required for viewing this content. Content has currently been tested to work on Firefox. 
H2 database functioning on your own PC will also help, unless you wish to attempt cloud functionality, which is all of 1/2 more dependencies and a few minor changes! 


### Installing

This section is very hypothetical due to the nature of the project spec. While project spec asked for fat jar, usually with these kind of applications, you would use WAR and have it called from elsewhere, or simply run by hand (find the .jar, run through command line)

```
    Open a command prompt window and go to the directory where you saved the java program (MyFirstJavaProgram.java). Assume it's C:\.

    Type 'javac MyFirstJavaProgram.java' and press enter to compile your code. If there are no errors in your code, the command prompt will take you to the next line (Assumption: The path variable is set).

    Now, type ' java MyFirstJavaProgram ' to run your program.

    You will be able to see the result printed on the window.
```

## Running the tests

Tests are built using JUnit and Mockito! Amazing tools. Best way to run your tests, is to locate the folders holding the tests and run them as grouped by folder. This is the intended testing set up. 
### Break down into end to end tests


```
Unit test: Testing standalone functionality of programmer and team, so that the next step can then be run:
Integration testing: tests the integration of each component into the greater product! amazing.
Selenium: Currently missing, however the best way to test the front end is Selenium. With this in mind, I made sure all relevant fields had ID's to make this part easier for me / you : )
```

## Deployment

Generally I would recommend constructing this as a WAR, then deploying it on a cloud server to run continuously. 
## Built With

* Intellij IDE
* Brackets text editor for HTML/CSS/JSCRIPT
* [Maven](https://maven.apache.org/) - Dependency Management
* Mockito
* JUnit
* Spring
## Authors

* **Matthew Burt/SmilingGecko** - 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to: Nick Johnson, for putting up with my perpetual madness induced by the ever eternal existential stress
* Hat tip to: Tadas, for helping me understand the whole operation better/teaching me how to Spring!
* Hat tip to: The resident Java wizard of the Kingdom of QA, who goes by the name of Jordan Harrison. Helped me fix problems no one could find. TWICE. IN SHORT PERIODS OF TIME. The finness is unreal. 
* Hat tip to: Mr.Salem, my cat, because I work hard so he can live the good life.
* Inspiration: Microsoft teams oddly enough. Wanted to make a team construction full stack application for programmres to use to form teams. Down the line, more functionality to make this desireable
* etc
