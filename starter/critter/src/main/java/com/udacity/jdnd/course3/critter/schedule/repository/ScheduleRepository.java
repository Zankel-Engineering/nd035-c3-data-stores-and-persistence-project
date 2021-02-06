package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.schedule.data.Schedule;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getAllByPetsContains(Pet pet);

    List<Schedule> getAllByEmployeesContains(Employee employee);

    List<Schedule> getAllByPetsIn(List<Pet> pets);
}
