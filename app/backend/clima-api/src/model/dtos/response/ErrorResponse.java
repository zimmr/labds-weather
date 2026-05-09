package model.dtos.response;

public class ErrorResponse {
    public int code;
    public String messsage;

    public ErrorResponse(int code, String messsage) {
        this.code = code;
        this.messsage = messsage;
    }
}
