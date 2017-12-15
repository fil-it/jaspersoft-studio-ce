/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.selector;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.datasource.MROlapMondrianConnection;
import com.jaspersoft.studio.server.wizard.resource.AddResourceWizard;

public class SelectorMondrianConnection extends ASelector {

	@Override
	protected AMResource getLocalResource(AMResource res, ResourceDescriptor runit, ANode pnode) {
		AddResourceWizard wizard = new AddResourceWizard(res, true);
		wizard.setMondrianOnly(true);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.create();
		if (dialog.open() != Dialog.OK)
			return null;
		AMResource r = wizard.getResource();
		ResourceDescriptor ref = r.getValue();
		ref.setIsNew(true);
		ref.setIsReference(false);
		ref.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files"); //$NON-NLS-1$
		ref.setDirty(true);
		return r;
	}

	@Override
	protected ResourceDescriptor createLocal(AMResource res) {
		return null;
	}

	@Override
	protected boolean isResCompatible(AMResource r) {
		return r instanceof MROlapMondrianConnection;
	}

	protected ResourceDescriptor getResourceDescriptor(ResourceDescriptor ru) {
		for (Object obj : ru.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			ResourceDescriptor tmp = checkReference(r);
			if (tmp != null)
				r = tmp;
			String t = r.getWsType();
			if (r.getIsReference() && r.getReferenceType() != null)
				t = r.getReferenceType();

			if (t.equals(ResourceDescriptor.TYPE_SECURE_MONDRIAN_CONNECTION)
					|| t.equals(ResourceDescriptor.TYPE_OLAP_MONDRIAN_CONNECTION))
				return r;
		}
		return null;
	}

	@Override
	protected String[] getIncludeTypes() {
		return new String[] { ResourceMediaType.MONDRIAN_CONNECTION_CLIENT_TYPE,
				ResourceMediaType.SECURE_MONDRIAN_CONNECTION_CLIENT_TYPE };
	}

	@Override
	protected String[] getExcludeTypes() {
		return null;
	}

}
