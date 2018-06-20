import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskListController extends MouseAdapter implements ActionListener {

  private MainController mainController;
  private ProjectListPanel projectListPanel;
  private TaskListPanel taskListPanel;

  public TaskListController(MainController mainController, ProjectListPanel projectListPanel,
  TaskListPanel taskListPanel) {
    this.mainController = mainController;
    this.projectListPanel = projectListPanel;
    this.taskListPanel = taskListPanel;
  }

  public void actionPerformed(ActionEvent e) {
    String name = ((JButton) e.getSource()).getName();
    if (name.equals("projectListButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"projectListPanel");
      taskListPanel.clearModel();
    }
    if (name.equals("createTaskButton")) {

    }
    if (name.equals("deleteTaskButton")) {

    }
  }

  public void mouseClicked(MouseEvent e) {

    JList taskList = (JList) e.getSource();
    int index = taskList.locationToIndex(e.getPoint());

     if (e.getClickCount() == 2) {
        if (index >= 0) {
          Object task = taskList.getModel().getElementAt(index);
          try {
            DatabaseAPI.removeTask(((Task)task));
            ((DefaultListModel) taskList.getModel()).removeElementAt(index);
            ArrayList<Object> tasks = taskListPanel.getTasks();
            for (int i = 0; i < taskListPanel.getModelSize();i++) {
              ((Task)tasks.get(i)).removePrerequisiteTask(((Task)task).getID());
            }
          }catch(SQLException err) {
            JOptionPane.showMessageDialog(taskListPanel, "Must delete prerequisite tasks first!", "Failure", JOptionPane.ERROR_MESSAGE);
            err.printStackTrace();
          }
        }
     }
  }

}
