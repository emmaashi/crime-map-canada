import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame implements ActionListener {
	



	ImageIcon logo = new ImageIcon("logo.png");
	JPanel background = new JPanel();
	JLabel greeting = new JLabel("Welcome to CrimeMap Canada!");
	JTextArea intro = new JTextArea("Look through graphs of collected data about"
			+ " reported crimes, in Canada or based around Ontario from\n\n2008-"
			+ "2012. There are pages focused on different types of crimes and pages"
			+ "about areas where they\n\nare recorded. Explore statistics of"
			+ " traffic offenses and homicides or the calculated violent crime "
			+ "severity of\n\nplaces around Canada, and see the final risk "
			+ "ratings of the areas in each graph.");
	
	JButton violentButton = new JButton("Violent Crime Severity");
	JButton propertyButton = new JButton("Property Crime Rates");
	JButton trafficButton = new JButton("Traffic Offenses");
	JButton incidentButton = new JButton("Incident-Based Crime Rates");
	JButton homicideButton = new JButton("Homicide Rate");
	
	/*PropertyFrame propertyFrame = new PropertyFrame();
	TrafficFrame trafficFrame = new TrafficFrame();
	IncidentFrame incidentFrame = new IncidentFrame();
	HomicideFrame homicideFrame = new HomicideFrame();*/
	
	public HomeFrame() {
		
		setTitle("CrimeMap Canada");
		setIconImage(logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

		
		ImageIcon image = new ImageIcon(logo.getImage());
		Image scaledImage = image.getImage();
		Image newImage = scaledImage.getScaledInstance(300,300,0);
		logo = new ImageIcon(newImage);
		JLabel greetimage = new JLabel(logo);
		greetimage.setBounds(40,20,300,300);
		background.add(greetimage);
		greeting.setBounds(350,20,1000,100);
		greeting.setFont(new Font("Arial",Font.BOLD,40));
		background.add(greeting);
		
		intro.setBounds(360,170,800,200);
		intro.setFont(new Font("Arial", Font.PLAIN, 16));
		intro.setEditable(false);
		background.add(intro);
		
		violentButton.setBounds(250,400,200,40);
		background.add(violentButton);
		violentButton.addActionListener(this);
		
		propertyButton.setBounds(550,400,200,40);
		background.add(propertyButton);
		propertyButton.addActionListener(this);
		
		trafficButton.setBounds(850,400,200,40);
		background.add(trafficButton);
		trafficButton.addActionListener(this);
		
		incidentButton.setBounds(400,500,200,40);
		background.add(incidentButton);
		incidentButton.addActionListener(this);
		
		homicideButton.setBounds(700,500,200,40);
		background.add(homicideButton);
		homicideButton.addActionListener(this);
		
		background.setBackground(Color.WHITE);
		background.setLayout(null);
		
		setContentPane(background);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == trafficButton) {
			
			this.setVisible(false);
			new TrafficOffenseFrame();
		}
		if (e.getSource() == violentButton ) {
			
			this.setVisible(false);
			new ViolentCrimeFrame();
		}
		 if (e.getSource() == propertyButton) {
				
				this.setVisible(false);
				new PropertyFrame();
			}
		if (e.getSource() == incidentButton) {
				
				this.setVisible(false);
				new IncidentBasedFrame();
			}
		
		if (e.getSource() == homicideButton) {
			
			this.setVisible(false);
			new HomicideFrame();
		}
		
		
		
		
	}
}