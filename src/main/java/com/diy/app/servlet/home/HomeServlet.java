package com.diy.app.servlet.home;

import com.diy.app.application.LectureService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/")
public class HomeServlet extends HttpServlet {

  private final LectureService lectureService = new LectureService();

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("lectures", lectureService.getLectures());
    req.getRequestDispatcher("/lecture-list.jsp").forward(req, resp);
  }
}
