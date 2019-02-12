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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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
    public Menu fileMenu;
    public Menu editMenu;
    public Menu optionsMenu;
    public Menu helpMenu;
    public ComboBox languageCombo;

    ResourceBundle bundle = ResourceBundle.getBundle("Trn");

    KindergartenDAO base = new KindergartenDAO();



    public void initialize(){


        radioChildren.setSelected(true);

        institutionCombo.setItems(base.getInstitutions());

        groupCombo.getItems().addAll(bundle.getString("Grupa12"), bundle.getString("Grupa35"), bundle.getString("GrupaSN"));

        languageCombo.getItems().addAll(bundle.getString("Bosanski"), bundle.getString("Engleski"));


        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        surenameColumn.setCellValueFactory(new PropertyValueFactory("surename"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory("jmbg"));

        languageCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(languageCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Bosanski"))){
                    Locale.setDefault(new Locale("bs", "BA"));
                    ResourceBundle bundle = ResourceBundle.getBundle("Trn");
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle(bundle.getString("appName"));
                    primaryStage.setScene(new Scene(root, 800, 600));
                    primaryStage.show();
                    Stage stage = (Stage) languageCombo.getScene().getWindow();
                    stage.close();
                    Locale.setDefault(new Locale("bs", "BA"));
                } else if((languageCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Engleski")))){
                    Locale.setDefault(new Locale("en", "US"));
                    ResourceBundle bundle = ResourceBundle.getBundle("Trn");
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle(bundle.getString("appName"));
                    primaryStage.setScene(new Scene(root, 800, 600));
                    primaryStage.show();
                    Stage stage = (Stage) languageCombo.getScene().getWindow();
                    stage.close();
                    Locale.setDefault(new Locale("en", "US"));
                }
            }
        });


        groupCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    }
                }
            }
        });

        institutionCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                    } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                        tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                    } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                        tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                    } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                alert.setTitle("About");
                alert.setHeaderText(bundle.getString("appName"));
                alert.setContentText(bundle.getString("Autor") + "Velid Poško");
                alert.showAndWait();
            }
        });

        addInstitutionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addInstitution.fxml"),bundle);
                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle(bundle.getString("registerInstitution"));
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
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChose"));

                    alert.showAndWait();
                }
                else if(radioChildren.isSelected()){
                    ResourceBundle bundle = ResourceBundle.getBundle("Trn");
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"), bundle);
                    loader1.setController(new ControllerAddChild(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addChild"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"),bundle);
                    loader1.setController(new ControllerAddEducator(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addEducator"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChose"));

                    alert.showAndWait();
                }
                else if(radioChildren.isSelected()){
                    ResourceBundle bundle = ResourceBundle.getBundle("Trn");
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"), bundle);
                    loader1.setController(new ControllerAddChild(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addChild"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"),bundle);
                    loader1.setController(new ControllerAddEducator(null));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addEducator"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChose"));

                    alert.showAndWait();
                } else if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noItem"));
                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"),bundle);
                    loader1.setController(new ControllerAddChild((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("updateChild"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"),bundle);
                    loader1.setController(new ControllerAddEducator((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addEducator"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChose"));

                    alert.showAndWait();
                } else if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noItem"));
                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addChild.fxml"),bundle);
                    loader1.setController(new ControllerAddChild((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("updateChild"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 470, 379));
                    stage.showAndWait();

                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        }
                    }

                } else if(radioWorker.isSelected()){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addEducator.fxml"),bundle);
                    loader1.setController(new ControllerAddEducator((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addEducator"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 362, 282));
                    stage.showAndWait();
                    if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                        if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                        } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                            tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                        } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                            tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                        } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
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
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noItem"));

                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText(bundle.getString("dialog"));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeChild(((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    }

                } else if (radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText(bundle.getString("dialog"));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeEducator(((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    }

                }
            }
        });

        removeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tableOfPersons.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noItem"));

                    alert.showAndWait();
                } else if(radioChildren.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText(bundle.getString("dialog"));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeChild(((Child)tableOfPersons.getSelectionModel().getSelectedItem()));
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    }

                } else if (radioWorker.isSelected()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("");
                    alert.setContentText(bundle.getString("dialog"));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        base.removeEducator(((Educator)tableOfPersons.getSelectionModel().getSelectedItem()));
                        if(!institutionCombo.getSelectionModel().isEmpty() && !groupCombo.getSelectionModel().isEmpty()) {
                            if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            } else if (radioChildren.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getChildren((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 3));

                            } else if ((radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa12")))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("Grupa35"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 1));
                            } else if (radioWorker.isSelected() && groupCombo.getSelectionModel().getSelectedItem().equals(bundle.getString("GrupaSN"))) {
                                tableOfPersons.setItems(base.getEducators((Institution) institutionCombo.getSelectionModel().getSelectedItem(), 2));
                            }
                        }
                    }

                }
            }
        });

        reportMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new PrintReport().showReport(DriverManager.getConnection("jdbc:sqlite:base.db"));
                } catch (JRException | SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        reportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new PrintReport().showReport(DriverManager.getConnection("jdbc:sqlite:base.db"));
                } catch (JRException | SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!radioWorker.isSelected() || tableOfPersons.getSelectionModel().isEmpty() || institutionCombo.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("startWork"));

                    alert.showAndWait();
                } else {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/forWork.fxml"),bundle);
                    loader1.setController(new ControllerForWork(groupCombo.getSelectionModel().getSelectedItem().toString(), (Educator) tableOfPersons.getSelectionModel().getSelectedItem(), (Institution)institutionCombo.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("work"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 365, 485));
                    stage.setResizable(false);
                    stage.show();
                    Stage stage1 = (Stage) startButton.getScene().getWindow();
                    stage1.close();
                }
            }
        });
    }




}