package p.lodz.pl.iad.task1.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import p.lodz.pl.iad.task1.helpers.FileChooser;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;
import p.lodz.pl.iad.task1.helpers.TableColumnHider;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.table.DefaultTableModel;

import p.lodz.pl.iad.task1.helpers.FileChooser;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;

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
	private JButton btnAppendResultsInside;
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
		this.setSize(1047,710);

		this.setLocation(200,200);
		getContentPane().setLayout(null);
		
		//Initial GUI starts here
		btnLoadDataSet = new JButton("Load data set from disk");
		btnLoadDataSet.setBounds(12, 12, 218, 25);
		getContentPane().add(btnLoadDataSet);
		
		textFieldAttributesNumber = new JTextField();
		textFieldAttributesNumber.setBounds(919, 40, 114, 19);
		getContentPane().add(textFieldAttributesNumber);
		textFieldAttributesNumber.setColumns(10);
		
		textFieldClassesNumber = new JTextField();
		textFieldClassesNumber.setBounds(919, 65, 114, 19);
		getContentPane().add(textFieldClassesNumber);
		textFieldClassesNumber.setColumns(10);
		
		textFieldFileName = new JTextField();
		textFieldFileName.setBounds(919, 12, 114, 19);
		getContentPane().add(textFieldFileName);
		textFieldFileName.setColumns(10);
		
		comboBoxChooseAttribute = new JComboBox();
		comboBoxChooseAttribute.setBounds(12, 49, 218, 25);
		getContentPane().add(comboBoxChooseAttribute);

		
		JPanel panel = new JPanel();
		panel.setBounds(12, 96, 1021, 54);
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel);
						
								
		btnAppendResultsInside = new JButton("Append results inside the table");
		btnAppendResultsInside.setBounds(12, 169, 284, 25);
		getContentPane().add(btnAppendResultsInside);
		
		model = new DefaultTableModel();

		// Create a couple of columns
		model.addColumn("Class name");
		
		model.addColumn("Assymetry coefficient"); 
		model.addColumn("Median");
		model.addColumn("Quantile 3/4"); 
		model.addColumn("Range"); 
		model.addColumn("Harmonic mean"); 
		model.addColumn("Standard deviation"); 
		model.addColumn("Variance"); 
		model.addColumn("Geometric mean"); 
		model.addColumn("Variation coefficient"); 
		model.addColumn("Skewness coefficient"); 
		model.addColumn("Quantile 1/4"); 
		model.addColumn("Curtosis"); 
		model.addColumn("Arithmetic mean"); 
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 206, 1021, 442);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 1021, 442);
		panel_1.add(pane);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel checkBoxes = new JPanel();
		checkBoxes.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane pane2 = new JScrollPane(checkBoxes);
		checkBoxes.setLayout(new GridLayout(1, 0, 0, 0));
		panel.add(pane2);
		
		final TableColumnHider hider = new TableColumnHider(table);System.out.print("OK");
        for (int i = 0; i < model.getColumnCount(); i++) {
            JCheckBox checkBox = new JCheckBox(model.getColumnName(i));
            checkBox.setSelected(true);
            checkBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    JCheckBox cb = (JCheckBox) evt.getSource();
                    String columnName = cb.getText();

                    if (cb.isSelected()) {
                        hider.show(columnName);
                    } else {
                        hider.hide(columnName);
                    }
                }
            });
            checkBoxes.add(checkBox);
            System.out.print("OK");
        }

		
		JLabel lblDataFileName = new JLabel("Data file name:");
		lblDataFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFileName.setBounds(802, 12, 114, 15);
		getContentPane().add(lblDataFileName);
		
		JLabel lblNumberOfAttributes = new JLabel("Number of attributes:");
		lblNumberOfAttributes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfAttributes.setBounds(749, 40, 167, 19);
		getContentPane().add(lblNumberOfAttributes);
		
		JLabel lblNumberOfClasses = new JLabel("Number of classes:");
		lblNumberOfClasses.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfClasses.setBounds(759, 67, 157, 15);
		getContentPane().add(lblNumberOfClasses);
		
		JButton btnClearResults = new JButton("Clear results");
		btnClearResults.setBounds(308, 169, 157, 25);
		getContentPane().add(btnClearResults);
		btnClearResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 model.setRowCount(0);
			}
        });

		
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
		btnAppendResultsInside.addActionListener(this);

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
    	
    	if ( event.getSource() == btnAppendResultsInside ) {
    		
    		String dataSetValue = comboBoxChooseAttribute.getSelectedItem().toString();
    		
			Pattern p = Pattern.compile("(\\d+)");
			Matcher m = p.matcher(dataSetValue);
			while(m.find())
			{
				dataSetValue = m.group(0);
			}    		
    		System.out.print(dataSetValue);
    		
    		int dataSetValueKey = Integer.parseInt(dataSetValue);
    		
    		//System.out.println(statistics.get(0).get("Iris-virginica").get("Median"));
    		
			//TODO tableData size x and y must be set differently, hardcoded now here.
			Object[] tableData = new Object[14];
			
			model.addRow( tableData );
			
			for (String key : statistics.get(dataSetValueKey).keySet())
			{
				
			    tableData[0] = statistics.get(key);
			    
			    tableData[1] = statistics.get(dataSetValueKey).get(key).get("Assymetry coefficient").toString();
			    tableData[2] = statistics.get(dataSetValueKey).get(key).get("Median").toString();
			    tableData[3] = statistics.get(dataSetValueKey).get(key).get("Quantile 3/4").toString();
			    tableData[4] = statistics.get(dataSetValueKey).get(key).get("Range").toString();
			    tableData[5] = statistics.get(dataSetValueKey).get(key).get("Harmonic mean").toString();
			    tableData[6] = statistics.get(dataSetValueKey).get(key).get("Standard deviation").toString();
			    tableData[7] = statistics.get(dataSetValueKey).get(key).get("Variance").toString();
			    tableData[8] = statistics.get(dataSetValueKey).get(key).get("Geometric mean").toString();
			    tableData[9] = statistics.get(dataSetValueKey).get(key).get("Variation coefficient").toString();
			    tableData[10] = statistics.get(dataSetValueKey).get(key).get("Skewness coefficient").toString();
			    tableData[11] = statistics.get(dataSetValueKey).get(key).get("Quantile 1/4").toString();
			    tableData[12] = statistics.get(dataSetValueKey).get(key).get("Curtosis").toString();
			    tableData[13] = statistics.get(dataSetValueKey).get(key).get("Arithmetic mean").toString();

			    model.addRow( tableData );
				
			}			
			
    		
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