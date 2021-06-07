package scene;

import java.awt.*;
import java.awt.List;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.*;
import javax.swing.*;

public class GridBagList extends GridBagPanel{
	private ArrayList<JComponent> _items;
	
	private Dimension defaultSize;
	private Point2D.Double defaultWeight;
	private int defaultAlignment;
	private int fillPolicy;
	
	public GridBagList() {
		_items = new ArrayList<>();
		_items.ensureCapacity(20);
		
		defaultSize = new Dimension(1, 1);
		defaultWeight = new Point2D.Double(1.0, 0.0);
		defaultAlignment = GridBagConstraints.FIRST_LINE_START;
		fillPolicy = GridBagConstraints.NONE;
		this.setGridCellPadding(5, 5, 0, 5);
	}
	
	public void setItems(ArrayList<JComponent> components) {
		_items.clear();
		_items = components;
		attachAllItems();
	}
	
	private void updateList() {
		try {
			detachAllItems();
			attachAllItems();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			this.revalidate();
			this.repaint();
		}
	}
	
	private void detachAllItems() throws Exception {
		for(var item : _items) {
			this.remove(item);
		}
	}
	
	private void attachAllItems(){
		int lastIndex = _items.size() - 1;
		Point2D.Double weight = defaultWeight;
		for(int i = 0; i < _items.size(); i++) {
			// add components from above
			if(i == lastIndex) {
				weight = new Point2D.Double(1.0, 0.9);
				this.setGridCellPadding(5, 5, 5, 5);
			}
			
			this.addComponentAtGrid(_items.get(i), 
								new Point(0, i), 
								defaultSize, 
								weight, 
								defaultAlignment, 
								fillPolicy);
		}
	}
	
	
	
}
