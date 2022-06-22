package com.nice.order.center.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.InetAddress;
import java.net.UnknownHostException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HostUtil {

    private static String hostName;

    public static String getHostNameForLiunx() {
        try {
            return (InetAddress.getLocalHost()).getHostName();
        } catch (UnknownHostException uhe) {
            String host = uhe.getMessage();
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }

    public static String getHostName() {
        if (hostName == null || hostName.isEmpty()) {
            if (System.getenv("COMPUTERNAME") != null) {
                hostName = System.getenv("COMPUTERNAME");
            } else {
                hostName = getHostNameForLiunx();
            }
        }
        return hostName;
    }

}