package jdiadev;

import java.lang.reflect.Field;

import diamond.run.core.model.Array;
import diamond.run.core.model.Value;
import javafx.scene.control.TreeItem;

public class ValueTreeItem extends TreeItem {

	private Value value;

	public ValueTreeItem(Value value) {
		this.value = value;
		this.setValue(value.get() + "");
		switch(value.getType()){
		case ARRAY: addArray(value.castArray()); break;
		}
	}

	private void addArray(Array a) {
		if(a.length() < 0){
			getChildren().add(new TreeItem("! Array length less than zero"));
		}else{
			for(int i = 0; i < a.length(); i++){
				getChildren().add(new ValueTreeItem(a.get(i)));
			}
		}
	}
	
}
