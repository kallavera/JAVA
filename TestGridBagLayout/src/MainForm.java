import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class MainForm extends JFrame
{
	private boolean shouldFill = false;
	private boolean shouldWeightX = true;
	
	public MainForm()
	{
		setSize(800, 600);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		componentes();
	}
	
	public static void main(String[] args)
	{
		new MainForm();
	}
	
	private void componentes()
	{
		JButton button;
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
		{
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		button = new JButton("Button 1");
		if (shouldWeightX)
		{
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(button, c);
		
		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(button, c);
		
		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(button, c);
		
		button = new JButton("Long-Named Button 4");
		button.setPreferredSize(new Dimension(500,400));
//		c.fill = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		add(button, c);
		
		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 2; // third row
		add(button, c);
	}
}
