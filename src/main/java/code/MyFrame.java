package code;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField textField;
    private JTextField textField2;
     private JTextField textField3;
     private JButton button;
    //private JLabel label3; 
    
    public MyFrame() {
        JPanel panel = new JPanel(new GridLayout(4,1));
        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         textField = new JTextField(20);
         textField.setName("EnterString");
         
         textField2 = new JTextField(20);
         textField2.setName("EnterPattern"); 
         
         textField3 = new JTextField(20);
         textField3.setName("Result");
         
         textField.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
        if (textField.getText() == "" || textField2.getText() == "") {
             button.setVisible(false);
         }
         else
             button.setVisible(true);
       }
     });
        
        JLabel label1 = new JLabel("Enter string:", JLabel.LEFT);
        label1.setLabelFor(textField);   
        
        JLabel label2 = new JLabel("Enter pattern:", JLabel.LEFT);
        label1.setLabelFor(textField2);
        
        JLabel res = new JLabel("Result:", JLabel.LEFT);
        res.setLabelFor(textField3);
        
       // label3 = new JLabel("Result", JLabel.CENTER);
        
        button = new JButton("Execute");
        button.setName("Execute");
        
        JButton button2 = new JButton("Cancel");
        button2.setName("Cancel");
        
        button2.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           textField.setText("");
           textField2.setText("");
           textField3.setText("");
       }
     });
        //TODO action listener to this?
        
        button.addActionListener(this);
        
        panel.add(label1);
        panel.add(textField);
        
        panel.add(label2);
        panel.add(textField2);
        
        panel.add(button); 
        panel.add(button2);
        
        panel.add(res);
        panel.add(textField3);

        this.pack();
        this.setLocationRelativeTo(null);
        
        button.setVisible(false);
        
        DocumentListener listener = new DocumentListener() {
			
			public void removeUpdate(DocumentEvent event) {
                            if (textField.getText().equals("") || textField2.getText().equals("")) {
                            button.setVisible(false);
                           }
                        else
                            button.setVisible(true);
			}
			
			public void insertUpdate(DocumentEvent event) {
                         if (textField.getText().equals("") || textField2.getText().equals("")) {
                            button.setVisible(false);
                           }
                        else
                        button.setVisible(true);
			}
			
			public void changedUpdate(DocumentEvent event) {
                        if (textField.getText().equals("") || textField2.getText().equals("")) {
                            button.setVisible(false);
                           }
                        else
                        button.setVisible(true);
			}
		};
        textField.getDocument().addDocumentListener(listener);
        textField2.getDocument().addDocumentListener(listener);
    }

    public MyFrame(String name) {
        this();
        setName(name);
    }
    
    private List<String> seekPatterns(String patterns)
    {
    	List<String> found = new ArrayList<String>();
    	if (!patterns.contains(","))
    		found.add(patterns);
    	//else
    		//;
        return found;
    }

    @Override
    public void actionPerformed(ActionEvent e) {   
        String string = textField.getText();
        String pattern = textField2.getText();
        List<String> patterns = seekPatterns(pattern);
        List<Integer> result = AhoCorasick.find(string, patterns);
        String res = result.toString();
        //label3.setText(res);
        textField3.setText(res);
    }
}
