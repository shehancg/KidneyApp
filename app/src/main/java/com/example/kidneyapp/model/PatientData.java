package com.example.kidneyapp.model;

public class PatientData {

    private String Blood_type;
    private int HLA_A1;
    private int HLA_A2;
    private int HLA_B1;
    private int HLA_B2;
    private int HLA_DR1;
    private int HLA_DR2;
    private double Id;
    private int agHBs;
    private int age;
    private int anti_HBc;
    private int anti_HCV;
    private String race;
    private String sex;

    public String getBlood_type() {
        return Blood_type;
    }

    public void setBlood_type(String blood_type) {
        Blood_type = blood_type;
    }

    public int getHLA_A1() {
        return HLA_A1;
    }

    public void setHLA_A1(int HLA_A1) {
        this.HLA_A1 = HLA_A1;
    }

    public int getHLA_A2() {
        return HLA_A2;
    }

    public void setHLA_A2(int HLA_A2) {
        this.HLA_A2 = HLA_A2;
    }

    public int getHLA_B1() {
        return HLA_B1;
    }

    public void setHLA_B1(int HLA_B1) {
        this.HLA_B1 = HLA_B1;
    }

    public int getHLA_B2() {
        return HLA_B2;
    }

    public void setHLA_B2(int HLA_B2) {
        this.HLA_B2 = HLA_B2;
    }

    public int getHLA_DR1() {
        return HLA_DR1;
    }

    public void setHLA_DR1(int HLA_DR1) {
        this.HLA_DR1 = HLA_DR1;
    }

    public int getHLA_DR2() {
        return HLA_DR2;
    }

    public void setHLA_DR2(int HLA_DR2) {
        this.HLA_DR2 = HLA_DR2;
    }

    public double getId() {
        return Id;
    }

    public void setId(double id) {
        Id = id;
    }

    public int getAgHBs() {
        return agHBs;
    }

    public void setAgHBs(int agHBs) {
        this.agHBs = agHBs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAnti_HBc() {
        return anti_HBc;
    }

    public void setAnti_HBc(int anti_HBc) {
        this.anti_HBc = anti_HBc;
    }

    public int getAnti_HCV() {
        return anti_HCV;
    }

    public void setAnti_HCV(int anti_HCV) {
        this.anti_HCV = anti_HCV;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
