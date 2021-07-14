package io.muic.ssc.webapp.basic.servlets;

import io.muic.ssc.webapp.basic.security.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            String username = (String) request.getSession().getAttribute("username");

            request.setAttribute("user", username);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/create.jsp");
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
            String username = StringUtils.trim(request.getParameter("username"));
            String displayName = StringUtils.trim(request.getParameter("displayName"));
            String password = StringUtils.trim(request.getParameter("password"));
            String cpassword = StringUtils.trim(request.getParameter("cpassword"));

            UserService userService = UserService.getInstance();
            String errorMessage = null;

            if (userService.findByUsername(username) != null) {
                errorMessage = String.format("Username %s has already been taken.", username);
            }
            else if (StringUtils.isBlank(username)) {
                errorMessage = "Username cannot be blank.";
            }
            else if (StringUtils.isBlank(displayName)) {
                errorMessage = "Display Name cannot be blank.";
            }
            else if (StringUtils.isBlank(password)) {
                errorMessage = "Password cannot be blank.";
            }
            else if (!StringUtils.equals(password, cpassword)) {
                errorMessage = "Confirming password does not match with the input password.";
            }

            if (errorMessage != null) {
                request.getSession().setAttribute("hasError", true);
                request.getSession().setAttribute("message", errorMessage);
            }
            else {
                // create user
                try {
                    userService.createUser(username, password, displayName);
                    // if no error redirect
                    request.getSession().setAttribute("hasError", false);
                    request.getSession().setAttribute("message", String.format("Username %s has successfully created.", username));
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
            request.setAttribute("password", password);
            request.setAttribute("cpassword", cpassword);

            // if not success, it will arrive here
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/create.jsp");
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
        return "/user/create";
    }


}
