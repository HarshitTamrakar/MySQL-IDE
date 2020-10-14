package newapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class MainPageController implements Initializable {

    @FXML
    private AnchorPane earthAnchor;
    @FXML
    private AnchorPane SpaceAnchor;
    int speed = 5;//5
    int speed2 = 2;//2
    int speed3 = 10;//10
    @FXML
    private JFXButton button;
    @FXML
    private AnchorPane MainPanel;
    @FXML
    private ImageView Satellite;
    @FXML
    private ImageView earth_image;
    @FXML
    private ImageView space;
    @FXML
    private ImageView space2;
    double init = 0.75;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image1;
    @FXML
    private AnchorPane Image_Panel;
    int speed_anim = 5;//intially = 5
    @FXML
    private Pane Intro_panel;
    Clip clip;
    @FXML
    private ImageView chain;
    @FXML
    private ImageView rocket;
    @FXML
    private ImageView ufo_right;
    @FXML
    private ImageView ufo_left;
    @FXML
    private AnchorPane Features_panel;
    @FXML
    private WebView webview;
    @FXML
    private JFXButton get_started_btn;
    @FXML
    private AnchorPane Finger_print_anchor;
    @FXML
    private Label ip_tf;
    String ip_add = "";
    static Socket s;
    static ServerSocket ss;
    static InputStreamReader isr;
    static BufferedReader br;
    static String message;
    @FXML
    private JFXTextField reply_tf;
    int i = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Interface.username);
        System.out.println(Interface.password);

        File f11 = new File("C:\\Users\\harsh\\Documents\\NetBeansProjects\\NewApp\\src\\newapp\\hello.html");
        WebEngine webEngine = webview.getEngine();
        webEngine.load(f11.toURI().toString());
