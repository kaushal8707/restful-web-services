package com.me.rest.webservices.restfulwebservices.exceptions;
import java.util.Date;

// In Response of APi format will be like ...
//  "timestamp": "2020-06-06T10:53:13.123+0000",
//    "status": 404,
//    "error": "Not Found",
//    "message": "id-500",
public class ExceptionResponse
{
    private Date timestamp;
    private String message;
    private String description;

    public ExceptionResponse(Date timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public Date getTimestamp() { return timestamp; }

    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
