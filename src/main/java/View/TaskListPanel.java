import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.util.ArrayList;

public class TaskListPanel extends JPanel {

  private static final Font MONACO_FONT = new Font("Monaco", Font.PLAIN, 15);
  private MainPanel mainPanel;
  private DefaultListModel<Object> model;
  private JList<Object> list;
  private JTabbedPane tabbedPane;
  private DefaultListCellRenderer renderer;
  private JPanel southPanel;
  private JButton projectListButton;
  private JButton createTaskButton;
  private JButton deleteTaskButton;
  private int taskSize;

  public TaskListPanel(MainPanel mainPanel) {
    super(new BorderLayout());
    this.mainPanel = mainPanel;
    model = new DefaultListModel<Object>();
    list = new JList<>(model);
    tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Task List", list);
    // renderer = (DefaultListCellRenderer) list.getCellRenderer();
    // renderer.setHorizontalAlignment(SwingConstants.CENTER);
    list.setFixedCellHeight(50);
    list.setFont(MONACO_FONT);
    add(tabbedPane, BorderLayout.NORTH);
    add(southPanel(), BorderLayout.SOUTH);
  }

  public JPanel southPanel() {
    southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());
    add(southPanel, BorderLayout.SOUTH);
    projectListButton = new JButton("Project List");
    projectListButton.setName("projectListButton");
    southPanel.add(projectListButton);
    createTaskButton = new JButton("Create Task");
    createTaskButton.setName("createTaskButton");
    southPanel.add(createTaskButton);
    deleteTaskButton = new JButton("Delete Task");
    deleteTaskButton.setName("deleteTaskButton");
    southPanel.add(deleteTaskButton);
    return southPanel;
  }

  public void addTasks(ArrayList<Task> tasks) {
    for (int i=0; i < tasks.size(); i++) {
      model.addElement(tasks.get(i));
    }
    taskSize = tasks.size();
  }

  public void clearModel() {
    model.clear();
  }

  public void addActionListeners(ActionListener actionListener) {
    projectListButton.addActionListener(actionListener);
    createTaskButton.addActionListener(actionListener);
    deleteTaskButton.addActionListener(actionListener);
    list.addListSelectionListener((ListSelectionListener)actionListener);
  }

  public ArrayList<Object> getTasks() {
    ArrayList<Object> tasks = new ArrayList<Object>();
    for(int i = 0; i< model.getSize();i++){
      tasks.add(model.getElementAt(i));
    }
    return tasks;
  }

  public int getModelSize() {
    return model.getSize();
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

  public int getSelectedListIndex() {
    return list.getSelectedIndex();
  }


}
