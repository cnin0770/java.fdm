package com.fdm.BarrierOptionMonitor.model;

import java.util.Arrays;

public enum OptionStatus {
    WAITING,
    KNOCKED_IN,
    KNOCKED_OUT,
    EXERCISED,
    EXPIRED;
    private static OptionStatus[] closedStatus = {KNOCKED_OUT, EXERCISED, EXPIRED};

    public static boolean isClosed(OptionStatus status) {
        return Arrays.asList(closedStatus).contains(status);
    }
}