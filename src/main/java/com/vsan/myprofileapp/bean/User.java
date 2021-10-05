package com.vsan.myprofileapp.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private boolean isEnabled;
	private String role;
	@Column(name = "user_link", nullable = true)
	private String userLink;
//	private String profileImage; TODO: how to store images in database?
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private List<Post> userPosts;
	@OneToMany(mappedBy = "userSender") 
	private List<Friendship> requestedFriends;
	@OneToMany(mappedBy = "userReceiver") 
	private List<Friendship> receivedFriends;
	private String registrationDate;
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<Notification> notifications;
	
	
	
	public User(String name, String lastname, String email, String password) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.isEnabled = false;
		this.role = "USER";
		this.userPosts = new ArrayList<Post>();
		this.requestedFriends = new ArrayList<Friendship>();
		this.receivedFriends = new ArrayList<Friendship>();
//		this.notifications = new ArrayList<Notification>();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
	     return Arrays.asList(authority);
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

//	public void setNotifications(Notification n) {
//		this.notifications.add(n);
//	}
	
	
	
	
	
	
	
}
