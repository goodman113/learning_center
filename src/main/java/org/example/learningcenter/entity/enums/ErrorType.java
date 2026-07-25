package org.example.learningcenter.entity.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    INTERNAL_ERROR("internal.server.error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_TYPE_ERROR("file.type.error", HttpStatus.BAD_REQUEST),
    ERROR_SAVING_FILE("error.saving.file", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_JSON("bad.json", HttpStatus.BAD_REQUEST),
    PAGE_NOT_FOUND("page.not.found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("user.not.found", HttpStatus.NOT_FOUND),
    LESSON_NOT_FOUND("lesson,not.found", HttpStatus.NOT_FOUND),
    ATTENDANCE_NOT_FOUND("attendance.not.found",HttpStatus.NOT_FOUND),
    UNSUPPORTED_MEDIA_TYPE("unsupported.media.type", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    METHOD_NOT_ALLOWED("method.not.allowed", HttpStatus.METHOD_NOT_ALLOWED),
    MISSING_PARAMETER("missing.parameter", HttpStatus.BAD_REQUEST),
    TYPE_MISMATCH("type.mismatch", HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR("validation.error", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("forbidden", HttpStatus.FORBIDDEN),
    PHONE_NUMBER_ALREADY_EXISTS("phone.number.alerady.exists", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS("user.alerady.exists", HttpStatus.BAD_REQUEST),
    ILLEGAL_USER("illegal.user", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER_OR_PASSWORD("illegal.phone.number.or.password", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_NOT_FOUND("phone.number.not.found", HttpStatus.NOT_FOUND),
    USER_IS_BLOCKED("user.is.blocked", HttpStatus.BAD_REQUEST),
    NO_PERMISSION("no.permission", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("acces.denied", HttpStatus.BAD_REQUEST),
    USER_NOT_MATCH("user.not.much", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_NOT_FOUND("refresh.token.not.found", HttpStatus.NOT_FOUND),
    TEST_NOT_FOUND("test.not.found", HttpStatus.NOT_FOUND),
    ATTACHMENT_NOT_FOUND("attachment.not.found", HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_EXPIRED("refresh.token.expired", HttpStatus.BAD_REQUEST ),
    GROUP_ALREADY_EXISTS_WITH_THIS_NAME("group.already.exists.with.this.name", HttpStatus.BAD_REQUEST),
    TEACHER_NOT_FOUND("teacher.not.found", HttpStatus.NOT_FOUND),
    TIMETABLE_NOT_FOUND("timetable.not.found", HttpStatus.NOT_FOUND),
    GROUP_NOT_FOUND("group.not.found", HttpStatus.NOT_FOUND),
    INVALID_TIME_RANGE("invalid.time.range", HttpStatus.BAD_REQUEST),
    INVALID_FILE_TYPE("invalid.file.type", HttpStatus.BAD_REQUEST);



    private final String msg;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    ErrorType(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    ErrorType(String msg) {
        this.msg = msg;
    }

    public int getStatusValue() {
        return status.value();
    }
}