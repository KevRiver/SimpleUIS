package scene;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*;

public class GridBagPanel extends JPanel{
	private GridBagConstraints _constraints;
	
	public GridBagPanel() {
		super.setLayout(new GridBagLayout());
		_constraints = new GridBagConstraints();
		_constraints.insets = new Insets(5,5,0,5);
	}
	
	public void addComponentAtGrid(Component component, Point gridPosition, Dimension size, Point2D.Double sizeRatio, int alignment, boolean fillUpCell) {
		try {
			_constraints.gridx = gridPosition.x;
			_constraints.gridy = gridPosition.y;
			
			_constraints.gridwidth = size.width;
			_constraints.gridheight = size.height;
			
			_constraints.weightx = sizeRatio.x;
			_constraints.weighty = sizeRatio.y;
			
			_constraints.fill = fillUpCell ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
			
			_constraints.anchor = alignment;
			
			add(component, _constraints);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void setGridCellPadding(int top, int left, int bottom, int right) {
		_constraints.insets = new Insets(top,left,bottom,right);
	}
}
