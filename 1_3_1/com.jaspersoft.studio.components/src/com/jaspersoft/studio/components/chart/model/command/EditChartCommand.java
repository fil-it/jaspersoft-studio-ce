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
package com.jaspersoft.studio.components.chart.model.command;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.wizard.ChartWizard;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class EditChartCommand extends Command {
	private JRElementGroup jrGroup;
	private JRDesignChart oldChart;
	private JRDesignChart newChart;
	protected JasperReportsConfiguration jConfig;
	private MChart originalNode;

	public EditChartCommand(MFrame parent, MChart mchart) {
		this(parent, mchart, -1);
	}

	public EditChartCommand(MBand parent, MChart mchart) {
		this(parent, mchart, -1);
	}

	public EditChartCommand(MElementGroup parent, MChart mchart) {
		this(parent, mchart, -1);
	}

	private EditChartCommand(ANode parent, MChart mchart, int index) {
		this.originalNode=mchart;
		this.jConfig = parent.getJasperConfiguration();
		this.oldChart = (JRDesignChart) mchart.getValue();
		if (parent instanceof IGroupElement)
			this.jrGroup = ((IGroupElement) parent).getJRElementGroup();
		else
			this.jrGroup = (JRElementGroup) parent.getValue();
	}

	@Override
	public void execute() {
		if (newChart == null) {
			JRDesignChart clone = (JRDesignChart) oldChart.clone();

			ChartWizard wizard = new ChartWizard(new MChart(null, clone, -1),
					(JRDesignElementDataset) clone.getDataset(), true);
			wizard.init(jConfig);
			wizard.setExpressionContext(ModelUtils.getElementExpressionContext(oldChart, originalNode));
			WizardDialog dialog = new WizardDialog(Display.getDefault()
					.getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				this.newChart = (JRDesignChart) wizard.getChart().getValue();
			} else
				return;
		}
		switchCharts(oldChart, newChart);
	}

	@Override
	public void undo() {
		if (newChart != null)
			switchCharts(newChart, oldChart);
	}

	private void switchCharts(JRDesignChart chart1, JRDesignChart chart2) {
		int index = jrGroup.getChildren().indexOf(chart1);
		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElement(chart1);
			((JRDesignElementGroup) jrGroup).addElement(index, chart2);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElement(chart1);
			((JRDesignFrame) jrGroup).addElement(index, chart2);
		}
	}
}
