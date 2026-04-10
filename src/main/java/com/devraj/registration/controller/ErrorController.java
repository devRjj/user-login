package com.devraj.registration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        int statusCode = 500;
        String errorMessage = "Internal Server Error";
        String errorType = "Error";

        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
            HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
            errorType = httpStatus.getReasonPhrase();
        }

        if (message != null) {
            errorMessage = message.toString();
        } else if (exception != null) {
            errorMessage = exception.toString();
        }

        model.addAttribute("status", statusCode);
        model.addAttribute("error", errorType);
        model.addAttribute("message", errorMessage);
        model.addAttribute("path", path);

        return "error";
    }
}
