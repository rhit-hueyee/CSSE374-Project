package mainApp;

import javax.swing.JLabel;

public class GameLabelObserver implements Observer {
    private JLabel label;
    private ScreenComponent screenComponent;

    public GameLabelObserver(ScreenComponent screenComponent, JLabel label) {
        this.screenComponent = screenComponent;
        this.label = label;
    }

    @Override
    public void update() {
        String lives = screenComponent.getLives();
        label.setText(lives);
    }
}
