package scene.model;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import scene.Constants;

public class VerticalBoxList extends BoxList{
	protected int _vStrut, _hStrut;
	
	public VerticalBoxList() {
		super(BoxLayout.Y_AXIS);
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		_items = new ArrayList<>();
		_hStrut = 5;
		_vStrut = 5;
		_activateFiller = false;
	}
	
	@Override
	public void initListWithItems(List<JComponent> items) {
		_items = items;
		addItemsToBox();
	}
	
	@Override
	protected void initList() {
		
	}
	
	@Override
	protected void addItemsToBox() {
		add(Box.createVerticalStrut(5));
		for(var item: _items) {
			add(Box.createHorizontalStrut(_hStrut));
			item.setAlignmentX(LEFT_ALIGNMENT);
			add(item);
			add(Box.createHorizontalStrut(_hStrut));
			add(Box.createVerticalStrut(_vStrut));
		}
		
		if(_activateFiller) {
			Dimension minSize = new Dimension(0,5);
			Dimension prefSize = new Dimension(0, Constants.PRIMARY_FRAME_HEIGHT / 4);
			Dimension maxSize = new Dimension(0, Constants.PRIMARY_FRAME_HEIGHT / 2);
			add(new Box.Filler(minSize, prefSize, maxSize));
		}
	}
	
	public void addItem(JComponent c) {
		_items.add(c);
		
		this.removeAll();
		
		initListWithItems(_items);
		
		revalidate();
		repaint();
	}
	
	@Override
	public void removeItem(JComponent c) {
		_items.remove(c);
		
		this.removeAll();
		
		initListWithItems(_items);
		
		revalidate();
		repaint();
	}
	
	public void removeAllItemsAndRefresh() {
		this.removeAll();
		
		initListWithItems(_items);
		
		revalidate();
		repaint();
	}
	
	public void setCellPadding(int hStrut, int vStrut) {
		_vStrut = vStrut;
		_hStrut = hStrut;
	}
	
	
}
