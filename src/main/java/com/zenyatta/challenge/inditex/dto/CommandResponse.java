package com.zenyatta.challenge.inditex.dto;

public record CommandResponse(
        Long itemId,
        ResponseStatus responseStatus,
        String errorMessage) {
    public static CommandResponse buildResponse(
            final Long itemId,
            final ResponseStatus status,
            final String errorMessage) {
        return new CommandResponse(itemId, status, errorMessage);
    }
}
