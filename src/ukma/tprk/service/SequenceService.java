package ukma.tprk.service;

import java.util.List;

public interface SequenceService {
	List<Double> getSequence();

	List<Double> getPoints();

	double getEpselon();
}
