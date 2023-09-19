package com.example.kidneyapp.model;

public class SpinnerItem {
    private String itemName;
    private int itemValue;

    public SpinnerItem(String itemName, int itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemValue() {
        return itemValue;
    }

    @Override
    public String toString() {
        return itemName; // Display the item name in the Spinner
    }
}
