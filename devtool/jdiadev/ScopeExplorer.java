package jdiadev;

import diamond.run.environment.Scope;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class ScopeExplorer extends BorderPane{
	
	private Scope scope;
	private TreeView<Scope> list;
	private Button update;

	public ScopeExplorer(Scope s){
		this.scope = s;
		this.setCenter(list = new TreeView<Scope>());
		this.setBottom(update = new Button("Update"));
		update.setOnAction((e) -> update());
		update();
	}

	private void update() {
		list.setRoot(new ScopeTreeItem(scope));
	}
	
}