//      InetAddress IP;
//        try {
//            IP = InetAddress.getLocalHost();
//            System.out.println("IP of my system is := "+IP.getHostAddress());
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
////////////////////////////////////////////////////////        String ip;
////////////////////////////////////////////////////////        List<String> ii = new ArrayList<>();
////////////////////////////////////////////////////////        try {
////////////////////////////////////////////////////////            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
////////////////////////////////////////////////////////            while (interfaces.hasMoreElements()) {
////////////////////////////////////////////////////////                NetworkInterface iface = interfaces.nextElement();
////////////////////////////////////////////////////////                // filters out 127.0.0.1 and inactive interfaces
////////////////////////////////////////////////////////                if (iface.isLoopback() || !iface.isUp()) {
////////////////////////////////////////////////////////                    continue;
////////////////////////////////////////////////////////                }
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////                Enumeration<InetAddress> addresses = iface.getInetAddresses();
////////////////////////////////////////////////////////                while (addresses.hasMoreElements()) {
////////////////////////////////////////////////////////                    InetAddress addr = addresses.nextElement();
////////////////////////////////////////////////////////                    ip = addr.getHostAddress();
////////////////////////////////////////////////////////                    System.out.println(iface.getDisplayName() + " " + ip);
////////////////////////////////////////////////////////                    ii.add(ip);
////////////////////////////////////////////////////////                    System.out.println("under" + ii.get(i) + "pos" + i);
////////////////////////////////////////////////////////                    ++i;
////////////////////////////////////////////////////////                }
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////            }
////////////////////////////////////////////////////////        } catch (Exception e) {
////////////////////////////////////////////////////////            System.out.println(e);
////////////////////////////////////////////////////////        }
////////////////////////////////////////////////////////        System.out.println("bahar" + ii.get(0));
////////////////////////////////////////////////////////        ip_tf.setText(ii.get(0));
        Linux_Controller l = new Linux_Controller();
        String ip = l.getIP();
        ip_tf.setText(ip);
        Satellite.setVisible(false);
        Finger_print_anchor.setVisible(false);
        try {
            File file = new File("E:\\Netbeans soundteack\\space.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-5);
            clip.start();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        FadeTransition ff2 = new FadeTransition(Duration.seconds(5), MainPanel);
        ff2.setFromValue(0);
        ff2.setToValue(1);
        ff2.play();

        FadeTransition ff = new FadeTransition(Duration.seconds(5), space2);
        ff.setFromValue(init);
        ff.setToValue(0.5);

        FadeTransition ff1 = new FadeTransition(Duration.seconds(5), space);
        ff1.setFromValue(init);
        ff1.setToValue(0.5);

        ParallelTransition p = new ParallelTransition(ff, ff1);

        FadeTransition ff4 = new FadeTransition(Duration.seconds(speed2), space);
        ff4.setToValue(init);
        FadeTransition ff5 = new FadeTransition(Duration.seconds(speed2), space2);
        ff5.setToValue(init);
        ParallelTransition p2 = new ParallelTransition(ff4, ff5);

        p.play();

        p.setOnFinished((e) -> {
            p2.play();
        });
        p2.setOnFinished((f) -> {
            p.play();
        });
//Path path = new Path();
//path.getElements().add(new MoveTo(0,0));
//        ArcTo arc = new ArcTo();
//        arc.setX(500);
//        arc.setX(500);
//        arc.setXAxisRotation(180);
//        arc.setRadiusX(100);
//        arc.setRadiusY(100);
//        path.getElements().add(arc);
//        PathTransition pp = new PathTransition();
//        pp.setDuration(Duration.seconds(5));
//pp.setPath(path);
//pp.setNode(Satellite);
//pp.play();

    }

    @FXML
    private void mousescrolled(ScrollEvent event) {
        double d = event.getDeltaY();
        if (d < 0) {
            Satellite.setVisible(true);
            TranslateTransition st = new TranslateTransition(Duration.seconds(speed), earthAnchor);
            st.setToY(-720);
            TranslateTransition st1 = new TranslateTransition(Duration.seconds(speed), SpaceAnchor);
            st1.setToY(-720);
            ParallelTransition p = new ParallelTransition(st, st1);
            p.play();

            Ellipse e = new Ellipse(1700.0f, 900.0f);
            e.setCenterX(0.0f);
            e.setCenterY(900.0f);
            PathTransition pp = new PathTransition(Duration.seconds(10), e);
            pp.setPath(e);
            pp.setNode(Satellite);
            pp.setCycleCount(PathTransition.INDEFINITE);
            pp.play();
            RotateTransition r = new RotateTransition(Duration.seconds(30), Satellite);
            r.setToAngle(360);
            r.play();

        } else if (d > 0) {
            TranslateTransition st = new TranslateTransition(Duration.seconds(speed), earthAnchor);
            st.setToY(0);
            TranslateTransition st1 = new TranslateTransition(Duration.seconds(speed), SpaceAnchor);
            st1.setToY(0);
            ParallelTransition p = new ParallelTransition(st, st1);
            p.play();
        }
    }

    @FXML
    private void buttonPressed(ActionEvent event) {
        Finger_print_anchor.setVisible(true);
        FadeTransition ff = new FadeTransition(Duration.seconds(2), Image_Panel);
        ff.setToValue(0);
        ff.play();
        FadeTransition ffff = new FadeTransition(Duration.seconds(2), Finger_print_anchor);
        ffff.setToValue(1);
        ffff.play();
        ffff.setOnFinished((t) -> {
            try {
                System.out.println("hello");
                ss = new ServerSocket(5000);
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                System.out.println(message);
                reply_tf.setText(message);
                KeyEvent event11 = null;
                reply_incoming(event11);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

    }

    @FXML
    private void image4_scrolled(ScrollEvent event) {
//        double d = event.getDeltaY();
//        if (d < 0) {
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed_anim * 4), image4);
//            tt.setToY(-1080);
//            ScaleTransition ss = new ScaleTransition(Duration.seconds(speed_anim * 6), image4);
//            ss.setToX(0.2);
//            ss.setToY(0.2);
//            RotateTransition rr = new RotateTransition(Duration.seconds(speed_anim * 4), image4);
//            rr.setToAngle(360);
//            rr.setCycleCount(RotateTransition.INDEFINITE);
//            ParallelTransition p = new ParallelTransition(tt, ss, rr);
//            p.play();
//            ss.setOnFinished((e) -> {
//                FadeTransition f = new FadeTransition(Duration.seconds(speed_anim), image4);
//                f.setToValue(0);
//                f.play();
//            });
//
//        } else if (d > 0) {
//
//        }

    }

    @FXML
    private void image3_scrolled(ScrollEvent event) {
//        double d = event.getDeltaY();
//        if (d < 0) {
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed_anim * 2), image3);
//            tt.setToY(540);
//            ScaleTransition ss = new ScaleTransition(Duration.seconds(speed_anim * 6), image3);
//            ss.setToX(0.2);
//            ss.setToY(0.2);
//            RotateTransition rr = new RotateTransition(Duration.seconds(speed_anim * 4), image3);
//            rr.setToAngle(360);
//            rr.setCycleCount(RotateTransition.INDEFINITE);
//            ParallelTransition p = new ParallelTransition(tt, ss, rr);
//            p.play();
//            ss.setOnFinished((e) -> {
//                FadeTransition f = new FadeTransition(Duration.seconds(speed_anim), image3);
//                f.setToValue(0);
//                f.play();
//            });

//            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed_anim * 4), image3);
//            tt.setToX(1920);
//            ScaleTransition ss = new ScaleTransition(Duration.seconds(speed_anim * 6), image3);
//            ss.setToX(0.2);
//            ss.setToY(0.2);
//            RotateTransition rr = new RotateTransition(Duration.seconds(speed_anim * 4), image3);
//            rr.setToAngle(360);
//            rr.setCycleCount(RotateTransition.INDEFINITE);
//            ParallelTransition p = new ParallelTransition(tt, ss, rr);
//            p.play();
//            ss.setOnFinished((e) -> {
//                FadeTransition f = new FadeTransition(Duration.seconds(speed_anim), image3);
//                f.setToValue(0);
//                f.play();
//            });
//        } else if (d > 0) {
//
//        }
    }

    @FXML
    private void image2_scrolled(ScrollEvent event) {
//        double d = event.getDeltaY();
//        if (d < 0) {
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed_anim*2), image2);
//           tt.setToX(-960);
//           ScaleTransition ss = new ScaleTransition(Duration.seconds(speed_anim*6), image2);
//           ss.setToX(0.2);
//           ss.setToY(0.2);
//           RotateTransition rr = new RotateTransition(Duration.seconds(speed_anim*4), image2);
//           rr.setToAngle(360);
//           rr.setCycleCount(RotateTransition.INDEFINITE);
//           ParallelTransition p = new ParallelTransition(tt, ss , rr);
//           p.play();
//           ss.setOnFinished((e)->{
//           FadeTransition f = new FadeTransition(Duration.seconds(speed_anim), image2);
//           f.setToValue(0);
//           f.play();
//           });

