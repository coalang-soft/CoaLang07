package jdiadev;

import java.util.Iterator;
import java.util.Set;

import diamond.run.environment.Scope;
import javafx.scene.control.TreeItem;

public class ScopeTreeItem extends TreeItem {

	private Scope scope;

	public ScopeTreeItem(Scope scope) {
		this.scope = scope;
		Set<String> varNames = scope.getVariables();
		setValue("Scope (" + varNames.size() + " items)");
		Iterator<String> i = varNames.iterator();
		while(i.hasNext()){
			getChildren().add(new VariableTreeItem(scope,i.next()));
		}
	}

}
