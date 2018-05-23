package com.example.codygividen.finalprojecttaskmanger;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity
public class TaskDefault implements Parcelable{
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

    protected TaskDefault(Parcel in) {
        id = in.readInt();
        title = in.readString();
        detailsOfTask = in.readString();
        taskCreatedDate = in.readString();
        taskDueDate = in.readString();
        isCompleted = in.readByte() != 0;
    }

    public static final Creator<TaskDefault> CREATOR = new Creator<TaskDefault>() {
        @Override
        public TaskDefault createFromParcel(Parcel in) {
            return new TaskDefault(in);
        }

        @Override
        public TaskDefault[] newArray(int size) {
            return new TaskDefault[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(detailsOfTask);
        dest.writeString(taskDueDate);
        dest.writeString(taskCreatedDate);
        dest.writeValue(isCompleted);
//        dest.writeByte((Byte)(isCompleted ? 1 : 0));

    }
}
