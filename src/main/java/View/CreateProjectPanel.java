import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public class CreateProjectPanel extends JPanel {

  private static final Font MONACO_FONT = new Font("Monaco", Font.PLAIN, 20);
  private MainPanel mainPanel;
  private JPanel centerPanel;
  private JLabel nameLabel;
  private JTextField nameTF;
  private JButton createProject;
  private JButton backButton;


  public CreateProjectPanel(MainPanel mainPanel) {
    super(new GridBagLayout());
    this.mainPanel = mainPanel;
    add(centerPanel());

    createProject = new JButton("Create Project");
    createProject.setName("createProjectButton");
    backButton = new JButton("Back");
    backButton.setName("backButton");

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(createProject,gbc);
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.gridx = 0;
    gbc2.gridy = 2;
    add(backButton,gbc2);

  }

  public JPanel centerPanel() {

    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));

    nameLabel = new JLabel("Project Name");
    nameLabel.setFont(MONACO_FONT);
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    nameTF = new JTextField(15);

    centerPanel.add(nameLabel);
    centerPanel.add(nameTF);

    centerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    return centerPanel;
  }

  public void addActionListener(ActionListener actionListener) {
    createProject.addActionListener(actionListener);
    backButton.addActionListener(actionListener);

  }

  public String getNameText() {
    return nameTF.getText();
  }

}
