import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.ArrayList;

public class CreateTaskController implements ActionListener, ItemListener{

  private MainController mainController;
  private TaskListPanel taskListPanel;
  private CreateTaskPanel createTaskPanel;

  public CreateTaskController(MainController mainController, TaskListPanel taskListPanel,
    CreateTaskPanel createTaskPanel) {
      this.mainController = mainController;
      this.taskListPanel = taskListPanel;
      this.createTaskPanel = createTaskPanel;
  }

  public void actionPerformed(ActionEvent e) {
    String name = ((JButton) e.getSource()).getName();

    if (name.equals("submitTaskButton")) {

    }
    if (name.equals("taskListButton")) {
      mainController.getCardLayout().show(mainController.getMainPanel(),"taskListPanel");
      createTaskPanel.clearTaskListCB();
    }
  }

  public void itemStateChanged(ItemEvent e) {

    String name = ((JComboBox)e.getSource()).getName();
    if (name.equals("taskListCB")) {
      if(e.getStateChange() == ItemEvent.SELECTED) {
        //Add to other comboBox
        System.out.println("potato");
      }
    }
  }

}
