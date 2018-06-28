import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class CreateTaskController implements ActionListener, ItemListener{

  private MainController mainController;
  private TaskListPanel taskListPanel;
  private CreateTaskPanel createTaskPanel;
  private DefaultComboBoxModel<Integer> taskListModel;
  private DefaultComboBoxModel<Integer>  prerequisiteModel;
  private boolean repeat;
  private boolean secondRepeat;

  public CreateTaskController(MainController mainController, TaskListPanel taskListPanel,
    CreateTaskPanel createTaskPanel) {
      this.mainController = mainController;
      this.taskListPanel = taskListPanel;
      this.createTaskPanel = createTaskPanel;
      taskListModel = createTaskPanel.getTaskListModel();
      prerequisiteModel = createTaskPanel.getPrerequisiteModel();
      repeat = false;
      secondRepeat = false;
  }

  public void actionPerformed(ActionEvent e) {

    String name = ((JButton) e.getSource()).getName();
    String taskName = null;
    String taskEffort = null;
    Task task = null;

    if (name.equals("submitTaskButton")) {
      taskName = createTaskPanel.getNameText();
      taskEffort = createTaskPanel.getEffortText();

      if (taskName.equals("")) {
        JOptionPane.showMessageDialog(createTaskPanel, "Name can not be empty!", "Failure", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (taskEffort.equals("")) {
        JOptionPane.showMessageDialog(createTaskPanel, "Effort can not be empty!", "Failure", JOptionPane.ERROR_MESSAGE);
        return;
      }

      try {
        int highestTaskID = DatabaseAPI.getHighestTaskID();
        task = new Task(highestTaskID+1,mainController.getCurrentProjectID(),taskName,Integer.parseInt(taskEffort),
        createTaskPanel.getPrerequisiteTasks());
        DatabaseAPI.addTask(task);
        System.out.println(task);
        taskListPanel.clearModel();
        taskListPanel.addTasks(DatabaseAPI.getTasks(mainController.getCurrentProjectID()));
        taskListModel.removeAllElements();
        prerequisiteModel.removeAllElements();
        createTaskPanel.clearNameText();
        createTaskPanel.clearEffortText();
        mainController.getCardLayout().show(mainController.getMainPanel(), "taskListPanel");

      }catch(Exception err) {
        err.printStackTrace();
        JOptionPane.showMessageDialog(createTaskPanel, "Effort must be a number!", "Failure", JOptionPane.ERROR_MESSAGE);
        return;
      }

    }
    if (name.equals("taskListButton")) {
      taskListModel.removeAllElements();
      createTaskPanel.clearNameText();
      createTaskPanel.clearEffortText();
      mainController.getCardLayout().show(mainController.getMainPanel(),"taskListPanel");
    }
  }

  public void itemStateChanged(ItemEvent e) {

    String name = ((JComboBox)e.getSource()).getName();
    int taskSelected = -1;
    Object temp = taskListModel.getSelectedItem();
    if (temp != null) {
      taskSelected = (int)temp;
    }
    int taskSelectedIndex = taskListModel.getIndexOf(taskSelected);

    if (name.equals("taskListCB") && e.getStateChange() == ItemEvent.SELECTED) {

      if (taskSelectedIndex > 1) {

        if (repeat) {
          taskSelected = -1;
          repeat = false;

        }else{
          prerequisiteModel.addElement(taskSelected);
          repeat = !repeat;
          taskListModel.removeElement(taskSelected);
          taskListModel.setSelectedItem(null);
        }
      }

      if(taskSelectedIndex == 1 && !repeat){
        prerequisiteModel.addElement(taskSelected);
        taskListModel.removeElement(taskSelected);
      }

      if (repeat) {
        repeat = false;
      }
    }

    int prerequisiteTaskSelected = -1;
    Object temp2 = prerequisiteModel.getSelectedItem();
    if (temp2 != null) {
      prerequisiteTaskSelected = (int)temp2;
    }
    int prerequisiteTaskSelectedIndex = prerequisiteModel.getIndexOf(prerequisiteTaskSelected);

    if (name.equals("prerequisiteCB") && e.getStateChange() == ItemEvent.SELECTED) {

      if (prerequisiteTaskSelectedIndex > 1) {

        if (secondRepeat) {
          prerequisiteTaskSelected = -1;
          secondRepeat = false;

        }else{
          taskListModel.addElement(prerequisiteTaskSelected);
          secondRepeat = !secondRepeat;
          prerequisiteModel.removeElement(prerequisiteTaskSelected);
          prerequisiteModel.setSelectedItem(null);
        }
      }

      if(prerequisiteTaskSelectedIndex == 1 && !secondRepeat){
        taskListModel.addElement(prerequisiteTaskSelected);
        prerequisiteModel.removeElement(prerequisiteTaskSelected);

      }

      if (secondRepeat) {
        secondRepeat = false;
      }
    }
  }
}
