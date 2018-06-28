import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskListController implements ActionListener, ListSelectionListener {

  private MainController mainController;
  private ProjectListPanel projectListPanel;
  private TaskListPanel taskListPanel;
  private CreateTaskPanel createTaskPanel;
  private Task currentTask;

  public TaskListController(MainController mainController, ProjectListPanel projectListPanel,
  TaskListPanel taskListPanel, CreateTaskPanel createTaskPanel) {
    this.mainController = mainController;
    this.projectListPanel = projectListPanel;
    this.taskListPanel = taskListPanel;
    this.createTaskPanel = createTaskPanel;
    currentTask = null;
  }

  public void valueChanged(ListSelectionEvent e) {
    if (!e.getValueIsAdjusting()) {
      currentTask = (Task)taskListPanel.getElementAtIndex(taskListPanel.getSelectedListIndex());
    }
  }

  public void actionPerformed(ActionEvent e) {
    String name = ((JButton) e.getSource()).getName();
    if (name.equals("projectListButton")) {

      mainController.getCardLayout().show(mainController.getMainPanel(),"projectListPanel");
      taskListPanel.clearModel();
    }
    if (name.equals("createTaskButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"createTaskPanel");
      try {
        ArrayList<Task> tasks = DatabaseAPI.getTasks(mainController.getProjectListController().getCurrentProjectID());
        JComboBox<Integer> taskListCB = createTaskPanel.getTaskListCB();
        taskListCB.addItem(null);
        createTaskPanel.clearPrerequisiteListCB();
        for(int i=0; i<tasks.size();i++) {
          taskListCB.addItem((Integer)tasks.get(i).getID());
        }

      }catch(SQLException err) {
        err.printStackTrace();
      }
    }

    if (name.equals("deleteTaskButton")) {
      if (currentTask != null) {
        int index = taskListPanel.getSelectedListIndex();
        if (index > -1) {
          Object task = taskListPanel.getElementAtIndex(index);
          try {
            DatabaseAPI.removeTask(((Task)task));
            taskListPanel.removeIndexFromDLM(index);
            ArrayList<Object> tasks = taskListPanel.getTasks();
            for (int i = 0; i < taskListPanel.getModelSize();i++) {
              ((Task)tasks.get(i)).removePrerequisiteTask(((Task)task).getID());
            }
          }catch(SQLException err) {
            JOptionPane.showMessageDialog(taskListPanel, "Must delete prerequisite tasks first!", "Failure", JOptionPane.ERROR_MESSAGE);
            err.printStackTrace();
          }
        }else{
          JOptionPane.showMessageDialog(taskListPanel, "Must select a task to delete!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
