package ch.justinbauer.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
@Entity(name = "MEMBER")
@NamedQueries({
  @NamedQuery(name = "Member.findByEmail", query = "SELECT u FROM MEMBER u WHERE u.email = :email"),
  @NamedQuery(name = "Member.findById", query = "SELECT u FROM MEMBER u WHERE u.id = :id")
})
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;
  
  @NotBlank(message="Firstname may not be blank")
  @Column(nullable = false )
  private String firstname;

  @NotBlank(message="Lastname may not be blank")
  @Column(nullable = false )
  private String lastname;

  @NotBlank(message="Email may not be blank")
  @Column(nullable = false, unique = true)
  private String email;

  @NotBlank(message="Password may not be blank")
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private boolean isBlocked;

  @ManyToOne
  @JoinColumn(name="role_id", nullable=false)
  private Role role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void setBlocked(boolean isBlocked) {
    this.isBlocked = isBlocked;
  }

  

  
}
