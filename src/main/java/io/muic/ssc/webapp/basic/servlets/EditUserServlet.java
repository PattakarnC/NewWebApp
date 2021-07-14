package io.muic.ssc.webapp.basic.servlets;

import io.muic.ssc.webapp.basic.security.User;
import io.muic.ssc.webapp.basic.security.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = StringUtils.trim(request.getParameter("username"));
            UserService userService = UserService.getInstance();

            // let's prefill the form
            User user = userService.findByUsername(username);
            request.setAttribute("user", user);
            request.setAttribute("username", user.getUsername());
            request.setAttribute("displayName", user.getDisplayName());

            // if not success, it will arrive here
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/edit.jsp");
            requestDispatcher.include(request, response);

            // removing attributes as soon as they are used is known as flash session
            request.getSession().removeAttribute("hasError");
            request.getSession().removeAttribute("message");
        } else {
            // just add some extra precaution to delete those two attributes
            request.removeAttribute("hasError");
            request.removeAttribute("message");
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            // edit user is similar to create user but we only allow editing display name
            String username = StringUtils.trim(request.getParameter("username"));
            String displayName = StringUtils.trim(request.getParameter("displayName"));

            UserService userService = UserService.getInstance();
            String errorMessage = null;

            if (userService.findByUsername(username) == null) {
                errorMessage = String.format("Username %s does not exist.", username);
            }
            else if (StringUtils.isBlank(displayName)) {
                errorMessage = "Display Name cannot be blank.";
            }

            if (errorMessage != null) {
                request.getSession().setAttribute("hasError", true);
                request.getSession().setAttribute("message", errorMessage);
            }
            else {
                // edit a user
                try {
                    userService.updateUserByUsername(username, displayName);
                    // if no error redirect
                    request.getSession().setAttribute("hasError", false);
                    request.getSession().setAttribute("message", String.format("User %s has successfully updated.", username));
                    response.sendRedirect("/");
                    return;
                } catch (Exception e) {
                    request.getSession().setAttribute("hasError", true);
                    request.getSession().setAttribute("message", e.getMessage());
                }

            }
            // let's prefill the form
            request.setAttribute("username", username);
            request.setAttribute("displayName", displayName);

            // if not success, it will arrive here
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/edit.jsp");
            requestDispatcher.include(request, response);

            // removing attributes as soon as they are used is known as flash session
            request.getSession().removeAttribute("hasError");
            request.getSession().removeAttribute("message");
        } else {
            // just add some extra precaution to delete those two attributes
            request.removeAttribute("hasError");
            request.removeAttribute("message");
            response.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/user/edit";
    }

}
