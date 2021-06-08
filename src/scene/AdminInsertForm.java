package scene;

public class AdminInsertForm extends QueryForm{
	private String queryFormat = "";
	
	public AdminInsertForm() {
		super();
		_paramNames.add("Table :");
		_paramNames.add("Attributes : ");
		addParamForm();
	}

}
