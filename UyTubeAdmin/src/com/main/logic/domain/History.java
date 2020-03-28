package com.main.logic.domain;

import java.time.LocalDate;

import com.main.logic.dts.HistoryDT;

public class History {
    
    private Long videoId;
    private int visitCount;
    private LocalDate lastVisit;

    public History(Long videoIdParam) {
        videoId = videoIdParam;
        visitCount = 1;
        lastVisit = LocalDate.now();
    }
    
    public int getVisitCount() {
        return visitCount;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }
    
    public Long getVideoId() {
        return videoId;
    }

    public HistoryDT getHistoryDt() {
        return new HistoryDT(visitCount, lastVisit);
    }
    
    public void newVisit() {
        visitCount++;
        lastVisit = LocalDate.now();
    }
    
}