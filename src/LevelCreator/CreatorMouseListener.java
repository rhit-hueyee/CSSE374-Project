package LevelCreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreatorMouseListener implements MouseListener {
	LevelCreatorComponent creator;
	
	public CreatorMouseListener(LevelCreatorComponent creator)
	{
		this.creator=creator;
	}
	
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
