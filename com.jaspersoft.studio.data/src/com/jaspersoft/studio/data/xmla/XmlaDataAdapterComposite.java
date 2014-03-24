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
package com.jaspersoft.studio.data.xmla;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.xmla.XmlaDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import rex.graphics.datasourcetree.elements.CatalogElement;
import rex.graphics.datasourcetree.elements.CubeElement;
import rex.graphics.datasourcetree.elements.DataSourceTreeElement;
import rex.metadata.ServerMetadata;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.secret.DataAdaptersSecretsProvider;
import com.jaspersoft.studio.swt.widgets.WSecretText;
import com.jaspersoft.studio.utils.Misc;

public class XmlaDataAdapterComposite extends ADataAdapterComposite {

	private Text xmlaUri;
	private Text textUsername;
	private WSecretText textPassword;
	private Combo cube;
	private Combo catalog;
	private Combo datasource;
	private DataSourceTreeElement[] dstes;
	private DataSourceTreeElement[] catalogs;
	private XmlaDataAdapter adapter;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public XmlaDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {

		super(parent, style, jrContext);
		setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText(Messages.XmlaDataAdapterComposite_0);

		xmlaUri = new Text(this, SWT.BORDER);
		xmlaUri.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button bGetMetadata = new Button(this, SWT.PUSH);
		bGetMetadata.setText(Messages.XmlaDataAdapterComposite_1);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_2);
		datasource = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		datasource.setItems(new String[] {});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		datasource.setLayoutData(gd);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_3);
		catalog = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		catalog.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		catalog.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		new Label(this, SWT.NONE).setText(Messages.XmlaDataAdapterComposite_4);

		cube = new Combo(this, SWT.READ_ONLY | SWT.BORDER);
		cube.setItems(new String[] {});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cube.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setText(Messages.JDBCDataAdapterComposite_username);

		textUsername = new Text(this, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textUsername.setLayoutData(gd);

		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setText(Messages.JDBCDataAdapterComposite_password);

		textPassword = new WSecretText(this, SWT.BORDER | SWT.PASSWORD);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		textPassword.setLayoutData(gd);

		bGetMetadata.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String url = xmlaUri.getText();
				boolean loginSuccesfull = validateUsernamePassword(url);
				if (loginSuccesfull) {
					ServerMetadata smd = new ServerMetadata(url);
					handleMetaDataChanged(smd);
				}
			}
		});

		datasource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleDatasourceChanged();
			}

		});

		catalog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleCatalogChanged();
			}
		});
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(xmlaUri, SWT.Modify), PojoObservables.observeValue(dataAdapter, "xmlaUrl")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(datasource), PojoObservables.observeValue(dataAdapter, "datasource")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(catalog), PojoObservables.observeValue(dataAdapter, "catalog")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(cube), PojoObservables.observeValue(dataAdapter, "cube")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textUsername, SWT.Modify), PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textPassword, SWT.Modify), PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new XmlaDataAdapterDescriptor();

		XmlaDataAdapter dataAdapter = (XmlaDataAdapter) dataAdapterDesc.getDataAdapter();

		dataAdapter.setXmlaUrl(xmlaUri.getText());
		dataAdapter.setDatasource(datasource.getText());
		dataAdapter.setCatalog(catalog.getText());
		dataAdapter.setCube(cube.getText());

		dataAdapter.setUsername(textUsername.getText());
		// configure widget if not done yet
		if (!textPassword.isWidgetConfigured()) {
			textPassword.loadSecret(DataAdaptersSecretsProvider.SECRET_NODE_ID, textPassword.getText());
		} else {
			// rely on back-compatibility and use clear text
			dataAdapter.setPassword(textPassword.getText());
		}

		return dataAdapterDesc;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDesc) {
		removeDirtyListenersToContext();
		adapter = (XmlaDataAdapter)dataAdapterDesc.getDataAdapter();
		if (dstes == null) {
			datasource.setItems(new String[] { Misc.nvl(adapter.getDatasource()) });
			datasource.select(0);

			catalog.setItems(new String[] { Misc.nvl(adapter.getCatalog()) });
			catalog.select(0);

			cube.setItems(new String[] { Misc.nvl(adapter.getCube()) });
			cube.select(0);
		} else {
			handleDatasourceChanged();
		}
		
		//The super call bind the widgets,it is called a the end to avoid it to used uninitialized
		//widgets
		super.setDataAdapter(dataAdapterDesc);
		addDirtyListenersToContext();
	}

	private int getIndex(String[] array, String value, int notFoundValue){
		int i = 0;
		for(String item : array){
			if (item.equals(value)) return i;
			i++;
		}
		return notFoundValue;
	}

	protected void handleDatasourceChanged() {
		if (dstes == null)
			return;
		catalog.removeAll();
		cube.removeAll();
		int ind = datasource.getSelectionIndex();
		catalogs = dstes[ind].getChildren();
		if (catalogs == null || catalogs.length == 0)
			return;

		String[] scat = new String[catalogs.length];
		for (int i = 0; i < catalogs.length; i++)
			scat[i] = ((CatalogElement) catalogs[i]).toString();
		catalog.setItems(scat);
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(scat, adapter.getCatalog(), 0);
		}
		catalog.select(selectionIndex);
		handleCatalogChanged();
	}

	protected void handleCatalogChanged() {
		if (dstes == null)
			return;
		cube.removeAll();
		int ind = catalog.getSelectionIndex();
		DataSourceTreeElement[] cubes = catalogs[ind].getChildren();
		if (cubes == null || cubes.length == 0)
			return;

		String[] scubes = new String[cubes.length];
		for (int i = 0; i < cubes.length; i++)
			scubes[i] = ((CubeElement) cubes[i]).toString();
		cube.setItems(scubes);
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(scubes, adapter.getCube(), 0);
		}
		cube.select(selectionIndex);
	}
	
	/**
	 * Set a custom swt validation dialog for an url
	 * 
	 * @param url the url of the server
	 * @return true if the operation was aborted, false otherwise
	 */
	private boolean validateUsernamePassword(String url) {
		try {
			/**
			 * Create the dialog
			 */
			final AuthenticationDialog ad = new AuthenticationDialog(UIUtils.getShell(), url);

			/**
			 * Set the dialog as authenticator
			 */
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					//If the user used the cancel key on the dialog then the operation is aborted to the 
					//authenticator must return null
					if (ad.cancelOperation()) return null;
					else {
						//Otherwise the fields are reseted to do another try and the dialog opened
						ad.resetFields();
						ad.openDialog();
						return new PasswordAuthentication(ad.getUsername(), ad.getPasswordCA());
					}
				}
			});
			URL endpoint = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) endpoint.openConnection();
			urlConnection.getResponseCode();
			return (!ad.cancelOperation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected void handleMetaDataChanged(ServerMetadata smd) {
		datasource.removeAll();
		catalog.removeAll();
		cube.removeAll();
		dstes = smd.discoverDataSources();
		if (dstes == null || dstes.length == 0)
			return;

		String[] dsources = new String[dstes.length];
		for (int i = 0; i < dstes.length; i++)
			dsources[i] = dstes[i].getDataSourceInfo();
		datasource.setItems(dsources);
		int selectionIndex = 0;
		if (adapter != null) {
			selectionIndex = getIndex(dsources, adapter.getDatasource(), 0);
		}
		datasource.select(selectionIndex);
		handleDatasourceChanged();
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_xmla");
	}

	@Override
	public void performAdditionalUpdates() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			textPassword.persistSecret();
			// update the "password" replacing it with the UUID key saved in secure
			// preferences
			XmlaDataAdapter dataAdapter = (XmlaDataAdapter) dataAdapterDesc.getDataAdapter();
			dataAdapter.setPassword(textPassword.getUUIDKey());
		}
	}
}
