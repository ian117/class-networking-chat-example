package network_class.chat_backend.database.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Room {

  private @Id @GeneratedValue Long id;
  private String name;
  private String description;

  // Constructor vacío para JPA
  Room() {}

  // Constructor con campos
  public Room(
    String name, 
    String description) {

    this.name = name;
    this.description = description;
  }

  // Getters
  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // equals()
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Room)) return false;
    Room room = (Room) o;
    return Objects.equals(this.id, room.id) &&
           Objects.equals(this.name, room.name) &&
           Objects.equals(this.description, room.description);
  }

  // hashCode()
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.description);
  }

  // toString()
  @Override
  public String toString() {
    return "Room{" +
           "id=" + this.id +
           ", name='" + this.name + '\'' +
           ", description='" + this.description + '\'' +
           '}';
  }
}
