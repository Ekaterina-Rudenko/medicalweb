package by.epam.medicalweb.model.entity;

public class Prescription extends AbstractEntity {
    private long prescriptionId;
    private long visitId;
    private String prescriptionText;

    public Prescription() {
    }

    public Prescription(long prescriptionId, long visitId, String prescriptionText) {
        this.prescriptionId = prescriptionId;
        this.visitId = visitId;
        this.prescriptionText = prescriptionText;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public String getPrescriptionText() {
        return prescriptionText;
    }

    public void setPrescriptionText(String text) {
        this.prescriptionText = text;
    }
    //todo

}
