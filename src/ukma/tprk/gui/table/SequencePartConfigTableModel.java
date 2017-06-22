package ukma.tprk.gui.table;

import javax.swing.table.AbstractTableModel;

import ukma.tprk.gui.config.GlobalConfig;
import ukma.tprk.gui.language.Language;
import ukma.tprk.model.PartConfig;

import static ukma.tprk.gui.config.GuiConstants.*;

import java.util.List;

public class SequencePartConfigTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4833742045018702454L;

	private Language language = GlobalConfig.getLanguage();
	private List<PartConfig> partConfigs;

	private static final int COLUMN_NUMBER = 3;

	public SequencePartConfigTableModel(List<PartConfig> partConfigs) {
		this.partConfigs = partConfigs;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NUMBER;
	}

	@Override
	public int getRowCount() {
		return partConfigs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PartConfig PartConfig = partConfigs.get(rowIndex);

		switch (columnIndex) {
		case FORMULA_COLUMN:
			return PartConfig.getInput();
		case START_COLUMN:
			return PartConfig.getStart();
		case END_COLUMN:
			return PartConfig.getEnd();
		}

		throw new IndexOutOfBoundsException("Such column index isn't supported!");
	}

	@Override
	public String getColumnName(int c) {
		String result = "";
		switch (c) {
		case FORMULA_COLUMN:
			result = language.getFormulaColumnName();
			break;
		case START_COLUMN:
			result = language.getStartColumnName();
			break;
		case END_COLUMN:
			result = language.getEndColumnName();
			break;
		}
		return result;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> result = null;
		switch (columnIndex) {
		case FORMULA_COLUMN:
			result = String.class;
			break;
		case START_COLUMN:
			result = Integer.class;
			break;
		case END_COLUMN:
			result = Integer.class;
			break;
		default:
			throw new IllegalArgumentException("Incorrect type");
		}

		return result;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		PartConfig row = partConfigs.get(rowIndex);
		switch (columnIndex) {
		case FORMULA_COLUMN:
			row.setInput((String) aValue);
			break;
		case START_COLUMN:
			row.setStart((Integer) aValue);
			break;
		case END_COLUMN:
			row.setEnd((Integer) aValue);
			break;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}
