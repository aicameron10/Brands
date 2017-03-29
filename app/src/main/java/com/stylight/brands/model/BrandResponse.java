package com.stylight.brands.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BrandResponse {

    @SerializedName("start")
    private int start;
    @SerializedName("count")
    private int count;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("next")
    private int next;
    @SerializedName("brands")
    private List<Brands> brands;


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public List<Brands> getBrands() {
        return brands;
    }

    public void setBrands(List<Brands> brands) {
        this.brands = brands;
    }

}

