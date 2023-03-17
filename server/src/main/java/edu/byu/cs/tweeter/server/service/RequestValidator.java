package edu.byu.cs.tweeter.server.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import edu.byu.cs.tweeter.model.net.request.Request;

public class RequestValidator {

    static <T extends Request> void validateRequest(Request request) {
        request = (T) request;
        try {
            for (Method m : request.getClass().getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                    Object field = m.invoke(request);
                    if (field == null) {
                        throw new RuntimeException("[Bad Request] Missing a " + m.getName().substring(3));
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
