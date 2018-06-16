package Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Profile {

    private int depth;

    private int overalTime;

    public Map<Integer, String> getDepthStopTime() {
        return depthStopTime;
    }

    public void setDepthStopTime(Map<Integer, String> depthStopTime) {
        this.depthStopTime = depthStopTime;
    }

    private Map<Integer, String> depthStopTime = new TreeMap<>(Collections.reverseOrder());

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getOveralTime() {
        return overalTime;
    }

    public void setOveralTime(int overalTime) {
        this.overalTime = overalTime;
    }



}
