package com.example.medicalweb.entity;

public class Recommendation extends AbstractEntity {
    private long recommendationId;
    private String prescription;

    public Recommendation() {
    }

    public Recommendation(long recommendationId, String prescription) {
        this.recommendationId = recommendationId;
        this.prescription = prescription;
    }
    //todo

}
