package jdiadev.interactive;

import diamond.run.core.model.Value;
import diamond.run.environment.Scope;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdiadev.ValueTreeItem;

public class InteractionView extends BorderPane {
	
	public InteractionView(Scope scope, Value value){
		SplitPane pane = new SplitPane();
		
		pane.getItems().add(new TreeView<>(new ValueTreeItem(scope, value)));
		ListView<Button> actions = new ListView<>();
		
		Button takeString = new Button("Take string");
		takeString.setOnAction((e) -> {
			Value v = new StringCallDialog(scope, value).showAndCall();
			Stage s = new Stage();
			s.setScene(new Scene(new InteractionView(scope, v)));
			s.show();
		});
		actions.getItems().add(takeString);
		
		Button takeNumber = new Button("Take number");
		takeNumber.setOnAction((e) -> {
			Value v = new NumberCallDialog(scope, value).showAndCall();
			Stage s = new Stage();
			s.setScene(new Scene(new InteractionView(scope, v)));
			s.show();
		});
		actions.getItems().add(takeNumber);
		
		pane.getItems().add(actions);
		
		setCenter(pane);
		setBottom(new Label("Java Class: " + value.get().getClass()));
	}
	
}
