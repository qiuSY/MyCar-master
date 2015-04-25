package edu.neu.cs5200.myCar.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@SequenceGenerator( name = "mySeq", sequenceName = "MY_SEQ", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="mySeq")
	private Integer UserId;
	private String Username;
	private String Password;
	private String Email;
	private String firstName;
	private String lastName;
	private String Type;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<Like> likes;
	
	@OneToMany(mappedBy="follower",cascade=CascadeType.REMOVE)
	private List<Follower> followings;
	
	@OneToMany(mappedBy="follow",cascade=CascadeType.REMOVE)
	private List<Follower> followers;
	
	public List<Follower> getFollowings() {
		return followings;
	}
	public void setFollowings(List<Follower> followings) {
		this.followings = followings;
	}
	public List<Follower> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	public User(Integer userId, String username, String password, String email,
			String firstName, String lastName, String type,
			List<Comment> comments, List<Like> likes,
			List<Follower> followings, List<Follower> followers) {
		super();
		UserId = userId;
		Username = username;
		Password = password;
		Email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		Type = type;
		this.comments = comments;
		this.likes = likes;
		this.followings = followings;
		this.followers = followers;
	}
	public User() {
		super();
	}
	
}
