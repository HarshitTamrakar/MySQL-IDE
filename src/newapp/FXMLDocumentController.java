package newapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Harshit
 */
public class FXMLDocumentController implements Initializable {

    public static boolean check(String[] arr, String s) {
        for (String x : arr) {
            if (s.contains(x)) {
                return true;
            }
        }
        return false;
    }

    private Label label;
    @FXML
    private TextArea OUTPUT_TextArea;
    @FXML
    private TextField INPUT_TextArea;
    @FXML
    private ListView<String> AUTOCOMPLETE_ListView;
    @FXML
    private ListView<String> SUGGESTIONS_ListView;
    @FXML
    private Label type_of_data;
    @FXML
    private Label Syntax;
    @FXML
    private Label type_of_data1;
    String db = "", username = "root", password = "123";
    Connection con;
    int i = -1;
    ResultSet rs;
    boolean isDatabase = false, isTable = false;
    int count = 1;
    String lastword = null;
    int items_jcombo = 0;
    String history[] = new String[100];
    int index_numerb_of_history = 0;
    int index_for_in = 0;
    @FXML
    private TextField Search_query_tf;
    @FXML
    private ListView<String> searched_query;
    @FXML
    private Button Save_button;
    @FXML
    private JFXHamburger ham1;
    @FXML
    private AnchorPane customization_panel;
    @FXML
    private Label press_control;
    @FXML
    private Label press_down;
    double speed = 0.5;
    int speed2 = 3;
    @FXML
    private JFXButton Clear_button;
    @FXML
    private AnchorPane basepane;
    @FXML
    private Label close_button;
    @FXML
    private JFXSlider text_size_slider;
    @FXML
    private Label text_size_label;
    @FXML
    private JFXColorPicker ChangeColor;
    @FXML
    private ImageView Profile_pic;
    @FXML
    private JFXButton reset_color_button;
    @FXML
    private JFXButton reset_text_button;
    @FXML
    private AnchorPane web_browser_pane;
    @FXML
    private WebView webview_browser;
    @FXML
    private ComboBox<String> size_combo_box;
    @FXML
    private JFXButton Close_button_browser;
    @FXML
    private Label move_label;
    double x, y;
    @FXML
    private JFXButton browser_button;
    HamburgerBackArrowBasicTransition tt;
    @FXML
    private ComboBox<String> size_combobox;
    @FXML
    private Label logo_noglow;
    @FXML
    private Label logo_glow;
    int sp = 2;
    @FXML
    private JFXButton Take_photo;
    @FXML
    private Label name_tf;
    @FXML
    private Label email_tf;
    @FXML
    private AnchorPane user_check_pane;
    @FXML
    private JFXTextField username_mysql_tf;
    @FXML
    private JFXPasswordField password_mysql_tf;
    @FXML
    private JFXButton check_mysql_tf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name_tf.setText(name_tf.getText() + " " + Interface.username);
        email_tf.setText(email_tf.getText() + " " + Interface.email);

        ham1.setDisable(true);
        Clear_button.setDisable(true);
        INPUT_TextArea.setDisable(true);
        Search_query_tf.setDisable(true);
//        File file = new File("firstCapture.jpg");
//        Image image = new Image(file.toURI().toString());
//        Profile_pic.setImage(image);
//        Profile_pic.setEffect(new Glow(1.0));

        FadeTransition glow = new FadeTransition(Duration.seconds(sp), logo_glow);
        glow.setToValue(1);
        FadeTransition noglow = new FadeTransition(Duration.seconds(sp), logo_noglow);
        noglow.setToValue(0);
        FadeTransition glow_not = new FadeTransition(Duration.seconds(sp), logo_glow);
        glow_not.setToValue(0);
        FadeTransition noglow_not = new FadeTransition(Duration.seconds(sp), logo_noglow);
        noglow_not.setToValue(1);
        ParallelTransition not_glowing = new ParallelTransition(glow_not, noglow_not);
        ParallelTransition glowing = new ParallelTransition(glow, noglow);
        glowing.play();
        glowing.setOnFinished((g) -> {
            not_glowing.play();
            not_glowing.setOnFinished((o) -> {
                glowing.play();
            });

        });

        web_browser_pane.setVisible(false);

        ObservableList<String> combo = FXCollections.observableArrayList("Introduction", "Commands", "Tutorials", "Practice Questions", "Doubts", "Youtube");
        size_combo_box.setItems(combo);
        ObservableList<String> size_combo = FXCollections.observableArrayList("Small", "Medium", "Large");
        size_combobox.setItems(size_combo);
        ChangeColor.setValue(Color.BLACK);
        INPUT_TextArea.setStyle("-fx-text-inner-color: white");
        Search_query_tf.setStyle("-fx-text-inner-color: white");
        OUTPUT_TextArea.setStyle("-fx-background-color: black;");
        type_of_data1.setVisible(false);
        press_control.setVisible(false);
        press_down.setVisible(false);
        type_of_data.setVisible(false);
        INPUT_TextArea.requestFocus();

