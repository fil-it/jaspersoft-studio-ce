/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.reader;

import java.util.List;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

import com.jaspersoft.studio.messages.Messages;

/**
 * Custom scriptlet used for data preview.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class DataPreviewScriptlet extends JRDefaultScriptlet {
	
	/** Location (plugin relative) of the custom report used for data preview */
	public static final String PREVIEW_REPORT_PATH="/resources/data.jrxml"; //$NON-NLS-1$
	/** Parameter name for data preview columns */
	public static final String PARAM_COLUMNS="jss.datapreview.columns"; //$NON-NLS-1$
	/** Parameter name for data preview listeners */
	public static final String PARAM_LISTENERS="jss.datapreview.listeners"; //$NON-NLS-1$
	
	private List<String> columns;
	private List<DatasetReaderListener> listeners;

	@Override
	public void afterDetailEval() throws JRScriptletException {
		if(columns==null){
			columns=(List<String>) getParameterValue(PARAM_COLUMNS);
		}
		if(listeners==null){
			listeners=(List<DatasetReaderListener>) getParameterValue(PARAM_LISTENERS);
		}
		
		Object[] record=new Object[columns.size()];
		int i=0;
		for(String col : columns){
			record[i++]=getFieldValue(col);
		}
		
		for(DatasetReaderListener l : listeners){
			if(!l.isValidStatus()){
				// This "dirty" solution will stop report running
				throw new DataPreviewInterruptedException(Messages.DataPreviewScriptlet_InterruptErrorMsg);
			}
			l.newRecord(record);
		}
		
		super.afterDetailEval();
	}
	
}
