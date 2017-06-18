package ukma.tprk.gui.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ukma.tprk.gui.config.GlobalConfig;
import ukma.tprk.gui.language.Language;

public class HelpFrame extends JFrame {

	private static final long serialVersionUID = 1902964965242057248L;

	private String help = "<html>" + "<center><h2>Задання правил:</h2></center>"
			+ "<br/>1. Записати формулу для правила. " + "<br/>* <i>Можна використовувати функції та число {i}.</i>"
			+ "<br/>** <i>{i} буде підставлено залежно від заданого проміжку.</i>"
			+ "<br/>2. Визначити межі, на яких діє правило." + "<br/>3. Нажати кнопку \"Додати\"."
			+ "<br/><br/><center><h2>Функції:</h2></center><br/>" + "<table>"
			+ "<tr><td><b>cos(X)</b></td><td>повертає косинус від X</td></tr>"
			+ "<tr><td><b>floor(X)</b></td><td>повертає цілу частину від X</td></tr>"
			+ "<tr><td><b>greater(X,Y)</b></td><td>повертає 1, якщо X >= за Y, і 0, якщо ні</td></tr>"
			+ "<tr><td><b>less(X,Y)</b></td><td>повертає 1, якщо X <= за Y, і 0, якщо ні</td></tr>"
			+ "<tr><td><b>ln(X)</b></td><td>повертає натуральний логарифм від X</td></tr>"
			+ "<tr><td><b>max(X,Y)</b></td><td>повертає найбільше значення з X та Y</td></tr>"
			+ "<tr><td><b>min(X,Y)</b></td><td>повертає мінімальне значення з X та Y</td></tr>"
			+ "<tr><td><b>rand(X)</b></td><td>повертає випадкове ціле число в межах [0, X)</td></tr>"
			+ "<tr><td><b>sin(X)</b></td><td>повертає синус від X</td></tr>"
			+ "<tr><td><b>tg(X)</b></td><td>повертає тангенс від X</td></tr>" + "</table>" + "<br/><br/>"
			+ "<center><h2>Перетворення в послідовність ймовірностей:</h2></center>"
			+ "<br/>1. Увімкнути \"Перетворити в послідовність ймовірностей\"" + "<br/>2. Задати перевірку (0 або 1)"
			+ "</html>";

	private static final int WIDTH = 550;
	private static final int HEIGHT = 500;

	private Language language = GlobalConfig.getLanguage();

	public HelpFrame() {
		JPanel helpPanel = new JPanel();
		helpPanel.add(new JLabel(help));

		JScrollPane jScrollPane = new JScrollPane(helpPanel);
		add(jScrollPane);

		setTitle(language.getTitle());
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
