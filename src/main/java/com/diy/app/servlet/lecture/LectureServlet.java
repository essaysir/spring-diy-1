package com.diy.app.servlet.lecture;

import com.diy.app.application.LectureService;
import com.diy.app.domain.Lecture;
import com.diy.app.servlet.lecture.dto.LectureCreateRequest;
import com.diy.app.servlet.lecture.dto.LectureUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/lectures")
public class LectureServlet extends HttpServlet {

  private final LectureService lectureService = new LectureService();
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json;charset=UTF-8");
    objectMapper.writeValue(resp.getWriter(), lectureService.getLectures());
  }

  @Override
  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    LectureCreateRequest request = objectMapper.readValue(req.getInputStream(), LectureCreateRequest.class);

    lectureService.register(request.name(), request.price());

    resp.sendRedirect("/");
  }

  @Override
  protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    LectureUpdateRequest request = objectMapper.readValue(req.getInputStream(), LectureUpdateRequest.class);

    try {
      Lecture updated = lectureService.modify(request.id(), request.name(), request.price());

      resp.setStatus(HttpServletResponse.SC_OK);
      resp.setContentType("application/json;charset=UTF-8");
      objectMapper.writeValue(resp.getWriter(), updated);
    } catch (NoSuchElementException e) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    try {
      lectureService.delete(Long.parseLong(req.getParameter("id")));
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (NoSuchElementException e) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
