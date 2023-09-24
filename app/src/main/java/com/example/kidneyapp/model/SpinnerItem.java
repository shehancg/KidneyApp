package com.example.kidneyapp.model;

public class SpinnerItem {
    private String itemName;
    private int itemValue;

    private String itemValueString;

    public SpinnerItem(String itemName, int itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    // Constructor for itemName and itemValueString
    public SpinnerItem(String itemName, String itemValueString) {
        this.itemName = itemName;
        this.itemValueString = itemValueString;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemValue() {
        return itemValue;
    }

    public String getItemValueString() {
        return itemValueString;
    }

    @Override
    public String toString() {
        return itemName; // Display the item name in the Spinner
    }
}
