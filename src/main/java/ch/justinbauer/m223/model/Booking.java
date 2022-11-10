package ch.justinbauer.m223.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Entity
@NamedQueries({
  @NamedQuery(name = "Booking.findByMember", query = "SELECT u FROM Booking u WHERE u.member.id = :id")
})
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private Boolean withPrinter;

  @ManyToOne
  @JoinColumn(name="place_id", nullable=false)
  private Place place;

  @ManyToOne
  @JoinColumn(name="duration_id", nullable=false)
  private Duration duration;

  @ManyToOne
  @JoinColumn(name="status_id", nullable=false)
  private Status status;

  @ManyToOne
  @JoinColumn(name="member_id", nullable=false)
  private Member member;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  
}