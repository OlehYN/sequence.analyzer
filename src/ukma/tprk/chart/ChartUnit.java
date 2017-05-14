package ukma.tprk.chart;

public class ChartUnit {

    private String name;
    private Double value;

    public ChartUnit(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChartUnit{" + "name=" + name + ", value=" + value + '}';
    }
}
