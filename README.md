# GUI-Student-Database
A GUI program created with JavaFX. Program has the ability to add, delete, edit, and export database of students' name, student ID num., and 
GPA. 

Usage:
Steps to use JavaFX in VScode:
1. Create a project (location and project name).
2. Select the explorer tab and select the "JAVA PROJECTS" drop down menu.
3. Then press the " + " on the "Referenced Libraries drop down menu.
4. In the new opened window, head over to the directory of the JavaFX libraries and choose all 
   the .jar files in the "lib" folder in the JavaFX SDK directory/folder. Press enter.
5. You will run into an error. So you must select "Run" at the top bar of VScode and choose "Add Configuration..."
6. Select the explorer tab on the left and in the .vscode folder of your project folder, there should be a new file called launch.json .
7. Select the launch.json file and after the 2nd "request" line, create a new line and paste this code in the new line. :

"vmArgs": "--module-path C:"pathToJavaFXsdk"/javafx-sdk-17.0.2/lib --add-modules javafx.controls,javafx.fxml",

OR (for Mac):

"vmArgs": "--module-path /Users/"pathToJavaFXsdk"/javafx-sdk-19/lib --add-modules javafx.controls,javafx.fxml",

Steps to run JavaFX through command line (Command Prompt):
1. Once you have completed your JavaFX file project and you have your .java file, copy and paste file somewhere else in a
   separate folder for testing.
2. Open the folder with the JavaFX program (file should be .java).
3. Open terminal IN the folder.
4. In the command prompt, we need to complile the .java file. Use this command to compile the .java file along with these arguments:

javac --module-path C:"pathToJavaFXsdk"\javafx-sdk-17.0.2\lib --add-modules javafx.controls,javafx.fxml "projectName".java

OR (for Mac):

javac --module-path /Users/"pathToJavaFXsdk"/javafx-sdk-19/lib --add-modules javafx.controls,javafx.fxml "projectName".java

5. If the file has no compilation/syntax errors, you should have gotten the appropriate classes used within the .java program.
6. Now, we need to run the file. To do so, use this command:

java --module-path C:\"pathToJavaFXsdk"\javafx-sdk-17.0.2\lib --add-modules javafx.controls,javafx.fxml "projectName".java

OR (for Mac):

java --module-path /"pathToJavaFXsdk"/javafx-sdk-19/lib --add-modules javafx.controls,javafx.fxml "projectName".java

7. Your JavaFX program should have ran successfully. 

