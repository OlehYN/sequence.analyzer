package ukma.tprk.gui.language.impl;

import ukma.tprk.gui.language.Language;

public class UkrainianLanguage implements Language {

	@Override
	public String getFormulaColumnName() {
		return "Формула";
	}

	@Override
	public String getStartColumnName() {
		return "Початок";
	}

	@Override
	public String getEndColumnName() {
		return "Кінець";
	}

	@Override
	public String getNewRowButtonName() {
		return "Додати";
	}

	@Override
	public String getRemoveRowButtonName() {
		return "Видалити";
	}

	@Override
	public String getMoveUpButtonName() {
		return "Вверх";
	}

	@Override
	public String getMoveDownButtonName() {
		return "Вниз";
	}

	@Override
	public String getFormulaLabel() {
		return "Формула:";
	}

	@Override
	public String getStartLabel() {
		return "Старт:";
	}

	@Override
	public String getEndLabel() {
		return "Кінець:";
	}

	@Override
	public String getMoveUpExceptionMessage() {
		return "Елемент і так знаходиться в самому верху або ж елемент взагалі не було обрано.";
	}

	@Override
	public String getMoveDownExceptionMessage() {
		return "Елемент і так знаходиться в самому низу або ж елемент взагалі не було обрано.";
	}

	@Override
	public String getRemoveExceptionMessage() {
		return "Виберіть, будь ласка, один елемент.";
	}

	@Override
	public String getAddExceptionMessage() {
		return "Заповніть, будь ласка, всі поля.";
	}

	@Override
	public String getTitle() {
		return "Sequence Analyzer";
	}

	@Override
	public String getErrorTitle() {
		return "Помилка!";
	}

	@Override
	public String getBuildSequenceCheckBoxName() {
		return "Послідовність частот";
	}

	@Override
	public String getBuildResultChartCheckBoxName() {
		return "Графік результатів";
	}

	@Override
	public String getBuildInputChartCheckBoxName() {
		return "Графік вхідних даних";
	}

	@Override
	public String getWriteToFileCheckBoxName() {
		return "Записати у файл";
	}

	@Override
	public String getShowResultsCheckBoxName() {
		return "Відобразити результати";
	}

	@Override
	public String getSubmitButtonName() {
		return "Згенерувати";
	}

	@Override
	public String getExitButtonName() {
		return "Вийти";
	}

	@Override
	public String getEpselonFieldLabel() {
		return "Точність: ";
	}

	@Override
	public String getZeroConfigsMessage() {
		return "Відсутні параметри!";
	}

	@Override
	public String getInvalidEpselonMessage() {
		return "Задайте, будь ласка, коректну точність!";
	}

	@Override
	public String getResultChartName() {
		return "Графік результатів";
	}

	@Override
	public String getInputChartName() {
		return "Графік вхідних даних";
	}

	@Override
	public String getIOExceptionMessage() {
		return "Помилка при записі результату!";
	}

	@Override
	public String getDataColumnName() {
		return "Дані";
	}

	@Override
	public String getNewMenuItem() {
		return "Очистити";
	}

	@Override
	public String getSaveMenuItem() {
		return "Зберегти";
	}

	@Override
	public String getLoadMenuItem() {
		return "Загрузити";
	}

	@Override
	public String getHelpMenuItem() {
		return "Допомога";
	}

	@Override
	public String getMenuName() {
		return "Меню";
	}

	@Override
	public String getIOOpenName() {
		return "Відкрити";
	}

	@Override
	public String getLoadFromFileButtonName() {
		return "Файл";
	}

}
