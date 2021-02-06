package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Customer saveCustomer(Customer costumer, List<Long> petIds) {
        List<Pet> pets = petIds != null ?
                petRepository.findAllById(petIds) :
                new ArrayList<>();
        costumer.setPets(pets);
        return customerRepository.save(costumer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepository.getOne(petId);
        return pet.getCustomer();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> availability) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(availability);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<Employee> employees = employeeRepository
                .getAllByDaysAvailableContains(date.getDayOfWeek()).stream()
                .filter(e -> e.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees;
    }
}
