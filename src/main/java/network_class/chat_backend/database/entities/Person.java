package network_class.chat_backend.database.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {

  private @Id @GeneratedValue Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String dateOfBirth;
  private String curp;

  Person() {}

  public Person(
    String firstName, 
    String lastName, 
    String email, 
    String password, 
    String dateOfBirth, 
    String curp) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.curp = curp;
  }

  /* Getters */
  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public Long getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.email;
  }

  public String getdateOfBirth() {
    return this.email;
  }

  public String getCurp() {
    return this.email;
  }

  /* Setters */
  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setCurp(String curp) {
    this.curp = curp;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Person))
      return false;
    Person person = (Person) o;
    return Objects.equals(this.id, person.id) && Objects.equals(this.firstName, person.firstName) && Objects.equals(this.lastName, person.lastName) && Objects.equals(this.email, person.email) && Objects.equals(this.password, person.password) && Objects.equals(this.dateOfBirth, person.dateOfBirth) && Objects.equals(this.curp, person.curp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.password, this.dateOfBirth, this.curp);
  }

  @Override
  public String toString() {
      return "Person{" +
          "id=" + this.id +
          ", firstName='" + this.firstName + '\'' +
          ", lastName='" + this.lastName + '\'' +
          ", email='" + this.email + '\'' +
          ", password='" + this.password + '\'' +
          ", dateOfBirth=" + this.dateOfBirth +
          ", curp='" + this.curp + '\'' +
          '}';
  }
}