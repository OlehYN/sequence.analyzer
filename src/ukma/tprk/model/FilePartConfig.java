package ukma.tprk.model;

import java.io.Serializable;

public class FilePartConfig implements Serializable, PartConfig {
	
	private static final long serialVersionUID = -2541173087084389484L;
	
	private String input;
	private Integer start;
	private Integer end;

	public FilePartConfig(String input, int start, int end) {
		super();

		if (input == null)
			throw new IllegalArgumentException("Incorrect file name");

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
}
