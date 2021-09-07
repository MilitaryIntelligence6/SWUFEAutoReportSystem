package cn.misection.autoreport.entity;

import java.io.Serializable;

import cn.misection.autoreport.constant.EnumCampus;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName ReportInfo
 * @Description TODO
 * @CreateTime 2021年09月06日 20:22:00
 */
public class ReportInfo implements Serializable {

    /**
     * 校区;
     */
    private EnumCampus campus;

    /**
     * 外出时间;
     */
    private String startTime;

    /**
     * 返校时间;
     */
    private String endTime;

    /**
     * 外出时长;
     */
    private String duration;

    /**
     * 目的地;
     */
    private String destination;

    /**
     * 交通方式;
     */
    private String transportation;

    /**
     * 外出理由;
     */
    private String reason;

    public ReportInfo() {
    }

    public ReportInfo(EnumCampus campus,
                      String startTime,
                      String endTime,
                      String duration,
                      String destination,
                      String transportation,
                      String reason) {
        this.campus = campus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.destination = destination;
        this.transportation = transportation;
        this.reason = reason;
    }

    public EnumCampus getCampus() {
        return campus;
    }

    public void setCampus(EnumCampus campus) {
        this.campus = campus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
