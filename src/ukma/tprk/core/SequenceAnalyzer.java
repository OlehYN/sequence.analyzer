package ukma.tprk.core;

import java.util.ArrayList;
import java.util.List;

public class SequenceAnalyzer {

    public static List<Double> generateProbabilitySequence(List<Double> sequence, double value) {
        List<Double> result = new ArrayList<Double>();

        int quantity = 0;
        int max = 0;

        for (double i : sequence) {
            if (i == value) {
                ++quantity;
            }
            ++max;

            result.add((double) quantity / max);
        }

        return result;
    }

    // Only for test purpose
    private static List<Double> generateSequence(int length) {
        List<Double> sequence = new ArrayList<Double>();

        int currentQuantity = 0;
        int stepQuantity = 1;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < stepQuantity; j++) {
                sequence.add(0d);
            }
            for (int j = 0; j < stepQuantity; j++) {
                sequence.add(1d);
            }

            currentQuantity = stepQuantity + currentQuantity;
            stepQuantity = currentQuantity;
        }
        return sequence;
    }

    public static List<Double> getPoints(List<Double> sequence, double epselon) {
        Double[] values = new Double[(int) (1 / epselon)];

        List<Double> result = new ArrayList<Double>();

        for (int i = sequence.size() / 2; i < sequence.size(); i++) {
            int index = (int) (sequence.get(i) / epselon);
            values[index] = sequence.get(i);
        }

        for (Double value : values) {
            if (value != null) {
                result.add(value);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<Double> sequence = SequenceAnalyzer.generateSequence(24);
        List<Double> probabilitySequence = SequenceAnalyzer.generateProbabilitySequence(sequence, 0d);
        List<Double> result = SequenceAnalyzer.getPoints(probabilitySequence, 0.001);
        System.out.println(result);
    }
}
