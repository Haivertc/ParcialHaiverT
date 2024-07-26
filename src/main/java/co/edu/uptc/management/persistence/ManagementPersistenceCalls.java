package co.edu.uptc.management.persistence;

import co.edu.uptc.management.call.dto.CallDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementPersistenceCalls {

    private List<CallDTO> callList;
    private static final double SAME_OPERATOR_SURCHARGE = 0.0;
    private static final double OTHER_OPERATOR_SURCHARGE = 0.05;
    private static final double NATIONAL_SURCHARGE = 0.10;
    private static final double INTERNATIONAL_SURCHARGE = 0.15;

    public ManagementPersistenceCalls() {
        this.callList = new ArrayList<>();
    }

    public List<CallDTO> getCallList() {
        return new ArrayList<>(callList);
    }

    public CallDTO getCallById(String id) {
        for (CallDTO call : callList) {
            if (call.getId().equals(id)) {
                return call;
            }
        }
        return null;
    }

    public Map<String, Object> getCallInfoById(String id) {
        CallDTO call = getCallById(id);
        if (call != null) {
            double totalLlamada = call.getCallRate() * call.getCallDuration();
            Map<String, Object> callInfo = new HashMap<>();
            callInfo.put("numeroOrigen", call.getPhoneNumberOrigin());
            callInfo.put("numeroDestino", call.getPhoneNumberDestination());
            callInfo.put("totalLlamada", totalLlamada);
            return callInfo;
        }
        return null;
    }

    
    public boolean addCall(CallDTO callDTO) {
        if (getCallById(callDTO.getId()) == null) {
            callDTO.setCallRate(calculateCallRate(callDTO));
            return callList.add(callDTO);
        }
        return false;
    }

    public boolean deleteCall(String id) {
        CallDTO callDTO = getCallById(id);
        if (callDTO != null) {
            return callList.remove(callDTO);
        }
        return false;
    }

    public boolean updateCall(CallDTO updatedCallDTO) {
        for (int i = 0; i < callList.size(); i++) {
            CallDTO callDTO = callList.get(i);
            if (callDTO.getId().equals(updatedCallDTO.getId())) {
                callList.set(i, updatedCallDTO);
                return true;
            }
        }
        return false;
    }

    private double calculateCallRate(CallDTO callDTO) {
        double rate = callDTO.getCallRate();
        switch (callDTO.getCallType().toLowerCase()) {
            case "mismo operador":
                return rate;
            case "otro operador":
                return rate + (rate * OTHER_OPERATOR_SURCHARGE);
            case "nacional":
                return rate + (rate * NATIONAL_SURCHARGE);
            case "internacional":
                return rate + (rate * INTERNATIONAL_SURCHARGE);
            default:
                return rate;
        }
    }

    public double getTotalCashByType(String callType) {
        double total = 0;
        for (CallDTO call : callList) {
            if (callType == null || call.getCallType().equalsIgnoreCase(callType)) {
                total += call.getCallRate() * call.getCallDuration();
            }
        }
        return total;
    }

    public int getTotalMinutesByType(String callType) {
        int totalMinutes = 0;
        for (CallDTO call : callList) {
            if (callType == null || call.getCallType().equalsIgnoreCase(callType)) {
                totalMinutes += call.getCallDuration();
            }
        }
        return totalMinutes;
    }

    public List<CallDTO> getCallsByFilter(CallDTO filter) {
        List<CallDTO> filteredCalls = new ArrayList<>();
        for (CallDTO call : callList) {
            if ((filter.getPhoneNumberOrigin() == null || call.getPhoneNumberOrigin().equals(filter.getPhoneNumberOrigin())) &&
                (filter.getPhoneNumberDestination() == null || call.getPhoneNumberDestination().equals(filter.getPhoneNumberDestination())) &&
                (filter.getCallType() == null || call.getCallType().equals(filter.getCallType())) &&
                (filter.getCallRate() == 0 || call.getCallRate() == filter.getCallRate()) &&
                (filter.getCallDuration() == 0 || call.getCallDuration() == filter.getCallDuration())) {
                filteredCalls.add(call);
            }
        }
        return filteredCalls;
    }
}
