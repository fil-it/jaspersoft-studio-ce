/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.style;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;

import com.jaspersoft.studio.editor.style.command.CreateStyleCommand;
import com.jaspersoft.studio.editor.style.command.CreateStyleTemplateReferenceCommand;
import com.jaspersoft.studio.editor.style.command.DeleteStyleCommand;
import com.jaspersoft.studio.editor.style.command.DeleteStyleTemplateCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStylesTemplate;

/**
 * A factory for creating OutlineTreeEditPart objects.
 */
public class StyleTreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof IContainerEditPart)
			editPart = new AStyleContainerTreeEditPart();
		else
			editPart = new AStyleTreeEditPart();
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

	/**
	 * Gets the delete command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @return the delete command
	 */
	public static Command getDeleteCommand(ANode parent, ANode child) {
		if (parent instanceof MStylesTemplate) {
			if (child instanceof MStyleTemplateReference)
				return new DeleteStyleTemplateCommand((MStylesTemplate) parent, (MStyleTemplateReference) child);
			if (child instanceof MStyle)
				return new DeleteStyleCommand((MStylesTemplate) parent, (MStyle) child);
		}
		return null;
	}

	/**
	 * Gets the reorder command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 * @return the reorder command
	 */
	public static Command getReorderCommand(ANode child, ANode parent, int newIndex) {
		// if (child instanceof MStyle) {
		// if (parent instanceof MStyles) {
		// return new ReorderStyleCommand((MStyle) child, (MStyles) parent, newIndex);
		// }
		// }
		return null;
	}

	/**
	 * Gets the creates the command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @param location
	 *          the location
	 * @param newIndex
	 *          the new index
	 * @return the creates the command
	 */
	public static Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		if (parent instanceof MStylesTemplate) {
			if (child instanceof MStyle)
				return new CreateStyleCommand((MStylesTemplate) parent, (MStyle) child, newIndex);
			if (child instanceof MStyleTemplateReference)
				return new CreateStyleTemplateReferenceCommand((MStylesTemplate) parent, (MStyleTemplateReference) child,
						newIndex);
		} else if (parent.getParent() instanceof MStylesTemplate) {
			if (child instanceof MStyle && !(child instanceof MConditionalStyle))
				return new CreateStyleCommand((MStylesTemplate) parent.getParent(), (MStyle) child, newIndex);
			if (child instanceof MStyleTemplateReference)
				return new CreateStyleTemplateReferenceCommand((MStylesTemplate) parent.getParent(),
						(MStyleTemplateReference) child, newIndex);
		}
		return null;
	}

	/**
	 * Gets the orphan command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @return the orphan command
	 */
	public static Command getOrphanCommand(ANode parent, ANode child) {
		return UnexecutableCommand.INSTANCE;
	}
}
