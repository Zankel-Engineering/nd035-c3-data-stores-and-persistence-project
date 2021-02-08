package com.udacity.jdnd.course3.critter.user.dto;

import java.time.DayOfWeek;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Data
@NoArgsConstructor
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;
}
