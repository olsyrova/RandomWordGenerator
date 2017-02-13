/**
 * Created by olgasyrova on 12.02.17.
 *
 * https://translate.yandex.net/api/v1.5/tr.json/translate ?
   key=<API key> trnsl.1.1.20170213T213623Z.c123df3cca69f0fc.20392e9eb413db2fd62bc82fae1a7777d3c753a3
 & text=<text to translate>
 & lang=<translation direction>
 & [format=<text format>]
 & [options=<translation options>]
 & [callback=<name of the callback function>]
 */

import controller.MyHttpClient;

/**
 * translate to other language
 */

public class YandexTranlateClientAPI {

    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private static final String API_KEY = "trnsl.1.1.20170213T213623Z.c123df3cca69f0fc.20392e9eb413db2fd62bc82fae1a7777d3c753a3";
    private static final String TEXT = "&text=";
    private static final String LANG = "&lang=";

    private static String buildUrl(String query, String targetLanguage){
        return URL + API_KEY + TEXT + query + LANG + targetLanguage;
    }

    public static String yandexTranslate(String query, String language){
        String result = MyHttpClient.sendRequest(buildUrl(query, language));
        return result;
    }

    public static void main(String[] args){
        String query = "apple";
        String language = "ru";
        String result = yandexTranslate(query, language);
        System.out.println(result);
    }

    // TO DO get all supported languages
}
