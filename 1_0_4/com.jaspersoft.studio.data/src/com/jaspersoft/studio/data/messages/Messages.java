/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.data.messages.messages"; //$NON-NLS-1$
	public static String BeanMappingTool_errormessage;
	public static String BeanMappingTool_labeltitle;
	public static String BeanMappingTool_selectfieldstitle;
	public static String BeanMappingTool_toolname;
	public static String JDBCDataAdapterComposite_attention;
	public static String JDBCDataAdapterComposite_classpath;
	public static String JDBCDataAdapterComposite_connectionproperties;
	public static String JDBCDataAdapterComposite_database;
	public static String JDBCDataAdapterComposite_databaselocation;
	public static String JDBCDataAdapterComposite_driverlabel;
	public static String JDBCDataAdapterComposite_password;
	public static String JDBCDataAdapterComposite_serveraddress;
	public static String JDBCDataAdapterComposite_urllabel;
	public static String JDBCDataAdapterComposite_username;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
