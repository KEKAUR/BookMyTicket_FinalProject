package com.capgemini.movieticketbooking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieShowDTO {

    @NotNull(message = "Show date and time is required")
    @Future(message = "Show date and time must be in the future")
    private LocalDateTime showDateTime;

    @PositiveOrZero(message = "Movie ID must be a positive number or zero")
    private int movieId;

    @PositiveOrZero(message = "Theatre ID must be a positive number or zero")
    private int theatreId;

}

