package scene;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

public class VerticalBoxList extends BoxList{
	protected int _vStrut, _hStrut;
	
	public VerticalBoxList() {
		super(BoxLayout.Y_AXIS);
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		_items = new ArrayList<>();
		_hStrut = 5;
		_vStrut = 5;
	}
	
	@Override
	public void initListWithItems(List<JComponent> items) {
		_items = items;
		initBox();
	}
	
	@Override
	protected void initList() {
		
	}
	
	protected void initBox() {
		addItemsToBox();
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
	}
	
	public void setCellPadding(int hStrut, int vStrut) {
		_vStrut = vStrut;
		_hStrut = hStrut;
	}
}
