package ukma.tprk.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Writer {
	public static void writeToFile(List<Double> results) throws IOException {
		String name = "sequence-" + System.currentTimeMillis() + ".txt";
		File file = new File(name);
		file.createNewFile();

		PrintWriter writer = new PrintWriter(name, "UTF-8");

		for (Double value : results)
			writer.print(value + " ");
		writer.close();
	}
}
