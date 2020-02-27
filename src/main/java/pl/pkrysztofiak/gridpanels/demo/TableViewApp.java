package pl.pkrysztofiak.gridpanels.demo;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewApp extends Application {

    private final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private final FilteredList<Patient> filteredPatients = new FilteredList<>(patients);
//    private final SortedList<Patient> sortedPatients = new SortedList<>(patients);
    
    private final ObservableList<Patient> subPatients = FXCollections.observableArrayList();
    
    private final Pagination pagination = new Pagination();
    private final Button getPatientsButton = new Button("Get patients");
    private final TextField filterTextField = new TextField();
    private final HBox hBox = new HBox(getPatientsButton, filterTextField);
    
    private final TableColumn<Patient, String> nameColumn = new TableColumn<>("Name");
    private final TableColumn<Patient, String> surnameColumn = new TableColumn<>("Surnname");
    private final TableColumn<Patient, String> ageColumn = new TableColumn<>("Age");
    private final TableColumn<Patient, String> emailColumn = new TableColumn<>("Email");
    private final TableView<Patient> tableView = new TableView<>(subPatients);
    
    private final VBox vBox = new VBox(hBox, tableView, pagination);
    private final Scene scene = new Scene(vBox);
    
    {
        nameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        surnameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
        ageColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAge()));
        emailColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEmail()));
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(surnameColumn);
        tableView.getColumns().add(ageColumn);
        tableView.getColumns().add(emailColumn);
        tableView.setFixedCellSize(24);
        tableView.setPrefHeight(2 * 24 + 26);
        tableView.setMinWidth(200);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        filteredPatients.addListener((ListChangeListener<Patient>) change -> {
            
            pagination.setCurrentPageIndex(0);
            int pageCount = change.getList().size() / 2 + 1;
            pagination.setPageCount(pageCount);
            
            int listSize = change.getList().size();
            int toIndex = listSize < 2 ? listSize : 2;
            subPatients.setAll(change.getList().subList(0, toIndex));
        });
        
        getPatientsButton.setOnAction(event -> patients.setAll(getPatients()));
        
        pagination.currentPageIndexProperty().addListener((ChangeListener<Number>) (observable, oldCurrentPageIndex, currentPageIndex) -> {
            int fromIndex = currentPageIndex.intValue() * 2;
            int toIndex = fromIndex + 2 <= patients.size() ? fromIndex + 2 : patients.size();
            subPatients.setAll(patients.subList(fromIndex, toIndex));
        });
        
        filterTextField.textProperty().addListener((ChangeListener<String>) (observable, oldText, text) -> {
            if ("".equals(text)) {
                filteredPatients.setPredicate(patient -> true);
            } else {
                filteredPatients.setPredicate(patient -> patient.getName().toUpperCase().contains(text.toUpperCase()));
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
        emailColumn.setResizable(false);
        emailColumn.prefWidthProperty().bind(
                tableView.widthProperty()
                .subtract(nameColumn.widthProperty())
                .subtract(surnameColumn.widthProperty())
                .subtract(ageColumn.widthProperty())
                .subtract(2)
                .subtract(14));
    }
    
    private List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John", "Doe", "30", "john.doe@mail.com"));
        patients.add(new Patient("Mark", "Smith", "20", "mark.smith@mail.com"));
        patients.add(new Patient("Susan", "Jacobson",  "53", "susan.jacobson@mail.com"));
        patients.add(new Patient("Jack", "Johnson",  "23", "jack.johnson@mail.com"));
        patients.add(new Patient("Alaister", "Windsor",  "70", "alaister.windsor@mail.com"));
        return patients;
    };
}

class Patient {
    
    private final String name;
    private final String surname;
    private final String age;
    private final String email;
    
    public Patient(String name, String surname, String age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}