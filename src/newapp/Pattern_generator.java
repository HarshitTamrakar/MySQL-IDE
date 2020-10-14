package newapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javafx.scene.control.TextArea;
import javax.swing.JTextArea;

/**
 *
 * @author 0kl
 */
public class Pattern_generator {

    public void pattern1(List<Integer> abc, TextArea t) {
        String Table_boundary = "+";
        for (int i : abc) {
            for (int j = 0; j <= i + 1; j++) {
                Table_boundary = Table_boundary + "-";

            }
            Table_boundary += "+";
        }
        t.appendText(Table_boundary);
        t.appendText("\n");
    }

    public void pattern2(List<Integer> length, List<String> data, TextArea t) {
       String pp = "|";

        for (int i = 0; i < data.size(); i++) {
            pp+=" " + data.get(i) + " ";
            for (int j = 0; j < length.get(i) - data.get(i).length(); j++) {
                pp+=" ";
            }
            pp+="|";
        }
        t.appendText(pp+"\n");

    }

}
