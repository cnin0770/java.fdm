package com.fdm.BarrierOptionMonitor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Builder.Default
    private int open = 0;
    @Builder.Default
    private int expire = 0;
}
