import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CreateTaskController implements ActionListener{

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
    }
  }

}
