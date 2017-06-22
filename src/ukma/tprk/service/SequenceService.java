package ukma.tprk.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ukma.tprk.core.SequenceAnalyzer;
import ukma.tprk.model.FilePartConfig;
import ukma.tprk.model.PartConfig;
import ukma.tprk.model.SequencePartConfig;
import ukma.tprk.util.CustomFileReader;

public class SequenceService {

	private List<Double> sequence;
	private List<Double> points;

	private final double epselon;

	public SequenceService(List<PartConfig> params, double epselon) throws IOException {
		this.epselon = epselon;
		sequence = new ArrayList<Double>();

		for (PartConfig param : params) {
			if (param instanceof SequencePartConfig) {
				SequenceGenerator sequenceGenerator = new SequenceGenerator(param.getInput(), param.getStart(),
						param.getEnd());
				List<Double> currentSequence = sequenceGenerator.getSequence();

				sequence.addAll(currentSequence);
			} else if (param instanceof FilePartConfig) {
				String text = CustomFileReader.getText(param.getInput());
				String[] numbers = text.split(" ");

				for (String number : numbers) {
					sequence.add(Double.valueOf(number));
				}
			}
		}

		this.points = SequenceAnalyzer.getPoints(sequence, epselon);
	}

	public SequenceService(List<PartConfig> params, int equalValue, double epselon) throws IOException {
		this.epselon = epselon;
		sequence = new ArrayList<Double>();

		for (PartConfig param : params) {
			if (param instanceof SequencePartConfig) {
				SequenceGenerator sequenceGenerator = new SequenceGenerator(param.getInput(), param.getStart(),
						param.getEnd());
				List<Double> currentSequence = sequenceGenerator.getSequence();

				sequence.addAll(currentSequence);
			} else if (param instanceof FilePartConfig) {
				String text = CustomFileReader.getText(param.getInput());
				String[] numbers = text.split(" ");

				for (String number : numbers) {
					sequence.add(Double.valueOf(number));
				}
			}
		}

		sequence = SequenceAnalyzer.generateProbabilitySequence(sequence, equalValue);
		this.points = SequenceAnalyzer.getPoints(sequence, epselon);
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
