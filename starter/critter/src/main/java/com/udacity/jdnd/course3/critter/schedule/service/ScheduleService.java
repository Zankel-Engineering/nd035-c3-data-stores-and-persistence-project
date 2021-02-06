package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesForEmployee(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> getAllSchedulesForPet(long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getAllSchedulesForCustomer(long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return scheduleRepository.getAllByPetsIn(customer.getPets());
    }

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
       List<Employee> employees = employeeRepository.findAllById(employeeIds);
       List<Pet> pets = petRepository.findAllById(petIds);
       schedule.setEmployees(employees);
       schedule.setPets(pets);
       return scheduleRepository.save(schedule);
    }

}
