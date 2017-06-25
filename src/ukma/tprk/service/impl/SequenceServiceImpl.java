package ukma.tprk.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ukma.tprk.core.SequenceAnalyzer;
import ukma.tprk.model.FilePartConfig;
import ukma.tprk.model.PartConfig;
import ukma.tprk.model.SequencePartConfig;
import ukma.tprk.service.FileSequenceGenerator;
import ukma.tprk.service.PartSequenceGenerator;
import ukma.tprk.service.SequenceService;

public class SequenceServiceImpl implements SequenceService {

	private List<Double> sequence;
	private List<Double> points;

	private final double epselon;

	private FileSequenceGenerator fileSequenceGenerator = new FileSequenceGeneratorImpl();
	private PartSequenceGenerator partSequenceGenerator = new PartSequenceGeneratorImpl();

	public SequenceServiceImpl(List<PartConfig> params, double epselon) throws IOException {
		this.epselon = epselon;
		init(params, epselon);

		this.points = SequenceAnalyzer.getPoints(sequence, epselon);
	}

	public SequenceServiceImpl(List<PartConfig> params, int equalValue, double epselon) throws IOException {
		this.epselon = epselon;
		init(params, epselon);

		sequence = SequenceAnalyzer.generateProbabilitySequence(sequence, equalValue);
		this.points = SequenceAnalyzer.getPoints(sequence, epselon);
	}

	private void init(List<PartConfig> params, double epselon) throws IOException {

		sequence = new ArrayList<Double>();

		for (PartConfig param : params) {
			if (param instanceof SequencePartConfig) {
				List<Double> currentSequence = partSequenceGenerator.getSequence(param.getInput(), param.getStart(),
						param.getEnd());

				sequence.addAll(currentSequence);
			} else if (param instanceof FilePartConfig) {
				sequence.addAll(fileSequenceGenerator.getSequence(param.getInput()));
			}
		}
	}

	public List<Double> getSequence() {
		return sequence;
	}

	public List<Double> getPoints() {
		return points;
	}

	public double getEpselon() {
		return epselon;
	}

}
