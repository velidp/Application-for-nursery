package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class AddChildController {
    public Button okButton;
    public Button cancelButton;
    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox placeCombo;
    public ComboBox institutionCombo;
    public TextField nameParentField;
    public TextField surenameParrentField;
    public TextField jmbgParentField;
    public DatePicker dateParrentField;
    public TextField phoneNumberField;
    public Button addPlaceButton;
    public KindergartenDAO base = new KindergartenDAO();
    public RadioButton specialNeedsNo;
    public RadioButton specialNeedsYes;
    public Person person;

    public AddChildController(Person person) {
        this.person = person;
    }

    public void initialize(){
        placeCombo.setItems(base.getPlaces());
        institutionCombo.setItems(base.getInstitutions());

        if(person != null){
            if(person.isType()) {
                specialNeedsYes.setSelected(true);
                specialNeedsNo.setSelected(false);
            }
            else if (!person.isType()) {
                specialNeedsYes.setSelected(false);
                specialNeedsNo.setSelected(true);
            }
            nameField.setText(person.getName());
            surenameField.setText(person.getSurename());
            jmbgField.setText(person.getJmbg());
            dateField.setValue(person.getDateOfBirth());

            placeCombo.getSelectionModel().select(person.getPlaceOfBirth());

            //ovo nesto ne radi kako treba??
            institutionCombo.getSelectionModel().select(1);

            if(person.isType()) specialNeedsYes.setSelected(true);
            else specialNeedsNo.setSelected(true);

            //podaci za roditelja...
            Person parrent = base.getParentt(person);
            nameParentField.setText(parrent.getName().trim());
            surenameParrentField.setText(parrent.getSurename().trim());
            jmbgParentField.setText(parrent.getJmbg());
            dateParrentField.setValue(parrent.getDateOfBirth());
            phoneNumberField.setText(parrent.getPhoneNumber());
        }


        specialNeedsYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsYes.isSelected()){
                    specialNeedsNo.setSelected(false);
                }
            }
        });

        specialNeedsNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsNo.isSelected()){
                    specialNeedsYes.setSelected(false);
                }
            }
        });



        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        addPlaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addPlace.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj mjesto");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 304, 159));
                int currentNumberOfInstitutions = base.getMaxIdFromPlaces();
                stage.showAndWait();
                placeCombo.setItems(base.getPlaces());
                if(currentNumberOfInstitutions != base.getMaxIdFromPlaces()) {
                    placeCombo.getSelectionModel().selectLast();
                }
            }

        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (person == null) {
                Child child = new Child();
                Parentt parentt = new Parentt();

                child.setName(nameField.getText().trim());
                child.setSurename(surenameField.getText().trim());
                child.setJmbg(jmbgField.getText().trim());
                child.setDateOfBirth(dateField.getValue());
                child.setPlaceOfBirth((Place) placeCombo.getSelectionModel().getSelectedItem());



                child.setInstitution((Institution) institutionCombo.getSelectionModel().getSelectedItem());

                parentt.setName(nameParentField.getText().trim());
                parentt.setSurename(surenameParrentField.getText().trim());
                parentt.setJmbg(jmbgParentField.getText().trim());
                parentt.setDateOfBirth(dateParrentField.getValue());
                parentt.setPhoneNumber(phoneNumberField.getText());
                parentt.setId(base.getMaxIdFromParents());
                child.setParentt(parentt);

                child.setType(specialNeedsYes.isSelected());



                    base.addParrent(parentt);

                    if (child.isType()) {
                        child.setId(base.getMaxIdFromChildrenWithSpecialNeeds());
                        base.addChildWithSpecialNeeds(child);
                    } else if ((child.getDateOfBirth() != null) && (LocalDate.now() != null)) {
                        if (Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 1 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 2) {
                            child.setId(base.getMaxIdFromChildren12());
                            base.addChild12(child);
                        }
                        if (Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 3 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 5) {
                            child.setId(base.getMaxIdFromChildren35());
                            base.addChild35(child);
                        }
                    }


                    File file = new File(child.getName() + String.valueOf(child.getId()) + ".xml");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                } else if (person != null) {

                    if (person.isType()) {
                        base.removeChildrenWithSpecilaNeeds(person);
                    } else if ((person.getDateOfBirth() != null) && (LocalDate.now() != null)) {
                        if (Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 1 && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= 2) {
                            base.removeChildren12(person);
                        }
                        if (Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= 3 && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= 5) {
                            base.removeChildren35(person);
                        }
                    }

                    Child child = new Child();
                    Parentt parentt = new Parentt();

                    child.setName(nameField.getText().trim());
                    child.setSurename(surenameField.getText().trim());
                    child.setJmbg(jmbgField.getText().trim());
                    child.setDateOfBirth(dateField.getValue());
                    child.setPlaceOfBirth((Place) placeCombo.getSelectionModel().getSelectedItem());
                    child.setInstitution((Institution) institutionCombo.getSelectionModel().getSelectedItem());


                    parentt.setName(nameParentField.getText().trim());
                    parentt.setSurename(surenameParrentField.getText().trim());
                    parentt.setJmbg(jmbgParentField.getText().trim());
                    parentt.setDateOfBirth(dateParrentField.getValue());
                    parentt.setPhoneNumber(phoneNumberField.getText());


                    parentt.setId(base.getMaxIdFromParents());
                    base.addParrent(parentt);

                    child.setParentt(parentt);
                    child.setType(specialNeedsYes.isSelected());




                    if (child.isType()) {
                        child.setId(base.getMaxIdFromChildrenWithSpecialNeeds());
                        base.addChildWithSpecialNeeds(child);
                    } else if ((child.getDateOfBirth() != null) && (LocalDate.now() != null)) {
                        if (Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 1 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 2) {
                            child.setId(base.getMaxIdFromChildren12());
                            base.addChild12(child);
                        }
                        if (Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 3 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 5) {
                            child.setId(base.getMaxIdFromChildren35());
                            base.addChild35(child);
                        }
                    }


                    File file = new File(child.getName() + String.valueOf(child.getId()) + ".xml");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
            }


        });
    }
}
