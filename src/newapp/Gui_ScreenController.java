/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 0kl
 */
public class Gui_ScreenController implements Initializable {

    boolean use_in_middle = true, use_in_right = false;
    boolean create_in_middle = false, create_in_right = true;
    boolean insert_in_middle = false, insert_in_right = true;
    boolean view_in_middle = false, view_in_right = true;
    boolean update_in_middle = false, update_in_right = true;
    boolean delete_in_middle = false, delete_in_right = true;
    boolean alter_in_middle = false, alter_in_right = true;
    double speed = 1;
    Connection con;
    String db = "", username = "root", password = "123";
    

    @FXML
    private AnchorPane Main_Panel;
    @FXML
    private AnchorPane Table_Panel;
    @FXML
    private AnchorPane Create_Panel;
    @FXML
    private JFXButton use_button;
    @FXML
    private JFXButton create_button;
    @FXML
    private JFXButton insert_button;
    @FXML
    private JFXButton view_button;
    @FXML
    private JFXButton update_button;
    @FXML
    private JFXButton alter_button;
    @FXML
    private JFXButton delete_button;
    @FXML
    private AnchorPane Use_Panel;
    @FXML
    private AnchorPane Insert_Panel;
    @FXML
    private AnchorPane View_Panel;
    @FXML
    private AnchorPane Update_Panel;
    @FXML
    private AnchorPane Delete_Panel;
    @FXML
    private AnchorPane Alter_Panel;
    @FXML
    private JFXComboBox<String> database_combobox;
    @FXML
    private JFXComboBox<String> table_combobox;
    @FXML
    private JFXButton create_button1;
    @FXML
    private TreeTableView main_table;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> strings = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
            Statement stmt = con.createStatement();
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet catlogue = dbmd.getCatalogs();
            while (catlogue.next()) {
                strings.add(catlogue.getString(1));

            }
        } catch (Exception e) {

            //m.out.println(e.getMessage());
        }
        database_combobox.setItems(FXCollections.observableArrayList(strings));
           
    }

    @FXML
    private void use_clicked(ActionEvent event) {

        if (use_in_right == true) {
            System.out.println("right");
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(0);
            tt.play();
            use_in_right = false;
            use_in_middle = true;
        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (create_in_middle == true) {
            System.out.println("create");
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }

    }

    @FXML
    private void create_clicked(ActionEvent event) {

        if (create_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(-1670);
            tt.play();
            create_in_right = false;
            create_in_middle = true;
            System.out.println(create_in_middle);

        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }
    }

    @FXML
    private void insert_clicked(ActionEvent event) {

        if (insert_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(-1670);
            tt.play();
            insert_in_right = false;
            insert_in_middle = true;
        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (create_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }
    }

    @FXML
    private void view_clicked(ActionEvent event) {

        if (view_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(-1670);
            tt.play();
            view_in_right = false;
            view_in_middle = true;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (create_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }
    }

    @FXML
    private void update_clicked(ActionEvent event) {

        if (update_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(-1670);
            tt.play();
            update_in_right = false;
            update_in_middle = true;
        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (create_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }
    }

    @FXML
    private void alter_clicked(ActionEvent event) {

        if (alter_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(-1670);
            tt.play();
            alter_in_right = false;
            alter_in_middle = true;
        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (delete_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            delete_in_right = true;
            delete_in_middle = false;
        }
        if (create_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Use_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
    }

    @FXML
    private void delete_clicked(ActionEvent event) {

        if (delete_in_right == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(-1670);
            tt.play();
            delete_in_right = false;
            delete_in_middle = true;
        }
        if (view_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), View_Panel);
            tt.setToX(1670);
            tt.play();
            view_in_right = true;
            view_in_middle = false;
        }
        if (insert_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Insert_Panel);
            tt.setToX(1670);
            tt.play();
            insert_in_right = true;
            insert_in_middle = false;
        }
        if (update_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Update_Panel);
            tt.setToX(1670);
            tt.play();
            update_in_right = true;
            update_in_middle = false;
        }
        if (use_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Delete_Panel);
            tt.setToX(1670);
            tt.play();
            use_in_right = true;
            use_in_middle = false;
        }
        if (create_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Create_Panel);
            tt.setToX(1670);
            tt.play();
            create_in_right = true;
            create_in_middle = false;
        }
        if (alter_in_middle == true) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Alter_Panel);
            tt.setToX(1670);
            tt.play();
            alter_in_right = true;
            alter_in_middle = false;
        }
    }

    @FXML
    private void databae_combobox_clicked(ActionEvent event) {
        db = database_combobox.getSelectionModel().getSelectedItem();
        List<String> strings = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("show tables ;");
            while (rs.next()) {
                strings.add(rs.getString(1));

            }
            //  //m.out.println("SHOW TABLE QUERY EXECUTED");
        } catch (Exception e) {

        }
        table_combobox.setItems(FXCollections.observableArrayList(strings));

    }

    @FXML
    private void table_combobox_clicked(ActionEvent event) {
        ObservableList<ObservableList> data;
        
        String table = table_combobox.getSelectionModel().getSelectedItem();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from "+table;
           ResultSet rs = con.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
                col.setOnEditCommit(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                   //////////////////////////////////////////////////////////////////// event.
                    }
                });
                
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param)
                    {                                                                                             
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                        
                    }                    
                });
             
                main_table.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                data.add(row);

            }

            //FINALLY ADDED TO TableView
           //dsfsfwehgkufweiuyfiweyfoyweoyoweytowyogiywoeiygoiwyeogyweoigyowieygoiweygowyeoigyweoigywoieyg               main_table.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }
    }


