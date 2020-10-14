/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author MrAhuja
 */
public class Linux_Controller {

    public static List<String> executeCommand(String command) {
        List<String> output = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(command);
            Scanner scn = new Scanner(p.getInputStream());
            while (scn.hasNext()) {
                String s = scn.nextLine();
                output.add(s);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return output;
    }

    public String getIP() {
        List<String> l = executeCommand("ipconfig");
        int ch = 0;
        String i = "IPv4 Address. . . . . . . . . . . : ";
        int len = i.length();
        String ip = "";
        for (String x : l) {
            if (x.equals("Wireless LAN adapter Wi-Fi:")) {
                ch = 1;
            }
            if (ch == 1) {
                if (x.contains("IPv4 Address. . . . . . . . . . . : ")) {
                    ip = x.substring(len+3);
                    System.out.println("ip="+ip);
                    break;
                }
            }
            System.out.println(x);
        }
        return ip;

    }
}
