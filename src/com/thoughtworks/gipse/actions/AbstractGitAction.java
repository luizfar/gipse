package com.thoughtworks.gipse.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.thoughtworks.gipse.shell.ShellCommandRunner;
import com.thoughtworks.gipse.views.GipseOutputDocument;

public abstract class AbstractGitAction implements IObjectActionDelegate {

  private List<String> resources = new ArrayList<String>();
  
  private ShellCommandRunner commandRunner = new ShellCommandRunner();

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    
  }

  public void run(IAction action) {
    String result = commandRunner.runCommand(getCommand());
    GipseOutputDocument.getGipseDocument().set(result);
  }

  public void selectionChanged(IAction action, ISelection selection) {
    resources.clear();
    if (selection instanceof TreeSelection && !selection.isEmpty()) {
      TreeSelection treeSelection = (TreeSelection) selection;
      for (TreePath path : treeSelection.getPaths()) {
        IResource resource = (IResource) path.getLastSegment();
        resources.add(resource.getFullPath().removeFirstSegments(1).toString());
      }
    }
  }
  
  public List<String> getResources() {
    return resources;
  }
  
  public abstract String getCommand();
}
