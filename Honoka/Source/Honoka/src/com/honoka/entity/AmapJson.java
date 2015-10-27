/**
 * 高德地图 API 返回 Json POJO
 */
package com.honoka.entity;

public class AmapJson {
    // 高德地图地址解析 Json POJO
    public static class AmapJsonGeocoding {
        // 状态
        Integer status;
        // 信息
        String info;
        // 条目数
        Integer count;
        // 结果
        Geocodes[] geocodes;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Geocodes[] getGeocodes() {
            return geocodes;
        }

        public void setGeocodes(Geocodes[] geocodes) {
            this.geocodes = geocodes;
        }

    }

    // 地址解析结果
    public static class Geocodes {
        // 地址信息
        String formatted_address;
        // 省市
        String province;
        // 区号
        String citycode;
        // 城市
        String city;
        // 高德的 API 很厉害，有结果的时候丢 STRING，没结果的时候丢 ARRAY，咱先不 Handle 这个鬼玩意儿了
        // 区
        // String district;
        // 乡镇
        // String[] township;
        // 社区信息
        // Neighborhood neighborhood;
        // 楼信息
        // Building building;
        // 所属兴趣点所在区域编码
        // String adcode;
        // 街道
        // String[] street;
        // 门牌
        // String[] number;
        // 坐标
        String location;
        // 匹配级别
        String level;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        // public String getDistrict() {
        // return district;
        // }
        //
        // public void setDistrict(String district) {
        // this.district = district;
        // }

        // public String[] getTownship() {
        // return township;
        // }
        //
        // public void setTownship(String[] township) {
        // this.township = township;
        // }
        //
        // public Neighborhood getNeighborhood() {
        // return neighborhood;
        // }
        //
        // public void setNeighborhood(Neighborhood neighborhood) {
        // this.neighborhood = neighborhood;
        // }
        //
        // public Building getBuilding() {
        // return building;
        // }
        //
        // public void setBuilding(Building building) {
        // this.building = building;
        // }
        //
        // public String getAdcode() {
        // return adcode;
        // }
        //
        // public void setAdcode(String adcode) {
        // this.adcode = adcode;
        // }
        //
        // public String[] getStreet() {
        // return street;
        // }
        //
        // public void setStreet(String[] street) {
        // this.street = street;
        // }
        //
        // public String[] getNumber() {
        // return number;
        // }
        //
        // public void setNumber(String[] number) {
        // this.number = number;
        // }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

    // 社区
    public static class Neighborhood {
        // 社区名称
        String[] name;
        // POI 类型
        String[] type;

        public String[] getName() {
            return name;
        }

        public void setName(String[] name) {
            this.name = name;
        }

        public String[] getType() {
            return type;
        }

        public void setType(String[] type) {
            this.type = type;
        }

    }

    // 建筑
    public static class Building {
        // 建筑名称
        String[] name;
        // 类型
        String[] type;

        public String[] getName() {
            return name;
        }

        public void setName(String[] name) {
            this.name = name;
        }

        public String[] getType() {
            return type;
        }

        public void setType(String[] type) {
            this.type = type;
        }

    }

}
