package com.thoughtworks.gipse.actions;


public class AddToIndexAction extends AbstractGitAction {

  @Override
  public String getCommand() {
    StringBuffer command = new StringBuffer("git add ");
    for (String resource : getResources()) {
      command.append(resource + " ");
    }
    
    return command.toString();
  }
}
