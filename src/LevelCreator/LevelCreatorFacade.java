package LevelCreator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import mainApp.Entity;

public class LevelCreatorFacade {
    private JFrame frame;
    private LevelCreatorComponent creator;
    private ArrayList<Entity> entities;
    private int drawState;

    public void run() {
        initializeComponents();
        setupUI();
        startTimer();
    }

    private void initializeComponents() {
        frame = new JFrame("Bomb Jack");
        frame.setSize(1000, 550);

        creator = new LevelCreatorComponent();
        entities = new ArrayList<>();
        drawState = 0;
    }

    private void setupUI() {
        frame.add(creator, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);

        JButton drawHero = new JButton("Draw Hero");
        drawHero.addActionListener(new DrawStateListener(0));
        panel.add(drawHero);

        JButton drawPlatform = new JButton("Draw Platform");
        drawPlatform.addActionListener(new DrawStateListener(1));
        panel.add(drawPlatform);

        JButton drawVerticalAlien = new JButton("Draw Vertical Alien");
        drawVerticalAlien.addActionListener(new DrawStateListener(2));
        panel.add(drawVerticalAlien);

        JButton drawHorizontalAlien = new JButton("Draw Horizontal Alien");
        drawHorizontalAlien.addActionListener(new DrawStateListener(3));
        panel.add(drawHorizontalAlien);

        JButton drawTrackerAlien = new JButton("Draw Tracker Alien");
        drawTrackerAlien.addActionListener(new DrawStateListener(4));
        panel.add(drawTrackerAlien);

        JButton drawBomb = new JButton("Draw Bomb");
        drawBomb.addActionListener(new DrawStateListener(5));
        panel.add(drawBomb);

        JButton save = new JButton("Save Level");
        save.addActionListener(new LevelSaveListener());
        panel.add(save);

        frame.addMouseListener((MouseListener) new CreatorMouseListener());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void startTimer() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creator.drawScreen();
            }
        });
        timer.start();
    }

    private class DrawStateListener implements ActionListener {
        private int drawState;

        public DrawStateListener(int drawState) {
            this.drawState = drawState;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            LevelCreatorFacade.this.drawState = drawState;
        }
    }

    private class CreatorMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            creator.handleMousePressedInput(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            creator.handleMouseReleasedInput(e.getX(), e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private class LevelSaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveLevel();
        }
    }

    private void saveLevel() {
        int level = 1;
        boolean fileAlreadyExists = true;
        while (fileAlreadyExists) {
            try {
                FileReader fReader = new FileReader("level" + level + ".txt");
                level++;
            } catch (FileNotFoundException e) {
                fileAlreadyExists = false;
            }
        }
        PrintWriter fWriter = null;

        try {
            fWriter = new PrintWriter("level" + level + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Entity e : entities) {
            String printString = e.getType();
            for (int i = 0; i < e.getFileValues().size(); i++) {
                printString += "," + e.getFileValues().get(i);
            }
            fWriter.println(printString);
        }

        fWriter.close();
    }
}
