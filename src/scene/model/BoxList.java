package scene.model;

import java.util.*;
import java.util.List;

import javax.swing.*;

public abstract class BoxList extends Box{
	protected List<JComponent> _items;
	protected boolean _activateFiller;
	
	protected BoxList(int axis) {
		super(axis);
		_items = new ArrayList<>();
		this.setOpaque(true);
		_activateFiller = false;
	}
	
	public abstract void initListWithItems(List<JComponent> items);
	
	protected abstract void initList();
	
	protected abstract void addItemsToBox();
	
	public abstract void removeItem(JComponent forRemove);
	
	public void activateFiller(boolean isActive) {
		_activateFiller = isActive;
	}
	
	
	
}
