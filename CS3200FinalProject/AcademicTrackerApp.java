package javamysql;
import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

public class AcademicTrackerApp {
  private static final String DB_URL = "jdbc:mysql://localhost/academictracker";
  private static final String DB_USER = getUsernameFromUser();
  private static final String DB_PASSWORD = getPasswordFromUser();

  public static void main(String[] args) {
    try {
      // Step 1: Establish a connection to the database
      Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      Scanner scanner = new Scanner(System.in);
      boolean exit = false;

      while (!exit) {
        System.out.println("Student Menu");
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("0. Exit");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Inner_Loop:
          switch (choice) {
          case 1:
            createMenu(connection);
            break Inner_Loop;
          case 2:
            readMenu(connection);
            break Inner_Loop;
          case 3:
            updateMenu(connection);
            break Inner_Loop;
          case 4:
            deleteMenu(connection);
            break Inner_Loop;
          case 0:
            exit = true;
            break Inner_Loop;
          default:
            System.out.println("Invalid choice. Please try again.");
          }

        System.out.println();
        break;
      }
      // Step 5: Close the connection
      connection.close();
      System.out.println("Session Finished.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void deleteMenu(Connection connection) throws SQLException {
    {
      boolean exit = false;
      while (!exit ) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Read Menu");
        System.out.println("1. Course");
        System.out.println("2. Transcript");
        System.out.println("3. Note");
        System.out.println("4. Assignment");
        System.out.println("5. Student");
        System.out.println("6. Staff");
        System.out.println("7. Resource");
        System.out.println("0. Exit");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
        case 1:
          getCourse(connection);
          int courseID;
          while (true) {
            try {
              System.out.println("Enter the course ID: ");
              courseID = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          deleteCourse(connection, courseID);
          break;

        case 2:
          getTranscript(connection);
          String name1;
          int courseID1;
          while (true) {
            try {
              System.out.println("Enter the course name: ");
              name1 = scanner.nextLine();

              System.out.println("Enter the course ID: ");
              courseID1 = Integer.parseInt(scanner.nextLine());
              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          deleteTranscript(connection, name1, courseID1);
          break;
        case 3:
          getNote(connection);
          String name2;
          while (true) {
            try {
              System.out.println("Enter the note name: ");
              name2 = scanner.nextLine();

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          deleteNote(connection, name2);
          break;
        case 4:
          getAssignment(connection);
          String name3;
          int courseID3;
          while (true) {
            try {
              System.out.println("Enter the assignment name: ");
              name3 = scanner.nextLine();

              System.out.println("Enter the course ID: ");
              courseID3 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          deleteAssignment(connection, name3, courseID3);
          break;
        case 5:
          getStudent(connection);
          String name4;
          String email4;
          int courseID4;
          while (true) {
            try {
              System.out.println("Enter the student's name: ");
              name4 = scanner.nextLine();

              System.out.println("Enter the student's email: ");
              email4 = scanner.nextLine();

              System.out.println("Enter the courseID: ");
              courseID4 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          deleteStudent(connection, name4, email4, courseID4);
          break;
        case 6:
          getStaff(connection);
          int staffID5;
          while (true) {
            try {
              System.out.println("Enter the staffID: ");
              staffID5 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          deleteStaff(connection, staffID5);
          break;
        case 7:
          getResource(connection);
          String resourceName6;

          while (true) {
            try {
              System.out.println("Enter the resource name: ");
              resourceName6 = scanner.nextLine();

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          deleteResource(connection, resourceName6);
          break;
        case 0:
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
        }
      }
    }

  }

  private static void updateMenu(Connection connection) throws SQLException {
    boolean exit = false;
    while (!exit ) {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Student Menu");
      System.out.println("1. Course");
      System.out.println("2. Transcript");
      System.out.println("3. Note");
      System.out.println("4. Assignment");
      System.out.println("5. Student");
      System.out.println("6. Staff");
      System.out.println("7. Resource");
      System.out.println("0. Exit");

      System.out.println("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline character
      switch (choice) {
      case 1:
        getCourse(connection);
        String name;
        int courseID;
        int grade;
        int year;
        while (true) {
          try {
            System.out.println("Enter the new course name: ");
            name = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the new grade: ");
            grade = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the new year: ");
            year = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        updateCourse(connection, courseID, name, grade, year);
        break;
      case 2:
        getTranscript(connection);
        String name1;
        int courseID1;
        int year1;
        int grade1;
        while (true) {
          try {
            System.out.println("Enter the course name: ");
            name1 = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID1 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the year: ");
            year1 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the grade: ");
            grade1 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        updateTranscript(connection, name1, courseID1, year1, grade1);
        break;
      case 3:
        getNote(connection);
        String name2;
        String filename2;
        int courseID2;
        while (true) {
          try {
            System.out.println("Enter the note name: ");
            name2 = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID2 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the filename: ");
            filename2 = scanner.nextLine();

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        updateNote(connection, name2, filename2, courseID2);
        break;
      case 4:
        getAssignment(connection);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
        String name3;
        Date dueDate3;
        int grade3;
        int courseID3;
        String type3;
        int weight3;
        while (true) {
          try {
            System.out.println("Enter the assignment name: ");
            name3 = scanner.nextLine();

            System.out.println("Enter the due date: ");
            dueDate3 = sdf1.parse(scanner.nextLine());

            System.out.println("Enter the grade: ");
            grade3 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the courseID: ");
            courseID3 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the type (Homework, Project, Participation, Exam, Other) : ");
            type3 = scanner.nextLine();

            System.out.println("Enter the weight: ");
            weight3 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException | ParseException e) {
            System.out.println("Invalid input. Please enter a valid integer or date.");
          }
        }
        updateAssignment(connection, name3, dueDate3, grade3, courseID3, type3, weight3);
        break;
      case 6:
        getStaff(connection);
        String name5;
        String email5;
        int staffID5;
        int courseID5;
        while (true) {
          try {
            System.out.println("Enter the staff name: ");
            name5 = scanner.nextLine();

            System.out.println("Enter the staff email: ");
            email5 = scanner.nextLine();

            System.out.println("Enter the staffID: ");
            staffID5 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the courseID: ");
            courseID5 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        updateStaff(connection, name5, email5, staffID5, courseID5);
        break;
      case 7:
        getResource(connection);
        String resourceName6;
        String type6;
        String authorName6;
        while (true) {
          try {
            System.out.println("Enter the resource name: ");
            resourceName6 = scanner.nextLine();

            System.out.println("Enter the type: ");
            type6 = scanner.nextLine();

            System.out.println("Enter the author's name: ");
            authorName6 = scanner.nextLine();

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        updateResource(connection, resourceName6, type6, authorName6);
        break;
      case 0:
        exit = true;
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void readMenu(Connection connection) throws SQLException {
    {
      boolean exit = false;
      while (!exit ) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Read Menu");
        System.out.println("1. Course");
        System.out.println("2. Transcript");
        System.out.println("3. Note");
        System.out.println("4. Assignment");
        System.out.println("5. Student");
        System.out.println("6. Staff");
        System.out.println("7. Resource");
        System.out.println("0. Exit");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
        case 1:
          getCourse(connection);
          int courseID;
          while (true) {
            try {
              System.out.println("Enter the course ID: ");
              courseID = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          readCourse(connection, courseID);
          break;

        case 2:
          getTranscript(connection);
          int courseID1;
          while (true) {
            try {
              System.out.println("Enter the course ID: ");
              courseID1 = Integer.parseInt(scanner.nextLine());
              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          readTranscript(connection, courseID1);
          break;

        case 3:
          getNote(connection);
          int courseID2;
          while (true) {
            try {

              System.out.println("Enter the course ID: ");
              courseID2 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          readNote(connection, courseID2);
          break;
        case 4:
          getAssignment(connection);
          String name3;
          while (true) {
            try {
              System.out.println("Enter the assignment name: ");
              name3 = scanner.nextLine();

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
            }
          }
          readAssignment(connection, name3);
          break;
        case 5:
          getStudent(connection);
          int courseID4;
          while (true) {
            try {

              System.out.println("Enter the courseID: ");
              courseID4 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          readStudent(connection, courseID4);
          break;
        case 6:
          getStaff(connection);
          int staffID5;
          while (true) {
            try {
              System.out.println("Enter the staffID: ");
              staffID5 = Integer.parseInt(scanner.nextLine());

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          readStaff(connection, staffID5);
          break;
        case 7:
          getResource(connection);
          String resourceName6;

          while (true) {
            try {
              System.out.println("Enter the resource name: ");
              resourceName6 = scanner.nextLine();

              break; // Break the loop if all inputs are valid
            } catch (NumberFormatException e) {
              System.out.println("Invalid input.");
            }
          }
          readResource(connection, resourceName6);
          break;
        case 0:
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
        }
      }
    }

  }

  private static void createMenu(Connection connection) throws SQLException {
    boolean exit = false;
    while (!exit ) {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Student Menu");
      System.out.println("1. Course");
      System.out.println("2. Transcript");
      System.out.println("3. Note");
      System.out.println("4. Assignment");
      System.out.println("5. Student");
      System.out.println("6. Staff");
      System.out.println("7. Resource");
      System.out.println("0. Exit");

      System.out.println("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline character
      switch (choice) {
      case 1:
        getCourse(connection);
        String name;
        int courseID;
        int grade;
        int year;
        while (true) {
          try {
            System.out.println("Enter the course name: ");
            name = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the grade: ");
            grade = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the year: ");
            year = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createCourse(connection, name, courseID, grade, year);
        break;

      case 2:
        getTranscript(connection);
        String name1;
        int courseID1;
        int year1;
        int grade1;
        while (true) {
          try {
            System.out.println("Enter the course name: ");
            name1 = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID1 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the year: ");
            year1 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the grade: ");
            grade1 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createTranscript(connection, name1, courseID1, year1, grade1);
        break;

      case 3:
        getNote(connection);
        String name2;
        String filename2;
        int courseID2;
        while (true) {
          try {
            System.out.println("Enter the note name: ");
            name2 = scanner.nextLine();

            System.out.println("Enter the course ID: ");
            courseID2 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the filename: ");
            filename2 = scanner.nextLine();

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createNote(connection, name2, filename2, courseID2);
        break;

      case 4:
        getAssignment(connection);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
        String name3;
        Date dueDate3;
        int grade3;
        int courseID3;
        String type3;
        int weight3;
        while (true) {
          try {
            System.out.println("Enter the assignment name: ");
            name3 = scanner.nextLine();

            System.out.println("Enter the due date: ");
            dueDate3 = sdf1.parse(scanner.nextLine());

            System.out.println("Enter the grade: ");
            grade3 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the courseID: ");
            courseID3 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the type (Homework, Project, Participation, Exam, Other) : ");
            type3 = scanner.nextLine();

            System.out.println("Enter the weight: ");
            weight3 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException | ParseException e) {
            System.out.println("Invalid input. Please enter a valid integer or date.");
          }
        }
        createAssignment(connection, name3, dueDate3, grade3, courseID3, type3, weight3);
        break;
      case 5:
        getStudent(connection);
        String name4;
        int courseID4;
        String email4;
        while (true) {
          try {
            System.out.println("Enter the student name: ");
            name4 = scanner.nextLine();

            System.out.println("Enter the courseID: ");
            courseID4 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the student email: ");
            email4 = scanner.nextLine();

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createStudent(connection, name4, email4, courseID4);
        break;
      case 6:
        getStaff(connection);
        String name5;
        String email5;
        int staffID5;
        int courseID5;
        while (true) {
          try {
            System.out.println("Enter the staff name: ");
            name5 = scanner.nextLine();

            System.out.println("Enter the staff email: ");
            email5 = scanner.nextLine();

            System.out.println("Enter the staffID: ");
            staffID5 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the courseID: ");
            courseID5 = Integer.parseInt(scanner.nextLine());

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createStaff(connection, name5, email5, staffID5, courseID5);
        break;
      case 7:
        getResource(connection);
        String resourceName6;
        int courseID6;
        String type6;
        String authorName6;
        while (true) {
          try {
            System.out.println("Enter the resource name: ");
            resourceName6 = scanner.nextLine();

            System.out.println("Enter the courseID: ");
            courseID6 = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the type: ");
            type6 = scanner.nextLine();

            System.out.println("Enter the author's name: ");
            authorName6 = scanner.nextLine();

            break; // Break the loop if all inputs are valid
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
          }
        }
        createResource(connection, resourceName6, courseID6, type6, authorName6);
        break;
      case 0:
        exit = true;
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static String getUsernameFromUser() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter MySQL username: ");
    return scanner.nextLine();
  }

  private static String getPasswordFromUser() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter MySQL password: ");
    return scanner.nextLine();
  }

  public static void createCourse(Connection connection, String name, int courseID, int grade, int year) throws SQLException {

    String query = "CALL createCourse(?, ?, ?, ?)";

    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setInt(2, courseID);
      statement.setInt(3, grade);
      statement.setInt(4, year);
      statement.execute();
      System.out.println("Course created successfully.");
    }
  }

  public static void readCourse(Connection connection, int courseID) throws SQLException {
    String query = "CALL readCourse(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        int courseId = resultSet.getInt("courseID");
        int grade = resultSet.getInt("grade");
        int year = resultSet.getInt("year");
        System.out.println("Name: " + name + ", Course ID: " + courseId + ", Grade: " + grade + ", Year: " + year);
      }
    }
  }

  public static void updateCourse(Connection connection, int courseID, String newName, int newGrade, int newYear) throws SQLException {
    String query = "CALL updateCourse(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      statement.setString(2, newName);
      statement.setInt(3, newGrade);
      statement.setInt(4, newYear);
      statement.execute();
      System.out.println("Course updated successfully.");
    }
  }

  public static void deleteCourse(Connection connection, int courseID) throws SQLException {
    String query = "CALL deleteCourse(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      statement.execute();
      System.out.println("Course deleted successfully.");
    }
  }

  public static void createTranscript(Connection connection, String name, int courseID, int year, int grade) throws SQLException {
    String query = "CALL createTranscript(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setInt(2, courseID);
      statement.setInt(3, year);
      statement.setInt(4, grade);
      statement.execute();
      System.out.println("Transcript created successfully.");
    }
  }

  public static void readTranscript(Connection connection, int courseID) throws SQLException {
    String query = "CALL readTranscript(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        int courseId = resultSet.getInt("courseID");
        int grade = resultSet.getInt("grade");
        int year = resultSet.getInt("year");
        System.out.println("Name: " + name + ", Course ID: " + courseId + ", Grade: " + grade + ", Year: " + year);
      }
    }
  }

  public static void updateTranscript(Connection connection, String name, int courseID, int newYear, int newGrade) throws SQLException {
    String query = "CALL updateTranscript(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setInt(2, courseID);
      statement.setInt(3, newYear);
      statement.setInt(4, newGrade);
      statement.execute();
      System.out.println("Transcript updated successfully.");
    }
  }

  public static void deleteTranscript(Connection connection, String name, int courseID) throws SQLException {
    String query = "CALL deleteTranscript(?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setInt(2, courseID);
      statement.execute();
      System.out.println("Transcript deleted successfully.");
    }
  }

  public static void createNote(Connection connection, String name, String filename, int courseID) throws SQLException {
    String query = "CALL createNote(?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setString(2, filename);
      statement.setInt(3, courseID);
      statement.execute();
      System.out.println("Note created successfully.");
    }
  }

  public static void readNote(Connection connection, int courseID) throws SQLException {
    String query = "CALL readNote(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        String filename = resultSet.getString("filename");
        int courseId = resultSet.getInt("courseID");
        System.out.println("Name: " + name + ", Filename: " + filename + ", Course ID: " + courseId);
      }
    }
  }

  public static void updateNote(Connection connection, String name, String newFilename, int newCourseID) throws SQLException {
    String query = "CALL updateNote(?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setString(2, newFilename);
      statement.setInt(3, newCourseID);
      statement.execute();
      System.out.println("Note updated successfully.");
    }
  }

  public static void deleteNote(Connection connection, String name) throws SQLException {
    String query = "CALL deleteNote(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.execute();
      System.out.println("Note deleted successfully.");
    }
  }

  public static void createAssignment(Connection connection, String name, Date dueDate, int grade, int courseID, String type, int weight) throws SQLException {
    String query = "CALL createAssignment(?, ?, ?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setDate(2, (java.sql.Date) dueDate);
      statement.setInt(3, grade);
      statement.setInt(4, courseID);
      statement.setString(5, type);
      statement.setInt(6, weight);
      statement.execute();
      System.out.println("Assignment created successfully.");
    }
  }

  public static void readAssignment(Connection connection, String name) throws SQLException {
    String query = "CALL readAssignment(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String assignmentName = resultSet.getString("name");
        Date dueDate = resultSet.getDate("due_date");
        int grade = resultSet.getInt("grade");
        int courseId = resultSet.getInt("courseID");
        String type = resultSet.getString("type");
        int weight = resultSet.getInt("weight");
        System.out.println("Assignment Name: " + assignmentName + ", Due Date: " + dueDate + ", Grade: " + grade +
            ", Course ID: " + courseId + ", Type: " + type + ", Weight: " + weight);
      }
    }
  }

  public static void updateAssignment(Connection connection, String name, Date newDueDate, int courseID, int newGrade, String newType, int newWeight) throws SQLException {
    String query = "CALL updateAssignment(?, ?, ?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setDate(2, (java.sql.Date) newDueDate);
      statement.setInt(3, courseID);
      statement.setInt(4, newGrade);
      statement.setString(5, newType);
      statement.setInt(6, newWeight);
      statement.execute();
      System.out.println("Assignment updated successfully.");
    }
  }

  public static void deleteAssignment(Connection connection, String name, int courseID) throws SQLException {
    String query = "CALL deleteAssignment(?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setInt(2, courseID);
      statement.execute();
      System.out.println("Assignment deleted successfully.");
    }
  }

  public static void createStudent(Connection connection, String name, String studentEmail, int courseID) throws SQLException {
    String query = "CALL createStudent(?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setString(2, studentEmail);
      statement.setInt(3, courseID);
      statement.execute();
      System.out.println("Student created successfully.");
    }
  }

  public static void readStudent(Connection connection, int courseID) throws SQLException {
    String query = "CALL readStudent(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, courseID);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        String studentEmail = resultSet.getString("student_email");
        System.out.println("Name: " + name + ", Student Email: " + studentEmail);
      }
    }
  }

  public static void deleteStudent(Connection connection, String name, String studentEmail, int courseID) throws SQLException {
    String query = "CALL deleteStudent(?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setString(2, studentEmail);
      statement.setInt(3, courseID);
      statement.execute();
      System.out.println("Student deleted successfully.");
    }
  }

  public static void createStaff(Connection connection, String name, String email, int staffID, int courseID) throws SQLException {
    String query = "CALL createStaff(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, name);
      statement.setString(2, email);
      statement.setInt(3, staffID);
      statement.setInt(4, courseID);
      statement.execute();
      System.out.println("Staff created successfully.");
    }
  }

  public static void readStaff(Connection connection, int staffID) throws SQLException {
    String query = "CALL readStaff(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, staffID);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        int courseId = resultSet.getInt("courseID");
        System.out.println("Name: " + name + ", Email: " + email + ", Course ID: " + courseId);
      }
    }
  }

  public static void updateStaff(Connection connection, String newName, String newEmail, int staffID, int newCourseID) throws SQLException {
    String query = "CALL updateStaff(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, newName);
      statement.setString(2, newEmail);
      statement.setInt(3, staffID);
      statement.setInt(4, newCourseID);
      statement.execute();
      System.out.println("Staff updated successfully.");
    }
  }

  public static void deleteStaff(Connection connection, int staffID) throws SQLException {
    String query = "CALL deleteStaff(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setInt(1, staffID);
      statement.execute();
      System.out.println("Staff deleted successfully.");
    }
  }

  public static void createResource(Connection connection, String resourceName, int courseID, String type, String authorName) throws SQLException {
    String query = "CALL createResource(?, ?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, resourceName);
      statement.setInt(2, courseID);
      statement.setString(3, type);
      statement.setString(4, authorName);
      statement.execute();
      System.out.println("Resource created successfully.");
    }
  }

  public static void readResource(Connection connection, String resourceName) throws SQLException {
    String query = "CALL readResource(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, resourceName);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String resource = resultSet.getString("resource_name");
        int courseId = resultSet.getInt("courseID");
        String type = resultSet.getString("type");
        String authorName = resultSet.getString("author_name");
        System.out.println("Resource Name: " + resource + ", Course ID: " + courseId + ", Type: " + type + ", Author Name: " + authorName);
      }
    }
  }

  public static void updateResource(Connection connection, String resourceName, String newType, String newAuthorName) throws SQLException {
    String query = "CALL updateResource(?, ?, ?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, resourceName);
      statement.setString(2, newType);
      statement.setString(3, newAuthorName);
      statement.execute();
      System.out.println("Resource updated successfully.");
    }
  }

  public static void deleteResource(Connection connection, String resourceName) throws SQLException {
    String query = "CALL deleteResource(?)";
    try (CallableStatement statement = connection.prepareCall(query)) {
      statement.setString(1, resourceName);
      statement.execute();
      System.out.println("Resource deleted successfully.");
    }
  }
  public static void getCourse(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getCourse()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getTranscript(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getTranscript()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getNote(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getNotes()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getAssignment(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getAssignment()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getStudent(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getStudent()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getStaff(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getStaff()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
  public static void getResource(Connection connection) throws SQLException {
    try (CallableStatement statement = connection.prepareCall("CALL getResource()")) {
      boolean hasResults = statement.execute();
      if (hasResults) {
        try (ResultSet resultSet = statement.getResultSet()) {
          ResultSetMetaData metaData = resultSet.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
              System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
          }
        }
      }
    }
  }
}