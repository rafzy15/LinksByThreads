import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by marcin on 11/11/14.
 */
public class ThreadTest implements ActionListener {
    JButton jButton;
    JButton jButton1;

   public static void main(String args[]){
       Elements links = new Elements();
       ThreadTest threadTest = new ThreadTest();
       threadTest.createComponents();

   }

    public static void linksGet(){
        try{
            for(int i=0;i<50;i++) {
                Document doc = Jsoup.connect("http://allegro.pl").get();
            }

        }catch(IOException e){

            e.printStackTrace();
        }

    }
    public void createComponents(){
        JFrame jFrame = new JFrame();
        jButton= new JButton("bez watkami");
        jButton1 = new JButton("z watkow");

        jButton.addActionListener(this);
        jButton1.addActionListener(this);

        jFrame.getContentPane().add(BorderLayout.WEST,jButton);
        jFrame.getContentPane().add(BorderLayout.EAST,jButton1);


        jFrame.setVisible(true);
        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent action) {
//        System.out.println(action.getSource());
        if(action.getSource() == jButton){
            System.out.println("licze bez watkow");
            long tStart = System.currentTimeMillis();

            linksGet();
            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedSeconds = tDelta / 1000.0;
            System.out.print("czas bez watkow "+ elapsedSeconds);
        }else if(action.getSource() == jButton1) {
            GetByThreads getByThreads = new GetByThreads();
            Thread thread1 = new Thread(getByThreads);
            Thread thread2 = new Thread(getByThreads);

            System.out.println("licze z watkami");

            long tStart = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedSeconds = tDelta / 1000.0;
            System.out.print("czas z 2 watkami " + elapsedSeconds);
        }


    }
}
