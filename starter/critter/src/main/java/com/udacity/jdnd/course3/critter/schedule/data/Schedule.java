package com.udacity.jdnd.course3.critter.schedule.data;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeSkill;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;
}
