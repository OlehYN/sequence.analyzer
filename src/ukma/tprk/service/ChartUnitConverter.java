package ukma.tprk.service;

import java.util.List;

import ukma.tprk.model.ChartUnit;
import ukma.tprk.service.impl.SequenceServiceImpl;

public interface ChartUnitConverter {

	List<ChartUnit> getSequence(SequenceServiceImpl sequenceService);

	List<ChartUnit> getPoints(SequenceServiceImpl sequenceService);
}
