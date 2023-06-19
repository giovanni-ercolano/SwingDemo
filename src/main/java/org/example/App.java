package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App 
{
    private static CustomFrame customframe = null;
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
        StartClient();
        //StartClient();
    }
    private static void createAndShowGUI() {

            //Create and set up the window.
        customframe = new CustomFrame();
        customframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customframe.setSize(850,750);
        customframe.setVisible(true);

    }

    private static void StartClient(){
        String hostName = "127.0.0.1";
        int portNumber = 1234;
        Socket echoSocket = null;
        try {
            echoSocket = new Socket(hostName, portNumber);
        } catch (IOException e) {
            System.out.println("cannot reach server "+e);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("cannot allocate bufferedreader");
        }
        readLoop(out, in);
    }

    private static void readLoop(PrintWriter out, BufferedReader in){
        String s = "";
        try {
            while ((s = in.readLine()) != null) {

                //System.out.println(s);
                if(customframe!=null){
                    customframe.updateMessage(s);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

