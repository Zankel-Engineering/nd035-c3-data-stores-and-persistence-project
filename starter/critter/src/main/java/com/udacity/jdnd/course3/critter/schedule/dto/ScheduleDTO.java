package com.udacity.jdnd.course3.critter.schedule.dto;

import com.udacity.jdnd.course3.critter.user.dto.EmployeeSkill;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Data
@NoArgsConstructor
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;
}
