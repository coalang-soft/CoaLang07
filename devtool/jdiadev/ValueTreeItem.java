package jdiadev;

import java.lang.reflect.Field;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import javafx.scene.control.TreeItem;

public class ValueTreeItem extends TreeItem {

	private Value value;
	private Scope scope;

	public ValueTreeItem(Scope scope, Value value) {
		this.scope = scope;
		this.value = value;
		this.setValue(value.get() + "");
		switch(value.getType()){
		case ARRAY: addArray(value.castArray()); break;
		}
		getChildren().add(new InteractiveTreeItem(scope, value));
	}

	private void addArray(Array a) {
		if(a.length() < 0){
			getChildren().add(new TreeItem("! Array length less than zero"));
		}else{
			for(int i = 0; i < a.length(); i++){
				getChildren().add(new ValueTreeItem(scope, a.get(i)));
			}
		}
	}
	
}
