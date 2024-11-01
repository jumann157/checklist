import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class CheckList implements ActionListener{
	
	JFrame frame;
	JButton addButton;
	JProgressBar progressBar;
	
	JPanel mainPanel;
	JPanel progressPanel;
	JPanel buttonPanel;
	JPanel miniMainPanel;
	
	JCheckBox checkbox;
	JTextField textField;
	JScrollPane scroll;
	
	int currentValue = 0;
	ArrayList<JCheckBox> checkboxList;
	
		CheckList() {
		
		checkboxList = new ArrayList<>();
		
		frame = new JFrame("CheckList Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));
		frame.setResizable(false);
		frame.setSize(500,700);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBackground(new Color(211,211,211));
			
		progressBar = new JProgressBar(0,100); 
	 	progressBar.setValue(0);
	 	progressBar.setPreferredSize(new Dimension(300,30));
	 	progressBar.setFont(new Font("Serif", Font.PLAIN, 17));
		progressBar.setStringPainted(true);
		currentValue = progressBar.getValue();
		
		 
		addButton = new JButton();
		addButton.setText("Add CheckBox");
		addButton.setFocusable(false);
		addButton.addActionListener(new ActionListener() {
			/*
			 * action listener for the addButton.
			 * creates a panel, checkbox and textfield.
			 * adds checkbox and textfield to the panel
			 * adds the panel to the main panel
			 */
			public void actionPerformed(ActionEvent e)  {
				if(e.getSource()==addButton) {
					miniMainPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 0));
					miniMainPanel.setPreferredSize(new Dimension(500,50));
					miniMainPanel.setBackground(new Color(211,211,211));
					checkbox = new JCheckBox();
					textField = new JTextField();
					textField.setPreferredSize(new Dimension(370,40));
					checkbox.setPreferredSize(new Dimension(25,25));
					miniMainPanel.add(checkbox);
					miniMainPanel.add(textField);
					mainPanel.add(miniMainPanel);
					checkboxList.add(checkbox); //adds checkbox to the arraylist
					/*
					 * action listener for the checkbox whenever it is clicked.
					 */
					checkbox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)  {
							progressBar.setValue(currentProgress());
							
							if(isCompleted()) {
								progressBar.setString("Completed!");
								progressBar.setBackground(Color.green);
							} else {
								progressBar.setStringPainted(true);
								progressBar.setString(String.format("%d%%", progressBar.getValue()));
							}
						}
					});
					
					mainPanel.revalidate();
				}
			}
		}
		);
		

		buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		

		progressPanel = new JPanel();
		progressPanel.add(progressBar);
		
		scroll = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(progressPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

@Override
		public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

public int countSelectedCheckBox() {
	
	int checkedCount = 0;
	
	for(JCheckBox checkbox: checkboxList) {
		if(checkbox.isSelected()) {
			checkedCount++;
		}
	}
	return checkedCount;
}

public int currentProgress() {
	int progress = (int) ((1.0 * countSelectedCheckBox()/checkboxList.size()) * 100);
	return progress;
}

public boolean isCompleted() {
	if(progressBar.getValue() == 100) {
		return true;
	}
	
	return false;
}

}
