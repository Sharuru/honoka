package com.honoka.entity;

public class Result {
    // 站点围栏 POJO
    public static class fencingResult {
        // 员工工号
        private String staffId;
        // 员工姓名
        private String staffName;
        // 线路名
        private String lineName;
        // 站点名
        private String staName;
        // 距离
        private String dist;

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public String getStaName() {
            return staName;
        }

        public void setStaName(String staName) {
            this.staName = staName;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

    }

    // POI 检索 POJO
    public static class POISearchResult {
        // 兴趣点名字
        private String poiName;
        // 兴趣点地址
        private String poiAddr;
        // 兴趣点联系电话
        private String poiTelephone;
        // 兴趣点百度记录纬度
        private float baiduRecordLng;
        // 兴趣点百度记录经度
        private float baiduRecordLat;

        private String lineDistance;
        private String drivingDistance;
        private String drivingDuration;
        private String transitDistance;
        private String transitDuration;

        public String getPoiName() {
            return poiName;
        }

        public void setPoiName(String poiName) {
            this.poiName = poiName;
        }

        public String getPoiAddr() {
            return poiAddr;
        }

        public void setPoiAddr(String poiAddr) {
            this.poiAddr = poiAddr;
        }

        public String getPoiTelephone() {
            return poiTelephone;
        }

        public void setPoiTelephone(String poiTelephone) {
            this.poiTelephone = poiTelephone;
        }

        public Float getBaiduRecordLng() {
            return baiduRecordLng;
        }

        public void setBaiduRecordLng(Float baiduRecordLng) {
            this.baiduRecordLng = baiduRecordLng;
        }

        public Float getBaiduRecordLat() {
            return baiduRecordLat;
        }

        public void setBaiduRecordLat(Float baiduRecordLat) {
            this.baiduRecordLat = baiduRecordLat;
        }

        public String getLineDistance() {
            return lineDistance;
        }

        public void setLineDistance(String lineDistance) {
            this.lineDistance = lineDistance;
        }

        public String getDrivingDistance() {
            return drivingDistance;
        }

        public void setDrivingDistance(String drivingDistance) {
            this.drivingDistance = drivingDistance;
        }

        public String getDrivingDuration() {
            return drivingDuration;
        }

        public void setDrivingDuration(String drivingDuration) {
            this.drivingDuration = drivingDuration;
        }

        public String getTransitDuration() {
            return transitDuration;
        }

        public void setTransitDuration(String transitDuration) {
            this.transitDuration = transitDuration;
        }

        public String getTransitDistance() {
            return transitDistance;
        }

        public void setTransitDistance(String transitDistance) {
            this.transitDistance = transitDistance;
        }
    }

    // 员工围栏 POJO
    public static class staffFencingResult extends Staff {
        Double baiduRecordLng;
        Double baiduRecordLat;
        String dist;

        public Double getBaiduRecordLng() {
            return baiduRecordLng;
        }

        public void setBaiduRecordLng(Double baiduRecordLng) {
            this.baiduRecordLng = baiduRecordLng;
        }

        public Double getBaiduRecordLat() {
            return baiduRecordLat;
        }

        public void setBaiduRecordLat(Double baiduRecordLat) {
            this.baiduRecordLat = baiduRecordLat;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }
    }
}
