package io.training.catalyte.finalproject.domains.encounters;

import static io.training.catalyte.finalproject.constants.StringConstants.REQUIRED_FIELD;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Encounter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private Long patientId;

  private String notes;

  @NotBlank
  private String visitCode;

  @NotBlank
  private String provider;

  @NotBlank
  private String billingCode;

  @NotBlank
  private String icd10;

  @NotNull
  private BigDecimal totalCost;

  @NotNull
  private BigDecimal copay;

  private String chiefComplaint;

  @NotNull
  private Integer pulse;

  private Integer systolic;

  private Integer diastolic;

  @NotNull(message = "date" + REQUIRED_FIELD)
  @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;

  public Encounter() {
  }

  public Encounter(@NotNull Long patientId, String notes,
      @NotBlank String visitCode, @NotBlank String provider,
      @NotBlank String billingCode, @NotBlank String icd10,
      @NotNull BigDecimal totalCost, @NotNull BigDecimal copay, String chiefComplaint,
      @NotNull Integer pulse, Integer systolic, Integer diastolic,
      @NotNull Date date) {
    this.patientId = patientId;
    this.notes = notes;
    this.visitCode = visitCode;
    this.provider = provider;
    this.billingCode = billingCode;
    this.icd10 = icd10;
    this.totalCost = totalCost;
    this.copay = copay;
    this.chiefComplaint = chiefComplaint;
    this.pulse = pulse;
    this.systolic = systolic;
    this.diastolic = diastolic;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getVisitCode() {
    return visitCode;
  }

  public void setVisitCode(String visitCode) {
    this.visitCode = visitCode;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getBillingCode() {
    return billingCode;
  }

  public void setBillingCode(String billingCode) {
    this.billingCode = billingCode;
  }

  public String getIcd10() {
    return icd10;
  }

  public void setIcd10(String icd10) {
    this.icd10 = icd10;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }

  public BigDecimal getCopay() {
    return copay;
  }

  public void setCopay(BigDecimal copay) {
    this.copay = copay;
  }

  public String getChiefComplaint() {
    return chiefComplaint;
  }

  public void setChiefComplaint(String chiefComplaint) {
    this.chiefComplaint = chiefComplaint;
  }

  public Integer getPulse() {
    return pulse;
  }

  public void setPulse(Integer pulse) {
    this.pulse = pulse;
  }

  public Integer getSystolic() {
    return systolic;
  }

  public void setSystolic(Integer systolic) {
    this.systolic = systolic;
  }

  public Integer getDiastolic() {
    return diastolic;
  }

  public void setDiastolic(Integer diastolic) {
    this.diastolic = diastolic;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Encounter encounter = (Encounter) o;
    return Objects.equals(getId(), encounter.getId()) &&
        Objects.equals(getPatientId(), encounter.getPatientId()) &&
        Objects.equals(getNotes(), encounter.getNotes()) &&
        Objects.equals(getVisitCode(), encounter.getVisitCode()) &&
        Objects.equals(getProvider(), encounter.getProvider()) &&
        Objects.equals(getBillingCode(), encounter.getBillingCode()) &&
        Objects.equals(getIcd10(), encounter.getIcd10()) &&
        Objects.equals(getTotalCost(), encounter.getTotalCost()) &&
        Objects.equals(getCopay(), encounter.getCopay()) &&
        Objects.equals(getChiefComplaint(), encounter.getChiefComplaint()) &&
        Objects.equals(getPulse(), encounter.getPulse()) &&
        Objects.equals(getSystolic(), encounter.getSystolic()) &&
        Objects.equals(getDiastolic(), encounter.getDiastolic()) &&
        Objects.equals(getDate(), encounter.getDate());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getId(), getPatientId(), getNotes(), getVisitCode(), getProvider(), getBillingCode(),
            getIcd10(), getTotalCost(), getCopay(), getChiefComplaint(), getPulse(), getSystolic(),
            getDiastolic(), getDate());
  }

  @Override
  public String toString() {
    return "Encounter{" +
        "id=" + id +
        ", patientId=" + patientId +
        ", notes='" + notes + '\'' +
        ", visitCode='" + visitCode + '\'' +
        ", provider='" + provider + '\'' +
        ", billingCode='" + billingCode + '\'' +
        ", icd10='" + icd10 + '\'' +
        ", totalCost=" + totalCost +
        ", copay=" + copay +
        ", chiefComplaint='" + chiefComplaint + '\'' +
        ", pulse=" + pulse +
        ", systolic=" + systolic +
        ", diastolic=" + diastolic +
        ", date=" + date +
        '}';
  }
}
