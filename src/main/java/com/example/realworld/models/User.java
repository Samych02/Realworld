package com.example.realworld.models;

import com.auth0.jwt.JWT;
import com.example.realworld.customValidator.FieldType;
import com.example.realworld.customValidator.Password;
import com.example.realworld.customValidator.Unique;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Unique(fieldType = FieldType.Email)
  @Email(message = "Incorrect email format.")
  @NotBlank(message = "Email address is required.")
  @Column(unique = true)
  private String email;

  @NotBlank(message = "Password is required.")
  @Password(min = 6, max = 25)
  private String password;

  @Transient
  private JWT token;

  @Unique(fieldType = FieldType.Username)
  @Size(min = 3, max = 20, message = "Username length must be between {min} and {max}.")
  @NotBlank(message = "Username is required.")
  @Column(unique = true)
  private String username;

  @Size(max = 255)
  @Null
  private String bio;

  @Size(max = 255)
  @Null
  private String image;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
