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
//        for (int i = 0; i < 25;i++){
        linksURL();
//        }
        tEnd = System.currentTimeMillis();
        System.out.println("liczba " + getNumberOfLinks() +"czas " + computeTime());

    }
    public void linksURL() {


        try {

            Document doc = Jsoup.connect("http://allegro.pl/listing/listing.php?bmatch=seng-v0-e-1021&limit=180&order=m&string=samsung").get();
            numberOfLinks++;
            links = doc.select("a[href*=html]");
            for (Element link : links) {
                System.out.println("\n"+link.attr("href"));
            }

        } catch (IOException e) {
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
