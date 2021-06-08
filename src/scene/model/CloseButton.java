package scene.model;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CloseButton extends JButton implements ActionListener {
	VerticalBoxList _removeFrom;
	JComponent _forRemove;
	
	public CloseButton(VerticalBoxList removeFrom, JComponent forRemove) {
		super();
		
		_removeFrom = removeFrom;
		_forRemove = forRemove;
		
		setText("close tab");
		setFont(new Font("Consolas", Font.BOLD, 12));
		
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((CloseButton)e.getSource() == this) {
			closeTab();
		}
	}
	
	public void closeTab() {
		_removeFrom.removeItem(_forRemove);
	}
}
