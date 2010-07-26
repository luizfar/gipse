package com.thoughtworks.gipse.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
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

  private List<IResource> resources = new ArrayList<IResource>();
  
  private ShellCommandRunner commandRunner = new ShellCommandRunner();

  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    
  }

  public void run(IAction action) {
    if (resources.isEmpty()) {
      return;
    }
    
    String command = getFullCommand();
    String text = "Running '" + command + "'\n";
    text += commandRunner.runCommand(command);
    GipseOutputDocument.getGipseDocument().set(text);
  }

  public void selectionChanged(IAction action, ISelection selection) {
    resources.clear();
    if (selection instanceof TreeSelection && !selection.isEmpty()) {
      TreeSelection treeSelection = (TreeSelection) selection;
      for (TreePath path : treeSelection.getPaths()) {
        resources.add((IResource) path.getLastSegment());
      }
    }
  }
  
  public String getResourcesAsString() {
    StringBuffer buffer = new StringBuffer();
    
    for (IResource resource : resources) {
      buffer.append(" ");
      buffer.append(resource.getProjectRelativePath());
    }
    
    return buffer.toString();
  }
  
  public abstract String getCommand();
  
  private String getFullCommand() {
    IProject project = resources.get(0).getProject();
    String workTree = project.getLocation().addTrailingSeparator().toString();
    String gitDir = workTree + ".git/ ";
    
    return "git --work-tree=" + workTree + " --git-dir=" + gitDir + getCommand();
  }
}
