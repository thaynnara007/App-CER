package com.cuidar.app_cer.model.patient;

import com.cuidar.app_cer.model.address.Address;

public class Patient {

    private int id;
    private String name;
    private String lastName;
    private String password;
    private String cpf;
    private String cpfFormatted;
    private String birthday;
    private String phoneNumber;
    private String email;
    private Boolean firstLogin;
    private Address address;

    public Patient(String name, String lastName, String birthday, String phoneNumber, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Patient(String name, String lastName, String birthday, String phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpfFormatted() {
        return cpfFormatted;
    }

    public void setCpfFormatted(String cpfFormatted) {
        this.cpfFormatted = cpfFormatted;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
