package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.PDF417Component;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;

public class MPDF417 extends MBarcode4j {
	public MPDF417() {
		super();
	}

	public MPDF417(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		el.setComponent(new PDF417Component());
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", "PDF417"));
		return el;
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		IntegerPropertyDescriptor minColumnsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MIN_COLUMNS,
				"Min Columns");
		minColumnsD.setDescription("Min columns.");
		desc.add(minColumnsD);

		IntegerPropertyDescriptor maxColumnsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MAX_COLUMNS,
				"Max Columns");
		maxColumnsD.setDescription("Max columns.");
		desc.add(maxColumnsD);

		IntegerPropertyDescriptor minRowsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MIN_ROWS, "Min Rows");
		minRowsD.setDescription("Min rows.");
		desc.add(minRowsD);

		IntegerPropertyDescriptor maxRowsD = new IntegerPropertyDescriptor(PDF417Component.PROPERTY_MAX_ROWS, "Max Rows");
		maxRowsD.setDescription("Max rows.");
		desc.add(maxRowsD);

		DoublePropertyDescriptor width2HeightRatioD = new DoublePropertyDescriptor(
				PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO, "Width To Height Ratio");
		width2HeightRatioD.setDescription("Width to height ratio.");
		desc.add(width2HeightRatioD);

		IntegerPropertyDescriptor errorCorrectionLevelD = new IntegerPropertyDescriptor(
				PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL, "Error Correction Level");
		errorCorrectionLevelD.setDescription("Error correction level.");
		desc.add(errorCorrectionLevelD);

		minColumnsD.setCategory("Barcode Properties, PDF417");
		maxColumnsD.setCategory("Barcode Properties, PDF417");
		minRowsD.setCategory("Barcode Properties, PDF417");
		maxRowsD.setCategory("Barcode Properties, PDF417");
		width2HeightRatioD.setCategory("Barcode Properties, PDF417");
		errorCorrectionLevelD.setCategory("Barcode Properties, PDF417");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		PDF417Component jrList = (PDF417Component) jrElement.getComponent();

		if (id.equals(PDF417Component.PROPERTY_MIN_COLUMNS))
			return jrList.getMinColumns();
		if (id.equals(PDF417Component.PROPERTY_MAX_COLUMNS))
			return jrList.getMaxColumns();
		if (id.equals(PDF417Component.PROPERTY_MIN_ROWS))
			return jrList.getMinRows();
		if (id.equals(PDF417Component.PROPERTY_MAX_ROWS))
			return jrList.getMaxRows();
		if (id.equals(PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO))
			return jrList.getWidthToHeightRatio();
		if (id.equals(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL))
			return jrList.getErrorCorrectionLevel();

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		PDF417Component jrList = (PDF417Component) jrElement.getComponent();

		if (id.equals(PDF417Component.PROPERTY_MIN_ROWS))
			jrList.setMinRows((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MAX_ROWS))
			jrList.setMaxRows((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MIN_COLUMNS))
			jrList.setMinColumns((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_MAX_COLUMNS))
			jrList.setMaxColumns((Integer) value);
		if (id.equals(PDF417Component.PROPERTY_WIDTH_TO_HEIGHT_RATIO))
			jrList.setWidthToHeightRatio((Double) value);
		if (id.equals(PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL))
			jrList.setErrorCorrectionLevel((Integer) value);

		super.setPropertyValue(id, value);
	}
}
