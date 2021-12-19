package com.example.medicalweb.entity;

public class Specialization extends AbstractEntity {
    private long specializationId;
    private String specializationName;

    public Specialization() {
    }

    public Specialization(String specializationName) {
        this.specializationName = specializationName;
    }

    public Specialization(long specializationId, String specializationName) {
        this.specializationId = specializationId;
        this.specializationName = specializationName;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialization that = (Specialization) o;
        return specializationId == that.specializationId &&
                specializationName != null ? specializationName.equals(that.specializationName) : that.specializationName == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) specializationId / 13;
        result = 31 * result + (specializationName != null ? specializationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Specialization{");
        sb.append("specializationId=").append(specializationId);
        sb.append(", specializationName='").append(specializationName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
