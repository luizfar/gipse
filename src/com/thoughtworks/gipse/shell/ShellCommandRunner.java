package com.thoughtworks.gipse.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellCommandRunner {

  public String runCommand(String command) {
    try {
      Process process = Runtime.getRuntime().exec(command);
      String result = read(process.getInputStream());
      if ("".equals(result)) {
        result = read(process.getErrorStream());
      }
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "";
  }
  
  private String read(InputStream inputStream) {
    StringBuffer buffer = new StringBuffer();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        buffer.append(line);
        buffer.append("\n");
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return buffer.toString().trim();
  }
}
