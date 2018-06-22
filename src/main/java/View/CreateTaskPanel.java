import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class CreateTaskPanel extends JPanel {

  private static final Font MONACO_FONT = new Font("Monaco", Font.PLAIN, 15);
  private MainPanel mainPanel;
  private JPanel centerPanel;
  private JButton submitTaskButton;
  private JButton taskListButton;
  private JLabel nameLabel;
  private JTextField nameTF;
  private JLabel effortLabel;
  private JTextField effortTF;
  private JLabel taskListLabel;
  private JComboBox<Integer> taskListCB;
  private JLabel prerequisiteTasksLabel;
  private JComboBox<Object> prerequisiteCB;

  public CreateTaskPanel(MainPanel mainPanel) {

    super(new GridBagLayout());
    this.mainPanel = mainPanel;

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(centerPanel(),gbc);

    submitTaskButton = new JButton("Submit");
    submitTaskButton.setName("submitTaskButton");
    taskListButton = new JButton("Task List");
    taskListButton.setName("taskListButton");

    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.gridx = 0;
    gbc2.gridy = 1;
    add(taskListButton,gbc2);
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridx = 1;
    gbc3.gridy = 1;
    add(submitTaskButton,gbc3);
  }

  public JPanel centerPanel() {

    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

    nameLabel = new JLabel("Task Name");
    nameLabel.setFont(MONACO_FONT);
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    nameTF = new JTextField(15);
    centerPanel.add(nameLabel);
    centerPanel.add(nameTF);

    effortLabel = new JLabel("Effort");
    effortLabel.setFont(MONACO_FONT);
    effortLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    effortTF = new JTextField(15);
    centerPanel.add(effortLabel);
    centerPanel.add(effortTF);

    taskListLabel = new JLabel("Task List");
    taskListLabel.setFont(MONACO_FONT);
    taskListLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(taskListLabel);

    taskListCB = new JComboBox<Integer>();
    taskListCB.setName("taskListCB");
    centerPanel.add(taskListCB);

    prerequisiteTasksLabel = new JLabel("Prerequisite Tasks");
    prerequisiteTasksLabel.setFont(MONACO_FONT);
    prerequisiteTasksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(prerequisiteTasksLabel);

    prerequisiteCB = new JComboBox<Object>();
    centerPanel.add(prerequisiteCB);

    return centerPanel;
  }

  public void addActionListeners(ActionListener actionListener) {
    submitTaskButton.addActionListener(actionListener);
    taskListButton.addActionListener(actionListener);
    taskListCB.addItemListener((ItemListener)actionListener);
  }

  public JComboBox<Integer> getTaskListCB() {
    return taskListCB;
  }

  public void clearTaskListCB() {
    taskListCB.removeAllItems();
  }
}
