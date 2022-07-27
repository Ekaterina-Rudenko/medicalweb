package by.epam.medicalweb.util.impl;

import static by.epam.medicalweb.controller.command.RequestParameterName.*;

import java.util.Map;

public class VisitValidator {

  public static boolean checkVisitData(Map<String, String> data){
    String specialization = data.get(SPECIALIZATION_ID);
    String doctor = data.get(DOCTOR_ID);
    String service = data.get(SERVICE_ID);
    String visitDateString = data.get(VISIT_DATE);
    String visitTimeString = data.get(VISIT_TIME);
    String typePaymentString = data.get(TYPE_PAYMENT);
    String patient = data.get(PATIENT_ID);

    boolean isValid = true;
    if (specialization == null) {
      data.put(SPECIALIZATION_ID, EMPTY_SPECIALIZATION);
      isValid = false;
    }
    if (doctor == null) {
      data.put(DOCTOR_ID, EMPTY_DOCTOR);
      isValid = false;
    }
    if (service == null) {
      data.put(SERVICE_ID, EMPTY_SERVICE);
      isValid = false;
    }
    if (visitDateString == null) {
      data.put(VISIT_DATE, EMPTY_VISIT_DATE);
      isValid = false;
    }
    if (visitTimeString == null) {
      data.put(VISIT_TIME, EMPTY_VISIT_TIME);
      isValid = false;
    }
    if (typePaymentString == null) {
      data.put(TYPE_PAYMENT, EMPTY_TYPE_PAYMENT);
      isValid = false;
    }
    if (patient == null) {
      data.put(PATIENT_ID, EMPTY_PATIENT);
      isValid = false;
    }
    return isValid;

  }
}
