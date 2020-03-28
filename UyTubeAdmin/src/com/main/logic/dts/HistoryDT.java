package com.main.logic.dts;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;

public class HistoryDT implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlElement
    private int visitCount;
    @XmlElement
    private LocalDate lastVisit;
    private VideoDT videoDt;

    public HistoryDT() {}
    
    public HistoryDT(int vc, LocalDate lv) {
        visitCount = vc;
        lastVisit = lv;
    }
    
    public int getVisitCount() {
        return visitCount;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }
    
    public VideoDT getVideoDt() {
        return videoDt;
    }
    
    public void setVideoDt(VideoDT vDt) {
        videoDt = vDt;
    }
    
}