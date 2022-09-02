// Illustrates how to do an HTTP GET

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class URLTester {



    public static void main(String[] args) throws IOException {
        String urlString = "https://www.wunderground.com/weather/ZYTL";

        URL u = new URL(urlString);
        URLConnection connection = u.openConnection();


        HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
        int code = httpsConnection.getResponseCode();

        String message = httpsConnection.getResponseMessage();
        System.out.println(code + " " + message);
        if (code != HttpURLConnection.HTTP_OK) {
            return;
        }

        InputStream instream = connection.getInputStream();
        Scanner in = new Scanner(instream);
        while (in.hasNextLine()) {
            //System.out.println(in.nextLine());
            System.out.println(Jsoup.parse(in.nextLine()).text());
        }



    }
}