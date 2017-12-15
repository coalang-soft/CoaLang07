package jdiadev;

import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;

public class VariableTreeItem extends TreeItem {

	private String name;
	private Scope scope;
	private Value value;

	public VariableTreeItem(Scope scope, String name) {
		this.scope = scope;
		this.name = name;
		this.value = scope.flatLookup(name);
		
		Label l = new Label(name);
		Color c;
		switch(value.getType()){
		case ARRAY: c = Color.LIGHTCORAL; break;
		case ARRAY_FUNCTION: c = Color.BLUE; break;
		case DEFAULT_SINGLE: c = Color.BLACK; break;
		case MACRO: c = Color.PURPLE; break;
		case SINGLE_FUNCTION: c = Color.LIGHTBLUE; break;
		default: c = Color.RED;
		}
		getChildren().add(new ValueTreeItem(scope, value));
		l.setTextFill(c);
		setValue(l);
	}

}
