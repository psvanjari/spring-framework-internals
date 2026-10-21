package com.example.springwebdemo.entity;

import com.example.springwebdemo.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    @Length(min = 2, max = 70, message = "Name must contain 2 to 70 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @Min(value = 18, message = "Employee must be at least 18")
    @Max(value = 70, message = "Age cannot exceed 70")
    @Range(min = 18, max = 70, message = "Age must be between 18 and 70")
    private int age;

    @DecimalMin(value = "0.01", inclusive = true, message = "Salary must be greater than zero")
    @DecimalMax(value = "10000000.00", message = "Salary exceeds the supported maximum")
    @Digits(integer = 8, fraction = 2, message = "Salary supports up to 8 integer and 2 fractional digits")
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    @PositiveOrZero(message = "Bonus cannot be negative")
    private BigDecimal bonus = BigDecimal.ZERO;

    @Negative(message = "Adjustment must be negative")
    private int negativeAdjustment = -1;

    @NegativeOrZero(message = "Balance adjustment cannot be positive")
    private int balanceAdjustment = 0;

    @AssertTrue(message = "Terms must be accepted")
    private boolean termsAccepted = true;

    @AssertFalse(message = "This demo employee must not be blocked")
    private boolean blocked = false;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

    @Future(message = "Contract end date must be in the future")
    private LocalDate contractEndDate = LocalDate.now().plusYears(1);

    @FutureOrPresent(message = "Review date cannot be in the past")
    private LocalDate nextReviewDate = LocalDate.now();

    @Pattern(regexp = "^[A-Z]{2}-[0-9]{4}$", message = "Employee code format must be AB-1234")
    private String employeeCode = "EM-1001";

    @Size(min = 3, max = 30, message = "Department label must contain 3 to 30 characters")
    private String departmentLabel = "Engineering";

    @NotNull(message = "External reference must be supplied")
    private String externalReference = "EMP-EXT-1";

    @Null(message = "This field must be null when supplied by a client")
    @Transient
    private String serverOnlyNote;

    @CreditCardNumber(message = "Card number is invalid (demo validation only)")
    @Transient
    private String testCardNumber;

    @URL(message = "Portfolio URL must be valid")
    private String portfolioUrl = "https://example.com";

    @ValidPassword
    @Transient
    private String password;

    public Employee() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public BigDecimal getBonus() { return bonus; }
    public void setBonus(BigDecimal bonus) { this.bonus = bonus; }
    public int getNegativeAdjustment() { return negativeAdjustment; }
    public void setNegativeAdjustment(int negativeAdjustment) { this.negativeAdjustment = negativeAdjustment; }
    public int getBalanceAdjustment() { return balanceAdjustment; }
    public void setBalanceAdjustment(int balanceAdjustment) { this.balanceAdjustment = balanceAdjustment; }
    public boolean isTermsAccepted() { return termsAccepted; }
    public void setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; }
    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public LocalDate getContractEndDate() { return contractEndDate; }
    public void setContractEndDate(LocalDate contractEndDate) { this.contractEndDate = contractEndDate; }
    public LocalDate getNextReviewDate() { return nextReviewDate; }
    public void setNextReviewDate(LocalDate nextReviewDate) { this.nextReviewDate = nextReviewDate; }
    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }
    public String getDepartmentLabel() { return departmentLabel; }
    public void setDepartmentLabel(String departmentLabel) { this.departmentLabel = departmentLabel; }
    public String getExternalReference() { return externalReference; }
    public void setExternalReference(String externalReference) { this.externalReference = externalReference; }
    public String getServerOnlyNote() { return serverOnlyNote; }
    public void setServerOnlyNote(String serverOnlyNote) { this.serverOnlyNote = serverOnlyNote; }
    public String getTestCardNumber() { return testCardNumber; }
    public void setTestCardNumber(String testCardNumber) { this.testCardNumber = testCardNumber; }
    public String getPortfolioUrl() { return portfolioUrl; }
    public void setPortfolioUrl(String portfolioUrl) { this.portfolioUrl = portfolioUrl; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
