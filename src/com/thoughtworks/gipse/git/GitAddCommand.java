package com.thoughtworks.gipse.git;

import java.util.List;

import org.eclipse.core.resources.IResource;

public class GitAddCommand extends GitCommand {

  private List<IResource> resources;
  
  public GitAddCommand(List<IResource> resources) {
    this.resources = resources;
  }
  
  @Override
  protected String getCommand() {
    StringBuffer command = new StringBuffer("git add ");
    for (IResource resource : resources) {
      command.append(resource.getFullPath().makeRelative() + " ");
    }
    
    return command.toString();
  }
}
