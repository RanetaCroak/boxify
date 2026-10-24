package com.albcas.boxify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Item {

    private Size size;
    private UUID id;
    private String name;
    private String note;
    private String category;
    private LocalDate purchaseTime;
    private BigDecimal purchasePrice;
    private int warrantyMonths;

    // Constructor
    public Item(Item.Size size, String name, String note) {
        this.size = size;
        this.id = UUID.randomUUID();
        this.name = name;
        this.note = note;
    }

    public enum Size {
        TINY, // Keys, flash drives, business cards.
        SMALL, // Wallets, bags, pencil cases
        MEDIUM, // Monitor, Speakers, Keyboards
        BIG, // Computers, Weird shaped objects
        BULKY // Extra
    };

    // getters setters

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDate purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // methods
    public LocalDate getWarrantyExpirationDate(){
        if (this.getPurchaseTime() == null || warrantyMonths <= 0) {
            return null;
        }
        return  this.getPurchaseTime().plusMonths(warrantyMonths);
    }

    public boolean warrantyIsInForce(){
        return (this.getWarrantyExpirationDate().isAfter(LocalDate.now()));
    }

}
