package cn.misection.autoreport.report.entity;

import java.io.Serializable;

import cn.misection.autoreport.common.constant.Campus;

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
    private Campus campus;

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

    private ReportInfo() {
    }

    private ReportInfo(Campus campus,
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

    public static class Builder {
        /**
         * 校区;
         */
        private Campus campus;

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

        public Builder() {
        }

        public ReportInfo create() {
            ReportInfo reportInfo = new ReportInfo();
            reportInfo.campus = this.campus;
            reportInfo.startTime = this.startTime;
            reportInfo.endTime = this.endTime;
            reportInfo.duration = this.duration;
            reportInfo.destination = this.destination;
            reportInfo.transportation = this.transportation;
            reportInfo.reason = this.reason;
            return reportInfo;
        }

        public Builder setCampus(Campus campus) {
            this.campus = campus;
            return this;
        }

        public Builder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setTransportation(String transportation) {
            this.transportation = transportation;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
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
