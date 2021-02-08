package com.udacity.jdnd.course3.critter.user.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the dastabase directly.
 */
@Data
@NoArgsConstructor
public class EmployeeRequestDTO {
    private Set<EmployeeSkill> skills;
    private LocalDate date;
}
