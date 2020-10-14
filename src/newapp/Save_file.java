/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.control.TextArea;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author 0kl
 */
public class Save_file {

    public void save(TextArea t) {
        try {
            File dir = null;
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showSaveDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }
            String timeStamp = new SimpleDateFormat("dd-MM-yy HHmmss").format(Calendar.getInstance().getTime());
            System.out.println(timeStamp);
            File file = new File(dir, timeStamp + ".txt");
            FileWriter fw = null;
            try {
                JTextArea j = new JTextArea();
                j.append(t.getText());;
                fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);

                j.write(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }

        } catch (Exception e) {
        }
        int dialogButton = JOptionPane.YES_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "In order to view the text file in proper format. You will need monospace font. Do you want to donload it ?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {

            try {
                String url_open = "https://urban-fonts.s3.amazonaws.com/download/Fira_Mono.zip?AWSAccessKeyId=AKIAJMQJIRI6AYFKCXTA&Expires=1517044231&Signature=dzY7OjRD9X11FO4L0YXmstvjLRU%3D";
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
            } catch (Exception e) {
            }

        } else if (dialogResult == JOptionPane.NO_OPTION) {

        }
    }

}
