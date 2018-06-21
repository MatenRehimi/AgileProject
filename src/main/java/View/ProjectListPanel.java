import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.ArrayList;

public class ProjectListPanel extends JPanel {

  private static final Font MONACO_FONT = new Font("Monaco", Font.PLAIN, 15);
  private MainPanel mainPanel;
  private DefaultListModel<Object> model;
  private JList<Object> list;
  private JTabbedPane tabbedPane;
  private JButton logOutButton;
  private JButton createProjectButton;
  private JButton viewProjectButton;
  private JButton deleteProjectButton;
  private JPanel bottomPanel;
  private int projectSize;
  private DefaultListCellRenderer renderer;


  public ProjectListPanel(MainPanel mainPanel) {

    super(new BorderLayout());
    this.mainPanel = mainPanel;
    model = new DefaultListModel<Object>();
    list = new JList<>(model);
    list.setName("projectList");
    tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Project List", list);
    renderer = (DefaultListCellRenderer) list.getCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    list.setFixedCellHeight(50);
    list.setFont(MONACO_FONT);
    add(tabbedPane, BorderLayout.NORTH);
    add(createSouthPanel(), BorderLayout.SOUTH);

  }

  public JPanel createSouthPanel() {
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
    add(bottomPanel, BorderLayout.SOUTH);
    logOutButton = new JButton("Logout");
    logOutButton.setName("logOutButton");
    bottomPanel.add(logOutButton);
    createProjectButton = new JButton("Create Project");
    createProjectButton.setName("createProjectButton");
    bottomPanel.add(createProjectButton);
    viewProjectButton = new JButton("View Project");
    viewProjectButton.setName("viewProjectButton");
    bottomPanel.add(viewProjectButton);
    deleteProjectButton = new JButton("Delete Project");
    deleteProjectButton.setName("deleteProjectButton");
    bottomPanel.add(deleteProjectButton);
    return bottomPanel;
  }

  public void addProjects(ArrayList<Project> projects) {
    for (int i=0; i < projects.size(); i++) {
      //System.out.println(projects.get(i));
      model.addElement(projects.get(i));
    }
    projectSize = projects.size();
  }

  public void addActionListeners(ActionListener actionListener) {
    logOutButton.addActionListener(actionListener);
    createProjectButton.addActionListener(actionListener);
    viewProjectButton.addActionListener(actionListener);
    deleteProjectButton.addActionListener(actionListener);
    list.addMouseListener(((MouseAdapter)actionListener));
    list.addListSelectionListener((ListSelectionListener)actionListener);
  }

  public void clearModel() {
    model.clear();
  }

  public int getProjectSize() {
    return projectSize;
  }

  public int getSelectedListIndex() {
    return list.getSelectedIndex();
  }

  public DefaultListModel getModel() {
    return model;
  }

  public void removeIndexFromDLM(int index) {
    model.removeElementAt(index);
  }

  public Object getElementAtIndex(int index) {
    if (index > -1) {
      return model.getElementAt(index);
    }else{
      return null;
    }
  }

}
