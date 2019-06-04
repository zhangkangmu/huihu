package com.huihu.module_home.addquestioncategory.entitiy;

/**
 * create by ouyangjianfeng on 2019/3/27
 * description:
 */
public class CategoryModel {


    /**
     * category : 理论交流
     * categoryId : 2
     * picture : https://gaimg.fx110.com/upload/images/glodcoinmall/2019/02/27/145615598.jpg
     */

    private String category;
    private int categoryId;
    private String picture;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
