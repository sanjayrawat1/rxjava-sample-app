package io.github.sanjayrawat1.reactivex.response;

import lombok.Data;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
@Data
public class ApiResponse {

    private boolean success;

    private Object response;

    public ApiResponse(boolean success) {
        this.success = success;
    }
}
