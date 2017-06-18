package ukma.tprk.model;

import java.io.Serializable;
import java.util.List;

public class State implements Serializable {

	private static final long serialVersionUID = -7180733917808527766L;

	private List<SequencePartConfig> sequencePartConfigs;

	private boolean buildSequence;
	private boolean buildResultChart;
	private boolean buildInputChart;
	private boolean writeToFile;
	private boolean showResults;

	public List<SequencePartConfig> getSequencePartConfigs() {
		return sequencePartConfigs;
	}

	public void setSequencePartConfigs(List<SequencePartConfig> sequencePartConfigs) {
		this.sequencePartConfigs = sequencePartConfigs;
	}

	public boolean isBuildSequence() {
		return buildSequence;
	}

	public void setBuildSequence(boolean buildSequence) {
		this.buildSequence = buildSequence;
	}

	public boolean isBuildResultChart() {
		return buildResultChart;
	}

	public void setBuildResultChart(boolean buildResultChart) {
		this.buildResultChart = buildResultChart;
	}

	public boolean isBuildInputChart() {
		return buildInputChart;
	}

	public void setBuildInputChart(boolean buildInputChart) {
		this.buildInputChart = buildInputChart;
	}

	public boolean isWriteToFile() {
		return writeToFile;
	}

	public void setWriteToFile(boolean writeToFile) {
		this.writeToFile = writeToFile;
	}

	public boolean isShowResults() {
		return showResults;
	}

	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
	}

}
