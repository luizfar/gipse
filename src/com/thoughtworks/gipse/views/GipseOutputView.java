package com.thoughtworks.gipse.views;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class GipseOutputView extends ViewPart {

	public static final String ID = "com.thoughtworks.gipse.views.GipseOutputView";

	private TextViewer viewer;

	public void createPartControl(Composite parent) {
		viewer = new TextViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "com.thoughtworks.gipse.viewer");
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
}