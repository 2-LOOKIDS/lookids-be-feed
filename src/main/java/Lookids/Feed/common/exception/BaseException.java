package Lookids.Feed.common.exception;

import lombok.Getter;
import Lookids.Feed.common.entity.BaseResponseStatus;

@Getter
public class BaseException extends RuntimeException {

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}