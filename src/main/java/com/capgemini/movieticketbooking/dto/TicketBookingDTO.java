package com.capgemini.movieticketbooking.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketBookingDTO {

    @Positive(message = "Theatre ID must be positive")
    @NotNull(message = "Theatre ID cannot be blank")
    private int theatreId;

    @Positive(message = "Show ID must be positive")
    @NotNull(message = "Show ID cannot be blank")
    private int showId;

    @Positive(message = "User ID must be positive")
    @NotNull(message = "User ID cannot be blank")
    private int userId;

    @Positive(message = "Amount must be positive")
    @NotNull(message = "Amount cannot be blank")  
    private int amount;

    @FutureOrPresent(message = "Purchase date must be in the present or future")
    private LocalDate purchaseDate;

    @NotNull(message = "List of seat IDs cannot be null")
    @Size(min = 1, message = "At least one seat ID must be provided")
    private List<Integer> listSeatId;

    @NotBlank(message = "Status cannot be blank")
    private String status;

}