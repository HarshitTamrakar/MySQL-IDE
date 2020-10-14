/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 0kl
 */
public class Main_screenController implements Initializable {

    @FXML
    private ImageView space_bigger;
    @FXML
    private Separator margin;
    @FXML
    private AnchorPane LOGIN_PANEL;
    @FXML
    private Label LOGIN;
    @FXML
    private AnchorPane SIGNUP_PANEL;
    @FXML
    private Label SIGNUP;
    @FXML
    private AnchorPane LOGIN_PANEL_PANE;
    @FXML
    private AnchorPane SIGNUP_PANEL_PANE;
    @FXML
    private ImageView cockpit;
    @FXML
    private AnchorPane MainPanel;
    private JFXTextField Username_tf;
    private JFXPasswordField password_tf;
    @FXML
    private JFXButton SignUp_btn;
    private JFXTextField emailid_tf;
    String usernamee = "root", passworde = "123";
    @FXML
    private AnchorPane Center_panel;
    @FXML
    private AnchorPane data_signup_panel;
    @FXML
    private JFXTextField signup_Username_tf;
    @FXML
    private JFXPasswordField signup_password_tf;
    @FXML
    private JFXTextField signup_emailid_tf;
    @FXML
    private AnchorPane data_login_panel;
    @FXML
    private JFXTextField login_Username_tf;
    @FXML
    private JFXPasswordField login_password_tf;
    @FXML
    private JFXTextField login_email_tf;
    @FXML
    private JFXButton Login_btn;
    Connection con;
    int speed = 2;
    Clip clip;
    @FXML
    private JFXButton signup_back;
    @FXML
    private JFXButton login_back;
    
