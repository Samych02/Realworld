package com.example.realworld.exceptions;

import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorBodyGenerator {
  private final List<String> messageList = new ArrayList<>();

  void addToMessageList(String message) {
    messageList.add(message);
  }

  JSONObject createErrorBody() {
    JSONObject bodyObject = new JSONObject();
    JSONArray bodyArray = new JSONArray();
    this.messageList.forEach(bodyArray::put);
    try {
      bodyObject.put("body", bodyArray);
      return new JSONObject().put("errors", bodyObject);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}