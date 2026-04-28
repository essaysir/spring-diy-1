package com.diy.framework.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModelAndView {
    private final String viewName;
    private final
    public ModelAndView(String viewName,Model model) {
        this.viewName = viewName;
        this.model = model;
    }



    public void render(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        View view = new ViewResolver().resolve(req,viewName);
        model.addToView(req);
        view.render(req, resp);
    }

}
