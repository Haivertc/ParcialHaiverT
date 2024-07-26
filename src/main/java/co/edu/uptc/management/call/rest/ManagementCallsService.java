package co.edu.uptc.management.call.rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import co.edu.uptc.management.call.dto.CallDTO;
import co.edu.uptc.management.persistence.ManagementPersistenceCalls;

@Path("/ManagementCalls")
public class ManagementCallsService {

    private static final ManagementPersistenceCalls managementPersistenceCalls = new ManagementPersistenceCalls();
    private static final String PHONE_NUMBER_ORIGIN = "1234567890";
    private static final double CALL_RATE = 0.10;

    @GET
    @Path("/getCalls")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CallDTO> getCalls() {
        return managementPersistenceCalls.getCallList();
    }

    @GET
    @Path("/getCallById")
    @Produces(MediaType.APPLICATION_JSON)
    public CallDTO getCallById(@QueryParam("id") String id) {
        return managementPersistenceCalls.getCallById(id);
    }
    
    @POST
    @Path("/addCall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addCall(CallDTO callDTO) {
        callDTO.setPhoneNumberOrigin(PHONE_NUMBER_ORIGIN);
        callDTO.setCallRate(CALL_RATE);
        return managementPersistenceCalls.addCall(callDTO);
    }

    @GET
    @Path("/getTotalCashByType")
    @Produces(MediaType.APPLICATION_JSON)
    public double getTotalCashByType(@QueryParam("callType") String callType) {
        return managementPersistenceCalls.getTotalCashByType(callType);
    }

    @GET
    @Path("/getTotalMinutesByType")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTotalMinutesByType(@QueryParam("callType") String callType) {
        return managementPersistenceCalls.getTotalMinutesByType(callType);
    }

    @POST
    @Path("/getCallsByFilter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CallDTO> getCallsByFilter(CallDTO filter) {
        return managementPersistenceCalls.getCallsByFilter(filter);
    }
}
