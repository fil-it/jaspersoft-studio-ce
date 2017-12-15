/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;

public class MListOfValues extends AMResource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MListOfValues(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("lov"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(ResourceDescriptor.TYPE_LOV);
		return rd;
	}

	@Override
	public String getJRSUrl() throws UnsupportedEncodingException {
		return "flow.html?_flowId=addListOfValuesFlow&isEdit=true&resource="
				+ URLEncoder.encode(getValue().getUriString(), "ISO-8859-1")
				+ "&ParentFolderUri="
				+ URLEncoder.encode(getValue().getParentFolder(), "ISO-8859-1");
	}
}
