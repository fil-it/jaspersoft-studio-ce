/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;

public class PdfActionHeading2 extends PdfActionAbstract {
	
	/** Id of the actions */
	public static final String ID_Heading2_Full = "PdfAction_Heading2_Full"; //$NON-NLS-1$
	public static final String ID_Heading2_Start = "PdfAction_Heading2_Start"; //$NON-NLS-1$
	public static final String ID_Heading2_End = "PdfAction_Heading2_End"; //$NON-NLS-1$
	public static final String ID_Heading2_None = "PdfAction_Heading2_None"; //$NON-NLS-1$
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionHeading2(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Heading2_Full, ID_Heading2_Start, ID_Heading2_End, ID_Heading2_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String getPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.h2";
	}



}
