package com.example.medicalweb.entity;

import java.math.BigDecimal;

public class MedicalService extends AbstractEntity {
    private long serviceId;
    private String serviceName;
    private BigDecimal price;

    public MedicalService() {
    }

    public MedicalService(long serviceId, String serviceName, BigDecimal price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalService that = (MedicalService) o;
        return serviceId == that.serviceId &&
                serviceName != null ? serviceName.equals(that.serviceName) : that.serviceName == null &&
                price != null ? (price.compareTo(that.price) == 0) : that.price == null;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalService that = (MedicalService) o;

        if (serviceId != that.serviceId) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int)(serviceId/13);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MedicalService{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    //DELETE THIS METHOD
    public static boolean compareTwoBIgNumber(BigDecimal a, BigDecimal b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(123123123);
        BigDecimal b = BigDecimal.valueOf(123123123.0);
        System.out.println(compareTwoBIgNumber(a, b));

    }
}
