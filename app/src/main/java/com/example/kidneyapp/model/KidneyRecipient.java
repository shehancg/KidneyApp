package com.example.kidneyapp.model;

public class KidneyRecipient {
    private int id;
    private double compatibilityScore;

    public KidneyRecipient(int id, double compatibilityScore) {
        this.id = id;
        this.compatibilityScore = compatibilityScore;
    }

    public int getId() {
        return id;
    }

    public double getCompatibilityScore() {
        return compatibilityScore;
    }
}
