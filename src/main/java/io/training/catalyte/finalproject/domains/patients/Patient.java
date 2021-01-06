package io.training.catalyte.finalproject.domains.patients;

import static io.training.catalyte.finalproject.constants.StringConstants.AGE_POSITIVE_VALIDATION_ERROR;
import static io.training.catalyte.finalproject.constants.StringConstants.GUEST_EMAIL_VALIDATION_ERROR;
import static io.training.catalyte.finalproject.constants.StringConstants.HEIGHT_POSITIVE_VALIDATION_ERROR;
import static io.training.catalyte.finalproject.constants.StringConstants.WEIGHT_POSITIVE_VALIDATION_ERROR;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String SSN;

  @NotBlank
  @Email(message = GUEST_EMAIL_VALIDATION_ERROR)
  private String email;

  @NotBlank
  private String street;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  @NotBlank
  private String postal;

  @NotNull
  @Positive(message = AGE_POSITIVE_VALIDATION_ERROR)
  private Integer age;

  @NotNull
  @Positive(message = HEIGHT_POSITIVE_VALIDATION_ERROR)
  private Double height;

  @NotNull
  @Positive(message = WEIGHT_POSITIVE_VALIDATION_ERROR)
  private Double weight;

  @NotBlank
  private String insurance;

  @NotBlank
  private String gender;

  public Patient() {
  }

  public Patient(Long id, @NotBlank String firstName,
      @NotBlank String lastName, @NotBlank String SSN,
      @NotBlank @Email(message = GUEST_EMAIL_VALIDATION_ERROR) String email,
      @NotBlank String street, @NotBlank String city,
      @NotBlank String state, @NotBlank String postal,
      @NotNull @Positive(message = AGE_POSITIVE_VALIDATION_ERROR) Integer age,
      @NotNull @Positive(message = HEIGHT_POSITIVE_VALIDATION_ERROR) Double height,
      @NotNull @Positive(message = WEIGHT_POSITIVE_VALIDATION_ERROR) Double weight,
      @NotBlank String insurance, @NotBlank String gender) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.SSN = SSN;
    this.email = email;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postal = postal;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.insurance = insurance;
    this.gender = gender;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSSN() {
    return SSN;
  }

  public void setSSN(String SSN) {
    this.SSN = SSN;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostal() {
    return postal;
  }

  public void setPostal(String postal) {
    this.postal = postal;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public String getInsurance() {
    return insurance;
  }

  public void setInsurance(String insurance) {
    this.insurance = insurance;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patient patient = (Patient) o;
    return Objects.equals(getId(), patient.getId()) &&
        Objects.equals(getFirstName(), patient.getFirstName()) &&
        Objects.equals(getLastName(), patient.getLastName()) &&
        Objects.equals(getSSN(), patient.getSSN()) &&
        Objects.equals(getEmail(), patient.getEmail()) &&
        Objects.equals(getStreet(), patient.getStreet()) &&
        Objects.equals(getCity(), patient.getCity()) &&
        Objects.equals(getState(), patient.getState()) &&
        Objects.equals(getPostal(), patient.getPostal()) &&
        Objects.equals(getAge(), patient.getAge()) &&
        Objects.equals(getHeight(), patient.getHeight()) &&
        Objects.equals(getWeight(), patient.getWeight()) &&
        Objects.equals(getInsurance(), patient.getInsurance()) &&
        Objects.equals(getGender(), patient.getGender());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getId(), getFirstName(), getLastName(), getSSN(), getEmail(), getStreet(), getCity(),
            getState(), getPostal(), getAge(), getHeight(), getWeight(), getInsurance(),
            getGender());
  }

  @Override
  public String toString() {
    return "Patient{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", SSN='" + SSN + '\'' +
        ", email='" + email + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postal='" + postal + '\'' +
        ", age=" + age +
        ", height=" + height +
        ", weight=" + weight +
        ", insurance='" + insurance + '\'' +
        ", gender='" + gender + '\'' +
        '}';
  }
}

