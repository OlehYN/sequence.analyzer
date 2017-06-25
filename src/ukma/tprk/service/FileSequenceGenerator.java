package ukma.tprk.service;

import java.io.IOException;
import java.util.List;

public interface FileSequenceGenerator {
	List<Double> getSequence(String fileName) throws IOException;
}
