package ukma.tprk.gui.config;

import ukma.tprk.gui.language.Language;
import ukma.tprk.gui.language.impl.UkrainianLanguage;

public class GlobalConfig {
	private static Language language = new UkrainianLanguage();

	public static Language getLanguage() {
		return language;
	}
}
