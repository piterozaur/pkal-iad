package p.lodz.pl.iad.task1.gui;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/**
 * InitialWindow class initializes Swing components, is responsible for view interaction and event listeners.
 * 
 * @author Piotr Kluch 165436
 *
 */
public class InitialWindow extends JFrame {

	/**
	 * Variables, basically main buttons
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btn;

	/**
	 * Create the application.
	 */
	public InitialWindow() {
		setTitle("Inteligentna Analiza Danych - Zadanie 1"); //[Piotr Kluch, Andrzej Lisowski]
		initialize();
		addActionListeners();
	}

	/**
	 * Initialize the contents of the JFrame and some basics.
	 */
	private void initialize() {
				
		//Defaults
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(635,305);
		this.setLocation(200,200);
		getContentPane().setLayout(null);
		
		//Initial GUI starts here
		
		
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mntmFile = new JMenu("File");
		menuBar.add(mntmFile);
		
		JMenuItem quit = new JMenuItem("Quit");
		mntmFile.add(quit);
		
		//Inline listener for quit action.
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });
		
		JMenu about = new JMenu("Help");
		menuBar.add(about);

		//Inline listener for about action.
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add about
			}
        });
		
	}
	
	
	/**
	 * All action listeners for current view
	 */
	public void addActionListeners() {
		
		//theView.btnGenerateKeys.addActionListener(this);

	}
	
	
	
	/**
	 * All actions performed on button click, etc...
	 */
    public void actionPerformed(ActionEvent event){
    	
    	////Generate keys
        //if ( event.getSource() == theView.btnGenerateKeys ) {
            
        //	setKeys(generateKey_1(), generateKey_2(), generateKey_3());
        	
        //}
        
    }

	
	/**
	 * All getters and setters for current view components
	 */
	public void setKeys(String something) {
	
	}

	
}