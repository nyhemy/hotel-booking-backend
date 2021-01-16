package io.training.catalyte.finalproject.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.training.catalyte.finalproject.entities.Patient;
import io.training.catalyte.finalproject.repositories.PatientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PatientControllerTest {

  @Autowired
  PatientRepository patientRepository;

  @Autowired
  private static MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  ObjectMapper mapper = new ObjectMapper();

  private static final String CONTEXT = "/patients";

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getAllPatients() throws Exception{
    String retType =
        mockMvc
            .perform(get(CONTEXT))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentType();

    Assert.assertEquals("application/json;charset=UTF-8", retType);
  }

  @Test
  public void getPatientById() throws Exception{
    String retType = mockMvc
        .perform(get(CONTEXT + "/1"))
        .andExpect(jsonPath("$.email").value("jd@gmail.com"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentType();

    Assert.assertEquals("application/json;charset=UTF-8", retType);
  }

  @Test
  public void getAllEncountersByPatientId() throws Exception{
    String retType = mockMvc
        .perform(get(CONTEXT + "/1/encounters"))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentType();

    Assert.assertEquals("application/json;charset=UTF-8", retType);
  }

  @Test
  public void createPatient() throws Exception{

    Patient patientFour = new Patient();

    patientFour.setId(1L);
    patientFour.setFirstName("Jane");
    patientFour.setLastName("Soe");
    patientFour.setSSN("000-00-0000");
    patientFour.setEmail("js@gmail.com");
    patientFour.setStreet("Drew Dr");
    patientFour.setCity("Turboville");
    patientFour.setState("MA");
    patientFour.setPostal("01545");
    patientFour.setAge(23);
    patientFour.setHeight(64);
    patientFour.setWeight(112);
    patientFour.setInsurance("Turbosure");
    patientFour.setGender("female");

    String patientAsJson = mapper.writeValueAsString(patientFour);

    String retType = mockMvc
        .perform(post(CONTEXT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(patientAsJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.*", hasSize(4)))
        .andReturn()
        .getResponse()
        .getContentType();

    Assert.assertEquals("application/json;charset=UTF-8", retType);
  }

  @Test
  public void createEncounterForPatient() {
  }

  @Test
  public void updatePatient() throws Exception{
    Patient patientFour = new Patient();

    patientFour.setId(1L);
    patientFour.setFirstName("Sane");
    patientFour.setLastName("Soe");
    patientFour.setSSN("000-00-0000");
    patientFour.setEmail("js@gmail.com");
    patientFour.setStreet("Drew Dr");
    patientFour.setCity("Turboville");
    patientFour.setState("MA");
    patientFour.setPostal("01545");
    patientFour.setAge(23);
    patientFour.setHeight(64);
    patientFour.setWeight(112);
    patientFour.setInsurance("Turbosure");
    patientFour.setGender("female");

    String patientAsJson = mapper.writeValueAsString(patientFour);

    String retType =
        mockMvc
            .perform(put(CONTEXT + "/1", patientAsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientAsJson))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sane"))
            .andReturn()
            .getResponse()
            .getContentType();

    Assert.assertEquals("application/json;charset=UTF-8", retType);
  }

  @Test
  public void updateEncounterForPatient() {
  }

  @Test
  public void deletePatient() throws Exception{
    mockMvc
        .perform(delete(CONTEXT + "/3")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}