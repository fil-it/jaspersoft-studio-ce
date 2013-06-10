package com.jaspersoft.studio.data.sql.model.query;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.viewers.StyledString;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;

public class MOrderByColumn extends AMQueryObject<MColumn> {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MOrderByColumn(ANode parent, MColumn value) {
		super(parent, value, null);
	}

	public MOrderByColumn(ANode parent, MColumn value, int index) {
		super(parent, value, null, index);
	}

	@Override
	public String getDisplayText() {
		String dt = getValue().toSQLString();
		dt += isDesc ? AMKeyword.DESCENDING_KEYWORD : AMKeyword.ASCENDING_KEYWORD;
		return dt;
	}

	@Override
	public StyledString getStyledDisplayText() {
		StyledString dt = new StyledString(getValue().toSQLString());
		dt.append(isDesc ? AMKeyword.DESCENDING_KEYWORD : AMKeyword.ASCENDING_KEYWORD, FontUtils.KEYWORDS_STYLER);
		return dt;
	}

	private boolean isDesc = true;

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
}