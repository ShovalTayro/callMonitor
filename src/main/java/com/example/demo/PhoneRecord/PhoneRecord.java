package com.example.demo.PhoneRecord;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class PhoneRecord{

    @Id
    @SequenceGenerator(
        name = "phoneRecord_sequence",
        sequenceName = "phoneRecord_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "phoneRecord_sequence"
)

    private Long id;
    private String phoneNumber;
    private String time;
    private String callType; //incoming/ outgoing
    private int duration;
    private boolean savedContact;

    public PhoneRecord() { }

    public PhoneRecord(String time, String description, int duration, String phoneNumber){
        this.time = time;
        this.callType = description;
        this.duration = duration;
        this.phoneNumber = phoneNumber;
        this.savedContact = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getCallType(){
        return callType;
    }

    public void setCallType(String callType){
        this.callType = callType;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public  boolean getsavedContact(){
        return savedContact;
    }

    public void setsavedContact(boolean savedContact){
        this.savedContact = savedContact;
    }

    @Override
    public String toString() {
        return "phoneRecord [,\ntime = '" + time +
                ",\ncallType = '" + callType +
                ",\nduration =" + duration +
                ",\nphoneNumber = " + phoneNumber +
                ",\nsavedContact = " + savedContact +
                " ]";
    }



}