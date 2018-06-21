import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.sql.SQLException;

public class ProjectListController extends MouseAdapter implements ActionListener, ListSelectionListener  {

  private MainController mainController;
  private LoginPanel loginPanel;
  private ProjectListPanel projectListPanel;
  private TaskListPanel taskListPanel;
  private Project currentProject;

  public ProjectListController(MainController mainController, LoginPanel loginPanel, ProjectListPanel projectListPanel, TaskListPanel taskListPanel) {
    this.mainController = mainController;
    this.loginPanel = loginPanel;
    this.projectListPanel = projectListPanel;
    this.taskListPanel = taskListPanel;
    currentProject = null;
  }

  public void valueChanged(ListSelectionEvent e) {
    //repeats double call with single click
    if (!e.getValueIsAdjusting()) {
      System.out.println("pop");
      currentProject = (Project)projectListPanel.getElementAtIndex(projectListPanel.getSelectedListIndex());
    }
  }

  public void actionPerformed(ActionEvent e)  {

    String name = ((JButton) e.getSource()).getName();
    if (name.equals("projectList")) {
      System.out.println("pop");
      int index = projectListPanel.getSelectedListIndex();
      if (index > -1) {
        Object project = projectListPanel.getModel().getElementAt(index);
        currentProject = (Project)project;
      }
    }
    if (name.equals("logOutButton")) {
      currentProject = null;
      mainController.getCardLayout().show(mainController.getMainPanel(),"loginPanel");
      projectListPanel.clearModel();
    }
    if (name.equals("createProjectButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"createProjectPanel");
      projectListPanel.clearModel();
    }
    if (name.equals("deleteProjectButton")) {
      if (currentProject != null) {
        try {
          int index = projectListPanel.getSelectedListIndex();
          if (index > -1) {
            Object project = projectListPanel.getElementAtIndex(index);
            DatabaseAPI.removeProject(((Project)project));
            projectListPanel.removeIndexFromDLM(index);
          }else{
            JOptionPane.showMessageDialog(projectListPanel, "Must select a project to delete!", "Failure", JOptionPane.ERROR_MESSAGE);
          }

        }catch(SQLException err) {
          err.printStackTrace();
          JOptionPane.showMessageDialog(projectListPanel, "Must delete Tasks first!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
      }else{
        JOptionPane.showMessageDialog(loginPanel, "Pick a project to delete!", "Failure", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (name.equals("viewProjectButton")) {
      if (currentProject != null) {
        try {
              taskListPanel.addTasks(DatabaseAPI.getTasks(currentProject.getID()));
              mainController.getCardLayout().show(mainController.getMainPanel(),"taskListPanel");

        }catch(SQLException err) {
          err.printStackTrace();
        }
      }else{
        JOptionPane.showMessageDialog(loginPanel, "Pick a project to view!", "Failure", JOptionPane.ERROR_MESSAGE);
      }

    }

  }

    public void mouseClicked(MouseEvent e) {

      JList projectList = (JList) e.getSource();
      int index = projectList.locationToIndex(e.getPoint());

       if (e.getClickCount() == 2) {
          if (index >= 0) {
            Object project = projectList.getModel().getElementAt(index);
            try {
            	DatabaseAPI.removeProject(((Project)project));
              ((DefaultListModel) projectList.getModel()).removeElementAt(index);
            }catch(SQLException err) {
              err.printStackTrace();
              JOptionPane.showMessageDialog(projectListPanel, "Must delete Tasks first!", "Failure", JOptionPane.ERROR_MESSAGE);
            }
          }

       }

       // if (e.getClickCount() == 1) {
       //    if (index >= 0) {
       //      Object project = projectList.getModel().getElementAt(index);
       //      currentProject = (Project)project;
       //    }
       // }
    }

}
