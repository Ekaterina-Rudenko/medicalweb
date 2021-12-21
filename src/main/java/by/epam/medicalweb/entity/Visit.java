package by.epam.medicalweb.entity;

import java.time.LocalDate;

public class Visit extends AbstractEntity {
    public enum TypePayment {
        CASH("cash"), CARD("card"), FREE("free"), INSURANCE("insurance");
        String typeOfPayment;

        TypePayment(String typeOfPayment) {
            this.typeOfPayment = typeOfPayment;
        }

        public String getTypeOfPayment() {
            return typeOfPayment;
        }
    }

    public enum VisitState {
        NEW("new"), COMPLETED("completed"), CANCELLED("cancelled");
        String visitState;

        VisitState(String visitState) {
            this.visitState = visitState;
        }

        public String getVisitState() {
            return visitState;
        }
    }

    private long visitId;
    private long specializationId;
    private long doctorId;
    private long serviceId;
    private LocalDate date;
    private int time;
    private TypePayment typeOfPayment;
    private VisitState state;
    private long patientId;

    public Visit(){}

    public Visit(long visitId, long specializationId, long doctorId, long serviceId,
                 LocalDate date, int time, TypePayment typeOfPayment,
                 VisitState visitState, long patientId) {
        this.visitId = visitId;
        this.specializationId = specializationId;
        this.doctorId = doctorId;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
        this.typeOfPayment = typeOfPayment;
        this.state = visitState;
        this.patientId = patientId;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = this.date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int visitTime) {
        this.time = visitTime;
    }

    public TypePayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypePayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public VisitState getState() {
        return state;
    }

    public void setState(VisitState state) {
        this.state = state;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return visitId == visit.visitId &&
                specializationId == visit.specializationId &&
                doctorId == visit.doctorId &&
                serviceId == visit.serviceId &&
                patientId == visit.patientId &&
                date != null ? date.equals(visit.date) : visit.date == null &&
                time == visit.time&&
                typeOfPayment != null ? typeOfPayment == visit.typeOfPayment : visit.typeOfPayment == null &&
                state != null ? state == visit.state : visit.state == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) (specializationId * doctorId * visitId * patientId * time/ 13);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (typeOfPayment != null ? typeOfPayment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visit{");
        sb.append("visitId=").append(visitId);
        sb.append(", specializationId=").append(specializationId);
        sb.append(", doctorId=").append(doctorId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", Date=").append(date);
        sb.append(", Time=").append(time).append(":00");
        sb.append(", typeOfPayment=").append(typeOfPayment);
        sb.append(", visitState=").append(state);
        sb.append(", patientId=").append(patientId);
        sb.append('}');
        return sb.toString();
    }
}
