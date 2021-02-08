package com.udacity.jdnd.course3.critter.user.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Data
@NoArgsConstructor
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;
}
