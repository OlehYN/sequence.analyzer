package ukma.tprk.test;

import java.util.ArrayList;
import java.util.List;

import ukma.tprk.core.SequenceAnalyzer;
import ukma.tprk.model.SequencePartConfig;
import ukma.tprk.service.SequenceGenerator;
import ukma.tprk.service.SequenceService;

public class Tester {

    public static void main(String[] args) {

        System.out.println("Sequence: " + new SequenceGenerator("greater({i}-2^(floor(ln({i})/ln(2))),2^(floor(ln({i})/ln(2)))div2)", 2, 48).getSequence());

        SequencePartConfig managerParam1 = new SequencePartConfig("0", 0, 1);
        SequencePartConfig managerParam2 = new SequencePartConfig("1", 1, 2);
        SequencePartConfig managerParam3 = new SequencePartConfig("greater({i}-2^(floor(ln({i})/ln(2))),2^(floor(ln({i})/ln(2)))div2)", 2, 48);

        List<SequencePartConfig> managerParams = new ArrayList<SequencePartConfig>();
        managerParams.add(managerParam1);
        managerParams.add(managerParam2);
        managerParams.add(managerParam3);

        SequenceService sequenceManager = new SequenceService(managerParams, 0, 0.001);
        System.out.println("Result: " + sequenceManager.getPoints());

        SequencePartConfig managerParam = new SequencePartConfig("rand(2)", 0, 2000);

        List<SequencePartConfig> randManagerParams = new ArrayList<SequencePartConfig>();
        randManagerParams.add(managerParam);

        SequenceService randSequenceManager = new SequenceService(randManagerParams, 0, 0.001);
        System.out.println("Result: " + randSequenceManager.getPoints());

        int[] points = {1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1};

        List<Double> list = new ArrayList<Double>();
        for (Integer i : points) {
            list.add((double) i);
        }

        System.out.println(SequenceAnalyzer.generateProbabilitySequence(list, 1));
    }
}
