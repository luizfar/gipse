package com.thoughtworks.gipse.actions;

public class AddToIndexAction extends AbstractGitAction {

  @Override
  public String getCommand() {
    return "add" + getResourcesAsString();
  }
}