        tt = new HamburgerBackArrowBasicTransition(ham1);
        tt.setRate(1);
        ham1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)
                -> {
            if (tt.getRate() == -1) {
                tt.play();
                tt.setRate(tt.getRate() * -1);
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), basepane);
                tt1.setToX(370);
                tt1.play();

            } else if (tt.getRate() == 1) {

                tt.setRate(tt.getRate() * -1);
                tt.play();
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), basepane);
                tt1.setToX(0);
                tt1.play();

            }
        });

        FadeTransition ff2 = new FadeTransition(Duration.seconds(speed2), type_of_data1);
        ff2.setFromValue(0);
        ff2.setToValue(1);
        FadeTransition ff12 = new FadeTransition(Duration.seconds(speed2), press_control);
        ff12.setFromValue(1);
        ff12.setToValue(0);
        ParallelTransition p2 = new ParallelTransition(ff2, ff12);

        FadeTransition ff = new FadeTransition(Duration.seconds(speed2), type_of_data1);
        ff.setFromValue(1);
        ff.setToValue(0);
        FadeTransition ff1 = new FadeTransition(Duration.seconds(speed2), press_control);
        ff1.setFromValue(0);
        ff1.setToValue(1);
        ParallelTransition p = new ParallelTransition(ff, ff1);
        p.play();
        p.setOnFinished((e) -> {
            p2.play();
            p2.setOnFinished((f) -> {
                p.play();
            });
        });

        FadeTransition ff22 = new FadeTransition(Duration.seconds(3), type_of_data);
        ff22.setFromValue(0);
        ff22.setToValue(1);
        FadeTransition ff122 = new FadeTransition(Duration.seconds(3), press_down);
        ff122.setFromValue(1);
        ff122.setToValue(0);
        ParallelTransition p22 = new ParallelTransition(ff22, ff122);

        FadeTransition ff3 = new FadeTransition(Duration.seconds(3), type_of_data);
        ff3.setFromValue(1);
        ff3.setToValue(0);
        FadeTransition ff13 = new FadeTransition(Duration.seconds(3), press_down);
        ff13.setFromValue(0);
        ff13.setToValue(1);
        ParallelTransition p3 = new ParallelTransition(ff3, ff13);
        p3.play();
        p3.setOnFinished((ee) -> {
            p22.play();
            p22.setOnFinished((fdf) -> {
                p3.play();
            });
        });

        String drop_if_exsist = "DROP TABLE IF EXISTS reserved_words;";
        String create_table = "CREATE TABLE reserved_words (keyword VARCHAR(30) NOT NULL PRIMARY KEY);";
        String insert = "INSERT INTO reserved_words VALUES('ACCESSIBLE'),('ADD'),('ALL'),('ALTER'),('ANALYZE'),('AND'),('AS'),('ASC')"
                + ",('ASENSITIVE'),('BEFORE'),('BETWEEN'),('BIGINT'),('BINARY'),('BLOB'),('BOTH'),('BY'),('CALL'),('CASCADE'),('CASE')"
                + ",('CHANGE'),('CHAR'),('CHARACTER'),('CHECK'),('COLLATE'),('COLUMN'),('CONDITION'),('CONSTRAINT'),('CONTINUE'),('CONVERT')"
                + ",('CREATE'),('CROSS'),('CURRENT_DATE'),('CURRENT_TIME'),('CURRENT_TIMESTAMP'),('CURRENT_USER'),('CURSOR'),('DATABASE')"
                + ",('DATABASES'),('DAY_HOUR'),('DAY_MICROSECOND'),('DAY_MINUTE'),('DAY_SECOND'),('DEC'),('DECIMAL'),('DECLARE'),('DEFAULT')"
                + ",('DELAYED'),('DELETE'),('DESC'),('DESCRIBE'),('DETERMINISTIC'),('DISTINCT'),('DISTINCTROW'),('DIV'),('DOUBLE'),('DROP')"
                + ",('DUAL'),('EACH'),('ELSE'),('ELSEIF'),('ENCLOSED'),('ESCAPED'),('EXISTS'),('EXIT'),('EXPLAIN'),('FALSE'),('FETCH')"
                + ",('FLOAT'),('FLOAT4'),('FLOAT8'),('FOR'),('FORCE'),('FOREIGN'),('FROM'),('FULLTEXT'),('GET'),('GRANT'),('GROUP')"
                + ",('HAVING'),('HIGH_PRIORITY'),('HOUR_MICROSECOND'),('HOUR_MINUTE'),('HOUR_SECOND'),('IF'),('IGNORE'),('IN'),('INDEX')"
                + ",('INFILE'),('INNER'),('INOUT'),('INSENSITIVE'),('INSERT'),('INT'),('INT1'),('INT2'),('INT3'),('INT4'),('INT8')"
                + ",('INTEGER'),('INTERVAL'),('INTO'),('IO_AFTER_GTIDS'),('IO_BEFORE_GTIDS'),('IS'),('ITERATE'),('JOIN'),('KEY')"
                + ",('KEYS'),('KILL'),('LEADING'),('LEAVE'),('LEFT'),('LIKE'),('LIMIT'),('LINEAR'),('LINES'),('LOAD'),('LOCALTIME')"
                + ",('LOCALTIMESTAMP'),('LOCK'),('LONG'),('LONGBLOB'),('LONGTEXT'),('LOOP'),('LOW_PRIORITY'),('MASTER_BIND')"
                + ",('MASTER_SSL_VERIFY_SERVER_CERT'),('MATCH'),('MAXVALUE'),('MEDIUMBLOB'),('MEDIUMINT'),('MEDIUMTEXT'),('MIDDLEINT')"
                + ",('MINUTE_MICROSECOND'),('MINUTE_SECOND'),('MOD'),('MODIFIES'),('NATURAL'),('NOT'),('NO_WRITE_TO_BINLOG'),('NULL')"
                + ",('NUMERIC'),('ON'),('OPTIMIZE'),('OPTION'),('OPTIONALLY'),('OR'),('ORDER'),('OUT'),('OUTER'),('OUTFILE'),('PARTITION')"
                + ",('PRECISION'),('PRIMARY'),('PROCEDURE'),('PURGE'),('RANGE'),('READ'),('READS'),('READ_WRITE'),('REAL'),('REFERENCES')"
                + ",('REGEXP'),('RELEASE'),('RENAME'),('REPEAT'),('REPLACE'),('REQUIRE'),('RESIGNAL'),('RESTRICT'),('RETURN'),('REVOKE')"
                + ",('RIGHT'),('RLIKE'),('SCHEMA'),('SCHEMAS'),('SECOND_MICROSECOND'),('SELECT'),('SENSITIVE'),('SEPARATOR'),('SET')"
                + ",('SHOW'),('SIGNAL'),('SMALLINT'),('SPATIAL'),('SPECIFIC'),('SQL'),('SQLEXCEPTION'),('SQLSTATE'),('SQLWARNING')"
                + ",('SQL_BIG_RESULT'),('SQL_CALC_FOUND_ROWS'),('SQL_SMALL_RESULT'),('SSL'),('STARTING'),('STRAIGHT_JOIN'),('TABLE')"
                + ",('TERMINATED'),('THEN'),('TINYBLOB'),('TINYINT'),('TINYTEXT'),('TO'),('TRAILING'),('TRIGGER'),('TRUE'),('UNDO')"
                + ",('UNION'),('UNIQUE'),('UNLOCK'),('UNSIGNED'),('UPDATE'),('USAGE'),('USE'),('USING'),('UTC_DATE'),('UTC_TIME')"
                + ",('UTC_TIMESTAMP'),('VALUES'),('VARBINARY'),('VARCHAR'),('VARCHARACTER'),('VARYING'),('WHEN'),('WHERE'),('WHILE')"
                + ",('WITH'),('WRITE'),('XOR'),('YEAR_MONTH'),('ZEROFILL');";

        String drop_syntax = "DROP TABLE IF EXISTS SYNTAX;";
        String create_table_syntax = "CREATE TABLE SYNTAX (TYPE VARCHAR(40) , SYNTAX VARCHAR(150));";
        String insert_syntax = "INSERT INTO SYNTAX VALUES('SELECT','SELECT [COLUMN_NAME] FROM [TABLE_NAME] WHERE [CONDITION]')"
                + ",('INSERT','INSERT INTO [TABLE_NAME] VALUES(VALUE1 , VALUE2 , ...)')"
                + ",('UPDATE','UPDATE [TABLE_NAME] SET [COLUMN_NAME]=[VALUE] WHERE [CONDITION]')"
                + ",('DELETE','DELETE FROM [TABLE_NAME] WHERE [CONDITION]')"
                + ",('CREATE','CREATE TABLE [TABLE_NAME](COUMN_NAME1 COLUMN1_TYPE (SIZE)...')"
                + ",('DROP','DROP DATABASE [DATABASE_NAME]')"
                + ",('ALTER','ALTER TABLE [TABLE_NAME] ADD/DROP/MODIFY [COLUMN_NAME] [COULMN_TYPE] ([SIZE])')"
                + ",('SHOW','SHOW TABLES / DATABASES')"
                + ",('USE','USE [DATABASE_NAME]')"
                + ",('insert_row','INSERT INTO [TABLE_NAME] VALUES (VALUE1 , VALUE2 , ......)')"
                + ",('remove_row','DELETE FROM [TABLE_NAME] WHERE [CONDITION]')"
                + ",('update_row','UPDATE [TABLE_NAME] SET [COLUMN_NAME] = [VALUE] WHERE [CONDITION]')"
                + ",('add_column','ALTER TABLE [TABLE_NAME] ADD [COLUMN_NAME] [TYPE] [SIZE]')"
                + ",('remove_column','ALTER TABLE [TABLE_NAME] DROP [COLUMN_NAME]')"
                + ",('modify_column','ALTER TABLE [TABLE_NAME] MODIFY [COLUMN_NAME] [NEW_DATA_TYPE]')"
                + ",('rename_column','ALTER TABLE [TABLE_NAME] RENAME COLUMN [OLD_COLUMN_NAME] TO [ NEW_COLUMN_NAME]')"
                + ",('rename_table','ALTER TABLE [TABLE_NAME] RENAME TO [NEW_TABLE_NAME]')"
                + ",('copy_table','CREATE TABLE [NEW_TABLE_NAME] AS SELECT * FROM [OLD_TABLE]')"
                + ",('update_row','UPDATE [TABLE_NAME] SET [COLMUN_NAME] = [VALUE] WHERE [CONDITION]');";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(drop_if_exsist);
            stmt.executeUpdate(create_table);
            stmt.executeUpdate(insert);
            stmt.executeUpdate(drop_syntax);
            stmt.executeUpdate(create_table_syntax);
            stmt.executeUpdate(insert_syntax);
        } catch (Exception e) {
            OUTPUT_TextArea.appendText("\n");
            OUTPUT_TextArea.appendText(e.getMessage());
        }
        //                                                                                            AUTOCOMPLETE_ListView.setModel(ddm);

    }

    @FXML
    private void Input_key_pressed(KeyEvent event) {
        AUTOCOMPLETE_ListView.setVisible(true);
        type_of_data.setVisible(true);
        press_down.setVisible(true);
        String text = INPUT_TextArea.getText();
        FadeTransition ff = new FadeTransition(Duration.seconds(speed), AUTOCOMPLETE_ListView);
        ff.setToValue(1);
        ff.play();

        String input_text = INPUT_TextArea.getText();
        if (event.getCode() == KeyCode.UP) {
            if (index_for_in > index_numerb_of_history) {
                String hist = history[index_numerb_of_history];
                index_numerb_of_history++;
                INPUT_TextArea.setText(hist);
            } else {
            }
        }

        if (event.getCode() == KeyCode.SPACE) {
            String first[] = input_text.split("\\s+");
            String first_wo = first[0];
            FadeTransition ff4 = new FadeTransition(Duration.seconds(speed), Syntax);
            ff4.setToValue(1);
            ff4.play();
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                Statement stmt = con.createStatement();
                String query = "select syntax from syntax where type = '" + first_wo + "';";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Syntax.setText(rs.getString(1));
                }
                con.close();
            } catch (Exception e) {
                OUTPUT_TextArea.appendText("\n");
                OUTPUT_TextArea.appendText(e.getMessage());
            }
        }
        if (event.getCode() == KeyCode.DOWN) {
            AUTOCOMPLETE_ListView.requestFocus();
            AUTOCOMPLETE_ListView.getSelectionModel().select(0);
        }

        if (event.getCode() == KeyCode.CONTROL) {
            SUGGESTIONS_ListView.requestFocus();
            SUGGESTIONS_ListView.getSelectionModel().select(0);
        }

        ObservableList<String> items = null;
        if (event.getCode() == KeyCode.PERIOD) {
            type_of_data1.setVisible(true);
            press_control.setVisible(true);
            SUGGESTIONS_ListView.setVisible(true);
            FadeTransition ff3 = new FadeTransition(Duration.seconds(speed), type_of_data1);
            FadeTransition ff4 = new FadeTransition(Duration.seconds(speed), press_control);
            FadeTransition ff5 = new FadeTransition(Duration.seconds(speed), SUGGESTIONS_ListView);
            ff3.setToValue(1);
            ff4.setToValue(1);
            ff5.setToValue(1);
            ParallelTransition po = new ParallelTransition(ff3, ff4, ff5);
            po.play();
            String keyword = text.substring(0, text.length());
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("show databases;");
                List<String> rowvalues = new ArrayList<String>();
                while (rs.next()) {
                    rowvalues.add(rs.getString(1));
                }
                String[] databaseArray = new String[rowvalues.size()];
                databaseArray = rowvalues.toArray(databaseArray);
                int count = 0;
                while (count < rowvalues.size()) {
                    if (keyword.equals(databaseArray[count])) {
//                        dd.addElement("createTable()");
//                        dd.addElement("removeDatabase()");
//                        dd.addElement("showTables()");
//                        dd.addElement("removeTable()");
                        isDatabase = true;
                        items = FXCollections.observableArrayList("createTable()", "removeDatabase()", "showTables()", "removeTable()");
                    }
//                    }else{
//                    items=FXCollections.observableArrayList("Table or database","Doesnot match");
//                    }
                    count++;

                }
                //   //m.out.println("PERIOD METHOD EXECUTED");
                con.close();
                SUGGESTIONS_ListView.setItems(items);
            } catch (Exception e) {
                OUTPUT_TextArea.appendText("\n");
                //m.out.println(e);
            }
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("show tables;");
                List<String> rowvalues = new ArrayList<String>();
                while (rs.next()) {
                    rowvalues.add(rs.getString(1));
                }
                String[] databaseArray = new String[rowvalues.size()];
                databaseArray = rowvalues.toArray(databaseArray);
                int count = 0;
                while (count < rowvalues.size()) {
                    if (keyword.equals(databaseArray[count])) {
//                        dd.addElement("insertRow()");
//                        dd.addElement("removeRow()");
//                        dd.addElement("updateRow()");
//                        dd.addElement("addColumn()");
//                        dd.addElement("removeColumn()");
//                        dd.addElement("modifyColumn()");
//                        dd.addElement("renameColumn()");
//                        dd.addElement("updateRow()");
//                        dd.addElement("showData()");
//                        dd.addElement("copyTable()");
//                        dd.addElement("renameTable()");
//                        isDatabase = true;
                        items = FXCollections.observableArrayList("insertRow()", "removeRow()", "updateRow()", "addColumn()", "removeColumn()", "modifyColumn()", "renameColumn()",
                                "updateRow()", "showData()", "copyTable()", "renameTable()");
                    }
//                    } else {
//                        items = FXCollections.observableArrayList("Table or database", "Doesnot match");
//                    }
                    count++;
                }
                //   //m.out.println("suggestion PERIOD METHOD EXECUTED");
                con.close();
                SUGGESTIONS_ListView.setItems(items);
            } catch (Exception e) {
                OUTPUT_TextArea.appendText("\n");
                OUTPUT_TextArea.setText(e.getMessage());
            }

            //////----------------------------------------------------------------------------------------------RECONGNIZES DATABASE AND SHOWS METHODS ACCRODINGLY
            int count = 0;

        }

        int pos = text.indexOf(".");
        String Query = INPUT_TextArea.getText();
        int len = Query.length();
        if (event.getCode() == KeyCode.BACK_SPACE && Query.isEmpty() == false) {
            if (Query.substring(len - 1, len).equals(".")) {
                SUGGESTIONS_ListView.getItems().clear();
            } else {
//                AUTOCOMPLETE_ListView.setVisible(false);
                type_of_data.setVisible(false);
                Syntax.setText("");
//                Syntax.setVisible(false);
                type_of_data1.setVisible(false);
                press_control.setVisible(false);
                press_down.setVisible(false);

                SUGGESTIONS_ListView.setVisible(false);
                FadeTransition ff6 = new FadeTransition(Duration.seconds(speed), AUTOCOMPLETE_ListView);
                FadeTransition ff7 = new FadeTransition(Duration.seconds(speed), type_of_data);
                FadeTransition ff8 = new FadeTransition(Duration.seconds(speed), Syntax);
                FadeTransition ff9 = new FadeTransition(Duration.seconds(speed), type_of_data1);
                FadeTransition ff10 = new FadeTransition(Duration.seconds(speed), press_control);
                FadeTransition ff11 = new FadeTransition(Duration.seconds(speed), press_down);
                ff6.setToValue(0);
                ff7.setToValue(0);
                ff8.setToValue(0);
                ff9.setToValue(0);
                ff10.setToValue(0);
                ff11.setToValue(0);
                ParallelTransition ppi = new ParallelTransition(ff6, ff7, ff8, ff9, ff10, ff11);
                ppi.play();
            }
        }

        if (event.getCode() == KeyCode.ENTER)//----------------------------------------ON PRESSING ENTER IN COMMAND WRITTING
        {
            String timeStamp = new SimpleDateFormat("(dd/MM/yy  HH:mm:ss)").format(Calendar.getInstance().getTime());

            Syntax.setText("");
            OUTPUT_TextArea.appendText("\n");
            OUTPUT_TextArea.appendText("mysql> ");
            String query = INPUT_TextArea.getText();
            String arr[] = query.split(" ", 3);
            String first_word = arr[0].trim();
            String second_word = arr[1].trim();
            String lower = first_word.toLowerCase();
            if (lower.equals("use")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String checking_use = query.substring(0, 3);
                    if (checking_use.equals("use") == true) {
                        stmt.executeQuery(query);
                        OUTPUT_TextArea.appendText(query + " " + timeStamp);
                        int pos_of_semicolon = text.indexOf(";");
                        String database_name_with_space = text.substring(3, pos_of_semicolon);
                        String database_name = database_name_with_space.trim();
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText("Database changes to " + database_name);
                        history[index_for_in] = query;
                        index_for_in++;
                        db = database_name;
                        // //m.out.println("USE QUERY EXECUTED");
                        con.close();
                    }
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } ///////////////////-----------------------------CHECKED FOR DATBASE NAME CHANGED
            else if (lower.equals("create")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    int pos_of_semicolon = text.indexOf(";");
                    String tablename;
                    OUTPUT_TextArea.appendText("\n");
                    String ar[] = text.split("\\s+");
                    OUTPUT_TextArea.appendText("" + ar[1] + " named " + ar[2] + " created successfully !");
                    // //m.out.println("CREATE QUERY EXECUTED");
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();

                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (lower.equals("select")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);

                    if (!rs.isBeforeFirst()) {
                        OUTPUT_TextArea.appendText(query + " " + timeStamp);
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText("No data ");
                    } else {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int col_count = rsmd.getColumnCount();
                        int row_count = 0;

//-------------------------------------------------------------------------------------
                        Table_generator tg = new Table_generator();
                        tg.table(rs, OUTPUT_TextArea);
                        // //m.out.println("5");
                        ////m.out.println("SELECT QUERY EXECUTED");
                        history[index_for_in] = query;
                        index_for_in++;
                        con.close();
                    }
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("show") && text.contains("databases")) {
                try {

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("show databases ;");
                    OUTPUT_TextArea.appendText("show databases ;" + " " + timeStamp);
                    Table_generator tg = new Table_generator();
                    tg.table(rs, OUTPUT_TextArea);
                    //  //m.out.println("SHOW DATABASE QUERY EXECUTED");
                    history[index_for_in] = "show databases ;";
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("show") && text.contains("tables")) {
                try {

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("show tables;");
                    OUTPUT_TextArea.appendText("show tables ;" + " " + timeStamp);
                    Table_generator tg = new Table_generator();
                    tg.table(rs, OUTPUT_TextArea);
                    // //m.out.println("SHOW TABLE QUERY EXECUTED");
                    history[index_for_in] = "show tables ;";
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("insert")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    //  //m.out.println("INSERT QUERY EXECUTED");
                    String table_name_temp[] = query.split("\\s+");
                    String table_name = table_name_temp[2];
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText("Data insertd into " + table_name + " successfully !");
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("update")) {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    // //m.out.println("UPDATE QUERY EXECUTED");
                    String table_name_temp[] = query.split("\\s+");
                    String table_name = table_name_temp[1];
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText("Data updated in " + table_name + " successfully !");
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("delete")) {

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    // //m.out.println("DELETE QUERY EXECUTED");
                    String table_name_temp[] = query.split("\\s+");
                    String table_name = table_name_temp[2];
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText("Data deleted from " + table_name + " successfully !");
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("drop")) {
                int dialogButton = JOptionPane.YES_OPTION;
                String table_name_temp[] = query.split("\\s+");
                String table_name = table_name_temp[2];
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you really want to delete " + table_name, "Warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // Saving code here
                    if (input_text.contains("database")) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(query);
                            OUTPUT_TextArea.appendText(query + " " + timeStamp);

                            //  //m.out.println("DROP QUERY EXECUTED");
                            OUTPUT_TextArea.appendText("\n");
                            OUTPUT_TextArea.appendText(table_name + " droped successfully !");
                            OUTPUT_TextArea.appendText("\nDatabase changes to mysql (default)");
                            history[index_for_in] = query;
                            db = "mysql";
                            index_for_in++;
                            con.close();
                        } catch (Exception e) {
                            OUTPUT_TextArea.appendText(query + " " + timeStamp);
                            OUTPUT_TextArea.appendText("\n");
                            OUTPUT_TextArea.appendText(e.getMessage());
                        }
                    } else {
                        try {
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, password);
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(query);
                            OUTPUT_TextArea.appendText(query + " " + timeStamp);

                            //  //m.out.println("DROP QUERY EXECUTED");
                            OUTPUT_TextArea.appendText("\n");
                            OUTPUT_TextArea.appendText(table_name + " droped successfully !");
                            history[index_for_in] = query;
                            index_for_in++;
                            con.close();
                        } catch (Exception e) {
                            OUTPUT_TextArea.appendText(query + " " + timeStamp);
                            OUTPUT_TextArea.appendText("\n");
                            OUTPUT_TextArea.appendText(e.getMessage());
                        }
                    }
                } else if (dialogResult == JOptionPane.NO_OPTION) {
                    INPUT_TextArea.setText(" ");
                }
            } else if (first_word.equals("alter")) {

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    //  //m.out.println("ALTER QUERY EXECUTED");
                    String table_name_temp[] = query.split("\\s+");
                    String table_name = table_name_temp[2];
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText("Table " + table_name + " altered successfully !");
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (first_word.equals("describe") || first_word.equals("desc")) {
//////DESCRIBE QUERY NOT FUNCTIONAL
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);

                    if (!rs.isBeforeFirst()) {
                        OUTPUT_TextArea.appendText(query + " " + timeStamp);
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText("No data ");
                    } else {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int col_count = rsmd.getColumnCount();
                        int row_count = 0;

//-------------------------------------------------------------------------------------
                        Table_generator tg = new Table_generator();
                        tg.table(rs, OUTPUT_TextArea);
                        // //m.out.println("5");
                        ////m.out.println("SELECT QUERY EXECUTED");
                        history[index_for_in] = query;
                        index_for_in++;
                        con.close();
                    }
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText(query + " " + timeStamp);
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }

            } else {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    history[index_for_in] = query;
                    index_for_in++;
                    con.close();

                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            }
            INPUT_TextArea.setText("");
            // AUTOCOMPLETE_ListView.setVisible(false);
            type_of_data1.setVisible(false);
            // SUGGESTIONS_ListView.setVisible(false);
            type_of_data.setVisible(false);
            press_control.setVisible(false);
            press_down.setVisible(false);
            //  Syntax.setVisible(false);
            FadeTransition ff12 = new FadeTransition(Duration.seconds(speed), AUTOCOMPLETE_ListView);
            ff12.setToValue(0);
            ff12.play();
            FadeTransition ff122 = new FadeTransition(Duration.seconds(speed), SUGGESTIONS_ListView);
            ff122.setToValue(0);
            ff122.play();
            FadeTransition ff1223 = new FadeTransition(Duration.seconds(speed), Syntax);
            ff1223.setToValue(0);
            ff1223.play();

        }
        if (event.getCode() == KeyCode.TAB) {
            Search_query_tf.requestFocus();
        }

