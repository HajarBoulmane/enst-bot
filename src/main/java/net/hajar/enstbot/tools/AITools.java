package net.hajar.enstbot.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AITools {

    record Employee(String name, double salary, int seniority) {}

    @Tool(name = "getEmployee", description = "Get information about a given employee")
    public Employee getEmployee(
            @ToolParam(description = "Name of the employee") String name) {
        return new Employee(name, 12300, 4);
    }

    @Tool(name = "getAllEmployees", description = "Get all employees")
    public List<Employee> getAllEmployees() {
        return List.of(
                new Employee("Hassan", 12300, 4),
                new Employee("Mohammed", 15000, 6),
                new Employee("Ahmed", 9800, 2)
        );
    }
}