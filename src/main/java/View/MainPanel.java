import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class MainPanel extends JPanel{

  private MainController mainController;
  private LoginPanel loginPanel;
  private ProjectListPanel projectListPanel;
  private CreateProjectPanel createProjectPanel;
  private TaskListPanel taskListPanel;
  private CreateTaskPanel createTaskPanel;

  private CardLayout cards;
  private JFrame frame;

  public MainPanel() {

    loginPanel = new LoginPanel(this);
    projectListPanel = new ProjectListPanel(this);
    createProjectPanel = new CreateProjectPanel(this);
    taskListPanel = new TaskListPanel(this);
    createTaskPanel = new CreateTaskPanel(this);

    frame = new JFrame("Agile Project");
    frame.setSize(800,550);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(this);

    cards = new CardLayout(10,10);
    setLayout(cards);

    add(loginPanel,"loginPanel");
    add(projectListPanel,"projectListPanel");
    add(createProjectPanel,"createProjectPanel");
    add(taskListPanel,"taskListPanel");
    add(createTaskPanel,"createTaskPanel");

    mainController = new MainController(this);
    loginPanel.addActionListeners(mainController.getLoginController());
    projectListPanel.addActionListeners(mainController.getProjectListController());
    createProjectPanel.addActionListener(mainController.getCreateProjectController());
    taskListPanel.addActionListeners(mainController.getTaskListController());
    createTaskPanel.addActionListeners(mainController.getCreateTaskController());

  }

  public CardLayout getCardLayout() {
    return cards;
  }

  public LoginPanel getLoginPanel() {
    return loginPanel;
  }

  public ProjectListPanel getProjectListPanel() {
    return projectListPanel;
  }

  public CreateProjectPanel getCreateProjectPanel() {
    return createProjectPanel;
  }

  public TaskListPanel getTaskListPanel() {
    return taskListPanel;
  }

  public CreateTaskPanel getCreateTaskPanel() {
    return createTaskPanel;
  }

}
