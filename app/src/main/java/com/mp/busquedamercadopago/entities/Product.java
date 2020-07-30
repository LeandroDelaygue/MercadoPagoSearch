package com.mp.busquedamercadopago.entities;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private String id;
    private String status;
    private String domain_id;
    private String name;
    private List<Atributes> attributes;
    private List<Pictures> pictures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Atributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Atributes> attributes) {
        this.attributes = attributes;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }

    public class Pictures implements Serializable {
        private String id;
        public String url;

    }

    public class Atributes implements Serializable {
        private String id;
        public String value_name;
        public String value_id;
        public String name;
    }
}





