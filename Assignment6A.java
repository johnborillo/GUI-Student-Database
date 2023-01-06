import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

//WHEN USING PROGRAM, MAKE SURE CHECKBOX IS SELECTED TO MAKE CHANGES
//When making changes, press "Add/Motify" button to save changes

public class Assignment6A extends Application {

    // 0. User interface
    GridPane pane = new GridPane();
    ComboBox<String> comboBox = new ComboBox<>();
    Text idLabel = new Text("ID:");
    Text gpaLabel = new Text("GPA:");

    TextField idTextField = new TextField();
    TextField gpaTextField = new TextField();

    CheckBox editCBox = new CheckBox("Edit");

    Button deleteButton = new Button("Delete Student");
    Button retrieveButton = new Button("Retrieve Database");
    Button addButton = new Button("Add/Modify");
    Button saveButton = new Button("Save Database");

    public void start(Stage stage) throws Exception {
        
/*      File myFile = new File("C:\\Users\\johnb\\Documents\\School\\University\\Sheridan\\Year 1\\Winter Term\\Java 2\\Week13\\Files\\database.bin");
        FileOutputStream outStream = new FileOutputStream(myFile);
        ObjectOutputStream output = new ObjectOutputStream(outStream);

        FileInputStream inStream = new FileInputStream(myFile);
        ObjectInputStream input = new ObjectInputStream(inStream);
*/

        // 1. HashMap information
        HashMap<String, Student> students = new HashMap<>(); // new HashMap called students

        Student james = new Student(45624, 2.8); // instances of students
        Student olivia = new Student(98305, 3.4);
        Student amanda = new Student(29581, 3.7);

        students.put("James", james); // adding students to "students" HashMap
        students.put("Olivia", olivia);
        students.put("Amanda", amanda);

        // 2. Set up the combo box
        comboBox.getItems().addAll(students.keySet());

        // 3. Handle combo box selection
        comboBox.setOnAction(e -> {
            String selection = comboBox.getValue();
            idTextField.setText("" + students.get(selection).id);
            gpaTextField.setText("" + students.get(selection).gpa);

            if (selection == null) {
                return;
            }
            if (students.get(selection) == null) {
                return;
            }
        });

        // 4. Setting up buttons
        // 4.1 Delete button
        deleteButton.setOnAction(e -> {
            String selection = comboBox.getValue();
            students.remove(selection);
            comboBox.getItems().remove(selection);
            idTextField.setText("");
            gpaTextField.setText("");
        });

        // 4.2 Add button
        addButton.setOnAction(E -> {
            String selection = comboBox.getValue();
            Student newStudent = new Student(Integer.valueOf(idTextField.getText()),
                    Double.valueOf(gpaTextField.getText()));
            students.put(String.valueOf(selection), newStudent);
            if (comboBox.getItems().contains(selection) == false) {
                comboBox.getItems().add(selection);
            }
        });
        
        // 4.3 Save button
        saveButton.setOnAction(E -> {
            try {
                File newFile = new File("C:\\Users\\johnb\\Documents\\School\\University\\Sheridan\\Year 1\\Winter Term\\Java 2\\Week13\\Files\\database.bin");
                FileOutputStream outStream = new FileOutputStream(newFile);
                ObjectOutputStream output = new ObjectOutputStream(outStream);
                output.writeObject(students);
                
                output.close();
                outStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        
        // 4.4 Retrieve button
        retrieveButton.setOnAction(E -> {
            HashMap<String, Student> readList;
            try {
                File newFile = new File("C:\\Users\\johnb\\Documents\\School\\University\\Sheridan\\Year 1\\Winter Term\\Java 2\\Week13\\Files\\database.bin");
                FileInputStream inStream = new FileInputStream(newFile);
                ObjectInputStream input = new ObjectInputStream(inStream);
                readList = (HashMap<String, Student>) input.readObject();
                for (Map.Entry<String, Student> entry : readList.entrySet()) {
                    String key = entry.getKey();
                    Student value = entry.getValue();
                    System.out.println(key + " | Student ID: " + value.id + " | Student GPA: " + value.gpa);
                }
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // 5. Setting up checkbox
        editCBox.setOnAction(e -> change(editCBox));

        // 6. Finish the rest of the GUI
        // 6.1 ComboBox placement + width
        pane.add(comboBox, 0, 0);
        comboBox.setPrefWidth(150);

        // 6.2 Text fields / labels placement + width
        pane.add(idLabel, 2, 0);
        pane.add(idTextField, 3, 0);
        pane.add(gpaLabel, 2, 1);
        pane.add(gpaTextField, 3, 1);

        idLabel.prefWidth(50);
        idTextField.setPrefWidth(50);
        gpaLabel.prefWidth(50);
        gpaTextField.setPrefWidth(50);

        // 6.3 Checkbox placement + width
        pane.add(editCBox, 0, 2);
        editCBox.setPrefWidth(150);

        // 6.4 Button placements + width
        pane.add(deleteButton, 0, 4);
        pane.add(retrieveButton, 1, 4);
        pane.add(addButton, 2, 4);
        pane.add(saveButton, 3, 4);

        deleteButton.setPrefWidth(150);
        retrieveButton.setPrefWidth(150);
        addButton.setPrefWidth(150);
        saveButton.setPrefWidth(150);

        // 6.5 The rest of the GUI
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(20);
        pane.setVgap(20);
        Scene scene = new Scene(pane, 700, 200);
        stage.setTitle("Assignment6");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            System.out.println("Program is closing");
            try {
                File newFile = new File("C:\\Users\\johnb\\Documents\\School\\University\\Sheridan\\Year 1\\Winter Term\\Java 2\\Week13\\Files\\database.bin");
                FileOutputStream outStream = new FileOutputStream(newFile);
                ObjectOutputStream output = new ObjectOutputStream(outStream);
                output.writeObject(students);
                
                output.close();
                outStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } 

        });

    }

    public static void main(String[] args) {

        launch();

    }

    // Disables all everything but the comboBox
    private void disableAll() {
        idTextField.setDisable(true);
        gpaTextField.setDisable(true);
        // editCBox.setDisable(true);
        deleteButton.setDisable(true);
        retrieveButton.setDisable(true);
        addButton.setDisable(true);
        saveButton.setDisable(true);
        comboBox.setEditable(false);
    }

    // Enables everything
    private void enableAll() {
        idTextField.setDisable(false);
        gpaTextField.setDisable(false);
        editCBox.setDisable(false);
        deleteButton.setDisable(false);
        retrieveButton.setDisable(false);
        addButton.setDisable(false);
        saveButton.setDisable(false);
        comboBox.setEditable(true);
    }

    // Allows checkbox functionality
    private void change(CheckBox editCBox) {
        if (editCBox.isSelected()) {
            enableAll();
        } else {
            disableAll();
        }
    }
}

// Student class
class Student implements java.io.Serializable {
    int id;
    double gpa;

    public Student(int id, double gpa) {
        this.id = id;
        this.gpa = gpa;

    }
}