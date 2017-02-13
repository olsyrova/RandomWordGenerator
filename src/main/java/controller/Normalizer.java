package controller;

/**
 * Created by olgasyrova on 13.02.17.
 */
public class Normalizer {
    public static String normalize(String query){
        String cleanedText =  query
                .replaceAll("[-\\.,:;*+\\'^•„=|\\(\\){}\\[\\]«»]", " ")
                .replaceAll("—", " ")
                .replaceAll("[\\d+]", "")
                .replaceAll("( )+", " ")
                .replaceAll("[，）（：“一；、。──》]", " ")

                .trim();
        return cleanedText;
    }
}
