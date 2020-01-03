package com.places.server.config.servlet;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class AppExceptionHandler implements ExceptionHandler {

  @Override
  public ServerFailure createServerFailure(Throwable throwable) {
    final String message = throwable == null ? "" : throwable.getMessage();
    return new ServerFailure("Server error: " + message, null, null, true);
  }
}
