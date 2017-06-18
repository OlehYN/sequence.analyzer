package ukma.tprk.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ukma.tprk.gui.config.GlobalConfig;
import ukma.tprk.gui.language.Language;
import ukma.tprk.gui.table.SequencePartConfigTableModel;
import ukma.tprk.model.SequencePartConfig;
import ukma.tprk.model.State;
import ukma.tprk.service.ChartUnitConverter;
import ukma.tprk.service.SequenceService;
import ukma.tprk.util.Writer;

public class MainFrame extends JFrame {

	private Language language = GlobalConfig.getLanguage();

	private static final long serialVersionUID = -5765031736293825255L;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 500;

	private static final int HGAP_LABEL = 15;
	private static final int VGAP_LABEL = 5;

	private static final int BUTTONS_GAP = 1;

	private static final int BORDER_SIZE = 7;
	private static final int TABLE_BORDER_SIZE = 7;

	private static final int EPSELON_FIELD_SIZE = 10;

	private JTextField endField;
	private JTextField startField;
	private JTextField formulaField;

	private JTextField epselonField;

	private JCheckBox buildSequenceCheckBox;
	private JCheckBox buildResultChartCheckBox;
	private JCheckBox buildInputChartCheckBox;
	private JCheckBox writeToFileCheckBox;
	private JCheckBox showResultsCheckBox;

	private JTable configsTable;

	private List<SequencePartConfig> sequencePartConfigs = new ArrayList<>();

	public MainFrame() {
		super();

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		SequencePartConfigTableModel tableModel = new SequencePartConfigTableModel(sequencePartConfigs);

		configsTable = new JTable(tableModel);
		JScrollPane tableScrollPane = new JScrollPane(configsTable);
		tablePanel.setBorder(BorderFactory.createEmptyBorder(TABLE_BORDER_SIZE, TABLE_BORDER_SIZE, TABLE_BORDER_SIZE,
				TABLE_BORDER_SIZE));

		tablePanel.add(tableScrollPane, BorderLayout.CENTER);
		tablePanel.add(getConfigurationPanel(), BorderLayout.SOUTH);

		getContentPane().add(tablePanel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenu());

		setVisible(true);
		setSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(language.getTitle());
		setJMenuBar(menuBar);
	}

