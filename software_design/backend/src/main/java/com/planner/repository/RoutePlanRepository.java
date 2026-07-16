package com.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planner.model.RoutePlan;

@Repository
public interface RoutePlanRepository extends JpaRepository<RoutePlan, Long> {
    // Custom query methods can be added here
} //interface
