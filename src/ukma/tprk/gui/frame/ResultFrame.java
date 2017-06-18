package ukma.tprk.gui.frame;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ukma.tprk.gui.config.GlobalConfig;
import ukma.tprk.gui.language.Language;

public class ResultFrame extends JFrame {

	private static final long serialVersionUID = -76412256656853162L;

	private Language language = GlobalConfig.getLanguage();

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	public ResultFrame(List<Double> data) {

		JTable dataTable = new JTable(convertData(data), new Object[] { language.getDataColumnName() });
		JScrollPane scrollPane = new JScrollPane(dataTable);

		getContentPane().add(scrollPane);

		setVisible(true);
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocationRelativeTo(null);
		setTitle(language.getTitle());
	}

	private Object[][] convertData(List<Double> data) {
		Object[][] objects = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			objects[i][0] = data.get(i);
		}

		return objects;
	}
}