	private JMenu createMenu() {
		JMenu mainMenu = new JMenu(language.getMenuName());

		JMenuItem newItem = new JMenuItem(language.getNewMenuItem());
		JMenuItem saveItem = new JMenuItem(language.getSaveMenuItem());
		JMenuItem loadItem = new JMenuItem(language.getLoadMenuItem());
		JMenuItem helpItem = new JMenuItem(language.getHelpMenuItem());

		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sequencePartConfigs.clear();
				configsTable.repaint();
				configsTable.revalidate();
			}
		});

		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HelpFrame();
			}
		});

		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						FileOutputStream fos = new FileOutputStream(fc.getSelectedFile());
						ObjectOutputStream outStream = new ObjectOutputStream(fos);

						State state = new State();
						state.setSequencePartConfigs(sequencePartConfigs);

						state.setBuildInputChart(buildInputChartCheckBox.isSelected());
						state.setBuildResultChart(buildResultChartCheckBox.isSelected());
						state.setBuildSequence(buildSequenceCheckBox.isSelected());
						state.setShowResults(showResultsCheckBox.isSelected());
						state.setWriteToFile(writeToFileCheckBox.isSelected());

						outStream.writeObject(state);
						outStream.flush();
						outStream.close();
					} catch (Exception e) {

						showErrorMessage(language.getIOExceptionMessage());
					}
				}

			}
		});

		loadItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser fileopen = new JFileChooser();
				int ret = fileopen.showDialog(null, language.getIOOpenName());
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					try {
						FileInputStream fis = new FileInputStream(file.getParent() + "/" + file.getName());
						ObjectInputStream inputStream = new ObjectInputStream(fis);
						State state = (State) inputStream.readObject();

						buildSequenceCheckBox.setSelected(state.isBuildSequence());
						buildResultChartCheckBox.setSelected(state.isBuildResultChart());
						buildInputChartCheckBox.setSelected(state.isBuildInputChart());
						writeToFileCheckBox.setSelected(state.isWriteToFile());
						showResultsCheckBox.setSelected(state.isShowResults());

						for (SequencePartConfig partConfig : state.getSequencePartConfigs())
							sequencePartConfigs.add(partConfig);

						inputStream.close();

						configsTable.repaint();
						configsTable.revalidate();

					} catch (Exception e) {

						showErrorMessage(language.getIOExceptionMessage());
					}
				}

			}
		});
		mainMenu.add(newItem);
		mainMenu.add(saveItem);
		mainMenu.add(loadItem);
		mainMenu.add(helpItem);

		return mainMenu;
	}

	private JPanel getConfigurationPanel() {
		JPanel configurationPanel = new JPanel();
		configurationPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, 0, BORDER_SIZE, 0));
		GridLayout configurationPanelLayout = new GridLayout(0, 1);
		configurationPanelLayout.setVgap(VGAP_LABEL);
		configurationPanel.setLayout(configurationPanelLayout);

		JPanel inputPanel = new JPanel();
		GridLayout inputPanelLayout = new GridLayout(1, 0);
		inputPanelLayout.setHgap(HGAP_LABEL);
		inputPanel.setLayout(inputPanelLayout);

		inputPanel.add(getFormulaPanel());
		inputPanel.add(getDurationPanel());

		configurationPanel.add(inputPanel);
		configurationPanel.add(getButtonsPanel());
		configurationPanel.add(getCheckBoxesPanel());
		configurationPanel.add(getSubmitPanel());
		return configurationPanel;
	}

	private JPanel getDurationPanel() {
		JPanel durationPanel = new JPanel();
		GridLayout durationPanelLayout = new GridLayout(1, 0);
		durationPanelLayout.setHgap(HGAP_LABEL);
		durationPanel.setLayout(durationPanelLayout);

		durationPanel.add(getStartPanel());
		durationPanel.add(getEndPanel());

		return durationPanel;
	}

	private JPanel getStartPanel() {
		JPanel startPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		layout.setHgap(HGAP_LABEL);
		startPanel.setLayout(layout);

		NumberFormat inf = NumberFormat.getIntegerInstance();
		startField = new JFormattedTextField(inf);
		startPanel.add(startField, BorderLayout.CENTER);

		JLabel startLabel = new JLabel(language.getStartLabel());
		startPanel.add(startLabel, BorderLayout.WEST);

		return startPanel;
	}

	private JPanel getEndPanel() {
		JPanel endPanel = new JPanel();

		BorderLayout layout = new BorderLayout();
		layout.setHgap(HGAP_LABEL);
		endPanel.setLayout(layout);

		NumberFormat inf = NumberFormat.getIntegerInstance();
		endField = new JFormattedTextField(inf);

		endPanel.add(endField, BorderLayout.CENTER);

		JLabel endLabel = new JLabel(language.getEndLabel());
		endPanel.add(endLabel, BorderLayout.WEST);

		return endPanel;
	}

	private JPanel getButtonsPanel() {
		JPanel buttonsPanel = new JPanel();

		GridLayout gridLayout = new GridLayout(1, 0);
		gridLayout.setHgap(BUTTONS_GAP);
		buttonsPanel.setLayout(gridLayout);

		JButton addNewRowButton = new JButton(language.getNewRowButtonName());
		JButton removeRowButton = new JButton(language.getRemoveRowButtonName());
		JButton moveUpButton = new JButton(language.getMoveUpButtonName());
		JButton moveDownButton = new JButton(language.getMoveDownButtonName());

		addNewRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				try {
					String startString = startField.getText().replace(" ", "");
					int start = Integer.valueOf(startString);

					String endString = endField.getText().replace(" ", "");
					int end = Integer.valueOf(endString);

					String formula = formulaField.getText();

					SequencePartConfig sequencePartConfig = new SequencePartConfig(formula, start, end);
					sequencePartConfigs.add(sequencePartConfig);

					configsTable.repaint();
					configsTable.revalidate();

					startField.setText("");
					endField.setText("");
					formulaField.setText("");
				} catch (Exception e) {
					showErrorMessage(language.getAddExceptionMessage());
				}
			}
		});

		removeRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = configsTable.getSelectedRow();
				if (selectedRow != -1) {
					sequencePartConfigs.remove(selectedRow);

					configsTable.repaint();
					configsTable.revalidate();
				} else {
					showErrorMessage(language.getRemoveExceptionMessage());
				}

				configsTable.clearSelection();
			}
		});

		moveUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = configsTable.getSelectedRow();
				if (selectedRow > 0) {
					SequencePartConfig upper = sequencePartConfigs.get(selectedRow - 1);
					SequencePartConfig lower = sequencePartConfigs.get(selectedRow);

					sequencePartConfigs.set(selectedRow, upper);
					sequencePartConfigs.set(selectedRow - 1, lower);

					configsTable.repaint();
					configsTable.revalidate();
				} else {
					showErrorMessage(language.getMoveUpExceptionMessage());
				}

				configsTable.clearSelection();

			}
		});

		moveDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = configsTable.getSelectedRow();
				if (selectedRow >= 0 && selectedRow < sequencePartConfigs.size() - 1) {
					SequencePartConfig upper = sequencePartConfigs.get(selectedRow);
					SequencePartConfig lower = sequencePartConfigs.get(selectedRow + 1);

					sequencePartConfigs.set(selectedRow + 1, upper);
					sequencePartConfigs.set(selectedRow, lower);

					configsTable.repaint();
					configsTable.revalidate();
				} else {
					showErrorMessage(language.getMoveDownExceptionMessage());
				}

				configsTable.clearSelection();
			}
		});

		buttonsPanel.add(addNewRowButton);
		buttonsPanel.add(removeRowButton);
		buttonsPanel.add(moveUpButton);
		buttonsPanel.add(moveDownButton);
		return buttonsPanel;
	}

	private JPanel getSubmitPanel() {
		JPanel submitPanel = new JPanel();
		submitPanel.setLayout(new BorderLayout());

		JPanel subButtonsPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 0);
		gridLayout.setHgap(BUTTONS_GAP);

		subButtonsPanel.setLayout(gridLayout);

		JButton submitButton = new JButton(language.getSubmitButtonName());
		subButtonsPanel.add(submitButton);

		JButton exitButton = new JButton(language.getExitButtonName());
		subButtonsPanel.add(exitButton);

		epselonField = new JTextField(EPSELON_FIELD_SIZE);
		JLabel epselonLabel = new JLabel(language.getEpselonFieldLabel());

		JPanel epselonPanel = new JPanel();
		epselonPanel.setLayout(new BorderLayout());
		epselonPanel.add(epselonField, BorderLayout.EAST);
		epselonPanel.add(epselonLabel, BorderLayout.WEST);

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				try {
					double epselon = Double.valueOf(epselonField.getText());

					if (sequencePartConfigs.size() > 0) {
						SequenceService sequenceService = buildSequenceCheckBox.isSelected()
								? new SequenceService(sequencePartConfigs, 0, epselon)
								: new SequenceService(sequencePartConfigs, epselon);

						if (buildResultChartCheckBox.isSelected()) {
							new ChartFrame(ChartUnitConverter.getPoints(sequenceService),
									language.getResultChartName());
						}

						if (buildInputChartCheckBox.isSelected()) {
							new ChartFrame(ChartUnitConverter.getSequence(sequenceService),
									language.getInputChartName());
						}

						if (writeToFileCheckBox.isSelected()) {
							Writer.writeToFile(sequenceService.getPoints());
						}

						if (showResultsCheckBox.isSelected()) {
							new ResultFrame(sequenceService.getPoints());
						}

					} else {
						showErrorMessage(language.getZeroConfigsMessage());
					}
				} catch (IOException e) {
					showErrorMessage(language.getIOExceptionMessage());
				} catch (Exception e) {
					showErrorMessage(language.getInvalidEpselonMessage());
				}
			}
		});

		submitPanel.add(subButtonsPanel, BorderLayout.EAST);
		submitPanel.add(epselonPanel, BorderLayout.WEST);

		return submitPanel;
	}

	private JPanel getFormulaPanel() {
		JPanel formulaPanel = new JPanel();

		BorderLayout layout = new BorderLayout();
		layout.setHgap(HGAP_LABEL);
		formulaPanel.setLayout(layout);

		formulaField = new JTextField();

		formulaPanel.add(formulaField, BorderLayout.CENTER);

		JLabel formulaLabel = new JLabel(language.getFormulaLabel());
		formulaPanel.add(formulaLabel, BorderLayout.WEST);

		return formulaPanel;
	}

	private JPanel getCheckBoxesPanel() {
		JPanel checkBoxPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 0);

		checkBoxPanel.setLayout(gridLayout);

		buildSequenceCheckBox = new JCheckBox(language.getBuildSequenceCheckBoxName());
		buildResultChartCheckBox = new JCheckBox(language.getBuildResultChartCheckBoxName());
		buildInputChartCheckBox = new JCheckBox(language.getBuildInputChartCheckBoxName());
		writeToFileCheckBox = new JCheckBox(language.getWriteToFileCheckBoxName());
		showResultsCheckBox = new JCheckBox(language.getShowResultsCheckBoxName());

		checkBoxPanel.add(buildSequenceCheckBox);
		checkBoxPanel.add(buildResultChartCheckBox);
		checkBoxPanel.add(buildInputChartCheckBox);
		checkBoxPanel.add(writeToFileCheckBox);
		checkBoxPanel.add(showResultsCheckBox);

		return checkBoxPanel;
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(MainFrame.this, message, language.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
