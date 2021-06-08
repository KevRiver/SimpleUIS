package scene.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.*;

import scene.Constants;

public class QueryResultTable extends VerticalBoxList{
	final int labelWidth = Constants.PRIMARY_FRAME_WIDTH / 20;
	final int labelHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
	final int labelFontSize = 12;
	final Font labelFont = new Font("Consolas", Font.BOLD, labelFontSize);
	
	public QueryResultTable() {	
		_items = new ArrayList<>();
	}
	
	public void initListWithQueryResult(List<Map<String, Object>> mappedResult, ResultTableType type) throws Exception{
		try {
			switch(type) {
			case TABLE:
				convertDataToRows(mappedResult);
				break;
			case COURSE_EXTEND_TO_STUDENT:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));

			case COURSE_EXTEND_TO_GRADE_EDITOR:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));

			case GRADE_EDITOR:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));

			case STUDENT_EXTEND_TO_GRADE:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));

			case TIME_TABLE:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));

			default:
				throw new Exception(String.format("TYPE %s is not defined", type.toString()));
			}
			
			this.initListWithItems(_items);
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void convertDataToRows(List<Map<String, Object>> results) {
		int rowCount = results.size();
		Map<String, Object> firstRowData = results.get(0);
		List<String> columnKeys = getSortedColumnKeys(firstRowData);
		
		List<JComponent> firstRowEntities = new ArrayList<>();
		for(var columnKey: columnKeys) {
			JLabel columnLabel = new JLabel(columnKey);
			columnLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
			columnLabel.setFont(labelFont);
			columnLabel.setForeground(Color.WHITE);
			columnLabel.setHorizontalAlignment(JLabel.CENTER);
			firstRowEntities.add(columnLabel);
		} // create first row entities
		HorizontalBoxList firstRow = new HorizontalBoxList();
		firstRow.initListWithItems(firstRowEntities);
		_items.add(firstRow);
		
		for(int i = 1; i < rowCount; i++) {
			List<JComponent> rowEntities = new ArrayList<>();
			for(var columnKey: columnKeys) {
				Object data = results.get(i).get(columnKey);
				JLabel columnValue = new JLabel(data.toString());
				columnValue.setPreferredSize(new Dimension(labelWidth, labelHeight));
				columnValue.setFont(labelFont);
				columnValue.setForeground(Color.WHITE);
				columnValue.setHorizontalAlignment(JLabel.CENTER);
				rowEntities.add(columnValue);
			}
			HorizontalBoxList row = new HorizontalBoxList();
			row.initListWithItems(rowEntities);
			_items.add(row);
		}
	}
	
	private List<String> getSortedColumnKeys(Map<String, Object> firstRowData){
		List<String> columnKeys = firstRowData.keySet()
				.stream()
				.sorted((k1, k2) -> ((Integer)firstRowData.get(k1)).compareTo(((Integer)firstRowData.get(k2))))
				.collect(Collectors.toList());
		
		return columnKeys;
	}
	
}
