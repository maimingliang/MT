package com.elk.setting;

import java.io.Serializable;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2016/10/16 15:50
 * author caojiaxu
 */
public class AppUpdateBeen implements Serializable {

    /**
     * code : ELK_000000
     * data : {"updateFlag":1,"fileInfoExt":{"fullPath":"M00/00/5B/wKgAalgZWP2AJ_beAIbpX7cHbHk676.apk","fullFileName":"milulx_guide.apk"},"versionNumber":"0.9","introduction":"","way":1}
     */
    private String code;

    private String message;
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public static class Data implements Serializable {
        /**
         * updateFlag : 1
         * fileInfoExt : {"fullPath":"M00/00/5B/wKgAalgZWP2AJ_beAIbpX7cHbHk676.apk","fullFileName":"milulx_guide.apk"}
         * versionNumber : 0.9
         * introduction :
         * way : 1
         */
        private Integer updateFlag;
        private FileInfoExt fileInfoExt;
        private String versionNumber;
        private String introduction;
        private Integer way;

        public void setUpdateFlag(Integer updateFlag) {
            this.updateFlag = updateFlag;
        }

        public void setFileInfoExt(FileInfoExt fileInfoExt) {
            this.fileInfoExt = fileInfoExt;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setWay(Integer way) {
            this.way = way;
        }

        public Integer getUpdateFlag() {
            return updateFlag;
        }

        public FileInfoExt getFileInfoExt() {
            return fileInfoExt;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public String getIntroduction() {
            return introduction;
        }

        public Integer getWay() {
            return way;
        }

        public static class FileInfoExt implements Serializable {
            /**
             * fullPath : M00/00/5B/wKgAalgZWP2AJ_beAIbpX7cHbHk676.apk
             * fullFileName : milulx_guide.apk
             */
            private String path;
            private String fullFileName;

            public void setPath(String path) {
                this.path = path;
            }

            public void setFullFileName(String fullFileName) {
                this.fullFileName = fullFileName;
            }

            public String getPath() {
                return path;
            }

            public String getFullFileName() {
                return fullFileName;
            }
        }
    }
}
