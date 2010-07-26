package com.thoughtworks.gipse.popup.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.thoughtworks.gipse.git.GitAddCommand;

public class AddToIndexAction implements IObjectActionDelegate {

  private List<IResource> files = new ArrayList<IResource>();

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    
  }

  public void run(IAction action) {
    System.out.println(new GitAddCommand(files).run());
//    MessageDialog.openInformation(shell, "Gipse", result);
  }

  public void selectionChanged(IAction action, ISelection selection) {
    files.clear();
    if (selection instanceof TreeSelection && !selection.isEmpty()) {
      TreeSelection treeSelection = (TreeSelection) selection;
      for (TreePath path : treeSelection.getPaths()) {
        files.add((IResource) path.getLastSegment());
      }
    }
  }
}
