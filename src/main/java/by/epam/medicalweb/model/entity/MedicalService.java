package by.epam.medicalweb.model.entity;

import java.math.BigDecimal;

public class MedicalService extends AbstractEntity {
    private long serviceId;
    private Specialization specialization;
    private String serviceName;
    private BigDecimal price;

    public MedicalService() {
    }

    public MedicalService(long serviceId, Specialization specialization, String serviceName, BigDecimal price) {
        this.serviceId = serviceId;
        this.specialization = specialization;
        this.serviceName = serviceName;
        this.price = price;
    }

    public static class ServiceBuilder{
        MedicalService medicalService = new MedicalService();
        public ServiceBuilder setServiceId(long id){
            medicalService.setServiceId(id);
            return this;
        }
        public ServiceBuilder setSpecialization(Specialization specialization){
            medicalService.setSpecialization(specialization);
            return this;
        }
        public ServiceBuilder setServiceName(String name){
            medicalService.setServiceName(name);
            return this;
        }
        public ServiceBuilder setServicePrice(BigDecimal price){
            medicalService.setPrice(price);
            return this;
        }
        public MedicalService build(){
            return medicalService;
        }
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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalService that = (MedicalService) o;
        return serviceId == that.serviceId &&
                serviceName != null ? serviceName.equals(that.serviceName) : that.serviceName == null &&
                price != null ? (price.compareTo(that.price) == 0) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) (serviceId / 13);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MedicalService{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", specialization=").append(specialization);
        sb.append(", serviceName='").append(serviceName);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
