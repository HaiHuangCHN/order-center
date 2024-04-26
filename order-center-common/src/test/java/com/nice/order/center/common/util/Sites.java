package com.nice.order.center.common.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Sites implements Comparable<Sites> {

    private String siteCode;

    private String siteName;

    private String channelCode;

    private Date date;

    private InconsistentInstance inconsistentInstance;

    @Override
    public String toString() {
        return "Sites{" +
                "siteCode='" + siteCode + '\'' +
                ", siteName='" + siteName + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Sites other) {
        int siteNameComparison = this.siteName.compareTo(other.siteName);

        if (siteNameComparison != 0) {
            return siteNameComparison;
        }

        return this.date.compareTo(other.date);
    }

}
