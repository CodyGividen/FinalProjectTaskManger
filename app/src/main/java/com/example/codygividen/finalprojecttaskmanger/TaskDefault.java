package com.example.codygividen.finalprojecttaskmanger;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class TaskDefault {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String detailsOfTask;
    private String taskCreatedDate;
    private String taskDueDate;
    private Date taskCompletedDate;
    private boolean isCompleted;

    public TaskDefault(String title, String detailsOfTask,String taskDueDate, String taskCreatedDate) {
        this.title = title;
        this.detailsOfTask = detailsOfTask;
        this.taskDueDate = taskDueDate;
        this.taskCreatedDate = taskCreatedDate;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetailsOfTask(String detailsOfTask) {
        this.detailsOfTask = detailsOfTask;
    }

    public void setTaskCreatedDate(String taskCreatedDate) {
        this.taskCreatedDate = taskCreatedDate;
    }

    public void setTaskCompletedDate(Date taskCompletedDate) {
        this.taskCompletedDate = taskCompletedDate;
    }


    public String getTitle() {
        return title;
    }

    public String getDetailsOfTask() {
        return detailsOfTask;
    }

    public String getTaskCreatedDate() {
        return taskCreatedDate;
    }

    public Date getTaskCompletedDate() {
        return taskCompletedDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
