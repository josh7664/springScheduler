package com.codenotfound.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RetailerDetails")
public class RetailerDetails {

    private String retailer_id;
    private String retailer_name;
    private String retailer_location;

    public RetailerDetails() {
    }

    public String getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(String retailer_id) {
        this.retailer_id = retailer_id;
    }

    public String getRetailer_name() {
        return retailer_name;
    }

    public void setRetailer_name(String retailer_name) {
        this.retailer_name = retailer_name;
    }

    public String getRetailer_location() {
        return retailer_location;
    }

    public void setRetailer_location(String retailer_location) {
        this.retailer_location = retailer_location;
    }

    @Override
    public String toString() {
        return "Retailerdetails [Retailerid=" + retailer_id + ", Retailername=" + retailer_name
                + ", retailerlocation=" + retailer_location + "]";
    }

}

