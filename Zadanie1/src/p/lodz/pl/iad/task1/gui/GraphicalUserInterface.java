package p.lodz.pl.iad.task1.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GraphicalUserInterface {

    public JFrame frame;

    /**
     * Create the application.
     */
    public GraphicalUserInterface() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmOpenDataFile = new JMenuItem("Open data file");
        mnNewMenu.add(mntmOpenDataFile);
        
        JMenuItem mntmTest = new JMenuItem("Close");
        mnNewMenu.add(mntmTest);
        frame.getContentPane().setLayout(null);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 79, 1, 1);
        frame.getContentPane().add(layeredPane);
        
        JButton btnNewButton = new JButton("Średnia arytmetyczna\n");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(0, 58, 171, 25);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnredniaGeometryczna = new JButton("Średnia geometryczna");
        btnredniaGeometryczna.setBounds(0, 93, 171, 25);
        frame.getContentPane().add(btnredniaGeometryczna);
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		if (e.isPopupTrigger()) {
        			showMenu(e);
        		}
        	}
        	public void mouseReleased(MouseEvent e) {
        		if (e.isPopupTrigger()) {
        			showMenu(e);
        		}
        	}
        	private void showMenu(MouseEvent e) {
        		popup.show(e.getComponent(), e.getX(), e.getY());
        	}
        });
    }
}
