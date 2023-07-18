package com.sb_kh.week1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeacherDTO {

    @NotNull
    private String name;
    @NotNull
    private LocalDateTime birthDate;

}
