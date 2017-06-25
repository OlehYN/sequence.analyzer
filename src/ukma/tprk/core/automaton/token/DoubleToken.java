package ukma.tprk.core.automaton.token;

public class DoubleToken implements AutomatonToken<Double> {

	private double value;

	public DoubleToken(double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
