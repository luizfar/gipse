package com.thoughtworks.gipse.views;

import org.eclipse.jface.text.Document;

public class GipseOutputDocument extends Document {

  private static final GipseOutputDocument doc = new GipseOutputDocument();
  
  public static GipseOutputDocument getGipseDocument() {
    return doc;
  }
}
