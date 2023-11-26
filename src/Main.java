import java.sql.*;
import java.util.Scanner;

public class Main
{
    //----------------------------------
    // Creating the connection // Step 2
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String userName = "root";
    private static final String password = "tictactoe";
    //-------------------------------------

    public static void main(String[] args)
    {
        //---------------------------------
        // loading driver  // Step 1
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error while loading the Driver !");
            System.out.println("-> " + e.getMessage());
        }
        //-----------------------------------
        // Step 2 continued
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            // Creating Statements - Step 3
//            Statement statement = connection.createStatement();
//            // selection query
//            String query = "select * from students;";
//            ResultSet resultSet = statement.executeQuery(query);
//            while(resultSet.next())
//            {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID : " + id + "\t Name : " + name + "\t Age : " + age + "\t Marks : " + marks);
//            }
//
//            // insertion query
//            String query2 = String.format("insert into students(name, age, marks)" +
//                    " values('%s', %o, %f)", "Rahul", 23, 77.33);
//            int rowsAffected = statement.executeUpdate(query2);
//            if(rowsAffected>0)
//            {
//                 System.out.println("Data inserted Successfully !!");
//                 System.out.println("-> " + rowsAffected + ", row/s affected.");
//            }
//            else
//            {
//                System.out.println("Insertion Unsuccessful!");
//            }
//
//            // update query
//            String query3 = String.format("update students set marks = %f where id = %o", 89.5, 2);
//            rowsAffected = statement.executeUpdate(query3);
//            if(rowsAffected>0)
//            {
//                System.out.println("Data Updated Successfully !!");
//                System.out.println("-> " + rowsAffected + ", row/s affected.");
//            }
//            else
//            {
//                System.out.println("Update Unsuccessful!");
//            }
//
//            // deletion query
//            String query4 = String.format("delete from students where name = '%s'", "Rahul");
//            rowsAffected = statement.executeUpdate(query4);
//            if(rowsAffected>0)
//            {
//                System.out.println("Data Deleted Successfully !!");
//                System.out.println("-> " + rowsAffected + ", row/s affected.");
//            }
//            else
//            {
//                System.out.println("deletion Unsuccessful!");
//            }


            // prepared statements
            // insertion query
//            String query = "insert into students(name, age, marks) " +
//                    "values(?, ?, ?);";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, "Aditya");
//            preparedStatement.setInt(2, 25);
//            preparedStatement.setDouble(3, 84.7);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if(rowsAffected>0)
//            {
//                System.out.println("Data Inserted Successfully !!");
//                System.out.println("-> " + rowsAffected + ", row/s affected.");
//            }
//            else
//            {
//                System.out.println("Insertion Unsuccessful!");
//            }
//
//            // select query
//            String query2 = "select marks from student where id = ?;";
//            PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
//            preparedStatement1.setInt(1, 1);
//            ResultSet resultSet = preparedStatement1.executeQuery();
//
//            if(resultSet.next())
//            {
//                System.out.println("Marks : " + resultSet.getDouble("marks"));
//            }
//            else
//            {
//                System.out.println("Marks not found!");
//            }

            // Batch Processing : // executing multiple sql statements
            Scanner scanner = new Scanner(System.in);
            PreparedStatement preparedStatement;
            while (true)
            {
                System.out.print("Enter Name: ");
                String name = scanner.next();
                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
                System.out.print("Enter Marks: ");
                double marks = scanner.nextDouble();
                System.out.println("Enter more Data (y/n) ? ");
                String choice = scanner.next();

                String query = "insert into students(name, age, marks) values (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);

                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("n")) break;
            }
            int[] arr = preparedStatement.executeBatch();
            for(int i=0; i<arr.length; i++)
            {
                if(arr[i] == 0)
                {
                    System.out.println("Query "+ (i+1) +" not executed !");
                }
            }

        }
        catch (SQLException e)
        {
            System.out.println("Error when Connecting to the database");
            System.out.println("-> " + e.getMessage());
        }
        //-------------------------------------




    }
}
