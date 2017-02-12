package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olgasyrova on 11.02.17.
 */
public class RandomWordProducer implements Runnable {
    private final BlockingQueue sharedQueue;

    public RandomWordProducer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        while (true){
            try {
                List<String> words = new ArrayList<>();
                for (int i = 0; i < 30; i++){
                    words.add(WikiRandomPageGenerator.parseRandomWikiPage());
                }
                for (String word : words){
                    sharedQueue.put(word);
                    System.out.println("Produced: " + word);
                }


            } catch (InterruptedException ex) {
                Logger.getLogger(RandomWordProducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
