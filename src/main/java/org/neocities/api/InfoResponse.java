package org.neocities.api;

import java.util.Date;
import java.util.List;

public class InfoResponse extends BaseResponse {

    public static class InfoNode {
        private String sitename;
        private int views;
        private int hits;
        private Date created_at;
        private Date last_update;
        private String domain;
        private List<String> tags;

        public String getSiteName() {
            return sitename;
        }

        public int getViews() {
            return views;
        }

        public int getHits() {
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

    private InfoNode info;

    public InfoNode getInfo() {
        return info;
    }
}
