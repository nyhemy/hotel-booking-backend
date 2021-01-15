package io.training.catalyte.finalproject.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.training.catalyte.finalproject.entities.Encounter;
import io.training.catalyte.finalproject.exceptions.ResourceNotFound;
import io.training.catalyte.finalproject.exceptions.ServiceUnavailable;
import io.training.catalyte.finalproject.repositories.EncounterRepository;
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

public class EncounterServiceImplTest {

  @Mock
  EncounterRepository mockEncounterRepository;

  @InjectMocks
  EncounterServiceImpl mockEncounterServiceImpl;

  List<Encounter> encounterList = new ArrayList<>();

  Encounter encounterOne = new Encounter();

  SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
  String dateOne = "2019-05-21";

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

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

    when(mockEncounterRepository.findAll()).thenReturn(encounterList);
    when(mockEncounterRepository.findAll(any(Example.class))).thenReturn(encounterList);
    when(mockEncounterRepository.findById(any(Long.class))).thenReturn(Optional.of(encounterOne));
  }

  @Test
  public void testGetAll() throws Exception{
    List<Encounter> actualResult = mockEncounterServiceImpl.getAll(new Encounter());

    Assert.assertEquals(encounterList, actualResult);
  }

  @Test
  public void testQueryEncountersNonNullExample() throws Exception {
    List<Encounter> actualResult = mockEncounterServiceImpl.getAll(encounterOne);

    Assert.assertEquals(encounterList, actualResult);

  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetAllDBError() throws Exception {

    when(mockEncounterRepository.findAll()).thenThrow(CannotCreateTransactionException.class);

    List<Encounter> actualResult = mockEncounterServiceImpl.getAll(new Encounter());

  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetAllUnexpectedError() throws Exception {

    when(mockEncounterRepository.findAll()).thenThrow(UnexpectedTypeException.class);

    List<Encounter> actualResult = mockEncounterServiceImpl.getAll(new Encounter());

  }

  @Test
  public void testGetById() throws Exception{
    Encounter actualResult = mockEncounterServiceImpl.getById(1L);

    Assert.assertEquals(encounterOne, actualResult);
  }

  @Test(expected = ResourceNotFound.class)
  public void testGetByIdIdNotFound() throws Exception {

    when(mockEncounterRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    Encounter actualResult = mockEncounterServiceImpl.getById(1L);
    Assert.assertNull(actualResult);

  }

  @Test(expected = ServiceUnavailable.class)
  public void testGetByIdDBError() throws Exception {

    when(mockEncounterRepository.findById(any(Long.class)))
        .thenThrow(CannotCreateTransactionException.class);

    Encounter actualResult = mockEncounterServiceImpl.getById(1L);

  }
}