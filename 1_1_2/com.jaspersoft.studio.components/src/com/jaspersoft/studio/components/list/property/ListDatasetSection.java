package com.jaspersoft.studio.components.list.property;

import net.sf.jasperreports.components.list.StandardListComponent;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ListDatasetSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent,
				"Dataset Run", false, 2, 2);
		createWidget4Property(group, MList.PREFIX
				+ StandardListComponent.PROPERTY_DATASET_RUN);
	}
}
