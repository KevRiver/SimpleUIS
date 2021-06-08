package scene;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class HorizontalBoxList extends BoxList{
protected int _vStrut, _hStrut;
	
	public HorizontalBoxList() {
		super(BoxLayout.X_AXIS);
		_items = new ArrayList<>();
		_hStrut = 5;
		_vStrut = 5;
	}
	
	@Override
	public void initListWithItems(List<JComponent> items) {
		_items = items;
		addItemsToBox();
	}
	
	public void setCellPadding(int hStrut, int vStrut) {
		_vStrut = vStrut;
		_hStrut = hStrut;
	}
	
	@Override
	protected void addItemsToBox() {
		for(var item: _items) {
			item.setAlignmentX(RIGHT_ALIGNMENT);
			add(item);
			add(Box.createHorizontalStrut(_hStrut));
		}
	}
	
	@Override
	protected void initList() {
		
	}
	
}
