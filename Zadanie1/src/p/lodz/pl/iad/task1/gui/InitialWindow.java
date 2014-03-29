package p.lodz.pl.iad.task1.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import p.lodz.pl.iad.task1.helpers.FileChooser;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.GridLayout;
 
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
	private JButton btnShowResultsInside;
	private JTable table;

	private DefaultTableModel model;

	public Map<Integer, Map<String, Map<String, Double>>> statistics;
	private JPanel panel_1;
	
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
		this.setSize(766,597);
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
		
		btnShowResultsInside = new JButton("Show results inside the table");
		btnShowResultsInside.setBounds(12, 255, 318, 25);
		getContentPane().add(btnShowResultsInside);
		
		model = new DefaultTableModel();
		
		panel_1 = new JPanel();
		panel_1.setBounds(22, 292, 707, 230);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		table = new JTable(model);
		table.setBounds(12, 292, 740, 243);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getContentPane().add(table);
		
		JScrollPane pane = new JScrollPane(table);
		panel_1.add(pane);
		
		
		
		
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
		btnShowResultsInside.addActionListener(this);

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
            
            statistics = statisticsHelper.getAllStatiscticsFromDataSets(dataMaps);
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
            
            //System.out.print(statistics.keySet().size());
    		
    	}
    	
    	if ( event.getSource() == btnShowResultsInside ) {
    		
    		// Create a couple of columns 
    		model.addColumn("iris-who"); 

    		// Append a row 
    		
    		
    		System.out.println(statistics.get(0).get("Iris-virginica").get("Median"));
    		
    			//TODO tableData size x and y must be set differently, hardcoded now here.
    			Object[] tableData = new Object[3];
    			
    			tableData[0] = "AC";
    			tableData[1] = "Median";
    			tableData[2] = "H mean";
    			
    			model.addRow( tableData );
    			
    			int index = 0;
    			for (String key : statistics.get(0).keySet())
    			{
    				
    				String ac = statistics.get(0).get(key).get("Assymetry coefficient").toString();
    				String median = statistics.get(0).get(key).get("Median").toString();
    				String hmean = statistics.get(0).get(key).get("Harmonic mean").toString();
    				
    				
    			    tableData[0] = ac;
    			    tableData[1] = median;
    			    tableData[2] = hmean;
    			    // and so forth
    			    index++;
    			    model.addRow( tableData );
    				
    			}
    			
    			//model.addRow( tableData );
    			
    			
    		
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