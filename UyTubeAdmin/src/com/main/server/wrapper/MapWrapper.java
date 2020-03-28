package com.main.server.wrapper;

import java.util.Map;

public class MapWrapper {
    private Map<String, Object> mapInfo;

    public MapWrapper() {}
    
    public MapWrapper(Map<String, Object> mapInfo) {
        this.mapInfo = mapInfo;
    }
    
    public Map<String, Object> getMapInfo() {
        return this.mapInfo;
    }

    public void setMapInfo(Map<String, Object> mapInfo) {
        this.mapInfo = mapInfo;
    }
    
    
}
