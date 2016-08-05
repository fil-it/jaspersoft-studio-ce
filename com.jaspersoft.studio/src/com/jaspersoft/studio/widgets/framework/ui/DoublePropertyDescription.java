package com.jaspersoft.studio.widgets.framework.ui;

import java.math.BigDecimal;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class DoublePropertyDescription extends NumberPropertyDescription<BigDecimal> {
	
	public DoublePropertyDescription() {
		this(null);
	}
	
	public DoublePropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}
	
	public DoublePropertyDescription(String name, String label, String description, boolean mandatory,  BigDecimal defaultValue, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, min, max, editor);
	}
	
	public DoublePropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, min, max, editor);
	}
	
	@Override
	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Double.class;
	}
	
	@Override
	public ItemPropertyDescription<BigDecimal> clone(IPropertyEditor editor){
		DoublePropertyDescription result = new DoublePropertyDescription(editor);
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		BigDecimal min = null;
		BigDecimal max = null;
		BigDecimal def = null;
		if (cpd.getMin() != null){
			min = new BigDecimal(cpd.getMin());
		}
		if (cpd.getMax() != null){
			max = new BigDecimal(cpd.getMax());
		}
		if (cpd.getDefaultValue() != null){
			def = new BigDecimal(cpd.getDefaultValue());
		}
		DoublePropertyDescription doubleDesc = new DoublePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max, editor);
		doubleDesc.setReadOnly(cpd.isReadOnly());
		return doubleDesc;
	}
}