package p6;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class P6_MainApp_MiniGUI {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("P6_Exception_Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(getActionButton());
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	private static Component getActionButton() {
		JButton btn  = new JButton();
		btn.setText("DO IT!");
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {	
				try {
					ComplicatedLogic.doThatAgain();
					ComplicatedLogic.doThisLastTime();
					ComplicatedLogic.doThis();
					ComplicatedLogic.doThisAgain();
					ComplicatedLogic.doThatLastTime();
					ComplicatedLogic.doThat();
					
					btn.setBackground(getRndColor());
				} catch(Exception e) {
					showErrorMessage(btn);
				}				
			}
		});
		
		return btn;
	}
	
	private static void showErrorMessage(Component parent) {
		JOptionPane.showMessageDialog(parent,
			    "Aw, snap! Something went wrong!", 
			    "Inane custom dialog",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private static Color getRndColor() {
		return new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
	}
}
