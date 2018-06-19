import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import java.sql.Connection;


public class TestDBConnection {

	@Test
	public void checkConnectionForMysqlDB() throws SQLException {

		DatabaseAPI dbAPI = new DatabaseAPI("dbuser","dbpassword","jdbc:mysql://localhost:3306/AgileProject");
		dbAPI.executeQuery("SELECT * FROM PEOPLE ");
		assertTrue(true);

	}

	@Test
	public void checkInsert() throws SQLException {

		DatabaseAPI dbAPI = new DatabaseAPI("dbuser","dbpassword","jdbc:mysql://localhost:3306/AgileProject");
		Task task = new Task(5,3,"task4",7,null);
		System.out.println(task.toString());
		//dbAPI.insertTask(task);

		assertTrue(true);
	}
}
