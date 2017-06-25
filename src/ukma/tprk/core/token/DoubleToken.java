package ukma.tprk.core.token;

public class DoubleToken implements AbstractToken<Double> {

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
