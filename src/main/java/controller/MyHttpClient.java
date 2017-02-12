package controller;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by olgasyrova on 11.02.17.
 */
public class MyHttpClient {
    private static final String httpProtocol = "https://";
    private static final String wikiUrl = ".wikipedia.org/wiki/Special:Random";


    MyHttpClient() {
    }

    private static String buildWikiUrl(String language) {
        return httpProtocol + language + wikiUrl;
    }

    public static String getRandomWikiPage(String language) {
        return sendRequest(buildWikiUrl(language));
    }

    public static String sendRequest(String url) {
        StringBuilder result = new StringBuilder();

        try {
            HttpClient client = HttpClientBuilder.create().build();

            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(500)
                    .build();
            httpGet.setConfig(requestConfig);

            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent())
                );

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}
