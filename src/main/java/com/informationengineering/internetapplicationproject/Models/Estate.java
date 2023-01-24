package com.informationengineering.internetapplicationproject.Models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Estates")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Double price;

    @Column(name = "stocks_count")
    Integer stocksCount;

    @Column(name = "sale_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date saleDate;

    @Column(name = "sale_price")
    Double salePrice;

    @Column(name = "buyer_name")
    String buyerName;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createdAt ;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updatedAt;
    @Column(name = "version")
    Integer version;


    public Estate() {
    }

    public Estate(String name, Double price, Integer stocksCount) {
        this.name = name;
        this.price = price;
        this.stocksCount = stocksCount;
    }

    public Estate(String name, Double price, Integer stocksCount, Date saleDate, Double salePrice, String buyerName) {
        this.name = name;
        this.price = price;
        this.stocksCount = stocksCount;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        this.buyerName = buyerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStocksCount() {
        return stocksCount;
    }

    public void setStocksCount(Integer stocksCount) {
        this.stocksCount = stocksCount;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
