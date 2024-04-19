package com.capgemini.movieticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

    private boolean isReserved ;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;

    private int theatreId; 
}
