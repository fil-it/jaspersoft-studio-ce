/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.cassandra;

import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.connectors.cassandra.CassandraDataSource;
import com.jaspersoft.connectors.cassandra.adapter.CassandraDataAdapter;
import com.jaspersoft.connectors.cassandra.adapter.CassandraDataAdapterImpl;
import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.ui.WizardQueryEditorComposite;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author gtoffoli
 * @author Eric Diaz
 * 
 */
public class CassandraDataAdapterDescriptor extends DataAdapterDescriptor
		implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private IFieldsProvider fieldsProvider;

	@Override
	public CassandraDataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new CassandraDataAdapterImpl();
		return (CassandraDataAdapter) dataAdapter;
	}

	@Override
	public CassandraDataAdapterEditor getEditor() {
		return new CassandraDataAdapterEditor();
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16)
			return Activator.getDefault().getImage("icons/cassandra.png");
		return null;
	}

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig,
			JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		getFieldProvider();
		return fieldsProvider.getFields(con, jConfig, reportDataset);
	}

	private void getFieldProvider() {
		if (fieldsProvider == null)
			fieldsProvider = new CassandraFieldsProvider();
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider();
		return fieldsProvider.supportsGetFieldsOperation(jConfig);
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new WizardQueryEditorComposite(parent, page, this, CassandraDataSource.QUERY_LANGUAGE);
	}

	@Override
	public String[] getLanguages() {
		return new String[] { CassandraDataSource.QUERY_LANGUAGE };
	}
}
