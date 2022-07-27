package by.epam.medicalweb.model.entity;

import java.time.LocalDate;

public class Visit extends AbstractEntity {
    public enum TypePayment {
        FREE("free"), INSURANCE("insurance"), BILLABLE("billable");
        String typeOfPayment;

        TypePayment(String typeOfPayment) {
            this.typeOfPayment = typeOfPayment;
        }

        public String getTypePaymentString() {
            return typeOfPayment;
        }
    }

    public enum VisitState {
        NEW("new"), COMPLETED("completed"), CANCELLED("cancelled");
        String visitState;

        VisitState(String visitState) {
            this.visitState = visitState;
        }

        public String getStateString() {
            return visitState;
        }
    }

    private long visitId;
    private Specialization specialization;
    private Doctor doctor;
    private MedicalService service;
    private LocalDate date;
    private int time;
    private TypePayment typeOfPayment;
    private VisitState state;
    private Patient patient;

    public Visit(){}

    public Visit(long visitId, Specialization specialization, Doctor doctor, MedicalService service,
                 LocalDate date, int time, TypePayment typeOfPayment,
                 VisitState visitState, Patient patient) {
        this.visitId = visitId;
        this.specialization = specialization;
        this.doctor = doctor;
        this.service = service;
        this.date = date;
        this.time = time;
        this.typeOfPayment = typeOfPayment;
        this.state = visitState;
        this.patient = patient;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public MedicalService getService() {
        return service;
    }

    public void setService(MedicalService service) {
        this.service = service;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return visitId == visit.visitId &&
                specialization != null ? specialization == visit.specialization : visit.specialization == null &&
                doctor != null ? doctor == visit.doctor : visit.doctor == null &&
                service != null ? service == visit.service : visit.service == null &&
                patient != null ? patient == visit.patient : visit.patient == null &&
                date != null ? date.equals(visit.date) : visit.date == null &&
                time == visit.time&&
                typeOfPayment != null ? typeOfPayment == visit.typeOfPayment : visit.typeOfPayment == null &&
                state != null ? state == visit.state : visit.state == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) (visitId * time/ 13);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (typeOfPayment != null ? typeOfPayment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visit{");
        sb.append("visitId=").append(visitId);
        sb.append(", specializationId=").append(specialization);
        sb.append(", doctorId=").append(doctor);
        sb.append(", serviceId=").append(service);
        sb.append(", Date=").append(date);
        sb.append(", Time=").append(time).append(":00");
        sb.append(", typeOfPayment=").append(typeOfPayment);
        sb.append(", visitState=").append(state);
        sb.append(", patientId=").append(patient);
        sb.append('}');
        return sb.toString();
    }

    public static class VisitBuilder{
        private  Visit visit = new Visit();

        public VisitBuilder setVisitId(long visitId) {
            visit.setVisitId(visitId);
            return this;
        }

        public VisitBuilder setSpecialization(Specialization specialization){
            visit.specialization = specialization;
            return this;
        }
        public VisitBuilder setDoctor(Doctor doctor){
            visit.doctor = doctor;
            return this;
        }
        public VisitBuilder setMedicalService(MedicalService service){
            visit.service = service;
            return this;
        }
        public VisitBuilder setLocalDate(LocalDate date){
            visit.date = date;
            return this;
        }
        public VisitBuilder setTime(int time){
            visit.time = time;
            return this;
        }
        public VisitBuilder setTypeOfPayment(TypePayment typeOfPayment){
            visit.typeOfPayment = typeOfPayment;
            return this;
        }
        public VisitBuilder setVisitState(VisitState state){
            visit.state = state;
            return this;
        }
        public VisitBuilder setPatient(Patient patient){
            visit.patient = patient;
            return this;
        }
        public Visit build(){
            return visit;
        }

    }
}
