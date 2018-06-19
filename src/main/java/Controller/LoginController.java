import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class LoginController implements ActionListener {

  private MainController mainController;
  private LoginPanel loginPanel;
  private ProjectListPanel projectListPanel;

  public LoginController(MainController mainController, LoginPanel loginPanel, ProjectListPanel projectListPanel) {
    this.mainController = mainController;
    this.loginPanel = loginPanel;
    this.projectListPanel = projectListPanel;
  }

  public void actionPerformed(ActionEvent e)  {
    Person person = null;

    try {
    		person = DatabaseAPI.checkPersonDetails(loginPanel.getLoginText(), loginPanel.getPasswordText());

        if (person != null) {
          projectListPanel.addProjects(DatabaseAPI.getProjects());
          mainController.setCurrentUser(person);
          mainController.getCardLayout().show(mainController.getMainPanel(),"projectListPanel");

        }else{
          JOptionPane.showMessageDialog(loginPanel, "Incorrect Details", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }catch(SQLException err) {
      err.printStackTrace();
    }

  }

}
