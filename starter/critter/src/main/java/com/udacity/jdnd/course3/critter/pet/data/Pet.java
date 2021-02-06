package com.udacity.jdnd.course3.critter.pet.data;

import com.udacity.jdnd.course3.critter.pet.dto.PetType;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PetType type;
    private String name;

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate birthDate;

    @Column(length=512)
    private String notes;
}
