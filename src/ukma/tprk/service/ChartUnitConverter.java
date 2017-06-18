package ukma.tprk.service;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.model.ChartUnit;

public class ChartUnitConverter {

	public static List<ChartUnit> getSequence(SequenceService sequenceService) {
		List<ChartUnit> units = new ArrayList<ChartUnit>();

		double[] values = new double[(int) (1 / sequenceService.getEpselon()) + 1];

		for (int i = 0; i < sequenceService.getSequence().size(); i++) {
			int index = (int) (sequenceService.getSequence().get(i) / sequenceService.getEpselon());
			++values[index];
		}

		for (int i = 0; i < values.length; i++) {
			units.add(new ChartUnit(String.valueOf(i), values[i]));
		}
		return units;
	}

	public static List<ChartUnit> getPoints(SequenceService sequenceService) {
		List<ChartUnit> units = new ArrayList<ChartUnit>();

		double[] values = new double[(int) (1 / sequenceService.getEpselon()) + 1];

		for (int i = 0; i < sequenceService.getPoints().size(); i++) {
			int index = (int) (sequenceService.getPoints().get(i) / sequenceService.getEpselon());
			++values[index];
		}

		for (int i = 0; i < values.length; i++) {
			units.add(new ChartUnit(String.valueOf(i), values[i]));
		}
		return units;
	}
}
