package jdiadev.interactive;

import java.util.Optional;

import diamond.run.core.model.Value;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public abstract class AbstractCallDialog extends TextInputDialog {

	protected Value value;

	public AbstractCallDialog(Value v) {
		this.value = v;
	}
	
	public Value showAndCall(){
		Optional<String> o = showAndWait();
		if(o.isPresent()){
			try{
				return interpretAndCall(o.get());
			}catch(RuntimeException e){
				Alert err = new Alert(AlertType.ERROR);
				err.setContentText(e.getMessage());
				err.show();
				return null;
			}
		}else{
			return null;
		}
	}

	protected abstract Value interpretAndCall(String in);

}
