import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by marcin on 11/11/14.
 */
public class GetByThreads implements Runnable{
    private Elements links;
    long tStart;
    long tEnd;
    int numberOfLinks = 0;
    public void run() {
        tStart= System.currentTimeMillis();
        for (int i = 0; i < 25;i++){
            linksURL();
        }
        tEnd = System.currentTimeMillis();
        System.out.println("liczba " + getNumberOfLinks() +"czas " + computeTime());

    }
    public synchronized void linksURL(){


        try{
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

                Document doc = Jsoup.connect("http://allegro.pl").get();
                numberOfLinks++;

//            links = doc.select("a[href]");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public int getNumberOfLinks(){
        return numberOfLinks;
    }
    public double computeTime(){

        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }
}
