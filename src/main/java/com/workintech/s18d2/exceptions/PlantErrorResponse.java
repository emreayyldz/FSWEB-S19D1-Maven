package com.workintech.s18d2.exceptions;

import java.time.LocalDateTime;

public record PlantErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}
