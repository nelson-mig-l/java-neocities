package org.neocities.api;

import java.util.Date;
import java.util.List;

public class ListResponse extends BaseResponse {

    private List<FilesNode> files;

    public static class FilesNode {
        private String path;
        private boolean is_directory;
        private int size;
        private Date updated_at;
        private String sha1_hash;

        public String getPath() {
            return path;
        }

        public boolean isDirectory() {
            return is_directory;
        }

        public int getSize() {
            return size;
        }

        public Date getUpdatedAt() {
            return updated_at;
        }

        public String getSha1Hash() {
            return sha1_hash;
        }
    }

    public List<FilesNode> getFiles() {
        return files;
    }
}
