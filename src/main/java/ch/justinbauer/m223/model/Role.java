package ch.justinbauer.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity(name = "ROLE")
@NamedQueries({
  @NamedQuery(name = "Role.findByTitle", query = "SELECT u FROM ROLE u WHERE u.title = :title")
})
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false, unique = true)
  private String title;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