    String login_username, login_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            File file = new File("E:\\Netbeans soundteack\\space.wav");//BACKGROUND SOUND
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }

        data_login_panel.setVisible(false);
        data_signup_panel.setVisible(false);
        String create_table = "CREATE TABLE Users (sno int(20)Primary KEY AUTO_INCREMENT , USERNAME VARCHAR(300) , PASSWORD VARCHAR(100),EMAIL VARCHAR(100));";
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", usernamee, passworde);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(create_table);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FadeTransition ffff = new FadeTransition(Duration.seconds(2), MainPanel);
        ffff.setToValue(1);
        ffff.play();
        ffff.setOnFinished((oo) -> {
            ScaleTransition sc = new ScaleTransition(Duration.seconds(5), space_bigger);
            sc.setToX(1.3);
            sc.setToY(1.3);
            sc.play();
            PauseTransition pp = new PauseTransition(Duration.seconds(5));
            pp.play();
            TranslateTransition tc = new TranslateTransition(Duration.seconds(1), margin);
            tc.setToY(1080);
            pp.setOnFinished((e) -> {
                tc.play();
            });
            tc.setOnFinished((f) -> {
                FadeTransition ff = new FadeTransition(Duration.seconds(2), LOGIN_PANEL);
                ff.setToValue(1);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(2), LOGIN_PANEL);
                tt.setToX(-500);
                ParallelTransition p = new ParallelTransition(ff, tt);
                p.play();
                FadeTransition ff1 = new FadeTransition(Duration.seconds(2), SIGNUP_PANEL);
                ff1.setToValue(1);
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(2), SIGNUP_PANEL);
                tt1.setToX(500);
                ParallelTransition p1 = new ParallelTransition(ff1, tt1);
                p1.play();

            });
        });
    }

    @FXML
    private void LOGIN_MOUSE_EXITED(MouseEvent event) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(0.5), LOGIN);
        sc.setToX(1);
        sc.setToY(1);
        // RotateTransition r = new RotateTransition(Duration.seconds(2), MainPanel);
        // r.setToAngle(0);
        // r.play();
        sc.play();
    }

    @FXML
    private void LOGIN_MOUSE_ENTERED(MouseEvent event) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(0.5), LOGIN);
        sc.setToX(1.1);
        sc.setToY(1.1);
        sc.play();
        //  RotateTransition r = new RotateTransition(Duration.seconds(2), MainPanel);
        // r.setToAngle(-5);
        // r.play();
    }

    @FXML
    private void SIGNUP_MOUSE_EXITED(MouseEvent event) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(0.5), SIGNUP);
        sc.setToX(1);
        sc.setToY(1);
        sc.play();
        //  RotateTransition r = new RotateTransition(Duration.seconds(2), MainPanel);
        // r.setToAngle(0);
        // r.play();
    }

    @FXML
    private void SIGNUP_MOUSE_ENTERED(MouseEvent event) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(0.5), SIGNUP);
        sc.setToX(1.1);
        sc.setToY(1.1);
        // RotateTransition r = new RotateTransition(Duration.seconds(2),MainPanel);
        //// r.setToAngle(5);
        // r.play();
        sc.play();
    }

    @FXML
    private void LOGIN_CLICKED(MouseEvent event) {
        try {
            File file = new File("E:\\Netbeans soundteack\\Ping.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        try {
            File file = new File("E:\\Netbeans soundteack\\down.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        data_login_panel.setVisible(true);
        data_signup_panel.setVisible(false);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
        tt.setToX(1920);
        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), LOGIN_PANEL_PANE);
        tt3.setToX(1920);
        ParallelTransition p = new ParallelTransition(tt, tt3);
        p.play();
        RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
        r.setToAngle(-5);
        r.play();
        r.setOnFinished((e) -> {
            RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
            r2.setToAngle(0);
            r2.play();
            r2.setOnFinished((f) -> {
                FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_login_panel);
                f1.setToValue(1);
                ParallelTransition p2 = new ParallelTransition(f1);
                p2.play();

            });
        });

    }

    @FXML
    private void SIGNUP_CLICKED(MouseEvent event) {
        try {
            File file = new File("E:\\Netbeans soundteack\\Ping.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        try {
            File file = new File("E:\\Netbeans soundteack\\down.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        data_signup_panel.setVisible(true);
        data_login_panel.setVisible(false);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
        tt.setToX(-1920);
        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), SIGNUP_PANEL_PANE);
        tt3.setToX(-1920);
        ParallelTransition p = new ParallelTransition(tt, tt3);
        p.play();
        RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
        r.setToAngle(5);
        r.play();
        r.setOnFinished((e) -> {
            RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
            r2.setToAngle(0);
            r2.play();
            r2.setOnFinished((f) -> {
                FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_signup_panel);
                f1.setToValue(1);
                f1.play();

            });
        });
    }

    @FXML
    private void signup_btn_clicked(ActionEvent event) {
        try {
            File file = new File("E:\\Netbeans soundteack\\Ping.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        if (signup_Username_tf.getText().equals("")) {
            signup_Username_tf.setUnFocusColor(Color.RED);

        } else if (signup_password_tf.getText().equals("")) {
            signup_password_tf.setUnFocusColor(Color.RED);
        } else if (signup_emailid_tf.getText().equals("")) {
            signup_emailid_tf.setUnFocusColor(Color.RED);

        } else {

            try {
                try {
                    File file = new File("E:\\Netbeans soundteack\\down.wav");
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(-2);
                    clip.start();

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }

                String username1 = signup_Username_tf.getText();
                String pass1 = new String(signup_password_tf.getText());

                String email1 = signup_emailid_tf.getText();
                String insert = "INSERT INTO USERS VALUES(NULL,'" + username1 + "','" + pass1 + "','" + email1 + "');";
                Connection con;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", usernamee, passworde);
                Statement stmt = con.createStatement();
                stmt.executeUpdate(insert);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
                tt.setToX(0);
                TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), SIGNUP_PANEL_PANE);
                tt3.setToX(0);
                ParallelTransition p = new ParallelTransition(tt, tt3);
                p.play();
                RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
                r.setToAngle(-5);
                r.play();
                FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_signup_panel);
                f1.setToValue(0);
                f1.play();
                r.setOnFinished((e) -> {
                    RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
                    r2.setToAngle(0);
                    r2.play();
                    r2.setOnFinished((f) -> {
//                        try {
//                            File file = new File("E:\\Netbeans soundteack\\down.wav");
//                            clip = AudioSystem.getClip();
//                            clip.open(AudioSystem.getAudioInputStream(file));
//                            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                            volume.setValue(-2);
//                            clip.start();
//
//                        } catch (Exception e2) {
//                            JOptionPane.showMessageDialog(null, e2.getMessage());
//                        }
//                        TranslateTransition tt6 = new TranslateTransition(Duration.seconds(speed), Center_panel);
//                        tt6.setToX(1920);
//                        TranslateTransition tt36 = new TranslateTransition(Duration.seconds(speed), LOGIN_PANEL_PANE);
//                        tt36.setToX(1920);
//                        ParallelTransition p6 = new ParallelTransition(tt6, tt36);
//                        p6.play();
//                        RotateTransition r6 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
//                        r6.setToAngle(-5);
//                        r6.play();
//                        r6.setOnFinished((e6) -> {
//                            RotateTransition r26 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
//                            r26.setToAngle(0);
//                            r26.play();
//                            r26.setOnFinished((f6) -> {
//                                data_signup_panel.setVisible(false);
//                                data_login_panel.setVisible(true);
//                                FadeTransition f16 = new FadeTransition(Duration.seconds(1), data_login_panel);
//                                f16.setToValue(1);
//                                ParallelTransition p2 = new ParallelTransition(f16);
//                                p2.play();
//
//                            });
//                        });
//

                        Clip clip;
                        try {
                            File file = new File("E:\\Netbeans soundteack\\launch.wav");
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            volume.setValue(-2);
                            clip.start();

                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, e2.getMessage());
                        }
                        ScaleTransition sc = new ScaleTransition(Duration.seconds(5), space_bigger);
                        sc.setFromX(1);
                        sc.setToX(1.5);
                        sc.setFromY(1);
                        sc.setToY(1.5);
                        sc.play();
                        PauseTransition ppp = new PauseTransition(Duration.seconds(4));
                        ppp.play();
                        FadeTransition ff = new FadeTransition(Duration.seconds(1), margin);
                        FadeTransition ff1 = new FadeTransition(Duration.seconds(1), LOGIN_PANEL);
                        FadeTransition ff2 = new FadeTransition(Duration.seconds(1), SIGNUP_PANEL);
                        ff.setToValue(0);
                        ff1.setToValue(0);
                        ff2.setToValue(0);
                        ParallelTransition pppp = new ParallelTransition(ff, ff1, ff2);
                        pppp.play();

                        ppp.setOnFinished((e11) -> {
                            FadeTransition fff = new FadeTransition(Duration.seconds(3), MainPanel);
                            fff.setToValue(0);
                            fff.play();
                            fff.setOnFinished((oo) -> {

                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

                                    Scene scene = new Scene(root);
                                    Stage curStage = (Stage) MainPanel.getScene().getWindow();
                                    curStage.setScene(scene);
                                } catch (IOException ex) {
                                    //Logger.getLogger(DisclaimerController.class.getName()).log(Level.SEVERE, null, ex);
                                    //System.out.println(ex);
                                }
                            });

                        });
                    });
                });
                login_Username_tf.setText(username1);
                login_email_tf.setText(email1);
                Interface.username = username1;
                        Interface.password = pass1;
                        Interface.email= email1;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
 
        }
    }

    @FXML
    private void login_btn_clicked(ActionEvent event) {
        try {
            File file = new File("E:\\Netbeans soundteack\\Ping.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }

        if (login_Username_tf.getText().equals("")) {
            login_Username_tf.setUnFocusColor(Color.RED);

        } else if (login_password_tf.getText().equals("")) {
            login_password_tf.setUnFocusColor(Color.RED);
        } else if (login_email_tf.getText().equals("")) {
            login_email_tf.setUnFocusColor(Color.RED);

        } else if (login_password_tf.getText().equals("")) {
            login_password_tf.setUnFocusColor(Color.RED);
        } else {
            try {
                String usern = login_Username_tf.getText();
                String paasw = login_password_tf.getText();
                String emaii = login_email_tf.getText();
                if (usern != null && paasw != null && emaii != null) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", usernamee, passworde);
                    Statement stmt = con.createStatement();
                    String sql = "Select * from users Where username='" + usern + "' and password='" + paasw + "' and email='" + emaii + "';";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        try {
                            File file = new File("E:\\Netbeans soundteack\\down.wav");//BACKGROUND SOUND
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            volume.setValue(-2);
                            clip.start();

                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, e2.getMessage());
                        }

                        TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
                        tt.setToX(0);
                        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), LOGIN_PANEL_PANE);
                        tt3.setToX(0);
                        ParallelTransition p = new ParallelTransition(tt, tt3);
                        p.play();
                        RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
                        r.setToAngle(5);
                        r.play();
                        FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_login_panel);
                        f1.setToValue(0);
                        f1.play();
                        r.setOnFinished((e) -> {
                            RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
                            r2.setToAngle(0);
                            r2.play();
                            r2.setOnFinished((f) -> {
                                // if(true){yaha pr login wala fingerprint check kr wana hai      sdfgwefkwefuywefyweyfoiweyfyweif
                                Clip clip;
                                try {
                                    File file = new File("E:\\Netbeans soundteack\\launch.wav");
                                    clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(file));
                                    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                                    volume.setValue(-2);
                                    clip.start();

                                } catch (Exception e2) {
                                    JOptionPane.showMessageDialog(null, e2.getMessage());
                                }
                                ScaleTransition sc = new ScaleTransition(Duration.seconds(5), space_bigger);
                                sc.setFromX(1);
                                sc.setToX(1.5);
                                sc.setFromY(1);
                                sc.setToY(1.5);
                                sc.play();
                                PauseTransition ppp = new PauseTransition(Duration.seconds(4));
                                ppp.play();
                                FadeTransition ff = new FadeTransition(Duration.seconds(1), margin);
                                FadeTransition ff1 = new FadeTransition(Duration.seconds(1), LOGIN_PANEL);
                                FadeTransition ff2 = new FadeTransition(Duration.seconds(1), SIGNUP_PANEL);
                                ff.setToValue(0);
                                ff1.setToValue(0);
                                ff2.setToValue(0);
                                ParallelTransition pppp = new ParallelTransition(ff, ff1, ff2);
                                pppp.play();

                                ppp.setOnFinished((e11) -> {
                                    FadeTransition fff = new FadeTransition(Duration.seconds(3), MainPanel);
                                    fff.setToValue(0);
                                    fff.play();
                                    fff.setOnFinished((oo) -> {
                                        Node node = (Node) event.getSource();
                                        Stage stage = (Stage) node.getScene().getWindow();
                                        Parent root;
                                        try {
                                            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                                            Scene scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.show();
                                            /* Exception */
                                        } catch (IOException ex) {
                                            Logger.getLogger(Main_screenController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

//                                        try {
//                                            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//
//                                            Scene scene = new Scene(root);
//                                            Stage curStage = (Stage) MainPanel.getScene().getWindow();
//                                            curStage.setScene(scene);
//                                        } catch (IOException ex) {
//                                            //Logger.getLogger(DisclaimerController.class.getName()).log(Level.SEVERE, null, ex);
//                                            //System.out.println(ex);
//                                        }
                                    });

                                });

                            });

                        });

                        Interface.username = usern;
                        Interface.password = paasw;
                        Interface.email= emaii;
//in this case enter when at least one result comes it means user is valid
                    } else {
                        JOptionPane.showMessageDialog(null, "Either Username , Password or Email-ID is invalid!!");
                        //in this case enter when  result size is zero  it means user is invalid
                    }
                }

                // You can also validate user by result size if its comes zero user is invalid else user is valid
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }

    @FXML
    private void signup_username_clicked(MouseEvent event) {
        signup_Username_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void signup_password_clicked(MouseEvent event) {
        signup_password_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void signup_email_clicked(MouseEvent event) {
        signup_emailid_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void login_username_clicked(MouseEvent event) {
        login_Username_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void login_password_clicked(MouseEvent event) {
        login_password_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void login_email_clicked(MouseEvent event) {
        login_email_tf.setUnFocusColor(Color.BLACK);
    }

    @FXML
    private void signup_back_clicked(ActionEvent event) {

        try {
            File file = new File("E:\\Netbeans soundteack\\down.wav");//BACKGROUND SOUND
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        try {
            File file = new File("E:\\Netbeans soundteack\\ping.wav");//BACKGROUND SOUND
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
            tt.setToX(0);
            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), SIGNUP_PANEL_PANE);
            tt3.setToX(0);
            ParallelTransition p = new ParallelTransition(tt, tt3);
            p.play();
            RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
            r.setToAngle(-5);
            r.play();
            FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_signup_panel);
            f1.setToValue(0);
            f1.play();
            r.setOnFinished((e) -> {
                RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
                r2.setToAngle(0);
                r2.play();
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void login_back_clicked(ActionEvent event) {

        try {
            File file = new File("E:\\Netbeans soundteack\\down.wav");//BACKGROUND SOUND
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        try {
            File file = new File("E:\\Netbeans soundteack\\ping.wav");//BACKGROUND SOUND
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-2);
            clip.start();

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }
        TranslateTransition tt = new TranslateTransition(Duration.seconds(speed), Center_panel);
        tt.setToX(0);
        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(speed), LOGIN_PANEL_PANE);
        tt3.setToX(0);
        ParallelTransition p = new ParallelTransition(tt, tt3);
        p.play();
        RotateTransition r = new RotateTransition(Duration.seconds(speed / 2), cockpit);
        r.setToAngle(5);
        r.play();
        FadeTransition f1 = new FadeTransition(Duration.seconds(1), data_login_panel);
        f1.setToValue(0);
        f1.play();
        r.setOnFinished((e) -> {
            RotateTransition r2 = new RotateTransition(Duration.seconds(speed / 2), cockpit);
            r2.setToAngle(0);
            r2.play();
        });

    }

}
