package com.schoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue
	@Column(name = "postId")
	private int postId;
	@Column(name = "postname")
	private String postName;
	@Column(name = "postText")
	private String postText;
	@Column(name = "postCreated")
	private String postCreated;
	@Column(name = "post_summary")
	private String postSummary;

	@ManyToOne
	@JoinColumn
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post() {
	}

	public String getPostSummary() {
		return postSummary;
	}

	public void setPostSummary(String postSummary) {
		this.postSummary = postSummary;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public String getPostCreated() {
		return postCreated;
	}

	public void setPostCreated(String postCreated) {
		this.postCreated = postCreated;
	}
}