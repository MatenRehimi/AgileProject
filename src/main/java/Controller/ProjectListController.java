import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.sql.SQLException;

public class ProjectListController extends MouseAdapter implements ActionListener  {

  private MainController mainController;
  private LoginPanel loginPanel;
  private ProjectListPanel projectListPanel;

  public ProjectListController(MainController mainController, LoginPanel loginPanel, ProjectListPanel projectListPanel) {
    this.mainController = mainController;
    this.loginPanel = loginPanel;
    this.projectListPanel = projectListPanel;
  }

  public void actionPerformed(ActionEvent e)  {

    String name = ((JButton) e.getSource()).getName();
    if (name.equals("logOutButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"loginPanel");
      projectListPanel.clearModel();
    }
    if (name.equals("createProjectButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"createProjectPanel");
      projectListPanel.clearModel();

    }

  }

    public void mouseClicked(MouseEvent e) {

       if (e.getClickCount() == 2) {
         JList projectList = (JList) e.getSource();
         int index = projectList.locationToIndex(e.getPoint());
          if (index >= 0) {
            Object project = projectList.getModel().getElementAt(index);
            ((DefaultListModel) projectList.getModel()).removeElementAt(index);
            try {
            	DatabaseAPI.removeProject(((Project)project));
            }catch(SQLException err) {
              err.printStackTrace();
            }
          }


       }
    }

}
