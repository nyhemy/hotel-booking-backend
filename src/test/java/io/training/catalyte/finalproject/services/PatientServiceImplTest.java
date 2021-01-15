package io.training.catalyte.finalproject.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.training.catalyte.finalproject.entities.Encounter;
import io.training.catalyte.finalproject.entities.Patient;
import io.training.catalyte.finalproject.exceptions.ResourceNotFound;
import io.training.catalyte.finalproject.exceptions.ServiceUnavailable;
import io.training.catalyte.finalproject.repositories.EncounterRepository;
import io.training.catalyte.finalproject.repositories.PatientRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.UnexpectedTypeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.transaction.CannotCreateTransactionException;

public class PatientServiceImplTest {

  @Mock
  PatientRepository mockPatientRepository;

  @Mock
  EncounterRepository mockEncounterRepository;

  @Mock
  EncounterService mockEncounterService;

  @InjectMocks
  PatientServiceImpl mockPatientServiceImpl;

  List<Patient> patientList = new ArrayList<>();
  List<Encounter> encounterList = new ArrayList<>();

  Encounter encounterOne = new Encounter();
  Patient patientOne = new Patient();

  SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
  String dateOne = "2019-05-21";

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    patientOne.setId(1L);
    patientOne.setFirstName("Jane");
    patientOne.setLastName("Doe");
    patientOne.setSSN("000-00-0000");
    patientOne.setEmail("jd@gmail.com");
    patientOne.setStreet("Drew Dr");
    patientOne.setCity("Turboville");
    patientOne.setState("MA");
    patientOne.setPostal("01545");
    patientOne.setAge(23);
    patientOne.setHeight(64);
    patientOne.setWeight(112);
    patientOne.setInsurance("Turbosure");
    patientOne.setGender("female");

    patientList.add(patientOne);

    encounterOne.setId(1L);
    encounterOne.setPatientId(1L);
    encounterOne.setNotes("health notes");
    encounterOne.setVisitCode("A1S 2D3");
    encounterOne.setProvider("ProviderCo");
    encounterOne.setBillingCode("123.456.789-12");
    encounterOne.setIcd10("A12");
    encounterOne.setTotalCost(BigDecimal.valueOf(129.99));
    encounterOne.setCopay(BigDecimal.valueOf(20));
    encounterOne.setChiefComplaint("not enought cheetos");
    encounterOne.setPulse(60);
    encounterOne.setSystolic(112);
    encounterOne.setDiastolic(70);
    encounterOne.setDate(Date.valueOf(dateOne));

    encounterList.add(encounterOne);

    when(mockPatientRepository.findAll()).thenReturn(patientList);
    when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patientOne));

    when(mockEncounterRepository.findAll()).thenReturn(encounterList);
    when(mockEncounterRepository.findAll(any(Example.class))).thenReturn(encounterList);
    when(mockEncounterRepository.findById(any(Long.class))).thenReturn(Optional.of(encounterOne));
  }

  @Test
  public void testGetAll() throws Exception {
    List<Patient> actualResult = mockPatientServiceImpl.getAll();

    Assert.assertEquals(patientList, actualResult);
  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetAllDBError() throws Exception {

    when(mockPatientRepository.findAll()).thenThrow(CannotCreateTransactionException.class);

    List<Patient> actualResult = mockPatientServiceImpl.getAll();

  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetAllUnexpectedError() throws Exception {

    when(mockPatientRepository.findAll()).thenThrow(UnexpectedTypeException.class);

    List<Patient> actualResult = mockPatientServiceImpl.getAll();

  }

  @Test
  public void testGetById() throws Exception {
    Patient actualResult = mockPatientServiceImpl.getById(1L);

    Assert.assertEquals(patientOne, actualResult);
  }

  @Test(expected = ResourceNotFound.class)
  public void testGetByIdIdNotFound() throws Exception {

    when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    Patient actualResult = mockPatientServiceImpl.getById(1L);
    Assert.assertNull(actualResult);

  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetByIdDBError() throws Exception {

    when(mockPatientRepository.findById(any(Long.class)))
        .thenThrow(CannotCreateTransactionException.class);

    Patient actualResult = mockPatientServiceImpl.getById(1L);

  }

  @Test
  public void testFindEncountersByPatientId() throws Exception {
//    List<Encounter> actualResult = mockPatientServiceImpl.findEncountersByPatientId(1L);
//
//    Assert.assertEquals(encounterList, actualResult);
  }

  @Test
  public void testCreatePatient() throws Exception {
  }

  @Test
  public void testCreateEncounterForPatientById() throws Exception {
  }

  @Test
  public void testUpdatePatient() throws Exception {
  }

  @Test
  public void testUpdateEncounterByPatientId() throws Exception {
  }

  @Test
  public void testDeletePatient() throws Exception {
  }
}