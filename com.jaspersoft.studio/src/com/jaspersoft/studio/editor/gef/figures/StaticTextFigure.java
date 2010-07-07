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
package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

/**
 * The Class StaticTextFigure.
 */
public class StaticTextFigure extends FrameFigure {

	/**
	 * Instantiates a new static text figure.
	 */
	public StaticTextFigure() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.GenericFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitStaticText((JRStaticText) jrElement);
	}

	/* (non-Javadoc)
	 * @see com.jaspersoft.studio.editor.gef.figures.FrameFigure#getLineBox()
	 */
	@Override
	protected JRLineBox getLineBox() {
		return ((JRStaticText) getJrElement()).getLineBox();
	}
}
