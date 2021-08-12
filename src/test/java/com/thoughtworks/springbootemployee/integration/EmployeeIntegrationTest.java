package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee(1, "Spongebob", 14, "male", 99);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Spongebob"))
                .andExpect(jsonPath("$[0].age").value("14"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value("99"));
    }

    @Test
    void should_return_employee_when_get_given_employee_id() throws Exception {
        //given
        final Employee employee = new Employee(1, "Francis", 24, "male", 2027);
        final Employee savedEmployee = employeeRepository.save(employee);
        int id = savedEmployee.getId();
        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Francis"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(2027));
    }

    @Test
    void should_return_employee_list_with_pagination_when_getByPageIndexAndPageSize_given_page_and_page_size() throws Exception {
        //given
        final Employee employee = new Employee(1, "Francis", 24, "male", 2021);
        final Employee employee2 = new Employee(2, "Eric", 22, "female", 2009   );
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("page", "1").param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Francis"));
    }

    @Test
    void should_create_employee_when_call_create_employee_api() throws Exception {
        //Given
        String employee = "{\n" +
                "    \"name\": \"Francis\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 2017\n" +
                "}";

        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Francis"));
    }


}
