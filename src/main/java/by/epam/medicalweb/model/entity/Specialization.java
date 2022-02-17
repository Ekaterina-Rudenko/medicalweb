package by.epam.medicalweb.model.entity;

public class Specialization extends AbstractEntity {
    private long specializationId;
    private String name;

    public Specialization() {
    }

    public Specialization(String name) {
        this.name = name;
    }

    public Specialization(long specializationId, String name) {
        this.specializationId = specializationId;
        this.name = name;
    }
    public static class Builder{
        private Specialization specialization = new Specialization();
        public Builder setSpecializationId(long specId){
            specialization.setSpecializationId(specId);
            return this;
        }
        public Builder setSpecializationName(String name){
            specialization.setName(name);
            return this;
        }
        public Specialization build(){
            return specialization;
        }
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialization that = (Specialization) o;
        return specializationId == that.specializationId &&
                name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) specializationId / 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Specialization{");
        sb.append("specializationId=").append(specializationId);
        sb.append(", specializationName='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
