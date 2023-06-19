package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomFrame extends JFrame implements Runnable {

    private String myMessage="ciap";
    private final int NOfRects;
    private int Yimage = 50;

    //Thread thread;
    public CustomFrame() {
        this.NOfRects = 5;
        //this.thread = new Thread(this);
        //this.thread.start();
    }
    public void paint(Graphics g) {
        super.paint(g);

        g.drawString(myMessage, 200,50);
        int w = getWidth();
        int h = getHeight();

        int xc = w/2;
        int yc = h/2;

        int rectwidth = 50;
        int rectheight = 100;

        Color red = Color.red;
        g.setColor(red);
        for (int i = 0; i < getNOfRects(); i++) {

            rectwidth+=30;
            rectheight+=30;
            int x = xc - rectwidth/2;
            int y = yc - rectheight/2;
            g.drawRect(x, y, rectwidth, rectheight);
        }

        myDrawImage(g, Yimage);
    }

    public int getNOfRects() {
        return NOfRects;
    }

    private void myDrawImage(Graphics g,int Yimage) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("pika.png");
        BufferedImage img= null;
        try {
            assert url != null;
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 50,Yimage, 150,150, null);

    }

    public void run(){
        /*
        while(true){
            Yimage +=20;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }*/

    }

    public void updateMessage(String a){

        SwingUtilities.invokeLater(() -> {
            this.myMessage = a;
            this.repaint();
        });
        System.out.println(myMessage);
    }
}