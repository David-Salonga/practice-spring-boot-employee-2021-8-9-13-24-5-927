package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

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
        final Employee savedEmployeeFromDB = employeeRepository.save(employee);
        int id = savedEmployeeFromDB.getId();
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
        final Employee employee2 = new Employee(2, "Princess", 22, "female", 2009);
        final Employee employee3 = new Employee(3, "Prince", 21, "male", 2009);
        employeeRepository.saveAll(Lists.list(employee, employee2, employee3));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("page", "1").param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Francis"))
                .andExpect(jsonPath("$[1].name").value("Princess"))
                .andExpect(jsonPath("$[2].name").value("Prince"));
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

    @Test
    void should_return_employees_when_find_employee_by_gender_given_employee_gender() throws Exception {
        //given
        final Employee firstEmployee = new Employee(1, "Francis", 24, "male", 1999);
        final Employee secondEmployee = new Employee(2, "Adrianne", 21, "female", 5000);
        employeeRepository.save(firstEmployee);
        employeeRepository.save(secondEmployee); //todo
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Adrianne"));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Francis"));
    }

    @Test
    void should_update_employee_when_put_given_employee_details() throws Exception {
        //Given
        final Employee employee = new Employee(1, "Francis", 20, "male", 2015);
        final Employee getEmployeeFromDB = employeeRepository.save(employee);
        int id = getEmployeeFromDB.getId();
        String employeeToBeUpdated = "{\n" +
                "    \"age\": 24,\n" +
                "    \"salary\": 2000\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeToBeUpdated))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Francis"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.salary").value(2000));
    }

    @Test
    void should_delete_employee_when_delete_given_employee_id() throws Exception {
        //given
        final Employee employee = new Employee(1, "Francis", 24, "male", 2103);
        final Employee saveEmployeeToDB = employeeRepository.save(employee);
        int id = saveEmployeeToDB.getId();
        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", id))
                .andExpect(status().isOk());
    }
}
