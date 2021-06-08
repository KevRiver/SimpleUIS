package scene;

import java.awt.Dimension;
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
		Dimension minSize = new Dimension(5,0);
		Dimension prefSize = new Dimension(Constants.PRIMARY_FRAME_WIDTH / 4, 0);
		Dimension maxSize = new Dimension(Constants.PRIMARY_FRAME_WIDTH / 2, 0);
		add(new Box.Filler(minSize, prefSize, maxSize));
	}
	
	@Override
	protected void initList() {
		
	}
	
}
