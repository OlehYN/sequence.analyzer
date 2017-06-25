package ukma.tprk.service.impl;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.model.ChartUnit;
import ukma.tprk.service.ChartUnitConverter;

public class ChartUnitConverterImpl implements ChartUnitConverter {

	public List<ChartUnit> getSequence(SequenceServiceImpl sequenceService) {
		return findPoints(sequenceService.getSequence(), sequenceService.getEpselon());
	}

	public List<ChartUnit> getPoints(SequenceServiceImpl sequenceService) {
		return findPoints(sequenceService.getPoints(), sequenceService.getEpselon());
	}

	private List<ChartUnit> findPoints(List<Double> points, double epselon) {
		List<ChartUnit> units = new ArrayList<ChartUnit>();

		double[] values = new double[(int) (1 / epselon) + 1];

		for (int i = 0; i < points.size(); i++) {
			int index = (int) (points.get(i) / epselon);
			++values[index];
		}

		for (int i = 0; i < values.length; i++) {
			units.add(new ChartUnit(String.valueOf(i), values[i]));
		}
		return units;
	}
}
