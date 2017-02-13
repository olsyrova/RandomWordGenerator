package model;

import controller.MyHttpClient;
import controller.Normalizer;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olgasyrova on 11.02.17.
 */
public class WikiRandomPageGenerator {
    static List<String> languages;

    static {
        languages = getLanguages();
    }

    WikiRandomPageGenerator(){
    }

    private static List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        try {
            languages = Files.readAllLines(Paths.get("wikipedia_languages.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return languages;
    }

    public static String parseRandomWikiPage(){
        return parseRandomWikiPageForLang(selectRandomLanguage());
    }

    private static String selectRandomLanguage(){
        return languages.get(getRandomIndexInRange(languages.size()));
    }

    private static int getRandomIndexInRange(int range){
        return ThreadLocalRandom.current().nextInt(0, range);
    }

    private static String parseRandomWikiPageForLang(String language){
        String wikiArticle = MyHttpClient.getRandomWikiPage(language);
        return getRandomWordFromArticle(wikiArticle);
    }

    private static String getRandomWordFromArticle(String wikiArticle){
        String text = Jsoup.parse(wikiArticle).body().select("p").text();
        return getRandomWord(Normalizer.normalize(text).split(" "));

    }

    private static String getRandomWord(String[] strings){
        String result = "";
        if (strings.length > 2){
            result = strings[getRandomIndexInRange(strings.length)];
            if (result.length() < 2){
                System.out.println("length smalle than 1 " + result + " strings were " + Arrays.toString(strings));
                result = getRandomWord(strings);

            }
        }

        return result;
    }


}
