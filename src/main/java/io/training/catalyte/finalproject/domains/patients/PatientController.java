package io.training.catalyte.finalproject.domains.patients;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

  private final Logger logger = LoggerFactory.getLogger(PatientController.class);

  @Autowired
  private PatientService patientService;

  /**
   * This method retrieves all patients from the database
   *
   * @return a collection of patients and 200 status code
   */
  @GetMapping
  @ApiOperation("Retrieve all patients")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Patient.class)
  })
  public ResponseEntity<List<Patient>> getAllPatients() {
    logger.info("Get all request received");
    return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
  }
}