//        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            String arr[] = input_text.split(" ", 3);
//            //m.out.println(arr[0]);
//            //m.out.println(arr[1]);
//            //m.out.println(arr[2]);
//
//        }
        // TODO add your handling code here:
    }

    @FXML
    private void Input_key_released(KeyEvent event) {
        //ArrayList<String> arr = new ArrayList<String>();
        //DefaultListModel<String> ddl = new DefaultListModel();
// jScrollPane2.setLocation((jScrollPane2.getLocationOnScreen().x+3), 570);
// int x = jScrollPane2.getLocationOnScreen().x;
// AUTOCOMPLETE_ListView.setLocation(x, 570);
        String table_name = null;
        String input_query = INPUT_TextArea.getText();
        if (type_of_data.getText().contains("Suggested Keywords")) {
            //  DefaultListModel dd12 = (DefaultListModel) AUTOCOMPLETE_ListView.getModel();
            int no_of_items = AUTOCOMPLETE_ListView.getItems().size();
            for (int i = 0; i < no_of_items; i++) {
                String item = (String) AUTOCOMPLETE_ListView.getItems().get(i);
                //m.out.println(item);
                if (input_query.contains(item)) {
                    //   //m.out.println("yes");
                }
            }
        }
        if (event.getCode() == KeyCode.SPACE) {
            AUTOCOMPLETE_ListView.getItems().clear();

            String arr[] = input_query.split("\\s+");
            if (arr[arr.length - 1].toLowerCase().equals("where") || arr[arr.length - 1].toLowerCase().equals("add") || arr[arr.length - 1].toLowerCase().equals("drop") || arr[arr.length - 1].toLowerCase().equals("modify") || arr[arr.length - 1].toLowerCase().equals("set") || arr[arr.length - 1].toLowerCase().equals("column")) {
                AUTOCOMPLETE_ListView.getItems().clear();
                type_of_data.setText("Columns");
                try {
                    if (input_query.contains("from")) {
                        String arr1[] = input_query.split("from");
                        String further_split[] = arr1[1].split("\\s+");
                        table_name = further_split[1];
                    } else if (input_query.contains("modify")) {
                        String arr1[] = input_query.split("\\s+");
                        table_name = arr1[2];
                        //  //m.out.println("table :" + table_name);
                    } else if (input_query.contains("add")) {
                        String arr1[] = input_query.split("\\s+");
                        table_name = arr1[2];
                        //   //m.out.println("table :" + table_name);
                    } else if (input_query.contains("drop")) {
                        String arr1[] = input_query.split("\\s+");
                        table_name = arr1[2];
                        //   //m.out.println("table :" + table_name);
                    } else if (input_query.contains("column")) {
                        String arr1[] = input_query.split("\\s+");
                        table_name = arr1[2];

                    } else {
                        String arr1[] = input_query.split("\\s+");
                        table_name = arr1[1];
                    }
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from " + table_name + " ;");
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int Col_Count = rsmd.getColumnCount();
                    for (int i = 1; i <= Col_Count; i++) {
                        AUTOCOMPLETE_ListView.getItems().add(rsmd.getColumnName(i));

                    }
                    //  //m.out.println("SHOW TABLE QUERY EXECUTED");
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            }
            if (arr[arr.length - 1].toLowerCase().equals("from") || arr[arr.length - 1].toLowerCase().equals("table") || arr[arr.length - 1].toLowerCase().equals("update") || arr[arr.length - 1].toLowerCase().equals("show") || arr[arr.length - 1].toLowerCase().equals("into")) {
                AUTOCOMPLETE_ListView.getItems().clear();
                type_of_data.setText("Tables");
                try {

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("show tables ;");
                    while (rs.next()) {
                        AUTOCOMPLETE_ListView.getItems().add(rs.getString(1));

                    }
                    //  //m.out.println("SHOW TABLE QUERY EXECUTED");
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            }
            if (arr[arr.length - 1].toLowerCase().equals("drop")) {
                AUTOCOMPLETE_ListView.getItems().add("TABLE");
                AUTOCOMPLETE_ListView.getItems().add("DATABASE");
            }

            if (arr[arr.length - 1].toLowerCase().equals("use") || arr[arr.length - 1].toLowerCase().equals("database")) {
                AUTOCOMPLETE_ListView.getItems().clear();
                type_of_data.setText("Databases");
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    DatabaseMetaData dbmd = con.getMetaData();
                    ResultSet catlogue = dbmd.getCatalogs();
                    while (catlogue.next()) {
                        AUTOCOMPLETE_ListView.getItems().add(catlogue.getString(1));

                    }
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    //m.out.println(e.getMessage());
                }
            }

        } else {
            String temp = null;

            String ss = " Select * from tab where name like s% ;";
            String arr[] = input_query.split("\\s+");
            for (int i = 0; i <= arr.length - 1; i++) {
                temp = arr[i];
                ////m.out.println(temp);
                ////m.out.println(arr.length);
            }

            //  String ar[] =input_query.split("\\s+",count);
            // //m.out.println(ar[count]); 
            type_of_data.setText("Suggested Keywords");
            String input_text = INPUT_TextArea.getText();
            try {
                AUTOCOMPLETE_ListView.getItems().clear();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String temp_query = "select keyword from reserved_words where keyword like '" + temp + "%' ;";
                // //m.out.println("1");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                Statement stmt = con.createStatement();
                // //m.out.println("2");
                ResultSet rs = stmt.executeQuery(temp_query);
                ////m.out.println("3keyword");
                //OUTPUT_TextArea.append("\n");
                while (rs.next()) {
                    AUTOCOMPLETE_ListView.getItems().add(rs.getString(1));
                }

            } catch (Exception e) {
                OUTPUT_TextArea.appendText("\n");
                OUTPUT_TextArea.appendText(e.getMessage());
            }
        }
    }

    @FXML
    private void AutoComplete_key_pressed(KeyEvent event) {
        String input_query = INPUT_TextArea.getText();
        // DefaultListModel<String> ddd = new DefaultListModel<String>();

        if (event.getCode() == KeyCode.ENTER) {///-------------------------DOES NOT REPLACES THE CURRENT WORD
            //lastword = input_query.substring(input_query.lastIndexOf(" ")-3);
            String bits[] = input_query.split(" ");
            lastword = bits[bits.length - 1];
            // //m.out.println("last |" + lastword + "|");
            if (lastword.toLowerCase().contains("use") || lastword.toLowerCase().contains("from") || lastword.toLowerCase().contains("where") || lastword.toLowerCase().contains("show") || lastword.toLowerCase().contains("table") || lastword.toLowerCase().contains("into") || lastword.toLowerCase().contains("set") || lastword.toLowerCase().contains("update") || lastword.toLowerCase().contains("database") || lastword.toLowerCase().contains("drop")) {
                String element = (String) AUTOCOMPLETE_ListView.getSelectionModel().getSelectedItem().toLowerCase();
                INPUT_TextArea.setText(INPUT_TextArea.getText() + element);
                INPUT_TextArea.requestFocus();
            } else {//////////////REPLACES THE INCOMPLETE WORD AND PLACES NEW ONE
                String element = (String) AUTOCOMPLETE_ListView.getSelectionModel().getSelectedItem().toLowerCase();
                String arr[] = input_query.split("\\s+");
                String s = input_query.replaceFirst(arr[arr.length - 1], element.toLowerCase());
                INPUT_TextArea.setText(s);
                INPUT_TextArea.requestFocus();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                INPUT_TextArea.requestFocus();
            }

        }
    }

    @FXML
    private void query_tf_key_pressed(KeyEvent event) {
        searched_query.getItems().clear();
        String text_case = Search_query_tf.getText();
        String text_lower = text_case.toLowerCase();
        FadeTransition ff = new FadeTransition(Duration.seconds(speed), searched_query);
        ff.setToValue(1);
        ff.play();
        //  DefaultListModel dd = new DefaultListModel();
        //  jScrollPane4.setVisible(true);
        if (items_jcombo == 0) {

            String insert[] = {"put", "insert", "place", "press", "push", "thrust", "add", "slide", "slip", "load", "fit", "position", "slot", "lodge", "install", "embed", "enter", "fill in", "fill"};
//            String show[]=("enjoy"," tap"," exert"," reprocess"," share ","put"," exploit"," consecrate"," apply"," put", to work extend recur give utilize avail resort take misapply recycle waste pull out all the stops exercise play go for reuse utilise cannibalise practice ply fall back overdrive address misuse hold strain devote work dedicate cannibalize employ overuse commit implement assign);
//            String update[];
//            String delete[];
//            String create[];
//            String drop[];
//            String alter[];
//            String use[];
            if (check(insert, text_lower)) {

                searched_query.getItems().clear();
                searched_query.getItems().add("Insert Query");
            }
            if (text_lower.contains("select")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Select Query");
            }
            if (text_lower.contains("update")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Update Query");
            }
            if (text_lower.contains("delete")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Delete Query");
            }
            if (text_lower.contains("create")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Create Query");
            }
            if (text_lower.contains("drop")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Drop Query");
            }
            if (text_lower.contains("alter")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Alter Query");
            }
            if (text_lower.contains("use")) {
                searched_query.getItems().clear();
                searched_query.getItems().add("Use Query");
            }

            if (event.getCode() == KeyCode.DOWN) {

                searched_query.requestFocus();
                searched_query.getSelectionModel().select(0);

            }
        } else if (items_jcombo == 1)//column ---------------------------------------
        {

        } else if (items_jcombo == 2)//table ----------------------------------------------
        {

        }

    }

    @FXML
    private void searched_query_key_pressed(KeyEvent event) {
        ////m.out.println("key pressed");
        if (event.getCode() == KeyCode.ENTER) {
            String item_temp = searched_query.getSelectionModel().getSelectedItem();
            String items[] = item_temp.split("\\s+");
            String item = items[0];
            //  //m.out.println(item);
            // Syntax.setVisible(true);
            FadeTransition ff122 = new FadeTransition(Duration.seconds(speed), Syntax);
            ff122.setToValue(1);
            ff122.play();

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //   //m.out.println("1");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                Statement stmt = con.createStatement();
                //  //m.out.println("2");
                String query = "select syntax from syntax where type like '" + item + "';";
                ResultSet rs = stmt.executeQuery(query);
                // //m.out.println("3searchquerykeypressed");
                while (rs.next()) {
                    Syntax.setText(rs.getString(1));
                }
                // //m.out.println("SELECT QUERY EXECUTED");
            } catch (Exception e) {
                OUTPUT_TextArea.appendText("\n");
                OUTPUT_TextArea.appendText(e.getMessage());
            }
        }
        if (event.getCode() == KeyCode.TAB) {
            INPUT_TextArea.requestFocus();
        }
        //searched_query.setVisible(false);
        FadeTransition ff122 = new FadeTransition(Duration.seconds(speed), searched_query);
        ff122.setToValue(0);
        ff122.play();

        Search_query_tf.setText("");
        INPUT_TextArea.requestFocus();
    }

    @FXML
    private void searched_query_click(MouseEvent event) {
        String item_temp = searched_query.getSelectionModel().getSelectedItem();
        String items[] = item_temp.split("\\s+");
        String item = items[0];
        //  //m.out.println(item);
        //Syntax.setVisible(true);
        FadeTransition ff122 = new FadeTransition(Duration.seconds(speed), Syntax);
        ff122.setToValue(1);
        ff122.play();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //  //m.out.println("1");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
            Statement stmt = con.createStatement();
            //  //m.out.println("2");
            String query = "select syntax from syntax where type like '" + item + "';";
            ResultSet rs = stmt.executeQuery(query);
            // //m.out.println("3searchquerymouseclicked");
            while (rs.next()) {
                Syntax.setText(rs.getString(1));
            }
            // //m.out.println("SELECT QUERY EXECUTED");
        } catch (Exception e) {
            OUTPUT_TextArea.appendText("\n");
            OUTPUT_TextArea.appendText(e.getMessage());
        }
        INPUT_TextArea.requestFocus();
        //searched_query.setVisible(false);
        FadeTransition ff1221 = new FadeTransition(Duration.seconds(speed), searched_query);
        ff1221.setToValue(0);
        ff1221.play();

        Search_query_tf.setText("");
    }

    @FXML
    private void Suggestion_key_pressed(KeyEvent event) {
        String input_query = INPUT_TextArea.getText();
        String item = SUGGESTIONS_ListView.getSelectionModel().getSelectedItem();
        // Syntax.setVisible(true);
        FadeTransition ff122 = new FadeTransition(Duration.seconds(speed), Syntax);
        ff122.setToValue(1);
        ff122.play();

        String table_name = input_query.substring(0, input_query.length() - 1);
        if (event.getCode() == KeyCode.ENTER) {
            int index = SUGGESTIONS_ListView.getSelectionModel().getSelectedIndex();
            if (index == 0) {

                if (item.contains("createTable")) {
                    INPUT_TextArea.setText("create table ");
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                        Statement stmt = con.createStatement();
                        String query = "select syntax from syntax where type = 'create';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Syntax.setText(rs.getString(1));
                        }
                        //m.out.println("SELECT QUERY EXECUTED");
                        con.close();
                    } catch (Exception e) {
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText(e.getMessage());
                    }
                } else {
                    INPUT_TextArea.setText("insert into " + table_name + " values( );");
                    INPUT_TextArea.positionCaret(20 + table_name.length());
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                        Statement stmt = con.createStatement();
                        String query = "select syntax from syntax where type = 'insert_row';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Syntax.setText(rs.getString(1));
                        }
                        //m.out.println("SELECT QUERY EXECUTED");
                        con.close();
                    } catch (Exception e) {
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText(e.getMessage());
                    }
                }
            } else if (index == 1) {
                if (item.contains("removeDatabase")) {

                    String query = "drop database " + table_name + " ;";
                    INPUT_TextArea.setText(query);

                } else {
                    INPUT_TextArea.setText("delete from " + table_name + " where");
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                        Statement stmt = con.createStatement();
                        String query = "select syntax from syntax where type = 'remove_row';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Syntax.setText(rs.getString(1));
                        }
                        //m.out.println("SELECT QUERY EXECUTED");
                        con.close();
                    } catch (Exception e) {
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText(e.getMessage());
                    }
                }
            } else if (index == 2) {
                if (item.contains("showTables")) {
                    INPUT_TextArea.setText("show tables ;");
                } else {
                    INPUT_TextArea.setText("update " + table_name + " set");
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                        Statement stmt = con.createStatement();
                        String query = "select syntax from syntax where type = 'update_row';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Syntax.setText(rs.getString(1));
                        }
                        //m.out.println("SELECT QUERY EXECUTED");
                        con.close();
                    } catch (Exception e) {
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText(e.getMessage());
                    }
                }
            } else if (index == 3) {
                if (item.contains("removeTable")) {
                    INPUT_TextArea.setText("drop table");
                } else {
                    INPUT_TextArea.setText("alter table " + table_name + " add");
                    try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                        Statement stmt = con.createStatement();
                        String query = "select syntax from syntax where type = 'add_column';";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Syntax.setText(rs.getString(1));
                        }
                        //m.out.println("SELECT QUERY EXECUTED");
                        con.close();
                    } catch (Exception e) {
                        OUTPUT_TextArea.appendText("\n");
                        OUTPUT_TextArea.appendText(e.getMessage());
                    }
                }
            } else if (index == 4) {
                INPUT_TextArea.setText("alter table " + table_name + " drop");
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'remove_column';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (index == 5) {
                INPUT_TextArea.setText("alter table " + table_name + " modify");
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'modify_column';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (index == 6) {
                INPUT_TextArea.setText("alter table " + table_name + " rename column");
                INPUT_TextArea.positionCaret(26 + table_name.length());
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'rename_column';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (index == 7) {
                INPUT_TextArea.setText("update " + table_name + " set");

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'update_row';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (index == 8) {
                INPUT_TextArea.setText("select * from " + table_name + "  ;");
                INPUT_TextArea.positionCaret(15 + table_name.length());
            } else if (index == 9) {
                INPUT_TextArea.setText("create table  as select * from " + table_name + " ;");
                INPUT_TextArea.positionCaret(13);
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'copy_table';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            } else if (index == 10) {
                INPUT_TextArea.setText("alter table " + table_name + " rename to  ;");
                INPUT_TextArea.positionCaret(23 + table_name.length());
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
                    Statement stmt = con.createStatement();
                    String query = "select syntax from syntax where type = 'rename_table';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Syntax.setText(rs.getString(1));
                    }
                    //m.out.println("SELECT QUERY EXECUTED");
                    con.close();
                } catch (Exception e) {
                    OUTPUT_TextArea.appendText("\n");
                    OUTPUT_TextArea.appendText(e.getMessage());
                }
            }

            INPUT_TextArea.requestFocus();

            SUGGESTIONS_ListView.getItems().clear();
            FadeTransition ff12244 = new FadeTransition(Duration.seconds(speed), SUGGESTIONS_ListView);
            ff12244.setToValue(0);
            ff12244.play();

            FadeTransition ff1224 = new FadeTransition(Duration.seconds(speed), AUTOCOMPLETE_ListView);
            ff1224.setToValue(0);
            ff1224.play();
            type_of_data.setVisible(false);
            type_of_data1.setVisible(false);
            press_control.setVisible(false);
            press_down.setVisible(false);
            FadeTransition ff12246 = new FadeTransition(Duration.seconds(speed), Syntax);
            ff12246.setToValue(0);
            ff12246.play();
        }
    }

    @FXML
    private void save_as_text_pressed(ActionEvent event) {
//        try {
//            File dir = null;
//            JFileChooser fc = new JFileChooser();
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            int resp = fc.showSaveDialog(null);
//            if (resp == JFileChooser.APPROVE_OPTION) {
//                dir = fc.getSelectedFile();
//            }
//            String timeStamp = new SimpleDateFormat("dd-MM-yy HHmmss").format(Calendar.getInstance().getTime());
//            //m.out.println(timeStamp);
//            File file = new File(dir, timeStamp + ".txt");
//            FileWriter fw = null;
//            try {
//                fw = new FileWriter(file);
//                BufferedWriter writer = new BufferedWriter(fw);
//                //                                                                                                      OUTPUT_TextArea.write(writer);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } finally {
//                if (fw != null) {
//                    try {
//                        fw.close();
//                    } catch (IOException ex) {
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//        }
//        int dialogButton = JOptionPane.YES_OPTION;
//        int dialogResult = JOptionPane.showConfirmDialog(null, "In order to view the text file in proper format. You will need monospace font. Do you want to donload it ?", "Warning", dialogButton);
//        if (dialogResult == JOptionPane.YES_OPTION) {
//
//            try {
//                String url_open = "https://urban-fonts.s3.amazonaws.com/download/Fira_Mono.zip?AWSAccessKeyId=AKIAJMQJIRI6AYFKCXTA&Expires=1517044231&Signature=dzY7OjRD9X11FO4L0YXmstvjLRU%3D";
//                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
//            } catch (Exception e) {
//            }
//
//        } else if (dialogResult == JOptionPane.NO_OPTION) {
//
//        }
        Save_file s = new Save_file();
        s.save(OUTPUT_TextArea);
    }

    @FXML
    private void hamburger_clicked(MouseEvent event) {

    }

    @FXML
    private void clear_button_pressed(ActionEvent event) {
        OUTPUT_TextArea.setText("");

    }

    @FXML
    private void text_size_mouse_clicked(MouseEvent event) {
        double re = text_size_slider.getValue();
        int r = (int) Math.round(re);
        text_size_label.setText("" + r);
        OUTPUT_TextArea.setFont(Font.font("monospaced", FontWeight.LIGHT, r));
    }

    @FXML
    private void exit_exited(MouseEvent event) {
        //close_button.setBackground(Color.BLACK);
        close_button.setStyle("-fx-background-color: black;");
    }

    @FXML
    private void exit_entered(MouseEvent event) {
        //   close_button.setBackground(Color.RED);
        close_button.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void close_button_pressed(MouseEvent event) {

        System.exit(0);

    }

    @FXML
    private void ColorChanger(ActionEvent event) {
        javafx.scene.paint.Color selectedColor = ChangeColor.getValue();
        basepane.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    private void reset_color_clicked(ActionEvent event) {
        basepane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        ChangeColor.setValue(Color.BLACK);
    }

    @FXML
    private void reset_text_clicked(ActionEvent event) {
        text_size_slider.setValue(24);
        text_size_label.setText("24");
        OUTPUT_TextArea.setFont(Font.font("monospaced", FontWeight.LIGHT, 24));

    }

    @FXML
    private void size_combobox_clicked(ActionEvent event) {

        String choice = size_combo_box.getSelectionModel().getSelectedItem();
        //  "Music", "Problem", "Youtube", "Tutorials"
        WebEngine we = webview_browser.getEngine();
        if (choice.equals("Introduction")) {
            we.load("https://www.tutorialspoint.com/mysql/mysql-introduction.htm");
        } else if (choice.equals("Commands")) {
            we.load("https://www.w3schools.in/mysql/ddl-dml-dcl/");

        } else if (choice.equals("Tutorials")) {
            we.load("https://www.w3schools.com/sql/");

        } else if (choice.equals("Practice Questions")) {
            we.load("https://www.hackerrank.com/domains/sql");

        } else if (choice.equals("Youtube")) {

            we.load("https://www.youtube.com/");
        } else if (choice.equals("Doubts")) {

            we.load("https://stackoverflow.com/");
        }
        size_combo_box.getSelectionModel().clearSelection();
    }

    @FXML
    private void browser_closed_button_pressed(ActionEvent event) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(0.1), web_browser_pane);
        sc.setToX(0.1);
        sc.play();
        sc.setOnFinished((e) -> {
            WebEngine we = webview_browser.getEngine();
            we.load("https://www.google.co.in/");
            web_browser_pane.setVisible(false);
        });
        browser_button.setText("Open browser");
    }

    @FXML
    private void move_dragged(MouseEvent event) {
        System.out.println(x);
        System.out.println(event.getSceneX());
        System.out.println(event.getScreenX());
        System.out.println(event.getX());

        web_browser_pane.setLayoutX(event.getSceneX() - x);
        web_browser_pane.setLayoutY(event.getScreenY() - y);
        //   web_browser_pane.setLayoutX(event.getSceneX() + dragDelta.x);
    }

    @FXML
    private void webview_panel_Clicked(MouseEvent event) {
        x = event.getX();
        y = event.getY();
    }

    @FXML
    private void browser_button_clicked(ActionEvent event) {
        if (browser_button.getText().equals("Open browser")) {
            web_browser_pane.setVisible(true);
            web_browser_pane.setLayoutX(340);
            web_browser_pane.setLayoutY(200);
            WebEngine we = webview_browser.getEngine();
            we.load("https://www.google.co.in/");
            // web_browser_pane.setMinSize(1040, 610);
            ScaleTransition sc = new ScaleTransition(Duration.seconds(0.1), web_browser_pane);
            sc.setToX(1);
            sc.play();
            tt.setRate(tt.getRate() * -1);
            tt.play();
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), basepane);
            tt1.setToX(0);
            tt1.play();
            web_browser_pane.setMinSize(640, 480);
            browser_button.setText("Close Browser");
        } else if (browser_button.getText().equals("Close Browser")) {
            ScaleTransition sc = new ScaleTransition(Duration.seconds(0.1), web_browser_pane);
            sc.setToX(0.1);
            sc.play();
            sc.setOnFinished((e) -> {
                WebEngine we = webview_browser.getEngine();
                we.load("https://www.google.co.in/");
                web_browser_pane.setVisible(false);
            });
            browser_button.setText("Open browser");
        }
    }

    @FXML
    private void size_combobox_clicked1(ActionEvent event) {
        String choice1 = size_combobox.getSelectionModel().getSelectedItem();
        if (choice1.equals("Small")) {
            web_browser_pane.setMinSize(640, 480);

        } else if (choice1.equals("Medium")) {
            web_browser_pane.setMinSize(1280, 720);

        } else if (choice1.equals("Large")) {
            web_browser_pane.setMinSize(1600, 900);

        }
        size_combobox.getSelectionModel().clearSelection();
    }

    @FXML
    private void check_username_password(ActionEvent event) {
        username = username_mysql_tf.getText();
        password = password_mysql_tf.getText();
        boolean isCorrect = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", username, password);
            isCorrect = true;
            con.close();
        } catch (Exception e) {
            OUTPUT_TextArea.appendText("\n");
            OUTPUT_TextArea.appendText(e.getMessage());
        }
        if (isCorrect) {

            ScaleTransition sc = new ScaleTransition(Duration.seconds(0.1), user_check_pane);
            sc.setToX(0.1);
            sc.play();
            sc.setOnFinished((o) -> {
                user_check_pane.setVisible(false);
            });
            OUTPUT_TextArea.setText("Connection established !!\n");
            OUTPUT_TextArea.appendText("Database changes to mysql");
            OUTPUT_TextArea.setDisable(false);
            ham1.setDisable(false);
            Clear_button.setDisable(false);
            INPUT_TextArea.setDisable(false);
            Search_query_tf.setDisable(false);
        }

    }

    @FXML
    private void OskTyping(MouseEvent event) {

    }

    @FXML
    private void Take_photo_clicked(ActionEvent event) {
    }

}
