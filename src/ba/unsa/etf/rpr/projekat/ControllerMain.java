package ba.unsa.etf.rpr.projekat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControllerMain {

    public MenuItem closeMenu;
    public MenuItem removeMenu;
    public MenuItem addMenu;
    public MenuItem menuChange;
    public MenuItem reportMenu;
    public RadioMenuItem radioChildren;
    public RadioMenuItem radioWorker;
    public MenuItem aboutMenu;
    public TableView tableOfPersons;
    public TableColumn nameColumn;
    public TableColumn surenameColumn;
    public TableColumn jmbgColumn;
    public ComboBox groupCombo;
    public Button addPerson;
    public Button removePerson;
    public Button editPerson;
    public Button reportButton;
    public Button startButton;
    public ComboBox institutionCombo;
    public Button addInstitutionButton;
    KindergartenDAO base = new KindergartenDAO();

    public void initialize(){
        radioChildren.setSelected(true);

        institutionCombo.setItems(base.getInstitutions());

        groupCombo.getItems().addAll("Grupa djece dobi od 1 do 2 godine", "Grupa djece dobi od 3 do 5 godina", "Grupa djece sa posbenim potrebama");


        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        surenameColumn.setCellValueFactory(new PropertyValueFactory("surename"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory("jmbg"));



        groupCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    }
                }
            }
        });

        institutionCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    }
                }
            }
        });


        radioChildren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(radioChildren.isSelected()) radioWorker.setSelected(false);
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    }
                }
            }
        });

        radioWorker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(radioWorker.isSelected()) radioChildren.setSelected(false);
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    }
                }
            }
        });


        closeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        aboutMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Aplikacija za vrtić");
                alert.setContentText("Autor: Velid Poško");
                alert.showAndWait();
            }
        });

        addInstitutionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addInstitution.fxml"));
                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Registruj ustanovu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 443, 333));
                stage.showAndWait();
                institutionCombo.setItems(base.getInstitutions());
                institutionCombo.getSelectionModel().selectLast();
            }
        });


        addPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!radioChildren.isSelected() && !radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Nije ništa odabrano u opcijama");

                    alert.showAndWait();
                }
                else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"));
                    loader1.setController(new ControllerAddChild(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj djete");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"));
                    loader1.setController(new ControllerAddEducator(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj odgajatelja");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }
                }
            }
        });

        addMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!radioChildren.isSelected() && !radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Nije ništa odabrano u opcijama");

                    alert.showAndWait();
                }
                else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"));
                    loader1.setController(new ControllerAddChild(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj djete");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"));
                    loader1.setController(new ControllerAddEducator(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj odgajatelja");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }
                }
            }
        });



        editPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!radioChildren.isSelected() && !radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Nije ništa odabrano u opcijama");

                    alert.showAndWait();
                } else if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste odabrali niti jednu stavku.");
                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"));
                    loader1.setController(new ControllerAddChild((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Izmjeni djete");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"));
                    loader1.setController(new ControllerAddEducator((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj odgajatelja");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }
                }

            }
        });

        menuChange.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                if(!radioChildren.isSelected() && !radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Nije ništa odabrano u opcijama");

                    alert.showAndWait();
                } else if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste odabrali niti jednu stavku.");
                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"));
                    loader1.setController(new ControllerAddChild((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Izmjeni djete");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"));
                    loader1.setController(new ControllerAddEducator((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Dodaj odgajatelja");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }
                }

            }
        });



        removePerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste odabrali niti jednu stavku.");

                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("Da li zaista želite izbrisati odabranu stavku?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeChild(((Child)tableOfPersons.getSelectionModel().getSelectedItem()).getId());
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                } else if (radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("Da li zaista želite izbrisati odabranu stavku?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeEducator(((Educator)tableOfPersons.getSelectionModel().getSelectedItem()).getId());
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                }
            }
        });

        removeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste odabrali niti jednu stavku.");

                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("Da li zaista želite izbrisati odabranu stavku?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeChild(((Child)tableOfPersons.getSelectionModel().getSelectedItem()).getId());
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                } else if (radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText("Da li zaista želite izbrisati odabranu stavku?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeEducator(((Educator)tableOfPersons.getSelectionModel().getSelectedItem()).getId());
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 1 do 2 godine"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece dobi od 3 do 5 godina")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals("Grupa djece sa posbenim potrebama")) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!radioWorker.isSelected() || tableOfPersons.getSelectionModel().isEmpty() || institutionCombo.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText("Da biste započeli sa radom odaberite ustanovu, odgajatelja i grupu.");

                    alert.showAndWait();
                } else {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/forWork.fxml"));
                    loader1.setController(new ControllerForWork(groupCombo.getSelectionModel().getSelectedItem().toString(), ((Educator) tableOfPersons.getSelectionModel().getSelectedItem()).getName(), ((Institution) institutionCombo.getSelectionModel().getSelectedItem()).getName(), tableOfPersons.getItems()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Rad");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 778, 475));
                    stage.show();
                    Stage stage1 = (Stage) startButton.getScene().getWindow();
                    stage1.close();
                }
            }
        });
    }
}