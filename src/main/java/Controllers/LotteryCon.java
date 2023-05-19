package Controllers;
//
//import com.example.models.Lottery;
//import com.example.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LotteryCon extends HttpServlet {

    private Connection connection;

    public void init() throws ServletException {
        // Initialize database connection
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request
        // Retrieve lottery number from the request
        int lotteryNumber = Integer.parseInt(request.getParameter("number"));

        // Search for the lottery number in the database
        boolean isWinner = searchLotteryNumber(lotteryNumber);

        // Prepare the result
        request.setAttribute("isWinner", isWinner);
        request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST request
        // Retrieve lottery numbers from the request
        String[] lotteryNumbers = request.getParameterValues("numbers");

        // Save lottery numbers to the database
        saveLotteryNumbers(lotteryNumbers);

        // Redirect to the homepage after saving
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private boolean searchLotteryNumber(int lotteryNumber) {
        try {
            // Prepare SQL statement
            String sql = "SELECT * FROM lottery WHERE number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lotteryNumber);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if the lottery number exists in the database
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveLotteryNumbers(String[] lotteryNumbers) {
        try {
            // Prepare SQL statement
            String sql = "INSERT INTO lottery (number) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Insert each lottery number into the database
            for (String number : lotteryNumbers) {
                statement.setInt(1, Integer.parseInt(number));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        // Close the database connection
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
