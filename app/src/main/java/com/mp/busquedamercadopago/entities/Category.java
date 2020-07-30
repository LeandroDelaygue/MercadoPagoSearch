package com.mp.busquedamercadopago.entities;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private String id;
    private String site_id;
    private String title;
    private String thumbnail;
    private double price;

    public  Installments  installments;
    public  Address  address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Installments getInstallments() {
        return installments;
    }

    public void setInstallments(Installments installments) {
        this.installments = installments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public class Installments implements Serializable {
        public String currency_id;
        public int quantity;
        public double rate;
        public double amount;
    }

    public class Address implements Serializable {
        private String state_id;
        public String state_name;
        public String city_id;
        public String city_name;
    }
}





