package io.training.catalyte.finalproject.data;

import io.training.catalyte.finalproject.domains.encounters.Encounter;
import io.training.catalyte.finalproject.domains.encounters.EncounterRepository;
import io.training.catalyte.finalproject.domains.patients.Patient;
import io.training.catalyte.finalproject.domains.patients.PatientRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private EncounterRepository encounterRepository;

  @Override
  public void run(String... args) {
    loadPatients();
    loadEncounters();
  }

  private void loadPatients() {
    patientRepository
        .save(new Patient(
            "Jane",
            "Doe",
            "000-00-0000",
            "jd@gmail.com",
            "Drew Dr",
            "Turboville",
            "MA",
            "01545",
            23,
            64,
            112,
            "Turbosure",
            "female"
        ));
    patientRepository
        .save(new Patient(
            "Jason",
            "Bourne",
            "000-00-0000",
            "null@gmail.com",
            "REDACTED",
            "REDACTED",
            "AK",
            "01545",
            40,
            73,
            165,
            "REDACTED",
            "male"
        ));
    patientRepository
        .save(new Patient(
            "Zero",
            "Encounters",
            "000-00-0000",
            "zero@gmail.com",
            "Place St",
            "Ether",
            "FL",
            "01545",
            25,
            67,
            180,
            "Weewoo Gotchu Inc",
            "male"
        ));
  }

  private void loadEncounters() {

    SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    String dateOne = "2019-05-21";
    String dateTwo = "2020-04-19";
    String dateThree = "2017-10-15";

    encounterRepository
        .save(new Encounter(
            1L,
            "health notes",
            "A1S 2D3",
            "ProviderCo",
            "123.456.789-12",
            "A12",
            BigDecimal.valueOf(129.99),
            BigDecimal.valueOf(20),
            "not enought cheetos",
            60,
            112,
            70,
            Date.valueOf(dateOne)
        ));
    encounterRepository
        .save(new Encounter(
            1L,
            "health notes",
            "A1S 2D4",
            "ProviderCo",
            "123.456.789-13",
            "A13",
            BigDecimal.valueOf(249.99),
            BigDecimal.valueOf(20),
            "still not enought cheetos",
            70,
            120,
            69,
            Date.valueOf(dateTwo)
        ));
    encounterRepository
        .save(new Encounter(
            2L,
            "REDACTED",
            "A1S 3D1",
            "REDACTED",
            "123.456.790-01",
            "B11",
            BigDecimal.valueOf(99.99),
            BigDecimal.valueOf(0),
            "REDACTED",
            40,
            110,
            60,
            Date.valueOf(dateThree)
        ));
  }
}
