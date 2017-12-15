package jdiadev;

import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import jdiadev.interactive.InteractionView;

public class InteractiveTreeItem extends TreeItem<Button> {

	public InteractiveTreeItem(Scope scope, Value value) {
		Button b = new Button("Interact");
		b.setOnAction((e) -> {
			Stage s = new Stage();
			s.setScene(new Scene(new InteractionView(scope, value)));
			s.show();
		});
		setValue(b);
	}

}
