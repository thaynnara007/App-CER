package com.cuidar.app_cer.model.patient;

import com.cuidar.app_cer.model.address.Address;

public class EditPatientBody {

    private Patient patient;
    private Address address;

    public EditPatientBody(Patient patient, Address address) {
        this.patient = patient;
        this.address = address;
    }
}