//            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), ufo_right);
//            tt.setToX(-1920);
//            tt.play();
//            try {
//                File file = new File("E:\\Netbeans soundteack\\down.wav");
//                clip = AudioSystem.getClip();
//                clip.open(AudioSystem.getAudioInputStream(file));
//                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                volume.setValue(1);
//                clip.start();
//
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//            tt.setOnFinished((t) -> {
//                FadeTransition ff = new FadeTransition(Duration.seconds(2), ufo_right);
//                ff.setToValue(0);
//                FadeTransition ff1 = new FadeTransition(Duration.seconds(2), ufo_left);
//                ff1.setToValue(1);
//                ParallelTransition p = new ParallelTransition(ff, ff1);
//                p.play();
//                p.setOnFinished((f) -> {//sound of beam---------------------------------------------------
//                    TranslateTransition t2 = new TranslateTransition(Duration.seconds(2), image2);
//                    t2.setToY(-200);
//                    
//                    FadeTransition f2 = new FadeTransition(Duration.seconds(2), image2);
//                    f2.setToValue(0);
//                    ParallelTransition p1 = new ParallelTransition(t2, f2 );
//                    p1.play();
//                    p1.setOnFinished((i) -> {
//                        image2.setVisible(false);
//                        FadeTransition ff3 = new FadeTransition(Duration.seconds(0.5), ufo_right);
//                        ff3.setToValue(1);
//                        FadeTransition ff13 = new FadeTransition(Duration.seconds(0.5), ufo_left);
//                        ff13.setToValue(0);
//                        ParallelTransition p3 = new ParallelTransition(ff3, ff13);
//                        p3.play();
//                        p3.setOnFinished((o) -> {
//                            try {
//                                File file = new File("E:\\Netbeans soundteack\\down.wav");
//                                clip = AudioSystem.getClip();
//                                clip.open(AudioSystem.getAudioInputStream(file));
//                                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                                volume.setValue(1);
//                                clip.start();
//
//                            } catch (Exception e) {
//                                System.err.println(e.getMessage());
//                            }
//                            TranslateTransition tt5 = new TranslateTransition(Duration.seconds(2), ufo_right);
//                            tt5.setToX(-3840);
//                            tt5.play();
//                        });
//                    });
//                });
//            });
//        } else if (d > 0) {
//
//        }
    }

    @FXML
    private void image1_scrolled(ScrollEvent event) {
        //  double d = event.getDeltaY();
//        if (d < 0) {
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(speed_anim*2), image1);
//           tt.setToX(960);
//           ScaleTransition ss = new ScaleTransition(Duration.seconds(speed_anim*6), image1);
//           ss.setToX(0.2);
//           ss.setToY(0.2);
//           RotateTransition rr = new RotateTransition(Duration.seconds(speed_anim*4), image1);
//           rr.setToAngle(360);
//           rr.setCycleCount(RotateTransition.INDEFINITE);
//           ParallelTransition p = new ParallelTransition(tt, ss , rr);
//           p.play();
//           ss.setOnFinished((e)->{
//           FadeTransition f = new FadeTransition(Duration.seconds(speed_anim), image1);
//           f.setToValue(0);
//           f.play();
//           });

