package ukma.tprk.util;

import java.io.Serializable;
import java.util.List;
import ukma.tprk.sequence.SequencePartConfig;

public class SerializePackage implements Serializable {

    public String logText;
    public List<SequencePartConfig> operationManagers;
}
