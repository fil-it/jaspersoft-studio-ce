/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class LegendSection extends AbstractRealValueSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, Messages.LegendSection_Legend_Label,
				true, 2);

		createWidget4Property(group, JRBaseChart.PROPERTY_SHOW_LEGEND);

		getWidgetFactory().createCLabel(group, Messages.LegendSection_Position_Label);
		createWidget4Property(group, JRBaseChart.PROPERTY_LEGEND_POSITION,
				false);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		
		Composite colorComposite = new Composite(group, SWT.NONE);
		colorComposite.setLayout(new GridLayout(4,false));
		colorComposite.setLayoutData(gd);
		
		getWidgetFactory().createCLabel(colorComposite, Messages.LegendSection_Forecolor_Label);
		createWidget4Property(colorComposite, JRBaseChart.PROPERTY_LEGEND_COLOR, false);

		getWidgetFactory().createCLabel(colorComposite, Messages.LegendSection_Backcolor_Label);
		createWidget4Property(colorComposite, JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, false);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRDesignChart.PROPERTY_LEGEND_FONT, false).getControl().setLayoutData(gd);

	}

}
