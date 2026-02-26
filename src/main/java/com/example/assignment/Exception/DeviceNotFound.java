package com.example.assignment.Exception;

public class DeviceNotFound extends RuntimeException{

    public DeviceNotFound(  String message) {
      super(message);

    }
}
