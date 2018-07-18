package com.example.todoapp.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
@ApiModel(value = "Task", description = "It represent a task to do")
public class Todo {
	@Id
	@ApiModelProperty(position = 1, value = "The id of task.")
	private String id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	@ApiModelProperty(position = 2, value = "The title of task.")
	private String title;

	@ApiModelProperty(position = 3, value = "Indicates if the task is completed.")
	private Boolean completed = false;

	@ApiModelProperty(position = 4, value = "The date of creation of task.")
	private Date createdAt = new Date();

	public Todo() {
		super();
	}

	public Todo(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return String.format("Todo[id=%s, title='%s', completed='%s']", id, title, completed);
	}
}