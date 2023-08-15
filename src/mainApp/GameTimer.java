package mainApp;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameTimer implements ActionListener {
    private JFrame frame;
    private Timer timer;
    private int timeRemaining;

    public GameTimer(JFrame frame, int initialTime) {
        this.frame = frame;
        this.timeRemaining = initialTime;
        this.timer = new Timer(1000, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (timeRemaining > 0) {
            updateTitle();
            timeRemaining--;
        } else {
            timer.stop();
            gameOver();
        }
    }

    private void updateTitle() {
        frame.setTitle("Time Remaining: " + timeRemaining);
    }

//    private void gameOver() {
//        frame.setTitle("Game Over");
//        openNameInputFrame();
//    }

    private void openNameInputFrame() {
        // TODO: Implement the code to open a new JFrame for player's name input.
        // You can add JTextField and JButton to get the player's name and save it to a file.
        // Remember to add action listeners for the button to handle the saving process.
        // You can also use the observer pattern to notify the MainApp when the player's name is saved.
    }
    
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void gameOver() {
        frame.setTitle("Game Over");
        for (Observer observer : observers) {
           // observer.onGameOver(); // Notify all observers that the game is over
        }
        openNameInputFrame();
    }
}

