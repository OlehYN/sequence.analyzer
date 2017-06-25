package ukma.tprk.service;

import java.util.List;

public interface PartSequenceGenerator {
	List<Double> getSequence(String input, int start, int end);
}
