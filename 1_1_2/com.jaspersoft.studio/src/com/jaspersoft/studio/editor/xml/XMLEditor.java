/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.xml;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.xml.outline.EditorContentOutlinePage;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

/*
 * The Class XMLEditor.
 */
public class XMLEditor extends TextEditor {

	/** The color manager. */
	private ColorManager colorManager;

	/** The action registry. */
	private ActionRegistry actionRegistry = new ActionRegistry() {
		@Override
		public org.eclipse.jface.action.IAction getAction(Object key) {
			return XMLEditor.this.getAction((String) key);
		};
	};

	/**
	 * Instantiates a new xML editor.
	 */
	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}

	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
		if (outlinePage != null)
			outlinePage.setInput(input);
	}

	@Override
	protected void editorSaved() {
		super.editorSaved();
		if (outlinePage != null)
			outlinePage.update();
	}

	@Override
	protected void createActions() {
		super.createActions();
		ResourceBundle bundle = new NodeIconDescriptor("").getResourceBundle(JaspersoftStudioPlugin.getInstance());
		setAction("ContentFormatProposal", new TextOperationAction(bundle, "ContentFormatProposal.", this,
				ISourceViewer.FORMAT));
		setAction("ContentAssistProposal", new TextOperationAction(bundle, "ContentAssistProposal.", this,
				ISourceViewer.CONTENTASSIST_PROPOSALS));
		setAction("ContentAssistTip", new TextOperationAction(bundle, "ContentAssistTip.", this,
				ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	@Override
	public void dispose() {
		colorManager.dispose();
		if (outlinePage != null)
			outlinePage.setInput(null);
		super.dispose();
	}

	/**
	 * Gets the action registry.
	 * 
	 * @return the action registry
	 */
	public ActionRegistry getActionRegistry() {
		if (actionRegistry == null)
			actionRegistry = new ActionRegistry();
		return actionRegistry;
	}

	private EditorContentOutlinePage outlinePage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.editors.text.TextEditor#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == ActionRegistry.class)
			return getActionRegistry();
		if (IContentOutlinePage.class.equals(adapter))
			return getOutlineView();

		return super.getAdapter(adapter);
	}

	protected IContentOutlinePage getOutlineView() {
		if (outlinePage == null) {
			outlinePage = new EditorContentOutlinePage(this);
			if (getEditorInput() != null)
				outlinePage.setInput(getEditorInput());
		}
		return outlinePage;
	}

	@Override
	protected void handleEditorInputChanged() {
		super.handleEditorInputChanged();
		if (outlinePage != null) {
			outlinePage.update();
		}
	}
}
