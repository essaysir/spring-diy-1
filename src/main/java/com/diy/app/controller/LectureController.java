package com.diy.app.controller;

import com.diy.app.application.LectureService;
import com.diy.app.domain.Lecture;
import com.diy.framework.web.ModelAndView;
import com.diy.framework.web.controller.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LectureController implements Controller {

    private final LectureService lectureService = new LectureService();

    @Override
    public ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if ("POST".equals(request.getMethod())) {
            return doPost(request, response);
        } else if ("GET".equals(request.getMethod())) {
            return doGet(request, response);
        }

        throw new RuntimeException("404 Not Found");
    }

    private ModelAndView doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        return new ModelAndView("redirect:/lectures");
    }

    private ModelAndView doGet(final HttpServletRequest req, final HttpServletResponse resp) throws Exception {
        final List<Lecture> lectures = lectureService.getLectures();

        final Map<String, Object> model = new HashMap<>();
        model.put("lectures", lectures);

        return new ModelAndView("lecture-list", model);
    }
}
