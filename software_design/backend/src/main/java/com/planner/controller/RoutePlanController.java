package com.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.model.RoutePlan;
import com.planner.service.RoutePlanService;

@RestController
@RequestMapping("/routeplans")
public class RoutePlanController {

    @Autowired
    private RoutePlanService routePlanService;

    // Endpoint to save a route plan
    @PostMapping
    public RoutePlan saveRoutePlan(@RequestBody RoutePlan routePlan) {
        return routePlanService.saveRoutePlan(routePlan);
    } // saveRoutePlan

    // Endpoint to get all route plans
    @GetMapping
    public List<RoutePlan> getAllRoutePlans() {
        return routePlanService.getAllRoutePlans();
    } // getAllRoutePlans
} // class 
