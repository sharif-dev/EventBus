package com.example.EventBus;

public class MyEvent {

    private String mMessage;

    MyEvent(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
