package com.mini.homeworks.MainActivity;

public class Course {
    private String name;
    private int imageId;
    private String siteId;
    public Course(String name, int imageId, String siteId) {
        this.name = name;
        this.imageId = imageId;
        this.siteId = siteId;
    }
    public String getName() {
        return name;
    }
    public int getImageId () {
        return imageId;
    }
    public String getSiteId() {
        return siteId;
    }
}
