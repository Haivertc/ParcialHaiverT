package co.edu.uptc.management.call.dto;

public class CallDTO {
	
    private String id;
    private String phoneNumberOrigin;
    private String phoneNumberDestination;
    private String callType;
    private double callRate;
    private int callDuration;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumberOrigin() {
        return phoneNumberOrigin;
    }

    public void setPhoneNumberOrigin(String phoneNumberOrigin) {
        this.phoneNumberOrigin = phoneNumberOrigin;
    }

    public String getPhoneNumberDestination() {
        return phoneNumberDestination;
    }

    public void setPhoneNumberDestination(String phoneNumberDestination) {
        this.phoneNumberDestination = phoneNumberDestination;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public double getCallRate() {
        return callRate;
    }

    public void setCallRate(double callRate) {
        this.callRate = callRate;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }
}

