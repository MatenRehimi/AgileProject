import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class DatabaseAPI {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword";
	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/AgileProject";

	public DatabaseAPI() {

	}

	public static void executeQuery(String query) throws SQLException{

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			) {

				System.out.println("Connected!");
				//printQuery(rs);

			} catch (SQLException e) {
				System.err.println(e);
			}
	}

	public static void printQuery(ResultSet rs) throws SQLException {

		ResultSetMetaData meta = rs.getMetaData();
		int columnCount = meta.getColumnCount();
		List<List<String>> rowList = new LinkedList<List<String>>();
		while (rs.next())
		{
			final List<String> columnList = new LinkedList<String>();
			rowList.add(columnList);

			for (int column = 1; column <= columnCount; ++column)
			{
				final Object value = rs.getObject(column);
				columnList.add(String.valueOf(value));
			}

			System.out.println(rowList.get(rs.getRow()-1));
		}
		rs.beforeFirst();
	}

	public static void insertTask(Task task) throws SQLException{

		String sql = "INSERT INTO TASK (taskID, projectID, name, effortEstimate) VALUES (?,?,?,?)";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {
			stmt.setInt(1,task.getID());
			stmt.setInt(2,task.getProjectID());
			stmt.setString(3,task.getName());
			stmt.setInt(4,task.getEffortEstimate());
			stmt.executeUpdate();

		}
	}

	public static Person checkPersonDetails(String username, String password) throws SQLException {

			String sql = "SELECT * FROM PERSON WHERE username = ? AND password = ?";

			try (
				Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
				stmt.setString(1,username);
				stmt.setString(2,password);
				ResultSet rs = stmt.executeQuery();
				//printQuery(rs);

				while (rs.next()) {
					Person person = new Person(rs.getInt(1), rs.getString(1), rs.getString(2), rs.getString(3));
					return person;
				}
				return null;
			}
	}

	public static ArrayList<Project> getProjects() throws SQLException {

		ArrayList<Project> projects = new ArrayList<Project>();
		String sql = "SELECT * FROM PROJECT";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {

			ResultSet rs = stmt.executeQuery();
			//printQuery(rs);

			while (rs.next()) {
				Project project = new Project(rs.getInt("projectID"), rs.getInt("managerID"), rs.getString("name"));
				projects.add(project);
			}

			return projects;
		}

	}

	public static void addProject(Project project) throws SQLException {

		String sql = "INSERT INTO PROJECT (projectID, managerID, name) VALUES (?,?,?)";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {
			stmt.setInt(1,project.getID());
			stmt.setInt(2,project.getManagerID());
			stmt.setString(3,project.getName());
			stmt.executeUpdate();

		}

	}

	public static void removeProject(Project project) throws SQLException {

		String sql = "DELETE FROM PROJECT WHERE projectID = ?";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {
			stmt.setInt(1,project.getID());
			stmt.executeUpdate();

		}
	}

	public static ArrayList<Integer> getPrerequisiteTasks(int taskID) throws SQLException {
		ArrayList<Integer> tasksToComplete = new ArrayList<Integer>();
		String sql = "SELECT * FROM PREREQUISITETASKS WHERE taskID = ?";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {

			stmt.setInt(1, taskID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tasksToComplete.add(rs.getInt("taskToComplete"));
			}

			return tasksToComplete;
		}
	}

	public static ArrayList<Task> getTasks(int projectID) throws SQLException {

		ArrayList<Task> tasks = new ArrayList<Task>();
		String sql = "SELECT * FROM TASK WHERE projectID = ?";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		) {

			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			//printQuery(rs);

			while (rs.next()) {
				Task task = new Task(rs.getInt("taskID"), rs.getInt("projectID"), rs.getString("name"), rs.getInt("effortEstimate"),
				getPrerequisiteTasks(rs.getInt("taskID")));
				tasks.add(task);
			}

			return tasks;
		}
	}

	public static void removeTask(Task task) throws SQLException {

		String sql = "DELETE FROM TASK WHERE taskID = ?";
		String sql2 = "DELETE FROM PREREQUISITETASKS WHERE taskToComplete = ?";

		try (
			Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement stmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
		) {
			stmt.setInt(1,task.getID());
			stmt2.setInt(1,task.getID());
			stmt2.executeUpdate();
			stmt.executeUpdate();

		}
	}


}
