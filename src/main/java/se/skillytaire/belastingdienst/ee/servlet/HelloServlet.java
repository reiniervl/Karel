package se.skillytaire.belastingdienst.ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servlet")
public class HelloServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   @Override
   protected void doGet(final HttpServletRequest request,
         final HttpServletResponse response)
         throws ServletException, IOException {
      response.getWriter().append("Karels bootjes case is alive :-) \n");
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   @Override
   protected void doPost(final HttpServletRequest request,
         final HttpServletResponse response)
         throws ServletException, IOException {
      this.doGet(request, response);
   }
}
