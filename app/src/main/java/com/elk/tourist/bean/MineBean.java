package com.elk.tourist.bean;

import java.math.BigDecimal;

/**
 * 类       名:   我的Bean
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/2/27
 * author   tanxiang(monster)
 */


public class MineBean {

    /**
     * code : ELK_000000
     * data : {"petName":"游客2588","unsettledMoney":3.4,"fileInfo":{"fullPath":"M00/00/8F/wKgAalhbjZuAIRAeAACgfrX3nAo442.jpg","groupName":"group1","createTime":"2016-12-22 08:23:55"},"id":"87c0782a-aebe-4519-872f-dfc5f69aace8"}
     */
    private String code;

    private String message;
    private DataEntity data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public class DataEntity {
        /**
         * petName : 游客2588
         * unsettledMoney : 3.4
         * fileInfo : {"fullPath":"M00/00/8F/wKgAalhbjZuAIRAeAACgfrX3nAo442.jpg","groupName":"group1","createTime":"2016-12-22 08:23:55"}
         * id : 87c0782a-aebe-4519-872f-dfc5f69aace8
         */
        private String petName;
        private BigDecimal unsettledMoney;
        private FileExtEntity fileExt;
        private String id;
        private String tguId;
        private Integer tguStatus;


        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public BigDecimal getUnsettledMoney() {
            return unsettledMoney;
        }

        public void setUnsettledMoney(BigDecimal unsettledMoney) {
            this.unsettledMoney = unsettledMoney;
        }

        public FileExtEntity getFileExt() {
            return fileExt;
        }

        public void setFileExt(FileExtEntity fileExt) {
            this.fileExt = fileExt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTguId() {
            return tguId;
        }

        public void setTguId(String tguId) {
            this.tguId = tguId;
        }

        public Integer getTguStatus() {
            return tguStatus;
        }

        public void setTguStatus(Integer tguStatus) {
            this.tguStatus = tguStatus;
        }

        public class FileExtEntity {
            /**
             * fullPath : M00/00/8F/wKgAalhbjZuAIRAeAACgfrX3nAo442.jpg
             * groupName : group1
             * createTime : 2016-12-22 08:23:55
             */
            private String path;
            private String groupName;
            private String createTime;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