//try {
//                File file = new File("E:\\Netbeans soundteack\\rocket_sound.wav");
//                clip = AudioSystem.getClip();
//                clip.open(AudioSystem.getAudioInputStream(file));
//                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                volume.setValue(-6);
//                clip.start();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            }
//            TranslateTransition ttone = new TranslateTransition(Duration.seconds(3), rocket);
//            ttone.setToX(2500);
//            TranslateTransition tttwo = new TranslateTransition(Duration.seconds(3), chain);
//            tttwo.setToX(2500);
//            ParallelTransition pause5 = new ParallelTransition(ttone, tttwo);
//            pause5.play();
//            PauseTransition pause6 = new PauseTransition(Duration.seconds(1.85));
//            pause6.play();
//            pause6.setOnFinished((r) -> {
//                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(2), image1);
//                tt2.setToX(1920);
//                tt2.play();
        // } else if (d > 0) {
//
//        }
    }

    @FXML
    private void get_started_btn_clicked(ActionEvent event) {
        double time = 3;//10
        FadeTransition onef = new FadeTransition(Duration.seconds(2), Features_panel);
        onef.setToValue(0);
        onef.play();
        onef.setOnFinished((f1) -> {
            WebEngine we = webview.getEngine();
            we.load("https://www.google.co.in/");
            Features_panel.setVisible(false);
            PauseTransition pause1 = new PauseTransition(Duration.seconds(time));
            PauseTransition pause2 = new PauseTransition(Duration.seconds(time + 1));
            PauseTransition pause3 = new PauseTransition(Duration.seconds(time + 1));
            PauseTransition pause4 = new PauseTransition(Duration.seconds(1));
            pause1.play();
            pause1.setOnFinished((p11) -> {//image1 scrolled
                try {
                    File file = new File("E:\\Netbeans soundteack\\rocket_sound.wav");
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(-6);
                    clip.start();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                TranslateTransition ttone = new TranslateTransition(Duration.seconds(3), rocket);
                ttone.setToX(2500);
                TranslateTransition tttwo = new TranslateTransition(Duration.seconds(3), chain);
                tttwo.setToX(2500);
                ParallelTransition pause5 = new ParallelTransition(ttone, tttwo);
                pause5.play();
                PauseTransition pause6 = new PauseTransition(Duration.seconds(1.85));
                pause6.play();
                pause6.setOnFinished((r) -> {
                    TranslateTransition tt2 = new TranslateTransition(Duration.seconds(2), image1);
                    tt2.setToX(1920);
                    tt2.play();
                    pause2.play();

                    pause2.setOnFinished((p22) -> {//image2 scrolled
                        TranslateTransition tt = new TranslateTransition(Duration.seconds(2), ufo_right);
                        tt.setToX(-1920);
                        tt.play();
                        try {
                            File file = new File("E:\\Netbeans soundteack\\down.wav");
                            clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            volume.setValue(1);
                            clip.start();

                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        tt.setOnFinished((t) -> {
                            FadeTransition ff = new FadeTransition(Duration.seconds(2), ufo_right);
                            ff.setToValue(0);
                            FadeTransition ff1 = new FadeTransition(Duration.seconds(2), ufo_left);
                            ff1.setToValue(1);
                            ParallelTransition p = new ParallelTransition(ff, ff1);
                            p.play();
                            p.setOnFinished((f) -> {//sound of beam---------------------------------------------------
                                TranslateTransition t2 = new TranslateTransition(Duration.seconds(1), image2);
                                t2.setToY(-100);

                                FadeTransition f2 = new FadeTransition(Duration.seconds(2), image2);
                                f2.setToValue(0);
                                ParallelTransition p1 = new ParallelTransition(t2, f2);
                                p1.play();
                                p1.setOnFinished((i) -> {
                                    image2.setVisible(false);
                                    FadeTransition ff3 = new FadeTransition(Duration.seconds(0.5), ufo_right);
                                    ff3.setToValue(1);
                                    FadeTransition ff13 = new FadeTransition(Duration.seconds(0.5), ufo_left);
                                    ff13.setToValue(0);
                                    ParallelTransition p3 = new ParallelTransition(ff3, ff13);
                                    p3.play();
                                    p3.setOnFinished((o) -> {
                                        try {
                                            File file = new File("E:\\Netbeans soundteack\\down.wav");
                                            clip = AudioSystem.getClip();
                                            clip.open(AudioSystem.getAudioInputStream(file));
                                            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                                            volume.setValue(1);
                                            clip.start();

                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                                        TranslateTransition tt5 = new TranslateTransition(Duration.seconds(2), ufo_right);
                                        tt5.setToX(-3840);
                                        tt5.play();
                                        pause3.play();
                                        pause3.setOnFinished((eee) -> {//IMAGE3 SCROLLED
                                            TranslateTransition ttq = new TranslateTransition(Duration.seconds(speed_anim * 4), image3);
                                            ttq.setToX(1920);
                                            ScaleTransition ssq = new ScaleTransition(Duration.seconds(speed_anim * 4), image3);
                                            ssq.setToX(0.2);
                                            ssq.setToY(0.2);
                                            RotateTransition rrq = new RotateTransition(Duration.seconds(speed_anim * 4), image3);
                                            rrq.setToAngle(360);
                                            rrq.setCycleCount(RotateTransition.INDEFINITE);
                                            ParallelTransition pq = new ParallelTransition(ttq, ssq, rrq);
                                            pq.play();
                                            ssq.setOnFinished((e) -> {
                                                FadeTransition fq = new FadeTransition(Duration.seconds(speed_anim), image3);
                                                fq.setToValue(0);
                                                fq.play();
                                                pause4.play();
                                                pause4.setOnFinished((pw) -> {//IMAGE4 SCROLLED
                                                    TranslateTransition ttr = new TranslateTransition(Duration.seconds(speed_anim * 4), image4);
                                                    ttr.setToY(-1080);
                                                    ScaleTransition ssr = new ScaleTransition(Duration.seconds(speed_anim * 4), image4);
                                                    ssr.setToX(0.2);
                                                    ssr.setToY(0.2);
                                                    RotateTransition rrr = new RotateTransition(Duration.seconds(speed_anim * 4), image4);
                                                    rrr.setToAngle(360);
                                                    rrr.setCycleCount(RotateTransition.INDEFINITE);
                                                    ParallelTransition pr = new ParallelTransition(ttr, ssr, rrr);
                                                    pr.play();
                                                    ssr.setOnFinished((er) -> {
                                                        FadeTransition fr = new FadeTransition(Duration.seconds(speed_anim), image4);
                                                        fr.setToValue(0);
                                                        fr.play();
                                                        //fr.setOnFinishedEvent
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });

        });
    }

    @FXML
    private void reply_incoming(KeyEvent event) {
        Satellite.setVisible(true);
        Ellipse e1 = new Ellipse(1700.0f, 900.0f);
        e1.setCenterX(0.0f);
        e1.setCenterY(900.0f);
        PathTransition pp3 = new PathTransition(Duration.seconds(10), e1);
        pp3.setPath(e1);
        pp3.setNode(Satellite);
        pp3.setCycleCount(PathTransition.INDEFINITE);
        pp3.play();
        RotateTransition r = new RotateTransition(Duration.seconds(30), Satellite);
        r.setToAngle(360);
        r.play();
        FadeTransition fff = new FadeTransition(Duration.seconds(2), Finger_print_anchor);
        fff.setToValue(0);
        fff.play();
        fff.setOnFinished((u) -> {
            RotateTransition r3 = new RotateTransition(Duration.seconds(200), earth_image);
            r3.setToAngle(360);
            r3.setCycleCount(RotateTransition.INDEFINITE);
            r3.play();
            TranslateTransition st = new TranslateTransition(Duration.seconds(speed), earthAnchor);
            st.setToY(-850);
            TranslateTransition st1 = new TranslateTransition(Duration.seconds(speed), SpaceAnchor);
            st1.setToY(-850);
            ParallelTransition p = new ParallelTransition(st, st1);
            p.play();
            try {
                File file = new File("E:\\Netbeans soundteack\\countdonw.wav");
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(1);
                clip.start();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            PauseTransition pp = new PauseTransition(Duration.seconds(20));
            pp.play();
            pp.setOnFinished((t) -> {
                //ignition
                try {
                    File file = new File("E:\\Netbeans soundteack\\launch.wav");
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(file));
                    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(5);
                    clip.start();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                ScaleTransition sc = new ScaleTransition(Duration.seconds(10), earthAnchor);
                sc.setToX(5);
                sc.setToY(5);
                sc.play();
                PauseTransition pp2 = new PauseTransition(Duration.seconds(4));
                pp2.play();
                pp2.setOnFinished((t2) -> {
                    FadeTransition ff = new FadeTransition(Duration.seconds(2), MainPanel);
                    ff.setToValue(0);
                    ff.play();
                    ff.setOnFinished((e) -> {
                        clip.stop();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                            Scene scene = new Scene(root);
                            Stage curStage = (Stage) MainPanel.getScene().getWindow();
                            curStage.setScene(scene);
                        } catch (IOException ex) {
                            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });
                });

            });
        });

    }

}
