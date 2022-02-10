package org.neocities.api;

import java.util.Date;
import java.util.List;

public class InfoResponse extends BaseResponse {

    private InfoNode info;

    public static class InfoNode {
        private String sitename;
        private long views;
        private long hits;
        private Date created_at;
        private Date last_update;
        private String domain;
        private List<String> tags;

        public String getSiteName() {
            return sitename;
        }

        public long getViews() {
            return views;
        }

        public long getHits() {
            return hits;
        }

        public Date getCreatedAt() {
            return created_at;
        }

        public Date getLastUpdate() {
            return last_update;
        }

        public String getDomain() {
            return domain;
        }

        public List<String> getTags() {
            return tags;
        }
    }

    public InfoNode getInfo() {
        return info;
    }
}
