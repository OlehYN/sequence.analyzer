package ukma.tprk.chart;

import java.util.ArrayList;
import java.util.List;
import ukma.tprk.sequence.SequenceService;

public class ChartUnitConverter {

    private final SequenceService sequenceService;

    public ChartUnitConverter(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    public List<ChartUnit> getSequence() {
        List<ChartUnit> units = new ArrayList<ChartUnit>();

        double[] values = new double[(int) (1 / sequenceService.getEpselon()) + 1];

        for (int i = 0; i < sequenceService.getSequence().size(); i++) {
            int index = (int) (sequenceService.getSequence().get(i) / sequenceService.getEpselon());
            ++values[index];
        }

        for (int i = 0; i < values.length; i++) {
            units.add(new ChartUnit(String.valueOf(i), values[i]));
        }
        return units;
    }

    public List<ChartUnit> getPoints() {
        List<ChartUnit> units = new ArrayList<ChartUnit>();

        double[] values = new double[(int) (1 / sequenceService.getEpselon()) + 1];

        for (int i = 0; i < sequenceService.getPoints().size(); i++) {
            int index = (int) (sequenceService.getPoints().get(i) / sequenceService.getEpselon());
            ++values[index];
        }

        for (int i = 0; i < values.length; i++) {
            units.add(new ChartUnit(String.valueOf(i), values[i]));
        }
        return units;
    }
}
