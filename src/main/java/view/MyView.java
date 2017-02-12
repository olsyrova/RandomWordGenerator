package view;

import model.RandomWordProducer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by olgasyrova on 11.02.17.
 */
public class MyView extends JFrame {

    private BlockingQueue sharedQueue;
    private JTextField sentence1;
    private JTextField sentence2;
    private JTextField sentence3;


    public MyView(BlockingQueue sharedQueue){

        this.sharedQueue = sharedQueue;

        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new GridLayout(3, 0));
        this.setSize(500, 500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(false);

        sentence1 = createTextField();
        sentence2 = createTextField();
        sentence3 = createTextField();
        this.add(sentence1);
        this.add(sentence2);
        this.add(sentence3);

        Timer updater = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                updateSentences(sentence1, sentence2, sentence3);

            }
        });

        updater.start();


        this.setVisible(true);

    }

    private static JTextField createTextField(){
        JTextField sentence1  = new JTextField();
        sentence1.setEditable(false);
        sentence1.setBackground(Color.BLACK);
        sentence1.setBorder(BorderFactory.createCompoundBorder(
                sentence1.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        sentence1.setFont(new Font("Serif", Font.BOLD, 22));
        sentence1.setForeground(Color.WHITE);
        return sentence1;
    }

    private void updateSentences(JTextField sentence1, JTextField sentence2, JTextField sentence3){
        if (!sharedQueue.isEmpty() && sharedQueue.size() >= 20){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateSentence(sentence1, 5);
            updateSentence(sentence2, 7);
            updateSentence(sentence3, 5);
        }
    }
    public void updateSentence(JTextField sentence1, int numberWords) {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < numberWords; i++){
            sentence.append(sharedQueue.poll()).append(" ");

        }
        sentence1.setText(sentence.toString());

    }

    public static void main(String[] args) throws InterruptedException {
        //Creating shared object
        BlockingQueue sharedQueue = new LinkedBlockingQueue(100);

        //Creating Producer and Consumer Thread
        Thread prodThread = new Thread(new RandomWordProducer(sharedQueue));

        //Starting producer and Consumer thread
        prodThread.start();
        MyView myView = new MyView(sharedQueue);
    }

}
