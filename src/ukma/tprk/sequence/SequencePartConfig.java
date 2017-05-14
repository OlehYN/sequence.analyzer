package ukma.tprk.sequence;

import java.io.Serializable;

public class SequencePartConfig implements Comparable<SequencePartConfig>, Serializable {

    private String input;
    private Integer start;
    private Integer end;

    public SequencePartConfig(String input, int start, int end) {
        super();
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
