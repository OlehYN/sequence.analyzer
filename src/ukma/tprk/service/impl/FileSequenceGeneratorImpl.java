package ukma.tprk.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ukma.tprk.service.FileSequenceGenerator;
import ukma.tprk.util.CustomFileReader;

public class FileSequenceGeneratorImpl implements FileSequenceGenerator {

	public List<Double> getSequence(String fileName) throws IOException {
		List<Double> sequence = new ArrayList<>();

		String text = CustomFileReader.getText(fileName);
		String[] numbers = text.split(" ");

		for (String number : numbers)
			sequence.add(Double.valueOf(number));

		return sequence;
	}
}
