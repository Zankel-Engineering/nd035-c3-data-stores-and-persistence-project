package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.data.Employee;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);
}
