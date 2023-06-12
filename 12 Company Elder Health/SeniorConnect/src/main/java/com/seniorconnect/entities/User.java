package com.seniorconnect.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.seniorconnect.entities.baseentity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

	@NotBlank
    @Size(max = 40)
    private String firstName;
	
	@NotBlank
    @Size(max = 40)
	private String lastName;
	
	@NotBlank
    @Size(max = 15)
    private String userName;

    @NaturalId 
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    private boolean isPrivate;

    private String url;

    private String bio;

    private String sex;

    private String phone;

    @NotBlank
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",//FOR NAME OF THIRD TABLE
            joinColumns = @JoinColumn(name = "user_id"), //NAME OF 1ST COLUMN OF FIRST TABLE
            inverseJoinColumns = @JoinColumn(name = "role_id")) //NAME OF 2ND COLUMN OF FIRST TABLE
    private Set<Role> roles = new HashSet<>();
}
