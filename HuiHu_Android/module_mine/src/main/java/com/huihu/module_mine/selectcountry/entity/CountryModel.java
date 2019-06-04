package com.huihu.module_mine.selectcountry.entity;

/**
 * create by ouyangjianfeng on 2019/3/26
 * description:
 */
public class CountryModel {


    /**
     * Code : 6541
     * Name : 中国大陆
     * ParentCode : 0
     * PostCode : null
     * Longitude : 0
     * Latitude : 0
     * AreaCode : null
     * Abbreviation : CN
     * IdCode : 1
     * EngName : China
     * RegionCode : 0086
     * Regular : ^(00)(86){0,1}1\d{10}$
     * ChildCount : 31
     * ChildAreaModelList : null
     */

    private int Code;
    private String Name;
    private int ParentCode;
    private int PostCode;
    private int Longitude;
    private int Latitude;
    private int AreaCode;
    private String Abbreviation;
    private String IdCode;
    private String EngName;
    private String RegionCode;
    private String Regular;
    private int ChildCount;
    private Object ChildAreaModelList;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getParentCode() {
        return ParentCode;
    }

    public void setParentCode(int ParentCode) {
        this.ParentCode = ParentCode;
    }

    public int getPostCode() {
        return PostCode;
    }

    public void setPostCode(int PostCode) {
        this.PostCode = PostCode;
    }

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int Longitude) {
        this.Longitude = Longitude;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int Latitude) {
        this.Latitude = Latitude;
    }

    public int getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(int AreaCode) {
        this.AreaCode = AreaCode;
    }

    public String getAbbreviation() {
        return Abbreviation;
    }

    public void setAbbreviation(String Abbreviation) {
        this.Abbreviation = Abbreviation;
    }

    public String getIdCode() {
        return IdCode;
    }

    public void setIdCode(String IdCode) {
        this.IdCode = IdCode;
    }

    public String getEngName() {
        return EngName;
    }

    public void setEngName(String EngName) {
        this.EngName = EngName;
    }

    public String getRegionCode() {
        return RegionCode;
    }

    public void setRegionCode(String RegionCode) {
        this.RegionCode = RegionCode;
    }

    public String getRegular() {
        return Regular;
    }

    public void setRegular(String Regular) {
        this.Regular = Regular;
    }

    public int getChildCount() {
        return ChildCount;
    }

    public void setChildCount(int ChildCount) {
        this.ChildCount = ChildCount;
    }

    public Object getChildAreaModelList() {
        return ChildAreaModelList;
    }

    public void setChildAreaModelList(Object ChildAreaModelList) {
        this.ChildAreaModelList = ChildAreaModelList;
    }
}
