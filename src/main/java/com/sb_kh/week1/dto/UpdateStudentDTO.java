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
public class UpdateStudentDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private LocalDateTime birthDate;
    private int semester;
    private int studentCentralId;

}
