package p.lodz.pl.iad.task1.gui;

import javax.swing.*;

import p.lodz.pl.iad.task1.helpers.FileChooser;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.Color;
 
/**
 * InitialWindow class initializes Swing components, is responsible for view interaction and event listeners.
 * 
 * @author Piotr Kluch 165436
 *
 */
public class InitialWindow extends JFrame implements ActionListener {

	/**
	 * Variables, basically main buttons
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton btnLoadDataSet;
	public JTextField textFieldAttributesNumber;
	public JTextField textFieldClassesNumber;
	private JTextField textFieldFileName;

	private JComboBox comboBoxChooseAttribute;
	
	private JCheckBox chckbxAverageStatistics;
	private JCheckBox chckbxAssymetryStatistics;
	private JCheckBox chckbxStatisticalDispertionStatistics;
	private JCheckBox chckbxConcentrationDistributionStatistics;

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
		btnLoadDataSet = new JButton("Load data set from disk");
		btnLoadDataSet.setBounds(12, 12, 202, 25);
		getContentPane().add(btnLoadDataSet);
		
		textFieldAttributesNumber = new JTextField();
		textFieldAttributesNumber.setBounds(495, 77, 114, 19);
		getContentPane().add(textFieldAttributesNumber);
		textFieldAttributesNumber.setColumns(10);
		
		textFieldClassesNumber = new JTextField();
		textFieldClassesNumber.setBounds(495, 46, 114, 19);
		getContentPane().add(textFieldClassesNumber);
		textFieldClassesNumber.setColumns(10);
		
		textFieldFileName = new JTextField();
		textFieldFileName.setBounds(495, 15, 114, 19);
		getContentPane().add(textFieldFileName);
		textFieldFileName.setColumns(10);
		
		comboBoxChooseAttribute = new JComboBox();
		comboBoxChooseAttribute.setBounds(12, 49, 202, 25);
		getContentPane().add(comboBoxChooseAttribute);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 88, 264, 155);
		getContentPane().add(panel);
		
		chckbxAverageStatistics = new JCheckBox("Average Statistics");
		panel.add(chckbxAverageStatistics);
		
		chckbxAssymetryStatistics = new JCheckBox("Assymetry Statistics");
		panel.add(chckbxAssymetryStatistics);
		
		chckbxStatisticalDispertionStatistics = new JCheckBox("Statistical Dispertion Statistics");
		panel.add(chckbxStatisticalDispertionStatistics);
		
		chckbxConcentrationDistributionStatistics = new JCheckBox("Concentration Distribution Statistics");
		panel.add(chckbxConcentrationDistributionStatistics);
		
		
		
		
		
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
		
		btnLoadDataSet.addActionListener(this);

	}
	
	
	
	/**
	 * All actions performed on button click, etc...
	 */
    public void actionPerformed(ActionEvent event){
    	
    	if ( event.getSource() == btnLoadDataSet ) {
    	
    		//TODO Vars duplicates here
    		String path;
    		String separator = ",";
    		
    		FileChooser fc = new FileChooser();
    		path = fc.openDialog();
    	
            Map<Integer, Map<String, List<Double>>> dataMaps = FileHelper.readDataFromFile(path, separator);
            StatisticsHelper statisticsHelper = new StatisticsHelper();
            
            Map<Integer, Map<String, Map<String, Double>>> statistics = statisticsHelper.getAllStatiscticsFromDataSets(dataMaps);
            //String statisticsString = FileHelper.saveStatisticsFromDataSets(statistics, STATISTICS_PATH);
            
            //Set Info values
            String fileName = fc.currentFileName;
            textFieldFileName.setText( fileName );
            String classesNumber = Integer.toString(statistics.get(0).keySet().size());
            textFieldClassesNumber.setText( classesNumber );
            String attributesNumber = Integer.toString(statistics.keySet().size()-1); //TODO better way (addon method)
            textFieldAttributesNumber.setText( attributesNumber );
            
            //Add attributes to combo box
            for(Integer key : statistics.keySet()){
            	comboBoxChooseAttribute.addItem( "Dataset: " + key );
            }
            
            System.out.print(statistics.keySet().size());
    		
    	}
    	
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