import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import javax.swing.JButton;

public class MainController implements ActionListener {

  private Person currentUser;
  private MainPanel mainPanel;
  private LoginController loginController;
  private ProjectListController projectListController;
  private CreateProjectController createProjectController;
  private CardLayout cards;

  public MainController(MainPanel mainPanel) {
    this.mainPanel = mainPanel;
    cards = mainPanel.getCardLayout();
    loginController = new LoginController(this, mainPanel.getLoginPanel(), mainPanel.getProjectListPanel());
    projectListController = new ProjectListController(this, mainPanel.getLoginPanel(), mainPanel.getProjectListPanel());
    createProjectController = new CreateProjectController(this, mainPanel.getProjectListPanel(), mainPanel.getCreateProjectPanel());
    currentUser = null;
  }

  public void actionPerformed(ActionEvent e) {
    cards.next(mainPanel);
    //mainPanel.getCardLayout().show(mainPanel,"Banana");
  }

  public void setLoginController(LoginController loginController) {
    this.loginController = loginController;
  }

  public LoginController getLoginController() {
    return loginController;
  }

  public ProjectListController getProjectListController() {
    return projectListController;
  }

  public CreateProjectController getCreateProjectController() {
    return createProjectController;
  }

  public MainPanel getMainPanel() {
    return mainPanel;
  }

  public CardLayout getCardLayout() {
    return cards;
  }

  public void setCurrentUser(Person currentUser) {
    this.currentUser = currentUser;
  }

  public int getCurrentUserID() {
    return currentUser.getID();
  }


}
