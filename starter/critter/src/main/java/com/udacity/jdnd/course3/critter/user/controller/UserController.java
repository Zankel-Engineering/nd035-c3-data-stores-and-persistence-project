package com.udacity.jdnd.course3.critter.user.controller;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.schedule.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customer.getNotes());
        return getCustomerDTO(userService.saveCustomer(customer, customerDTO.getPetIds()));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return userService.getAllCustomers().stream()
                .map(this::getCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = userService.getOwnerByPet(petId);
        return getCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        return getEmployeeDTO(userService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
       Employee employee = userService.getEmployee(employeeId);
       return getEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setAvailability(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return userService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate())
             .stream().map(this::getEmployeeDTO).collect(Collectors.toList());
    }

    private CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    private EmployeeDTO getEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setSkills(employee.getSkills());
        return employeeDTO;
    }
}
