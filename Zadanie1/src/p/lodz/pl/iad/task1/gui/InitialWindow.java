package p.lodz.pl.iad.task1.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import p.lodz.pl.iad.task1.helpers.FileChooser;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.PlotHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;
import p.lodz.pl.iad.task1.helpers.TableColumnHider;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.GridLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.FlowLayout;

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
	
	private static final String USER_DIR = System.getProperty("user.dir");
	
    private static final String SAMPLE_PATH = USER_DIR + "/data/sample.dat";
    private static final String SEPARATOR = ",";
    private static final String STATISTICS_PATH = USER_DIR + "/data/stats.txt";
    private static final String HISTOGRAM_PATH = USER_DIR + "/data/graphs";
	
    public JFrame frame;
    
	public JButton btnLoadDataSet;
	public JTextField textFieldAttributesNumber;
	public JTextField textFieldClassesNumber;
	public JTextField textFieldFileName;

	private JComboBox comboBoxChooseAttribute;
	private JButton btnAppendResultsInside;
	private JTable table;

	private DefaultTableModel model;

	public Map<Integer, Map<String, Map<String, Double>>> statistics;
	private JPanel panel_1;
	private JTextField textField;
	private JTextField textFieldSavePath;

	private JTextField textFieldAttrSeparator;
	private JTextField textFieldHistogramDir;


	/**
	 * Create the application.
	 */
	public InitialWindow() {
		setTitle("Inteligentna Analiza Danych - Zadanie 1"); //[Piotr Kluch, Andrzej Lisowski]
		initialize();
		addActionListeners();
		
		//Load data at start
		loadData(false);
	}

	/**
	 * Initialize the contents of the JFrame and some basics.
	 */
	private void initialize() {
				
		//Defaults
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1047,759);

		this.setLocation(200,200);
		getContentPane().setLayout(null);
		
		//Initial GUI starts here
		btnLoadDataSet = new JButton("Load data set from disk");
		btnLoadDataSet.setBounds(12, 12, 238, 39);
		getContentPane().add(btnLoadDataSet);
		
		textFieldAttributesNumber = new JTextField();
		textFieldAttributesNumber.setBounds(900, 37, 114, 19);
		getContentPane().add(textFieldAttributesNumber);
		textFieldAttributesNumber.setColumns(10);
		
		textFieldClassesNumber = new JTextField();
		textFieldClassesNumber.setBounds(900, 62, 114, 19);
		getContentPane().add(textFieldClassesNumber);
		textFieldClassesNumber.setColumns(10);
		
		textFieldFileName = new JTextField();
		textFieldFileName.setBounds(900, 12, 114, 19);
		getContentPane().add(textFieldFileName);
		textFieldFileName.setColumns(10);
		
		comboBoxChooseAttribute = new JComboBox();
		comboBoxChooseAttribute.setBounds(12, 62, 238, 22);
		getContentPane().add(comboBoxChooseAttribute);

		
		JPanel panel = new JPanel();
		panel.setBounds(12, 145, 1021, 54);
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel);
						
								
		btnAppendResultsInside = new JButton("Append results inside the table");
		btnAppendResultsInside.setBounds(12, 218, 284, 25);
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
		
		model.addColumn("Filename");
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel checkBoxes = new JPanel();
		checkBoxes.setBackground(Color.LIGHT_GRAY);
		
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(12, 255, 1021, 442);
		getContentPane().add(pane);
				
		panel_1 = new JPanel();
		pane.setColumnHeaderView(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane pane2 = new JScrollPane(checkBoxes);
		checkBoxes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(pane2);
		
		final TableColumnHider hider = new TableColumnHider(table);
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
        }

		
		JLabel lblDataFileName = new JLabel("Data file name:");
		lblDataFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataFileName.setBounds(730, 14, 168, 15);
		getContentPane().add(lblDataFileName);
		
		JLabel lblNumberOfAttributes = new JLabel("Number of attributes:");
		lblNumberOfAttributes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfAttributes.setBounds(730, 37, 167, 19);
		getContentPane().add(lblNumberOfAttributes);
		
		JLabel lblNumberOfClasses = new JLabel("Number of classes:");
		lblNumberOfClasses.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfClasses.setBounds(740, 64, 157, 15);
		getContentPane().add(lblNumberOfClasses);
		
		JButton btnClearResults = new JButton("Clear results");
		btnClearResults.setBounds(308, 218, 157, 25);
		getContentPane().add(btnClearResults);
		btnClearResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 model.setRowCount(0);
			}
        });
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(662, 0, 371, 96);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setForeground(Color.GREEN);
		lblInfo.setBounds(12, 12, 70, 15);
		panel_2.add(lblInfo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.LIGHT_GRAY);
		panel_3.setLayout(null);
		panel_3.setBackground(UIManager.getColor("Button.darkShadow"));
		panel_3.setBounds(262, 0, 405, 96);
		getContentPane().add(panel_3);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setForeground(UIManager.getColor("Button.select"));
		lblSettings.setBounds(12, 12, 70, 15);
		panel_3.add(lblSettings);
		
		JLabel lblOutputFileName = new JLabel("Output file path:");
		lblOutputFileName.setForeground(UIManager.getColor("RadioButtonMenuItem.background"));
		lblOutputFileName.setBounds(32, 44, 167, 19);
		panel_3.add(lblOutputFileName);
		lblOutputFileName.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldAttrSeparator = new JTextField();
		textFieldAttrSeparator.setBounds(273, 12, 114, 19);
		panel_3.add(textFieldAttrSeparator);
		textFieldAttrSeparator.setText(",");
		textFieldAttrSeparator.setColumns(10);
		
		JLabel lblAttributesSeparator = new JLabel("Attributes separator:");
		lblAttributesSeparator.setBounds(100, 12, 167, 15);
		panel_3.add(lblAttributesSeparator);
		lblAttributesSeparator.setForeground(UIManager.getColor("RadioButtonMenuItem.background"));
		lblAttributesSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textFieldSavePath = new JTextField();
		textFieldSavePath.setBounds(158, 44, 229, 19);
		panel_3.add(textFieldSavePath);
		textFieldSavePath.setColumns(10);
		textFieldSavePath.setText(STATISTICS_PATH);
		
		textFieldHistogramDir = new JTextField();
		textFieldHistogramDir.setText(HISTOGRAM_PATH);
		textFieldHistogramDir.setColumns(10);
		textFieldHistogramDir.setBounds(158, 65, 229, 19);
		panel_3.add(textFieldHistogramDir);
		
		JLabel lblHistogramDirPath = new JLabel("Histogram dir path:");
		lblHistogramDirPath.setHorizontalAlignment(SwingConstants.LEFT);
		lblHistogramDirPath.setForeground(UIManager.getColor("Button.background"));
		lblHistogramDirPath.setBounds(12, 65, 167, 19);
		panel_3.add(lblHistogramDirPath);

		
		JLabel lblSelectFilter = new JLabel("Select / filter out appropriate attributes:");
		lblSelectFilter.setBounds(12, 118, 414, 15);
		getContentPane().add(lblSelectFilter);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(0, 106, 1045, 103);
		getContentPane().add(panel_4);
		
		JButton btnSaveCharacteristics = new JButton("Save characteristics");
		btnSaveCharacteristics.setBounds(820, 218, 213, 25);
		getContentPane().add(btnSaveCharacteristics);
		btnSaveCharacteristics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        String statisticsString = FileHelper.saveStatisticsFromDataSets(statistics, textFieldSavePath.getText());
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
		
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		
		JMenuItem about = new JMenuItem("About");
		help.add(about);
		
		//Inline listener for about action.
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						frame, 
						"Inteligentna Analiza Danych\nZadanie 1 \n\nVersion: v. 0.0.1\n\nAuthors:\nAndrzej Lisowski 171131\nPiotr Kluch 165436\n\n", 
						"About", 
						JOptionPane.INFORMATION_MESSAGE
						);
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
    	
    		loadData(true);
    		
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
			Object[] tableData = new Object[15];
			
			model.addRow( tableData );
			
			String className = null;
			String fileNameCell = textFieldFileName.getText().toString();
			
			for (String key : statistics.get(dataSetValueKey).keySet())
			{
				//System.out.print(statistics.get(dataSetValueKey).keySet());
				
				//Class name
				tableData[0] = key;
			    
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
			    
			    tableData[14] = fileNameCell;

			    model.addRow( tableData );
				
			}			
			
    		
    	}
    	

    	////Generate keys
        //if ( event.getSource() == theView.btnGenerateKeys ) {
            
        //	setKeys(generateKey_1(), generateKey_2(), generateKey_3());
        	
        //}

    }

	public void loadData(boolean dialogAction) {
		
		FileChooser fc = new FileChooser();
		String path;
		String separator;
		String fileName = null;
		
		if ( dialogAction )  {
		
			//TODO Vars duplicates here
			separator = textFieldAttrSeparator.getText();
			
			path = fc.openDialog();
			fileName = fc.currentFileName;
	
		} else {
			
			path = SAMPLE_PATH;
			separator = textFieldAttrSeparator.getText();
			String table[] = path.split("/");
			int tSize = table.length;
			fileName = table[tSize-1];
			
		}
		
		Map<Integer, Map<String, List<Double>>> dataMaps = null;
		statistics = null;
		try {
		    //TODO:
		    boolean isHypothesisTestingActive = true;
	        dataMaps = FileHelper.readDataFromFile(path, separator, isHypothesisTestingActive);
	        StatisticsHelper statisticsHelper = new StatisticsHelper();
	        
	        statistics = statisticsHelper.getAllStatiscticsFromDataSets(dataMaps, isHypothesisTestingActive);
//	        String statisticsString = FileHelper.saveStatisticsFromDataSets(statistics, STATISTICS_PATH);
        
		} catch ( Exception e ) {
			
			JOptionPane.showMessageDialog(this,
				    "It looks like the attribute separator is not correct. Please provide appropriate separator in the \"Settings\" box.",
				    "Data parse error",
				    JOptionPane.ERROR_MESSAGE);
			
		}
        
		//Set Info values
        textFieldFileName.setText( fileName );
        String classesNumber = Integer.toString(statistics.get(0).keySet().size());
        textFieldClassesNumber.setText( classesNumber );
        String attributesNumber = Integer.toString(statistics.keySet().size()); //Now counts with all and subclasses val
        textFieldAttributesNumber.setText( attributesNumber );
        
        //Remove old
        comboBoxChooseAttribute.removeAllItems();
        
        //Add attributes to combo box
        for(Integer key : statistics.keySet()){
        	comboBoxChooseAttribute.addItem( fileName + " - Dataset: " + key );
        }
        
        //System.out.print(statistics.keySet().size());
        
        String histogramPath = HISTOGRAM_PATH; //textFieldHistogramDir.getText();
        
        //Draws histograms
        for(int key : dataMaps.keySet()){
            PlotHelper.drawHistogram(String.valueOf(key+1), dataMaps.get(key), histogramPath);
        }
        
		
	}
    
	/**
	 * All getters and setters for current view components
	 */
	public void setKeys(String something) {
	
	}
}