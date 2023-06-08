package LevelCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawStateListener implements ActionListener {
	private int drawState;
	private LevelCreatorComponent creator;
	
	public DrawStateListener(int drawState, LevelCreatorComponent creator)
	{
		this.drawState=drawState;
		this.creator=creator;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		creator.setDrawState(drawState);
	}

}
