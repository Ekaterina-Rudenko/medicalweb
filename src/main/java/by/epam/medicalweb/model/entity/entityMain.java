package by.epam.medicalweb.model.entity;

public class entityMain {
    public static void main(String[] args) {
        Visit visit = new Visit.VisitBuilder()
                .setVisitId(1)
                .setVisitState(Visit.VisitState.NEW)
                .build();
        System.out.println(visit);
    }

}
