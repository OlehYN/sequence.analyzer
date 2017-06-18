package ukma.tprk.model;

import java.io.Serializable;

public class SequencePartConfig implements Comparable<SequencePartConfig>, Serializable {

	private static final long serialVersionUID = -9091369509587821478L;

	private String input;
	private Integer start;
	private Integer end;

	public SequencePartConfig(String input, int start, int end) {
		super();

		if (input == null)
			throw new IllegalArgumentException("Incorrect formula");

		this.input = input;
		this.start = start;
		this.end = end;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	@Override
	public int compareTo(SequencePartConfig arg0) {
		return start.compareTo(arg0.getStart());
	}

}
