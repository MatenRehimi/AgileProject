import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CreateProjectController implements ActionListener {

  private MainController mainController;
  private ProjectListPanel projectListPanel;
  private CreateProjectPanel createProjectPanel;

  public CreateProjectController(MainController mainController, ProjectListPanel projectListPanel,
    CreateProjectPanel createProjectPanel) {
      this.mainController = mainController;
      this.projectListPanel = projectListPanel;
      this.createProjectPanel = createProjectPanel;
    }

  public void actionPerformed (ActionEvent e) {
    String name = ((JButton) e.getSource()).getName();

    if (name.equals("createProjectButton")) {
      String projectName = createProjectPanel.getNameText();
      if (projectName.length() > 0) {
          try {
              Project project = new Project(DatabaseAPI.getHighestProjectID()+1, mainController.getCurrentUserID(),
              createProjectPanel.getNameText());
              DatabaseAPI.addProject(project);
              projectListPanel.addProjects(DatabaseAPI.getProjects());
          }catch(SQLException err) {
            err.printStackTrace();
          }
        createProjectPanel.clearNameText();
        mainController.getCardLayout().show(mainController.getMainPanel(),"projectListPanel");
      }else{
        JOptionPane.showMessageDialog(createProjectPanel, "Name can not be empty!", "Failure", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (name.equals("backButton")) {
      createProjectPanel.clearNameText();
      mainController.getCardLayout().show(mainController.getMainPanel(), "projectListPanel");
      try {
        projectListPanel.addProjects(DatabaseAPI.getProjects());
      }catch(SQLException err) {
        err.printStackTrace();
      }
    }
  }
}
