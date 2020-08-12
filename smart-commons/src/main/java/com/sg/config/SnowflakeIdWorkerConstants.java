package com.sg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.id")
public class SnowflakeIdWorkerConstants {
    /**
     * 工作ID (0~31)
     */
    private long workId = 0;
    /**
     * 数据中心ID (0~31)
     */
    private long centerId = 0;

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpenIdGenProperties{");
        sb.append("workId=").append(workId);
        sb.append(", centerId=").append(centerId);
        sb.append('}');
        return sb.toString();
    }
}
