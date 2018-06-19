import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

public class LoginPanel extends JPanel {

  private static final Font MONACO_FONT = new Font("Monaco", Font.PLAIN, 20);

  private JPanel centerPanel;
  private JButton submitButton;

  private JLabel loginLabel;
  private JLabel passwordLabel;
  private JTextField loginTF;
  private JPasswordField passwordTF;

  private MainPanel mainPanel;

  public LoginPanel(MainPanel mainPanel) {
    
    super(new GridBagLayout());
    this.mainPanel = mainPanel;
    add(centerPanel());

    submitButton = new JButton("submit");
    submitButton.setName("submitButton");
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(submitButton,gbc);
  }

  public JPanel centerPanel() {

    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));

    loginLabel = new JLabel("login");
    loginLabel.setFont(MONACO_FONT);
    loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    passwordLabel = new JLabel("password");
    passwordLabel.setFont(MONACO_FONT);
    passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    loginTF = new JTextField(15);
    loginTF.setFont(MONACO_FONT);
    passwordTF = new JPasswordField(15);
    passwordTF.setFont(MONACO_FONT);

    centerPanel.add(loginLabel);
    centerPanel.add(loginTF);
    centerPanel.add(passwordLabel);
    centerPanel.add(passwordTF);

    centerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    return centerPanel;
  }

  public void addActionListeners(ActionListener actionListener) {
    submitButton.addActionListener(actionListener);
  }


  public String getLoginText() {
    return loginTF.getText();
  }

  public String getPasswordText() {
    return String.valueOf(passwordTF.getPassword());
  }


}
