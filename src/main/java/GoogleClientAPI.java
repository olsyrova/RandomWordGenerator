import controller.MyHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by olgasyrova on 12.12.16.
 */
public class GoogleClientAPI {

    private static final String HTTP = "http://google.";
    private static final String QUERY = "/search?q=";
    private static final String NUMBER_RESULTS = "&num=10";
    private static final String USER_AGENT = "ExampleBot 1.0 (+http://example.com/bot)";
    private static final String ENCODING = "UTF-8";


    public static void main(String[] args){
        queryGoogle("de", "apple");
    }


    private static String buildUrl(String country, String query){
        return HTTP + country + QUERY + query + NUMBER_RESULTS;
    }

    public static void queryGoogle(String country, String query){
        String url = buildUrl(country, query);
        try {
            String result = Jsoup.connect(url).userAgent(USER_AGENT).get().body().text();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String result = MyHttpClient.sendRequest(url);
        //String text = Jsoup.parse(result).body().text();
        //System.out.println(text);
    }


}
