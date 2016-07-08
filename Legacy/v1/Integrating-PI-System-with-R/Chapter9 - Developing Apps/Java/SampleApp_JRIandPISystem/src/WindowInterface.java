
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class WindowInterface {
	private Application MyApp;
	private JComboBox cbPIServer;
	private JLabel lbPIServer;
	private JButton btnConnect;
	private JComboBox cbRFunction;
	private JLabel lbRFunction;
	private JLabel lbNumTags;
	private JComboBox cbNumTags;
	private JLabel lbParam1;
	private JComboBox cbParam1;
	private JLabel lbParam2;
	private JComboBox cbParam2;
	private JLabel lbParam3;
	private JComboBox cbParam3;
	private JLabel lbParam4;
	private JComboBox cbParam4;
    private JLabel lbTag1;
    private JLabel lbTag2;
    private JLabel lbTag3;
    private JLabel lbTag4;
    private JLabel lbTag5;
    private JLabel lbStartTime;
    private JLabel lbEndTime;
    private JLabel lbInterval;
    private JLabel lbMode;
    private JTextField tfTag1;
    private JTextField tfTag2;
    private JTextField tfTag3;
    private JTextField tfTag4;
    private JTextField tfTag5;
    private JTextField tfStartTime;
    private JTextField tfEndTime;
    private JTextField tfInterval;
    private JRadioButton rbRecorded;
    private JRadioButton rbInterpolated;
    private JCheckBox cbShowAdvSet;
    private JPanel RFunctionPanel;
    private JFrame aWindow;
    private String SavedParam1 = "";
    private String SavedParam2 = "";
    private String SavedParam3 = "";
    private String SavedParam4 = "";
    private String SavedTagName1;
    private String SavedTagName2;
    private String SavedTagName3;
    private String SavedTagName4;
    private String SavedTagName5;   
    private String SavedStarttime;
    private String SavedEndtime;
    private String SavedInterval;
    private String SavedMode;
    private JLabel lbDataAccess;
    private JRadioButton rbPIJDBC;
    private JRadioButton rbPIWS;
    private JRadioButton rbPIWA;
   
    
    
	public WindowInterface()
	{
		MyApp = new Application();
	};
	
	public void createWindow(){
        aWindow = new JFrame("This is the Window Title");
        Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
        Dimension wndSize = theKit.getScreenSize();                        // Get screen size

        // Set the position to screen center & size to half screen size
        aWindow.setSize(600, 800);                // Set window size
        aWindow.setLocationRelativeTo(null);                               // Center window
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        
        
        
        
        
        Box PISystemInfoBox = Box.createVerticalBox();
        lbPIServer = new JLabel("Please select a PI Server to connect");
        cbPIServer = new JComboBox();
        cbPIServer.addItem("MARC-PI2014");
        cbPIServer.addItem("MARC");
        btnConnect = new JButton("Connect");
        ActionListener ConnectActionListener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			MyApp.Connect(cbPIServer.getSelectedItem().toString());
    			if (MyApp.isConnected==1)
    			{
    				JOptionPane.showMessageDialog(aWindow,"Connected");
    			}
    			else
    			{
    				
    				JOptionPane.showMessageDialog(aWindow,"Not Connected");
    			}
    				
    				
    		}
    	};
    	
    	
        ActionListener rbPIDataAccessMethodChangedActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			PIDataAccessMethodChanged();
     		}
        };
        
        btnConnect.addActionListener(ConnectActionListener);
    	
        PISystemInfoBox.add(lbPIServer);
        PISystemInfoBox.add(cbPIServer);
        PISystemInfoBox.add(btnConnect);
        
        Box DataAccessBox = Box.createVerticalBox();
        lbDataAccess = new JLabel("Chose PI Data Access");
        rbPIJDBC= new JRadioButton("PI JDBC");
        rbPIWS= new JRadioButton("PI Web Services");
        rbPIWA= new JRadioButton("PI Web API");
        rbPIJDBC.addActionListener(rbPIDataAccessMethodChangedActionListener);
        rbPIWS.addActionListener(rbPIDataAccessMethodChangedActionListener);
        rbPIWA.addActionListener(rbPIDataAccessMethodChangedActionListener);
        DataAccessBox.add(lbDataAccess);
        DataAccessBox.add(rbPIJDBC);
        DataAccessBox.add(rbPIWS);
        DataAccessBox.add(rbPIWA);
        ButtonGroup radioGroupDA = new ButtonGroup();
        radioGroupDA.add(rbPIJDBC);
        radioGroupDA.add(rbPIWS);
        radioGroupDA.add(rbPIWA);
        
        

        
        
        JPanel PISystemInfoPanel = new JPanel(new BorderLayout());
        PISystemInfoPanel.setBorder(new TitledBorder(new EtchedBorder(),"PI System information"));                     // Border title
        PISystemInfoPanel.add(PISystemInfoBox, BorderLayout.WEST);
        PISystemInfoPanel.add(DataAccessBox, BorderLayout.EAST);
        
        Border edge = BorderFactory.createRaisedBevelBorder(); // Button border
        PISystemInfoBox.setBorder(edge);
        PISystemInfoBox.setSize(600,600);
        

       
        Box RInfoBox = Box.createVerticalBox();
        lbRFunction = new JLabel("Please select a R function");
        cbRFunction = new JComboBox();
        cbRFunction.addItem("PI Histogram");
        cbRFunction.addItem("PI Density Plot");
        cbRFunction.addItem("PI Density Compare");
        cbRFunction.addItem("PI Box Plot");
        cbRFunction.addItem("PI Regular Correlation");
        cbRFunction.addItem("PI Smooth Scatter");
        cbRFunction.addItem("PI Multi-Correlation");
        
        ActionListener cbShowAdvActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			UpdateShowAdvSet();
     		}
        };
        
        ActionListener cbNumTagsActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			System.out.println("NumTagAction");
     			NumTagsChanged();
     		}
        };
        
        ActionListener btnDefaultActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
                NonCorrelationConf();
                tfStartTime.setText("1-Oct-2012");
                tfEndTime.setText("1-Nov-2012");
                tfInterval.setText("1h");
                lbParam1.setText("Breaks");
                lbParam2.setText("Density");
                lbParam3.setText("Colour");                
     		}
        };
       
        ActionListener rbInterpolatedActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			SetInterpolatedMode();
     		}
        };
        
        ActionListener rbRecordedActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			SetRecordedMode();
     		}
        };
        
        
        ActionListener cbActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			UpdateSettings();
     		}
        };
        cbRFunction.addActionListener(cbActionListener);

  
 
        
        
        
        lbNumTags = new JLabel("Number of tags:");
        cbNumTags = new JComboBox();
        cbNumTags.addActionListener(cbNumTagsActionListener);
        RInfoBox.add(Box.createVerticalStrut(30));
        RInfoBox.add(lbRFunction);
        RInfoBox.add(Box.createVerticalStrut(30));
        RInfoBox.add(cbRFunction);
        RInfoBox.add(Box.createVerticalStrut(30));
        RInfoBox.add(lbNumTags);
        RInfoBox.add(Box.createVerticalStrut(30));
        RInfoBox.add(cbNumTags);
        RInfoBox.add(Box.createVerticalStrut(30));
        JPanel RInfoPanel = new JPanel(new BorderLayout());
        RInfoPanel.setBorder(new TitledBorder(new EtchedBorder(),"R Information"));
        RInfoPanel.add(RInfoBox, BorderLayout.CENTER);
        
        Box RFunctionBox = Box.createVerticalBox();
        Dimension size = new Dimension(20,5);
        lbParam1 = new JLabel("Option 1");
        cbParam1 = new JComboBox();
        lbParam2 = new JLabel("Option 2");
        cbParam2 = new JComboBox();
        lbParam3 = new JLabel("Option 3");
        cbParam3 = new JComboBox();
        lbParam4 = new JLabel("Option 4");
        cbParam4 = new JComboBox();
   
        

        
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(lbParam1);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(cbParam1);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(lbParam2);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(cbParam2);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(lbParam3);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(cbParam3);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(lbParam4);
        RFunctionBox.add(Box.createVerticalStrut(20));
        RFunctionBox.add(cbParam4);
        RFunctionBox.add(Box.createVerticalStrut(20));
  
        RFunctionPanel = new JPanel(new BorderLayout());
        RFunctionPanel.setBorder(new TitledBorder(new EtchedBorder(),"R Function"));
        RFunctionPanel.add(RFunctionBox, BorderLayout.CENTER);
        
        
        
        
        
        Box PITagsBox = Box.createVerticalBox();
        PITagsBox.add(Box.createVerticalStrut(20));
        lbTag1 = new JLabel("PI Tag 1");
        lbTag2 = new JLabel("PI Tag 2");
        lbTag3 = new JLabel("PI Tag 3");
        lbTag4 = new JLabel("PI Tag 4");
        lbTag5 = new JLabel("PI Tag 5");
        lbStartTime = new JLabel("Start Time");
        lbEndTime = new JLabel("End Time");
        lbInterval = new JLabel("Interval");
        lbMode = new JLabel("Mode");
        
        
        tfTag1 = new  JTextField();
        tfTag2 = new  JTextField();
        tfTag3 = new  JTextField();
        tfTag4 = new  JTextField();
        tfTag5 = new  JTextField();
        tfStartTime = new  JTextField();
        tfEndTime = new  JTextField();
        tfInterval = new  JTextField();
        
        PITagsBox.add(lbTag1);
        PITagsBox.add(tfTag1);
        PITagsBox.add(lbTag2);
        PITagsBox.add(tfTag2);
        PITagsBox.add(lbTag3);
        PITagsBox.add(tfTag3);
        PITagsBox.add(lbTag4);
        PITagsBox.add(tfTag4);
        PITagsBox.add(lbTag5);
        PITagsBox.add(tfTag5);
        
        
        
        
        PITagsBox.add(lbStartTime);
        PITagsBox.add(tfStartTime);
        
        PITagsBox.add(lbEndTime);
        PITagsBox.add(tfEndTime);
        
        PITagsBox.add(lbInterval);
        PITagsBox.add(tfInterval);
        PITagsBox.add(lbMode);
        rbRecorded = new JRadioButton("Recorded");
        rbRecorded.addActionListener(rbRecordedActionListener);
        rbInterpolated = new JRadioButton("Interpolated");
        rbInterpolated.addActionListener(rbInterpolatedActionListener);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(rbRecorded);
        radioGroup.add(rbInterpolated);
        PITagsBox.add(rbRecorded); // Add to Box
        PITagsBox.add(rbInterpolated);
        cbShowAdvSet = new JCheckBox("Show advanced settings");
        cbShowAdvSet.addActionListener(cbShowAdvActionListener);
        PITagsBox.add(Box.createVerticalStrut(20));
        PITagsBox.add(cbShowAdvSet);
        JButton btnDefault = new JButton("Use default settings");
        btnDefault.addActionListener(btnDefaultActionListener);
        JButton btnGetData = new JButton("Get PI Data!");
        PITagsBox.add(Box.createVerticalStrut(20));
        PITagsBox.add(btnDefault);
        PITagsBox.add(Box.createVerticalStrut(20));
        PITagsBox.add(btnGetData);
        
        
        ActionListener btnGetDataActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			GetData();
     		}
        };
        btnGetData.addActionListener(btnGetDataActionListener);
        
        
        JPanel PITagsPanel = new JPanel(new BorderLayout());
        PITagsPanel.setBorder(new TitledBorder(new EtchedBorder(),"PI Tags Information"));
        PITagsPanel.add(PITagsBox, BorderLayout.CENTER);
        
        Box GenerateGraphicsBox =Box.createVerticalBox();
        JButton btnGenGraphic = new JButton("Generate Graphic!");
        JLabel lbRFunctionStatus = new JLabel("Using R function: no function selected");
        GenerateGraphicsBox.add(Box.createVerticalStrut(20));
        GenerateGraphicsBox.add(lbRFunctionStatus);
        GenerateGraphicsBox.add(Box.createVerticalStrut(20));
        GenerateGraphicsBox.add(btnGenGraphic);
        GenerateGraphicsBox.add(Box.createVerticalStrut(20));
        JPanel GenerateGraphicsPanel = new JPanel(new BorderLayout());
        GenerateGraphicsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Generate graphic"));
        GenerateGraphicsPanel.add(GenerateGraphicsBox, BorderLayout.CENTER);
        
        ActionListener btnGenerateGraphicsActionListener = new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			GenerateGraphics();
     		}
        };
        btnGenGraphic.addActionListener(btnGenerateGraphicsActionListener);
        
        
        JPanel upperPanel = new JPanel();
        Box bottomPanel = Box.createHorizontalBox();
        Box leftPanel = Box.createVerticalBox();
        Box rightPanel = Box.createVerticalBox();
        
        upperPanel.add(PISystemInfoPanel); 
        leftPanel.add(RInfoPanel);
        leftPanel.add(RFunctionPanel);

        rightPanel.add(PITagsPanel);
        rightPanel.add(GenerateGraphicsPanel);
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
       
        
        
        // Add top and bottom panel to content pane
        Container content = aWindow.getContentPane();                      // Get content pane
        
        
        
        // Vertical for content pane
        content.setLayout(new BorderLayout());
        content.add(upperPanel, BorderLayout.NORTH);
        content.add(bottomPanel , BorderLayout.SOUTH);
   
        cbRFunction.setSelectedIndex(0);
        UpdateSettings();

        //aWindow.pack();                                                    // Size for components
        aWindow.setVisible(true);                                          // Display the window
      }
	
    private boolean ChangedStrings()
    {
        boolean Changed = false;
        
        if (SavedParam1 != cbParam1.getSelectedItem().toString());
        {
            Changed = true;
        }
        if (SavedParam2 != cbParam2.getSelectedItem().toString());
        {
            Changed = true;
        }
        if (SavedParam3 != cbParam3.getSelectedItem().toString());
        {
            Changed = true;
        }
        if (SavedParam4 != cbParam4.getSelectedItem().toString());
        {
            Changed = true;
        }
        return Changed;
    }
	
    
    private void PIDataAccessMethodChanged()
    {
        String DataMethod;
        if (rbPIJDBC.isSelected())
        {
            DataMethod = "PIJDBC";
        }
        else if (rbPIWA.isSelected())
        {
            DataMethod = "PIWA";
        }
        else
        {
            DataMethod = "PIWS";
        }
        MyApp.SetDataAccessMethod(DataMethod);
    
        MyApp.Inicialize(); 
        JOptionPane.showMessageDialog(aWindow,DataMethod);
        
    }
    
	private void GenerateGraphics()
	{

		if ((ChangedStrings()== false) && (MyApp.ProgramCurrentStep == 3) && CheckSavedStrings() == true)
        {
            JOptionPane.showMessageDialog(aWindow,"Please modify a configuration.");
        }
        else
        {

            if ((MyApp.ProgramCurrentStep >= 2) && (CheckSavedStrings() == true))
            {
                ProcessParameters();
                SaveParamStrings();
                MyApp.ProgramCurrentStep = 3;
                MyApp.GenerateGhaphic(param1, param2, param3, param4);
                if (MyApp.GetRFunction() == "PI Regular Correlation")
                {
                	JOptionPane.showMessageDialog(aWindow,"Correlation coefficient = " + MyApp.ValueToBeShown);
                }
            }
            else
            {
            	JOptionPane.showMessageDialog(aWindow,"Please click first on \"Get Data\" button");
            }
        }
		
	}
	


	
    private void GetData()
    {
        int result = -1;
        if (MyApp.GetNumTags() == 1)
        {
            tfTag2.setText("");
            tfTag3.setText("");
            tfTag4.setText("");
            tfTag5.setText("");

        }

        if (MyApp.GetNumTags() == 2)
        {
            tfTag3.setText("");
            tfTag4.setText("");
            tfTag5.setText("");
        }

        if (MyApp.GetNumTags() == 3)
        {
            tfTag4.setText("");
            tfTag5.setText("");
        }

        if (MyApp.GetNumTags() == 4)
        {
            tfTag5.setText("");
        }

        
        if (MyApp.isConnected==1)
        {
        result = MyApp.GetPIData(tfTag1.getText(), tfTag2.getText(), tfTag3.getText(), tfTag4.getText(), tfTag5.getText(),tfStartTime.getText(), tfEndTime.getText(), tfInterval.getText());
        
        if (result == 0)
        {
            SaveStrings();
            MyApp.ProgramCurrentStep = 2;
        	JOptionPane.showMessageDialog(aWindow,GenerateStringValuesOnMemory());
            
        }
        if (result == 1)
        {
        	JOptionPane.showMessageDialog(aWindow,"Invalid Tag Name: Could find tag on the Server.");
          
        }
        if (result == 2)
        {
        	JOptionPane.showMessageDialog(aWindow,"Invalid Start Time and End Time.");       
        }
        }
        else
        {
        	JOptionPane.showMessageDialog(aWindow,"Please connect first");     
        }
    }
    
    
    private String param1 = null;
    private String param2 = null;
    private String param3 = null;
    private String param4 = null;

    
    private void ProcessParameters()
    {
        param1 = cbParam1.getSelectedItem().toString();
        param2 = cbParam2.getSelectedItem().toString();
        param3 = cbParam3.getSelectedItem().toString();
        param4 = cbParam4.getSelectedItem().toString();
        if (MyApp.GetRFunction() == "PI Histogram")
        {
            try
            {
                int a = Integer.parseInt(cbParam1.getSelectedItem().toString());
                param1 = cbParam1.getSelectedItem().toString();

            }
            catch (Exception e)
            {

                param1 = "5";
            }

            try
            {
                int a = Integer.parseInt(cbParam2.getSelectedItem().toString());
                param2 = cbParam2.getSelectedItem().toString();

            }
            catch (Exception e)
            {

                param2 = "0";
            }


            if (cbParam3.getSelectedItem().toString()=="White")
                param3 = "0";
            else if (cbParam3.getSelectedItem().toString() == "Black")
                param3 = "1";
            else if (cbParam3.getSelectedItem().toString() == "Red")
                param3 = "2";
            else if (cbParam3.getSelectedItem().toString() == "Green")
                param3 = "3";
            else if (cbParam3.getSelectedItem().toString() == "Blue")
                param3 = "4";
            else if (cbParam3.getSelectedItem().toString() == "Water Blue")
                param3 = "5";
            else if (cbParam3.getSelectedItem().toString() == "Magenta")
                param3 = "6";
            else if (cbParam3.getSelectedItem().toString() == "Yellow")
                param3 = "7";
            else if (cbParam3.getSelectedItem().toString() == "Gray")
                param3 = "8";
            else 
                param3 = "1";
        
        }




        if (MyApp.GetRFunction() == "PI Density Plot")
        {

            if ((cbParam1.getSelectedItem().toString() == "Grey") || (cbParam1.getSelectedItem().toString() == "Yellow") || (cbParam1.getSelectedItem().toString() == "Magenta") || (cbParam1.getSelectedItem().toString() == "Blue") || (cbParam1.getSelectedItem().toString() == "Red") || (cbParam1.getSelectedItem().toString() == "Green") || (cbParam1.getSelectedItem().toString() == "Black") || (cbParam1.getSelectedItem().toString() == "White") || (cbParam1.getSelectedItem().toString() == "Pink") || (cbParam1.getSelectedItem().toString() == "Orange") || (cbParam1.getSelectedItem().toString() == "Purple") || (cbParam1.getSelectedItem().toString() == "Brown"))
            {
            }
            else
            {
                param1 = "Red";
            }

            if ((cbParam2.getSelectedItem().toString() == "Grey") || (cbParam2.getSelectedItem().toString() == "Yellow") || (cbParam2.getSelectedItem().toString() == "Magenta") || (cbParam2.getSelectedItem().toString() == "Blue") || (cbParam2.getSelectedItem().toString() == "Red") || (cbParam2.getSelectedItem().toString() == "Green") || (cbParam2.getSelectedItem().toString() == "Black") || (cbParam2.getSelectedItem().toString() == "White") || (cbParam2.getSelectedItem().toString() == "Pink") || (cbParam2.getSelectedItem().toString() == "Orange") || (cbParam2.getSelectedItem().toString() == "Purple") || (cbParam2.getSelectedItem().toString() == "Brown"))
            {
            }
            else
            {
                param2 = "Blue";
            }
            if (cbParam3.getSelectedItem().toString() == "Points")
                param3 = "p";
            else if (cbParam3.getSelectedItem().toString() == "Lines")
                param3 = "l";
            else if (cbParam3.getSelectedItem().toString() == "Both")
                param3 = "b";
            else if (cbParam3.getSelectedItem().toString() == "Both overplotted")
                param3 = "o";
            else if (cbParam3.getSelectedItem().toString() == "Histogram")
                param3 = "h";
            else if (cbParam3.getSelectedItem().toString() == "Stair Steps")
                param3 = "s";
            else
                param3 = "l";

            try
            {
                double a = Double.parseDouble(cbParam4.getSelectedItem().toString());
                param4 = cbParam4.getSelectedItem().toString();

            }
            catch (Exception e)
            {
                param4 = "NULL";
            }

        }


        if (MyApp.GetRFunction() == "PI Box Plot")
        {
            try
            {
                double a = Double.parseDouble(cbParam1.getSelectedItem().toString());
                param1 = cbParam1.getSelectedItem().toString();

            }
            catch (Exception e)
            {

                param1 = "1.5";
            }

            try
            {
                double a = Double.parseDouble(cbParam2.getSelectedItem().toString());
                param2 = cbParam2.getSelectedItem().toString();

            }
            catch (Exception e)
            {

                param2 = "0.8";
            }

            try
            {
                double a = Double.parseDouble(cbParam3.getSelectedItem().toString());
                param3 = cbParam3.getSelectedItem().toString();

            }
            catch (Exception e)
            {

                param3 = "0.5";
            }
        }
    }

	


    
    private String GenerateStringValuesOnMemory()
    {

        String text ="No values on memory";
        int numtags = MyApp.GetNumTags();
        if (numtags == 1)
        {
            text = MyApp.IntCountPIValues(1) + " values on memory.";
        }
        else if (numtags == 2)
        {
            text = MyApp.IntCountPIValues(1) + "+" + MyApp.IntCountPIValues(2) + " values on memory.";
        }
        else if (numtags == 3)
        {
        	text = MyApp.IntCountPIValues(1) + "+" + MyApp.IntCountPIValues(2)+ "+" + MyApp.IntCountPIValues(3) + " values on memory.";
            
        }
        else if (numtags == 4)
        {
        	text = MyApp.IntCountPIValues(1) + "+" + MyApp.IntCountPIValues(2)+ "+" + MyApp.IntCountPIValues(3) + "+" + MyApp.IntCountPIValues(4)+ " values on memory.";
        }
        else
        {
        	text = MyApp.IntCountPIValues(1) + "+" + MyApp.IntCountPIValues(2)+ "+" + MyApp.IntCountPIValues(3) + "+" + MyApp.IntCountPIValues(4)+  "+" + MyApp.IntCountPIValues(5) + " values on memory.";
        }
        return (text);
    }
	
	
	
	private void UpdateSettings()
	{

		   String selected = cbRFunction.getItemAt(cbRFunction.getSelectedIndex()).toString();
		   ResetAdvParamGroupBox();
		
      SetRecordedMode();
      rbRecorded.setVisible(true);
   

		   if(selected=="PI Histogram")
		{
			String[] stringArray1 = new String[] { "1", "2","5","10","20","30","50" };
            String[] stringArray2 = new String[] { "1", "2", "5", "10", "20", "30", "50" };
            String[] stringArray3 = new String[] {"While", "Black", "Red", "Green", "Blue", "Water Blue", "Magenta", "Yellow", "Gray" };

            
            tbForOneTag();
            NonCorrelationConf();
            lbParam1.setText("Breaks");
            lbParam2.setText("Density");
            lbParam3.setText("Colour");
            lbParam4.setVisible(false);
            
            for (String string : stringArray1) { 
            	cbParam1.addItem(string); 
				}
            cbParam1.setSelectedIndex(3);
            
            for (String string : stringArray2) { 
            	cbParam2.addItem(string); 
				}
            cbParam2.setSelectedIndex(4);
            
            for (String string : stringArray3) { 
            	cbParam3.addItem(string); 	
			}
            cbParam3.setSelectedIndex(2);
            cbParam4.addItem(""); 
            
            cbParam4.setVisible(false);
			System.out.println(selected);
		}
		if(selected.equals("PI Density Plot"))
		{
            NonCorrelationConf();
            tbForOneTag();
            lbParam1.setText("Graphic Colour");
            lbParam2.setText("Border Colour");
            lbParam3.setText("Type");
            lbParam4.setText("ASP");
            
            String[] stringArray1 = new String[] { "Grey","Yellow","Magenta","Blue","Red","Green","Black","White","Pink","Orange","Purple","Brown" };
            String[] stringArray2 = new String[] { "Points", "Lines", "Both", "Both overplotted", "Histogram", "Stair Steps" };
            String[] stringArray3 = new String[] { "0.001","0.01", "0.1", "1", "10", "100", "1000"};
            
            
            
            for (String string : stringArray1) { 
            	cbParam1.addItem(string); 
				}
            
            
            for (String string : stringArray1) { 
            	cbParam2.addItem(string); 
				}
            
            for (String string : stringArray2) { 
            	cbParam3.addItem(string); 
				}
            
            
            for (String string : stringArray3) { 
            	cbParam4.addItem(string); 
				}
            
            System.out.println(selected);
		}
		if(selected.equals("PI Density Compare"))
		{
            NoAdvSetOpt();
            NonCorrelationConf();
            lbNumTags.setVisible(true);
            cbNumTags.setVisible(true);

            String[] stringArray = new String[] { "2", "3" };
            for (String string : stringArray) { 
            	cbNumTags.addItem(string); 
				}
            
            
            tbForTwoTags();
            cbParam1.addItem(""); 
            cbParam2.addItem(""); 
            cbParam3.addItem(""); 
            cbParam4.addItem(""); 
			System.out.println(selected);
		}
		if(selected.equals("PI Box Plot"))
		{
            tbForTwoTags();
            NonCorrelationConf();
			
			lbParam1.setText("Range");
            lbParam2.setText("Boxwex");
            lbParam3.setText("Staplewex");
            
            //lbParam4.setVisible(false);
            cbParam1.addItem("1.5");
            cbParam2.addItem("0.8");
            cbParam3.addItem("0.5");
            cbParam4.addItem(""); 
            cbParam4.setVisible(false);
			System.out.println(selected);
		}
		if(selected.equals("PI Regular Correlation"))
		{
            NoAdvSetOpt();
            CorrelationConf();
            rbRecorded.setVisible(false);
            SetInterpolatedMode();
            tbForTwoTags();
            cbParam1.addItem(""); 
            cbParam2.addItem(""); 
            cbParam3.addItem(""); 
            cbParam4.addItem(""); 
			System.out.println(selected);
		}
		if(selected.equals("PI Smooth Scatter"))
		{
            NoAdvSetOpt();
            CorrelationConf();
            rbRecorded.setVisible(false);
            SetInterpolatedMode();
            tbForTwoTags();
            cbParam1.addItem(""); 
            cbParam2.addItem(""); 
            cbParam3.addItem(""); 
            cbParam4.addItem(""); 
			System.out.println(selected);
		}
		if(selected.equals("PI Multi-Correlation"))
		{
                NoAdvSetOpt();
                CorrelationConf();
                rbRecorded.setVisible(false);
                SetInterpolatedMode();
                lbNumTags.setVisible(true);
                cbNumTags.setVisible(true);
                String[] stringArray = new String[] { "3", "4", "5" };
                for (String string : stringArray) { 
                	cbNumTags.addItem(string); 
					}
                tbForThreeTags();
                cbParam1.addItem(""); 
                cbParam2.addItem(""); 
                cbParam3.addItem(""); 
                cbParam4.addItem(""); 
			System.out.println(selected);
		}	
		SetMyAppRFunction();
	}
	
	private void NumTagsChanged()
    {
		try
		{
		String SelectedRFunction=cbRFunction.getItemAt(cbRFunction.getSelectedIndex()).toString();
		String SeletedNumTags = cbNumTags.getItemAt(cbNumTags.getSelectedIndex()).toString();
        if ((SelectedRFunction== "PI Density Compare") &  (SeletedNumTags=="2"))
        {
            tbForTwoTags();
           
        }

        if ((SelectedRFunction== "PI Density Compare") &  (SeletedNumTags=="3"))
        {
            tbForThreeTags();
   
        }

        if ((SelectedRFunction== "PI Multi-Correlation") &  (SeletedNumTags=="3"))
        {
            tbForThreeTags();
      
        }

        if ((SelectedRFunction== "PI Multi-Correlation") &  (SeletedNumTags=="4"))
        {
            tbForFourTags();
    
        }

        if ((SelectedRFunction== "PI Multi-Correlation") &  (SeletedNumTags=="5"))
        {
            tbForFiveTags();
    
        }
        SetMyAppRFunction();
		}
		catch(Exception ex)
		{
			
		}


    }
	

	  private void tbForOneTag()
      {
          lbTag1.setText("Tag Name");
          lbTag2.setVisible(false);
          tfTag2.setVisible(false);
          tfTag3.setVisible(false);
          lbTag3.setVisible(false);
          tfTag4.setVisible(false);
          lbTag4.setVisible(false);
          tfTag5.setVisible(false);
          lbTag5.setVisible(false);
          MyApp.SetNumTags(1);
      }

      private void tbForTwoTags()
      {
          lbTag2.setText("Tag Name 1");
          lbTag1.setText("Tag Name 2");
          lbTag2.setVisible(true);
          tfTag2.setVisible(true);
          tfTag3.setVisible(false);
          lbTag3.setVisible(false);
          tfTag4.setVisible(false);
          lbTag4.setVisible(false);
          tfTag5.setVisible(false);
          lbTag5.setVisible(false);
          MyApp.SetNumTags(2);
      }

      private void tbForThreeTags()
      {
          lbTag2.setText("Tag Name 2");
          lbTag1.setText("Tag Name 3");
          lbTag3.setText("Tag Name 1");
          lbTag2.setVisible(true);
          tfTag2.setVisible(true);
          tfTag3.setVisible(true);
          lbTag3.setVisible(true);
          tfTag4.setVisible(false);
          lbTag4.setVisible(false);
          tfTag5.setVisible(false);
          lbTag5.setVisible(false);
          MyApp.SetNumTags(3);
      }

      private void tbForFourTags()
      {
          lbTag1.setText("Tag Name 4");
          lbTag2.setText("Tag Name 3");
          lbTag3.setText("Tag Name 2");
          lbTag4.setText("Tag Name 1");
          lbTag2.setVisible(true);
          tfTag2.setVisible(true);
          tfTag3.setVisible(true);
          lbTag3.setVisible(true);
          tfTag4.setVisible(true);
          lbTag4.setVisible(true);
          tfTag5.setVisible(false);
          lbTag5.setVisible(false);
          MyApp.SetNumTags(4);
      }

      private void tbForFiveTags()
      {
          lbTag1.setText("Tag Name 5");
          lbTag2.setText("Tag Name 4");
          lbTag3.setText("Tag Name 3");
          lbTag4.setText("Tag Name 2");
          lbTag5.setText("Tag Name 1");
          lbTag2.setVisible(true);
          tfTag2.setVisible(true);
          tfTag3.setVisible(true);
          lbTag3.setVisible(true);
          tfTag4.setVisible(true);
          lbTag4.setVisible(true);
          tfTag5.setVisible(true);
          lbTag5.setVisible(true);
          MyApp.SetNumTags(5);
      }



      private void SetInterpolatedMode()
      {

          rbInterpolated.setSelected(true);
          MyApp.SetMode("Interpolated");
          lbInterval.setVisible(true);
          tfInterval.setVisible(true);
      
      }

      private void SetRecordedMode()
      {

          rbRecorded.setSelected(true);
          MyApp.SetMode("Recorded");
          lbInterval.setVisible(false);
          tfInterval.setVisible(false);
      }


      private void ResetAdvParamGroupBox()
      {
          cbNumTags.setVisible(false);
          cbNumTags.removeAllItems();
          lbNumTags.setVisible(false);
          cbParam1.removeAllItems();
          cbParam2.removeAllItems();
          cbParam3.removeAllItems();
          cbParam4.removeAllItems();
          lbParam1.setVisible(true);
          cbParam1.setVisible(true);
          lbParam2.setVisible(true);
          cbParam2.setVisible(true);
          lbParam3.setVisible(true);
          cbParam3.setVisible(true);
          lbParam4.setVisible(true);
          cbParam4.setVisible(true);
          cbShowAdvSet.setVisible(true);
          UpdateShowAdvSet();
      
      }

      private void NoAdvSetOpt()
      {
    	  RFunctionPanel.setVisible(false);
          cbShowAdvSet.setVisible(false);

      }

      private void UpdateShowAdvSet()
      {
          if (cbShowAdvSet.isSelected() == true)
          {
        	  RFunctionPanel.setVisible(true);
          }
          else
          {
        	  RFunctionPanel.setVisible(false);
          }
      }
      
      private void CorrelationConf()
      {

          tfTag5.setText("FAC.OAK.Power-Total_Demand_Calc.PV");
          tfTag4.setText("FAC.OAK.Weather-Outside_Humidity-Val.Pv");
          tfTag3.setText("FAC.OAK.Weather-Inside_Humidity-Val.PV");
          tfTag2.setText("FAC.OAK.Power-Total_Demand_Calc.PV");
          tfTag1.setText("FAC.OAK.Weather-Outside_Temperature-Val.PV");
      }

      private void NonCorrelationConf()
      {

          tfTag5.setText("FAC.OAK.Power-Total_Demand_Calc.PV");
          tfTag4.setText("FAC.OAK.Weather-Outside_Humidity-Val.Pv");
          tfTag3.setText("FAC.OAK.Weather-Inside_Humidity-Val.PV");
          tfTag1.setText("FAC.OAK.Weather-Outside_Temperature-Val.PV");
          tfTag2.setText("FAC.OAK.Weather-Inside_Temperature-Val.PV");
      }

      
      private void SaveStrings()
      { 
      SavedTagName1 = tfTag1.getText();
      SavedTagName2 = tfTag2.getText();
      SavedTagName3 = tfTag3.getText();
      SavedTagName4 = tfTag4.getText();
      SavedTagName5 = tfTag5.getText();
      SavedStarttime = tfStartTime.getText();
      SavedEndtime = tfEndTime.getText();
      SavedInterval = tfInterval.getText();
      if (rbInterpolated.isSelected() == true)
    	  SavedMode = "Interpolated";
      if (rbRecorded.isSelected() == true)
    	  SavedMode = "Recorded";
      }

      private void SaveParamStrings()
      {
          SavedParam1 = cbParam1.getSelectedItem().toString();
          SavedParam2 = cbParam2.getSelectedItem().toString();
          SavedParam3 = cbParam3.getSelectedItem().toString();
          SavedParam4 = cbParam4.getSelectedItem().toString();
      }


      private boolean CheckSavedStrings()
      {
    	  boolean GoodState = true;
          if (SavedTagName1.equals(tfTag1.getText())==false)
          {
              GoodState = false;
          }
          if (SavedTagName2.equals(tfTag2.getText())==false)
          {
              GoodState = false;
          }
          if (SavedTagName3.equals(tfTag3.getText())==false)
          {
              GoodState = false;
          }
          if (SavedTagName4.equals(tfTag4.getText())==false)
          {
              GoodState = false;
          }
          if (SavedTagName5.equals(tfTag5.getText())==false)
          {
              GoodState = false;
          }
          if (SavedStarttime.equals(tfStartTime.getText())==false)
          {
              GoodState = false;
          }
          if (SavedEndtime.equals(tfEndTime.getText())==false)
          {
              GoodState = false;
          }
          if (SavedInterval.equals(tfInterval.getText())==false)
          {
              GoodState = false;
          }
          if ((SavedMode == "Recorded") && (rbRecorded.isSelected() == false))
          {
              GoodState = false;
          }
          if ((SavedMode == "Interpolated") && (rbInterpolated.isSelected() == false))
          {
              GoodState = false;
          }
          return GoodState;
      }






      private void SetMyAppRFunction()
      { 
          if (cbRFunction.getSelectedItem().toString()== "PI Density Compare")
          {
              if (MyApp.GetNumTags()==2)
                  MyApp.SetRFunction("PI Density Compare for two tags"); 
              if (MyApp.GetNumTags() == 3)
                  MyApp.SetRFunction("PI Density Compare for three tags"); 


          }
          else if (cbRFunction.getSelectedItem().toString() == "PI Multi-Correlation")
          {
              if (MyApp.GetNumTags() == 3)
                  MyApp.SetRFunction("PI Multi-Correlation for three tags");
              if (MyApp.GetNumTags() == 4)
                  MyApp.SetRFunction("PI Multi-Correlation for four tags");
              if (MyApp.GetNumTags() == 5)
                  MyApp.SetRFunction("PI Multi-Correlation for five tags");
          }
          else
          {
              MyApp.SetRFunction(cbRFunction.getSelectedItem().toString());
          }
      }
	
}