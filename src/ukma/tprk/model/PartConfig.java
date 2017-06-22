package ukma.tprk.model;

public interface PartConfig {
	String getInput();

	Integer getStart();

	Integer getEnd();

	void setStart(Integer aValue);

	void setInput(String aValue);

	void setEnd(Integer aValue);
}
