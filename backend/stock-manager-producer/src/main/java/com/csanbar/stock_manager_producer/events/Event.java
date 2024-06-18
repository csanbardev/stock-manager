package com.csanbar.stock_manager_producer.events;


import lombok.Data;

import java.util.Date;

@Data
public abstract class Event <T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public EventType getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
