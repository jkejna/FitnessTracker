package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserDetailsDto(@Nullable Long id, String firstName, String lastName){
}
