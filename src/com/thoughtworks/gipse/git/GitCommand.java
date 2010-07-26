package com.thoughtworks.gipse.git;

import com.thoughtworks.gipse.shell.ShellCommandRunner;

public abstract class GitCommand {
  
  private ShellCommandRunner commandRunner = new ShellCommandRunner();

  protected abstract String getCommand();
  
  public String run() {
    System.out.println("gipse: running command \"" + getCommand() + "\"");
    return commandRunner.runCommand(getCommand());
  }
}
