package com.thoughtworks.gipse.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandRunner {

  public String runCommand(String command) {
    StringBuffer result = new StringBuffer();
    try {
      Process process = Runtime.getRuntime().exec(command);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
        result.append("\n");
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return result.toString();
  }
}
