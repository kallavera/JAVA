import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestSoloNumeros extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5322473518422031770L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TestSoloNumeros frame = new TestSoloNumeros();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TestSoloNumeros()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 186, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("New label");
		label.setBounds(10, 82, 100, 20);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 41, 100, 30);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					double numero = Double.parseDouble(textField.getText());
					double resultado = numero * 2;
					String texto = Double.toString(resultado);
					label.setText(texto);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
	}
}
