package jdiadev;

import diamond.run.environment.Scope;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class ScopeExplorer extends BorderPane{
	
	private Scope scope;
	private TreeView<Scope> list;
	private Button update;

	public ScopeExplorer(Scope s){
		this.setBottom(update = new Button("Update"));
		update.setOnAction((e) -> update(scope));
		update(s);
	}

	private void update(Scope s) {
		this.scope = s;
		this.setCenter(list = new TreeView<Scope>());
		list.setRoot(new ScopeTreeItem(scope));
	}
	
}
