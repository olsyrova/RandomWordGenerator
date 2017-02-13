import controller.MyHttpClient;
import controller.Normalizer;
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

    String charset = "UTF-8";
    private static final String HTTP = "http://google.";
    private static final String QUERY = "/search?q=";
    private static final String NUMBER_RESULTS = "&num=10";
    //private static final String USER_AGENT = "ExampleBot 1.0 (+http://example.com/bot)";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
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
            String result = Jsoup.connect(url).userAgent(USER_AGENT).get().body().select("div#ires").text();
            System.out.println(Normalizer.normalize(result));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
