/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VSimpleErrorPreview extends APreview {

	public VSimpleErrorPreview(Composite parent, JasperReportsConfiguration jContext) {
		super(parent, jContext);
	}

	private Label tmessage;

	@Override
	public Control createControl(final Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout(layout);

		createMessages(container);

		return container;
	}

	public void setFocus() {
		container.setFocus();
	}

	protected void createMessages(Composite composite) {
		tmessage = new Label(composite, SWT.PUSH | SWT.CENTER | SWT.WRAP);
		tmessage.setText("Starting to generate a new report, please wait ...");
		// GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalAlignment = SWT.CENTER;
		layoutData.verticalAlignment = SWT.CENTER;
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		layoutData.horizontalSpan = 1;
		layoutData.verticalSpan = 1;

		layoutData.heightHint = UIUtils.getCharHeight(tmessage) * 2 + 50;
		tmessage.setLayoutData(layoutData);
	}

	public void setMessage(String msg) {
		tmessage.setText(msg);
	}

	public void addMessage(String msg) {
		tmessage.setText(tmessage.getText() + msg + "\n");
		tmessage.getParent().update();
		tmessage.getParent().layout();
	}

	private Composite container;

	public void clear() {
		tmessage.setText("");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
