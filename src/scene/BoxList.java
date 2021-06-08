package scene;

import java.util.*;
import java.util.List;

import javax.swing.*;

public abstract class BoxList extends Box{
	protected List<JComponent> _items;
	
	protected BoxList(int axis) {
		super(axis);
		_items = new ArrayList<>();
		this.setOpaque(true);
	}
	
	public abstract void initListWithItems(List<JComponent> items);
	protected abstract void initList();
	protected abstract void addItemsToBox();
	
}
