package bincom.assessement.reportapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
    @Builder
    @AllArgsConstructor
    @Data
    public class ApiResponse {
        private ZonedDateTime timeStamp;
        private boolean isSuccessful;
        private Object data;
        private int status;
        private String path;

}
