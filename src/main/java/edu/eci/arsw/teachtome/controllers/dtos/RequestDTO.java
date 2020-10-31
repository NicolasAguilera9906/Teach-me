package edu.eci.arsw.teachtome.controllers.dtos;

import edu.eci.arsw.teachtome.model.Request;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RequestDTO implements Serializable {

    private RequestPKDTO requestId;

    private Boolean accepted;

    private GetUserDTO student;

    public RequestDTO() {
    }

    public RequestDTO(Request request) {
        if (request.getRequestId() != null) {
            this.requestId = new RequestPKDTO(request.getRequestId());
        }
        if (request.hasAnswer()) {
            this.accepted = request.isAccepted();
        }
        this.student = new GetUserDTO(request.getStudent());
    }

    public RequestPKDTO getRequestId() {
        return requestId;
    }

    public void setRequestId(RequestPKDTO requestId) {
        this.requestId = requestId;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public GetUserDTO getStudent() {
        return student;
    }

    public void setStudent(GetUserDTO student) {
        this.student = student;
    }

    public boolean hasAnswer() {
        return accepted != null;
    }

    public static List<RequestDTO> getRequestsDTO(List<Request> requests) {
        List<RequestDTO> requestDTOS = new CopyOnWriteArrayList<>();
        for (Request request : requests) {
            requestDTOS.add(new RequestDTO(request));
        }
        return requestDTOS;
    }
}
