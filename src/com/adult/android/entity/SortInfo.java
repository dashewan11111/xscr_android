package com.adult.android.entity;

/**
 * Created by huangchao on 8/11/2015.
 */
public class SortInfo {

    private String sort_inter_param;

    private String sort_condition_content;

    public String getSort_inter_param() {
        return sort_inter_param;
    }

    public void setSort_inter_param(String sort_inter_param) {
        this.sort_inter_param = sort_inter_param;
    }

    public String getSort_condition_content() {
        return sort_condition_content;
    }

    public void setSort_condition_content(String sort_condition_content) {
        this.sort_condition_content = sort_condition_content;
    }


    @Override
    public String toString() {
        return "SortInfo{" +
                "sort_inter_param='" + sort_inter_param + '\'' +
                ", sort_condition_content='" + sort_condition_content + '\'' +
                '}';
    }
}
