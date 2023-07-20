package mainApp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class KeyPressedCounterProxy implements KeyListener {
	private KeyHandler keyHandler;
	private int keyPressCount = 0;
	
	public KeyPressedCounterProxy(ScreenComponent screen) {
		this.keyHandler = new KeyHandler(screen);
	}
	
    @Override
    public void keyTyped(KeyEvent e) {
        keyHandler.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressCount++;
        keyHandler.keyPressed(e);
        saveKeyPressCountToFile();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyHandler.keyReleased(e);
    }

    private void saveKeyPressCountToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("key_press_count.txt"))) {
            writer.write(String.valueOf(keyPressCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
