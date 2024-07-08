package com.rawat.todo.todoMain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Todo {
    private int id;
    private String title;
    private String content;
    // try Using Enumn here.
    private String status;

    private Date currentDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date completeDate;

    public Todo() {

    }

    public Todo(int id, String title, String content, String status, Date currentDate, Date completeDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.currentDate = currentDate;
        this.completeDate = completeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", currentDate=" + currentDate +
                ", completeDate=" + completeDate +
                '}';
    }
}
