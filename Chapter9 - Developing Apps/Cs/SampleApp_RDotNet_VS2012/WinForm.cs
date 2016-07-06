using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace SampleApp_RDotNet_VS2012
{
    public partial class WinForm : Form
    {
        private RApplication MyApp;
        private string SavedTagName1 = null;
        private string SavedTagName2 = null;
        private string SavedTagName3 = null;
        private string SavedTagName4 = null;
        private string SavedTagName5 = null;
        private string SavedParam1 = null;
        private string SavedParam2 = null;
        private string SavedParam3 = null;
        private string SavedParam4 = null;
        private string SavedStarttime = null;
        private string SavedEndtime = null;
        private string SavedInterval = null;
        private string SavedMode = null;

        public WinForm()
        {
            InitializeComponent();
            MyApp = new RApplication();
            tsPISDKVersion.Text = "AFSDK Version: " + MyApp.GetVersion();
            cbRFunctions.Items.Add("PI Histogram");
            cbRFunctions.Items.Add("PI Density Plot");
            cbRFunctions.Items.Add("PI Density Compare");
            cbRFunctions.Items.Add("PI Box Plot");
            cbRFunctions.Items.Add("PI Regular Correlation");
            cbRFunctions.Items.Add("PI Smooth Scatter");
            cbRFunctions.Items.Add("PI Multi-Correlation");

            tsProgressBar.Value = 0;

        }

        private void bConnect_Click(object sender, EventArgs e)
        {
            gbR.Visible = false;
            gbRFunctionInfo.Visible = false;
            gbGetData.Visible = false;
            btGraphic.Visible = false;
            lbCurrRFunction.Visible = false;
            lbUsingRFunction.Visible = false;
            tsConnected.Text = "Not connected";
            bool connectedtatus = true;
            if (cbPIServersList.Text == string.Empty)
            {
                connectedtatus = MyApp.Connect(null);
            }
            else
            {
                connectedtatus = MyApp.Connect(cbPIServersList.Text);
            }
            if (connectedtatus == true)
            {
                tsConnected.Text = "Connected to " + MyApp.GetPIServerName();
                MyApp.ProgramCurrentStep = 0;
                tsProgressBar.Value = 25;
                cbPIServersList.Text = MyApp.GetPIServerName();
                gbR.Visible = true;

            }
            else
            {
                tsConnected.Text = "Not Connected";
                MessageBox.Show("Could not connect to " + MyApp.GetPIServerName());

            }
        }



        private void comboBox1_DropDown(object sender, EventArgs e)
        {
            cbPIServersList.Items.Clear();
            cbPIServersList.Items.AddRange(MyApp.GetPIDataArchiveNames());
        }



        private void bGetData_Click(object sender, EventArgs e)
        {
            int result = -1;
            if (MyApp.NumTags == 1)
            {
                tbTag2.Text = String.Empty;
                tbTag3.Text = String.Empty;
                tbTag4.Text = String.Empty;
                tbTag5.Text = String.Empty;

            }

            if (MyApp.NumTags == 2)
            {
                tbTag3.Text = String.Empty;
                tbTag4.Text = String.Empty;
                tbTag5.Text = String.Empty;
            }

            if (MyApp.NumTags == 3)
            {
                tbTag4.Text = String.Empty;
                tbTag5.Text = String.Empty;
            }
            if (MyApp.NumTags == 4)
            {
                tbTag5.Text = String.Empty;
            }


            result = MyApp.GetPIData(tbTag1.Text, tbTag2.Text, tbTag3.Text, tbTag4.Text, tbTag5.Text, tbStartTime.Text, tbEndTime.Text, tbInterval.Text);

            if (result == 0)
            {
                btGraphic.Visible = true;
                SaveStrings();
                MyApp.ProgramCurrentStep = 2;
                tsProgressBar.Value = 75;
            }
            if (result == 1)
            {
                MessageBox.Show("Invalid Tag Name: Could find tag on the Server.");
                MyApp.ProgramCurrentStep = 1;
            }
            if (result == 2)
            {
                MessageBox.Show("Invalid Start Time and End Time.");
                MyApp.ProgramCurrentStep = 1;
            }
            tsValCount.Text = GenerateStringValuesOnMemory();
            lbCurrRFunction.Text = MyApp.RFunction;
            lbCurrRFunction.Visible = true;
            lbUsingRFunction.Visible = true;
            btGraphic.Visible = true;


        }

        private string GenerateStringValuesOnMemory()
        {

            string text = "No values on memory";
            int numtags = MyApp.NumTags;
            if (numtags == 1)
            {
                text = Convert.ToString(MyApp.IntCountAFValues(1)) + " values on memory.";
            }
            else if (numtags == 2)
            {
                text = Convert.ToString(MyApp.IntCountAFValues(1)) + "+" + Convert.ToString(MyApp.IntCountAFValues(2)) + " values on memory.";
            }
            else if (numtags == 3)
            {
                text = Convert.ToString(MyApp.IntCountAFValues(1)) + "+" + Convert.ToString(MyApp.IntCountAFValues(2)) + "+" + Convert.ToString(MyApp.IntCountAFValues(3)) + " values on memory.";

            }
            else if (numtags == 4)
            {
                text = Convert.ToString(MyApp.IntCountAFValues(1)) + "+" + Convert.ToString(MyApp.IntCountAFValues(2)) + "+" + Convert.ToString(MyApp.IntCountAFValues(3)) + "+" + Convert.ToString(MyApp.IntCountAFValues(4)) + " values on memory.";

            }
            else
            {
                text = Convert.ToString(MyApp.IntCountAFValues(1)) + "+" + Convert.ToString(MyApp.IntCountAFValues(2)) + "+" + Convert.ToString(MyApp.IntCountAFValues(3)) + "+" + Convert.ToString(MyApp.IntCountAFValues(4)) + "+" + Convert.ToString(MyApp.IntCountAFValues(5)) + " values on memory.";

            }
            return (text);
        }


        private void tbForOneTag()
        {
            lbTag1.Text = "Tag Name";
            lbTag2.Visible = false;
            tbTag2.Visible = false;
            tbTag3.Visible = false;
            lbTag3.Visible = false;
            tbTag4.Visible = false;
            lbTag4.Visible = false;
            tbTag5.Visible = false;
            lbTag5.Visible = false;
            MyApp.NumTags = 1;
        }

        private void tbForTwoTags()
        {
            lbTag2.Text = "Tag Name 1";
            lbTag1.Text = "Tag Name 2";
            lbTag2.Visible = true;
            tbTag2.Visible = true;
            tbTag3.Visible = false;
            lbTag3.Visible = false;
            tbTag4.Visible = false;
            lbTag4.Visible = false;
            tbTag5.Visible = false;
            lbTag5.Visible = false;
            MyApp.NumTags = 2;
        }

        private void tbForThreeTags()
        {
            lbTag2.Text = "Tag Name 2";
            lbTag1.Text = "Tag Name 3";
            lbTag3.Text = "Tag Name 1";
            lbTag2.Visible = true;
            tbTag2.Visible = true;
            tbTag3.Visible = true;
            lbTag3.Visible = true;
            tbTag4.Visible = false;
            lbTag4.Visible = false;
            tbTag5.Visible = false;
            lbTag5.Visible = false;
            MyApp.NumTags = 3;
        }

        private void tbForFourTags()
        {
            lbTag1.Text = "Tag Name 4";
            lbTag2.Text = "Tag Name 3";
            lbTag3.Text = "Tag Name 2";
            lbTag4.Text = "Tag Name 1";
            lbTag2.Visible = true;
            tbTag2.Visible = true;
            tbTag3.Visible = true;
            lbTag3.Visible = true;
            tbTag4.Visible = true;
            lbTag4.Visible = true;
            tbTag5.Visible = false;
            lbTag5.Visible = false;
            MyApp.NumTags = 4;
        }

        private void tbForFiveTags()
        {
            lbTag1.Text = "Tag Name 5";
            lbTag2.Text = "Tag Name 4";
            lbTag3.Text = "Tag Name 3";
            lbTag4.Text = "Tag Name 2";
            lbTag5.Text = "Tag Name 1";
            lbTag2.Visible = true;
            tbTag2.Visible = true;
            tbTag3.Visible = true;
            lbTag3.Visible = true;
            tbTag4.Visible = true;
            lbTag4.Visible = true;
            tbTag5.Visible = true;
            lbTag5.Visible = true;
            MyApp.NumTags = 5;
        }



        private void SetInterpolatedMode()
        {

            rbnInterp.Checked = true;
            MyApp.Mode = "Interpolated";
            lbInterval.Visible = true;
            tbInterval.Visible = true;

        }

        private void SetRecordedMode()
        {

            rbnRecorded.Checked = true;
            MyApp.Mode = "Recorded";
            lbInterval.Visible = false;
            tbInterval.Visible = false;
        }


        private void ResetAdvParamGroupBox()
        {
            cbNumTags.Visible = false;
            cbNumTags.Items.Clear();
            lbNumTags.Visible = false;
            cbParam1.Items.Clear();
            cbParam2.Items.Clear();
            cbParam3.Items.Clear();
            cbParam4.Items.Clear();
            cbParam1.Text = string.Empty;
            cbParam2.Text = string.Empty;
            cbParam3.Text = string.Empty;
            cbParam4.Text = string.Empty;
            lbParam1.Visible = true;
            cbParam1.Visible = true;
            lbParam2.Visible = true;
            cbParam2.Visible = true;
            lbParam3.Visible = true;
            cbParam3.Visible = true;
            lbParam4.Visible = true;
            cbParam4.Visible = true;
            cbShowAdvSet.Visible = true;
            UpdateShowAdvSet();

        }

        private void NoAdvSetOpt()
        {
            gbRFunctionInfo.Visible = false;
            cbShowAdvSet.Visible = false;

        }

        private void UpdateShowAdvSet()
        {
            if (cbShowAdvSet.Checked == true)
            {
                gbRFunctionInfo.Visible = true;
            }
            else
            {
                gbRFunctionInfo.Visible = false;
            }
        }


        private void SetMyAppRFunction()
        {

            if (cbRFunctions.Text == "PI Density Compare")
            {
                if (MyApp.NumTags == 2)
                    MyApp.RFunction = "PI Density Compare for two tags";
                if (MyApp.NumTags == 3)
                    MyApp.RFunction = "PI Density Compare for three tags";


            }
            else if (cbRFunctions.Text == "PI Multi-Correlation")
            {
                if (MyApp.NumTags == 3)
                    MyApp.RFunction = "PI Multi-Correlation for three tags";
                if (MyApp.NumTags == 4)
                    MyApp.RFunction = "PI Multi-Correlation for four tags";
                if (MyApp.NumTags == 5)
                    MyApp.RFunction = "PI Multi-Correlation for five tags";
            }
            else
            {
                MyApp.RFunction = cbRFunctions.Text;
            }
        }


        private void bConfirmRFunction_Click(object sender, EventArgs e)
        {
            ResetAdvParamGroupBox();
            MyApp.ProgramCurrentStep = 1;
            tsProgressBar.Value = 50;

            if (cbRFunctions.Text != String.Empty)
            {
                gbGetData.Visible = true;
                SetRecordedMode();
                rbnRecorded.Visible = true;

            }

            if (cbRFunctions.Text == "PI Histogram")
            {

                tbForOneTag();
                NonCorrelationConf();
                lbParam1.Text = "Breaks";
                lbParam2.Text = "Density";
                lbParam3.Text = "Colour";
                lbParam4.Visible = false;
                cbParam1.Text = "5";
                cbParam1.Items.AddRange(new string[] { "1", "2", "5", "10", "20", "30", "50" });
                cbParam2.Text = "0";
                cbParam2.Items.AddRange(new string[] { "1", "2", "5", "10", "20", "30", "50" });
                cbParam3.Text = "Black";
                cbParam3.Items.AddRange(new string[] { "While", "Black", "Red", "Green", "Blue", "Water Blue", "Magenta", "Yellow", "Gray" });
                cbParam4.Visible = false;

            }
            if (cbRFunctions.Text == "PI Density Plot")
            {
                NonCorrelationConf();
                tbForOneTag();
                lbParam1.Text = "Graphic Colour";
                lbParam2.Text = "Border Colour";
                lbParam3.Text = "Type";
                lbParam4.Text = "ASP";
                cbParam1.Text = "Red";
                cbParam1.Items.AddRange(new string[] { "Grey", "Yellow", "Magenta", "Blue", "Red", "Green", "Black", "White", "Pink", "Orange", "Purple", "Brown" });

                cbParam2.Text = "Blue";
                cbParam2.Items.AddRange(new string[] { "Grey", "Yellow", "Magenta", "Blue", "Red", "Green", "Black", "White", "Pink", "Orange", "Purple", "Brown" });

                cbParam3.Text = "Lines";
                cbParam3.Items.AddRange(new string[] { "Points", "Lines", "Both", "Both overplotted", "Histogram", "Stair Steps" });

                cbParam4.Text = "NULL";
                cbParam4.Items.AddRange(new string[] { "0.001", "0.01", "0.1", "1", "10", "100", "1000" });

            }




            if (cbRFunctions.Text == "PI Box Plot")
            {
                tbForTwoTags();
                NonCorrelationConf();
                lbParam1.Text = "Range";
                lbParam2.Text = "Boxwex";
                lbParam3.Text = "Staplewex";
                lbParam4.Visible = false;
                cbParam1.Text = "1.5";
                cbParam2.Text = "0.8";
                cbParam3.Text = "0.5";
                cbParam4.Visible = false;

            }
            if (cbRFunctions.Text == "PI Regular Correlation")
            {
                NoAdvSetOpt();
                CorrelationConf();
                rbnRecorded.Visible = false;
                SetInterpolatedMode();
                tbForTwoTags();
            }
            if (cbRFunctions.Text == "PI Smooth Scatter")
            {
                NoAdvSetOpt();
                CorrelationConf();
                rbnRecorded.Visible = false;
                SetInterpolatedMode();
                tbForTwoTags();
            }
            if (cbRFunctions.Text == "PI Multi-Correlation")
            {
                NoAdvSetOpt();
                CorrelationConf();
                rbnRecorded.Visible = false;
                SetInterpolatedMode();
                lbNumTags.Visible = true;
                cbNumTags.Visible = true;
                cbNumTags.Text = "3";
                cbNumTags.Items.AddRange(new string[] { "3", "4", "5" });
                tbForThreeTags();
            }

            if (cbRFunctions.Text == "PI Density Compare")
            {
                NoAdvSetOpt();
                NonCorrelationConf();
                lbNumTags.Visible = true;
                cbNumTags.Visible = true;
                cbNumTags.Text = "2";
                cbNumTags.Items.AddRange(new string[] { "2", "3" });
                tbForTwoTags();
            }


            SetMyAppRFunction();







        }


        private string param1 = String.Empty;
        private string param2 = String.Empty;
        private string param3 = String.Empty;
        private string param4 = String.Empty;

        private void ProcessParameters()
        {
            param1 = cbParam1.Text;
            param2 = cbParam2.Text;
            param3 = cbParam3.Text;
            param4 = cbParam4.Text;
            if (MyApp.RFunction == "PI Histogram")
            {
                try
                {
                    int a = Convert.ToInt16(cbParam1.Text);
                    param1 = cbParam1.Text;

                }
                catch
                {

                    param1 = "5";
                }

                try
                {
                    int a = Convert.ToInt16(cbParam2.Text);
                    param2 = cbParam2.Text;

                }
                catch
                {

                    param2 = "0";
                }


                if (cbParam3.Text == "White")
                    param3 = "0";
                else if (cbParam3.Text == "Black")
                    param3 = "1";
                else if (cbParam3.Text == "Red")
                    param3 = "2";
                else if (cbParam3.Text == "Green")
                    param3 = "3";
                else if (cbParam3.Text == "Blue")
                    param3 = "4";
                else if (cbParam3.Text == "Water Blue")
                    param3 = "5";
                else if (cbParam3.Text == "Magenta")
                    param3 = "6";
                else if (cbParam3.Text == "Yellow")
                    param3 = "7";
                else if (cbParam3.Text == "Gray")
                    param3 = "8";
                else
                    param3 = "1";

            }




            if (MyApp.RFunction == "PI Density Plot")
            {

                if ((cbParam1.Text == "Grey") || (cbParam1.Text == "Yellow") || (cbParam1.Text == "Magenta") || (cbParam1.Text == "Blue") || (cbParam1.Text == "Red") || (cbParam1.Text == "Green") || (cbParam1.Text == "Black") || (cbParam1.Text == "White") || (cbParam1.Text == "Pink") || (cbParam1.Text == "Orange") || (cbParam1.Text == "Purple") || (cbParam1.Text == "Brown"))
                {
                }
                else
                {
                    param1 = "Red";
                }

                if ((cbParam2.Text == "Grey") || (cbParam2.Text == "Yellow") || (cbParam2.Text == "Magenta") || (cbParam2.Text == "Blue") || (cbParam2.Text == "Red") || (cbParam2.Text == "Green") || (cbParam2.Text == "Black") || (cbParam2.Text == "White") || (cbParam2.Text == "Pink") || (cbParam2.Text == "Orange") || (cbParam2.Text == "Purple") || (cbParam2.Text == "Brown"))
                {
                }
                else
                {
                    param2 = "Blue";
                }


                if (cbParam3.Text == "Points")
                    param3 = "p";
                else if (cbParam3.Text == "Lines")
                    param3 = "l";
                else if (cbParam3.Text == "Both")
                    param3 = "b";
                else if (cbParam3.Text == "Both overplotted")
                    param3 = "o";
                else if (cbParam3.Text == "Histogram")
                    param3 = "h";
                else if (cbParam3.Text == "Stair Steps")
                    param3 = "s";
                else
                    param3 = "l";

                try
                {
                    double a = Convert.ToDouble(cbParam4.Text);
                    param4 = cbParam4.Text;

                }
                catch
                {

                    param4 = "NULL";
                }

            }


            if (MyApp.RFunction == "PI Box Plot")
            {
                try
                {
                    double a = Convert.ToDouble(cbParam1.Text);
                    param1 = cbParam1.Text;

                }
                catch
                {

                    param1 = "1.5";
                }

                try
                {
                    double a = Convert.ToDouble(cbParam2.Text);
                    param2 = cbParam2.Text;

                }
                catch
                {

                    param2 = "0.8";
                }

                try
                {
                    double a = Convert.ToDouble(cbParam3.Text);
                    param3 = cbParam3.Text;

                }
                catch
                {

                    param3 = "0.5";
                }
            }
        }





        private void btGenGraph(object sender, EventArgs e)
        {
            if ((ChangedStrings() == false) && (MyApp.ProgramCurrentStep == 3) && CheckSavedStrings() == true)
            {
                MessageBox.Show("Please modify a configuration.");
            }
            else
            {
                bool test = CheckSavedStrings();
                if ((MyApp.ProgramCurrentStep >= 2) && (CheckSavedStrings() == true))
                {

                    ProcessParameters();
                    SaveParamStrings();
                    MyApp.ProgramCurrentStep = 3;
                    MyApp.GenerateGhaphic(param1, param2, param3, param4);
                    if (MyApp.RFunction == "PI Regular Correlation")
                    {
                        MessageBox.Show("Correlation coefficient = " + MyApp.ValueToBeShown);
                    }
                    tsProgressBar.Value = 100;
                }
                else
                {
                    MessageBox.Show("Please click first on \"Get Data\" button");
                }
            }

        }

        private void btUseDefault_Click(object sender, EventArgs e)
        {

            NonCorrelationConf();
            tbStartTime.Text = "1-Oct-2012";
            tbEndTime.Text = "1-Nov-2012";
            tbInterval.Text = "1h";

        }




        private void rbnInterp_Click(object sender, EventArgs e)
        {
            SetInterpolatedMode();

        }

        private void rbnRecorded_Click(object sender, EventArgs e)
        {
            SetRecordedMode();
        }

        private void cbShowAdvSet_CheckedChanged(object sender, EventArgs e)
        {
            UpdateShowAdvSet();
        }

        private void cbNumTags_SelectedValueChanged(object sender, EventArgs e)
        {

            if ((cbRFunctions.Text == "PI Density Compare") & (cbNumTags.Text == "2"))
            {
                tbForTwoTags();

            }

            if ((cbRFunctions.Text == "PI Density Compare") & (cbNumTags.Text == "3"))
            {
                tbForThreeTags();

            }

            if ((cbRFunctions.Text == "PI Multi-Correlation") & (cbNumTags.Text == "3"))
            {
                tbForThreeTags();

            }

            if ((cbRFunctions.Text == "PI Multi-Correlation") & (cbNumTags.Text == "4"))
            {
                tbForFourTags();

            }

            if ((cbRFunctions.Text == "PI Multi-Correlation") & (cbNumTags.Text == "5"))
            {
                tbForFiveTags();

            }
            SetMyAppRFunction();
            MyApp.ProgramCurrentStep = 1;
            tsProgressBar.Value = 50;

        }




        private void CorrelationConf()
        {

            tbTag5.Text = "FAC.OAK.Power-Total_Demand_Calc.PV";
            tbTag4.Text = "FAC.OAK.Weather-Outside_Humidity-Val.Pv";
            tbTag3.Text = "FAC.OAK.Weather-Inside_Humidity-Val.PV";
            tbTag2.Text = "FAC.OAK.Power-Total_Demand_Calc.PV";
            tbTag1.Text = "FAC.OAK.Weather-Outside_Temperature-Val.PV";
        }

        private void NonCorrelationConf()
        {

            tbTag5.Text = "FAC.OAK.Power-Total_Demand_Calc.PV";
            tbTag4.Text = "FAC.OAK.Weather-Outside_Humidity-Val.Pv";
            tbTag3.Text = "FAC.OAK.Weather-Inside_Humidity-Val.PV";
            tbTag1.Text = "FAC.OAK.Weather-Outside_Temperature-Val.PV";
            tbTag2.Text = "FAC.OAK.Weather-Inside_Temperature-Val.PV";
        }

        private void SaveStrings()
        {
            SavedTagName1 = tbTag1.Text;
            SavedTagName2 = tbTag2.Text;
            SavedTagName3 = tbTag3.Text;
            SavedTagName4 = tbTag4.Text;
            SavedTagName5 = tbTag5.Text;
            SavedStarttime = tbStartTime.Text;
            SavedEndtime = tbEndTime.Text;
            SavedInterval = tbInterval.Text;
            if (rbnInterp.Checked == true)
                SavedMode = "Interpolated";
            if (rbnRecorded.Checked == true)
                SavedMode = "Recorded";
        }

        private void SaveParamStrings()
        {
            SavedParam1 = cbParam1.Text;
            SavedParam2 = cbParam2.Text;
            SavedParam3 = cbParam3.Text;
            SavedParam4 = cbParam4.Text;
        }


        private bool CheckSavedStrings()
        {
            bool GoodState = true;
            if (SavedTagName1 != tbTag1.Text)
            {
                GoodState = false;
            }
            if (SavedTagName2 != tbTag2.Text)
            {
                GoodState = false;
            }
            if (SavedTagName3 != tbTag3.Text)
            {
                GoodState = false;
            }
            if (SavedTagName4 != tbTag4.Text)
            {
                GoodState = false;
            }
            if (SavedTagName5 != tbTag5.Text)
            {
                GoodState = false;
            }
            if (SavedStarttime != tbStartTime.Text)
            {
                GoodState = false;
            }
            if (SavedEndtime != tbEndTime.Text)
            {
                GoodState = false;
            }
            if (SavedInterval != tbInterval.Text)
            {
                GoodState = false;
            }
            if ((SavedMode == "Recorded") && (rbnRecorded.Checked == false))
            {
                GoodState = false;
            }
            if ((SavedMode == "Interpolated") && (rbnInterp.Checked == false))
            {
                GoodState = false;
            }
            return GoodState;
        }



        private bool ChangedStrings()
        {
            bool Changed = false;
            if (SavedParam1 != cbParam1.Text)
            {
                Changed = true;
            }
            if (SavedParam2 != cbParam2.Text)
            {
                Changed = true;
            }
            if (SavedParam3 != cbParam3.Text)
            {
                Changed = true;
            }
            if (SavedParam4 != cbParam4.Text)
            {
                Changed = true;
            }


            return Changed;
        }

        private void PIDataAccessMethodChanged(object sender, EventArgs e)
        {
            tsProgressBar.Value = 0;
            lbInterval.Text = "Interval";
            string DataMethod;
            if (rbPIAFSDK.Checked)
            {
                DataMethod = "PIAFSDK";
            }
            else if (rbPIWA.Checked)
            {
                DataMethod = "PIWA";
            }
            else
            {
                DataMethod = "PIWS";
            }
            MyApp.DataAccessMethod = DataMethod;

            MyApp.Inicialize();

            if (rbPIAFSDK.Checked)
            {
                tsPISDKVersion.Text = "AFSDK Version: " + MyApp.GetVersion();
            }
            else if (rbPIWA.Checked)
            {
                tsPISDKVersion.Text = "PIWA Version: " + MyApp.GetVersion();
            }
            else
            {
                tsPISDKVersion.Text = "PIWS Version: " + MyApp.GetVersion();
                lbInterval.Text = "NumValues";
            }
            tsValCount.Text = "No values on memory";


            tsConnected.Text = "Not connected";
            gbR.Visible = false;
            gbRFunctionInfo.Visible = false;
            gbGetData.Visible = false;
            btGraphic.Visible = false;
            lbCurrRFunction.Visible = false;
            lbUsingRFunction.Visible = false;

        }

    }
}
