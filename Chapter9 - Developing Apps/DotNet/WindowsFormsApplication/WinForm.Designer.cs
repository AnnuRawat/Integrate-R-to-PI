namespace WindowsFormsApplication
{

    partial class WinForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WinForm));
            this.btConnect = new System.Windows.Forms.Button();
            this.lbPIServerName = new System.Windows.Forms.Label();
            this.cbPIServersList = new System.Windows.Forms.ComboBox();
            this.tsPISDKVersion = new System.Windows.Forms.ToolStripStatusLabel();
            this.StatusStrip = new System.Windows.Forms.StatusStrip();
            this.tsConnected = new System.Windows.Forms.ToolStripStatusLabel();
            this.tsProgressBar = new System.Windows.Forms.ToolStripProgressBar();
            this.tsValCount = new System.Windows.Forms.ToolStripStatusLabel();
            this.tbTag1 = new System.Windows.Forms.TextBox();
            this.lbTag1 = new System.Windows.Forms.Label();
            this.btGetPIData = new System.Windows.Forms.Button();
            this.tbStartTime = new System.Windows.Forms.TextBox();
            this.tbEndTime = new System.Windows.Forms.TextBox();
            this.lbStartTime = new System.Windows.Forms.Label();
            this.lbEndtime = new System.Windows.Forms.Label();
            this.gbGetData = new System.Windows.Forms.GroupBox();
            this.cbShowAdvSet = new System.Windows.Forms.CheckBox();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.rbnInterp = new System.Windows.Forms.RadioButton();
            this.rbnRecorded = new System.Windows.Forms.RadioButton();
            this.tbInterval = new System.Windows.Forms.TextBox();
            this.UseDefault = new System.Windows.Forms.Button();
            this.lbTag5 = new System.Windows.Forms.Label();
            this.lbTag3 = new System.Windows.Forms.Label();
            this.lbTag4 = new System.Windows.Forms.Label();
            this.lbTag2 = new System.Windows.Forms.Label();
            this.tbTag5 = new System.Windows.Forms.TextBox();
            this.tbTag4 = new System.Windows.Forms.TextBox();
            this.tbTag3 = new System.Windows.Forms.TextBox();
            this.tbTag2 = new System.Windows.Forms.TextBox();
            this.lbMode = new System.Windows.Forms.Label();
            this.lbInterval = new System.Windows.Forms.Label();
            this.cbRFunctions = new System.Windows.Forms.ComboBox();
            this.lbRfunction = new System.Windows.Forms.Label();
            this.gbR = new System.Windows.Forms.GroupBox();
            this.lbNumTags = new System.Windows.Forms.Label();
            this.cbNumTags = new System.Windows.Forms.ComboBox();
            this.btGraphic = new System.Windows.Forms.Button();
            this.lbUsingRFunction = new System.Windows.Forms.Label();
            this.lbCurrRFunction = new System.Windows.Forms.Label();
            this.gbRFunctionInfo = new System.Windows.Forms.GroupBox();
            this.lbParam4 = new System.Windows.Forms.Label();
            this.cbParam4 = new System.Windows.Forms.ComboBox();
            this.cbParam3 = new System.Windows.Forms.ComboBox();
            this.cbParam2 = new System.Windows.Forms.ComboBox();
            this.cbParam1 = new System.Windows.Forms.ComboBox();
            this.lbParam3 = new System.Windows.Forms.Label();
            this.lbParam2 = new System.Windows.Forms.Label();
            this.lbParam1 = new System.Windows.Forms.Label();
            this.gbPIDA = new System.Windows.Forms.GroupBox();
            this.rbPIWA = new System.Windows.Forms.RadioButton();
            this.rbPIAFSDK = new System.Windows.Forms.RadioButton();
            this.StatusStrip.SuspendLayout();
            this.gbGetData.SuspendLayout();
            this.gbR.SuspendLayout();
            this.gbRFunctionInfo.SuspendLayout();
            this.gbPIDA.SuspendLayout();
            this.SuspendLayout();
            // 
            // btConnect
            // 
            this.btConnect.Location = new System.Drawing.Point(314, 45);
            this.btConnect.Name = "btConnect";
            this.btConnect.Size = new System.Drawing.Size(75, 23);
            this.btConnect.TabIndex = 0;
            this.btConnect.Text = "Connect";
            this.btConnect.UseVisualStyleBackColor = true;
            this.btConnect.Click += new System.EventHandler(this.bConnect_Click);
            // 
            // lbPIServerName
            // 
            this.lbPIServerName.AutoSize = true;
            this.lbPIServerName.Location = new System.Drawing.Point(30, 22);
            this.lbPIServerName.Name = "lbPIServerName";
            this.lbPIServerName.Size = new System.Drawing.Size(183, 13);
            this.lbPIServerName.TabIndex = 1;
            this.lbPIServerName.Text = "Please select a PI Server to connect:";
            // 
            // cbPIServersList
            // 
            this.cbPIServersList.FormattingEnabled = true;
            this.cbPIServersList.Location = new System.Drawing.Point(33, 47);
            this.cbPIServersList.Name = "cbPIServersList";
            this.cbPIServersList.Size = new System.Drawing.Size(264, 21);
            this.cbPIServersList.TabIndex = 4;
            this.cbPIServersList.DropDown += new System.EventHandler(this.comboBox1_DropDown);
            // 
            // tsPISDKVersion
            // 
            this.tsPISDKVersion.Name = "tsPISDKVersion";
            this.tsPISDKVersion.Size = new System.Drawing.Size(124, 25);
            this.tsPISDKVersion.Text = "PISDK Version";
            // 
            // StatusStrip
            // 
            this.StatusStrip.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.StatusStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsPISDKVersion,
            this.tsConnected,
            this.tsProgressBar,
            this.tsValCount});
            this.StatusStrip.Location = new System.Drawing.Point(0, 618);
            this.StatusStrip.Name = "StatusStrip";
            this.StatusStrip.Size = new System.Drawing.Size(739, 30);
            this.StatusStrip.TabIndex = 6;
            this.StatusStrip.Text = "StatusStrip";
            // 
            // tsConnected
            // 
            this.tsConnected.Name = "tsConnected";
            this.tsConnected.Size = new System.Drawing.Size(132, 25);
            this.tsConnected.Text = "Not Connected";
            // 
            // tsProgressBar
            // 
            this.tsProgressBar.Name = "tsProgressBar";
            this.tsProgressBar.Size = new System.Drawing.Size(100, 24);
            // 
            // tsValCount
            // 
            this.tsValCount.Name = "tsValCount";
            this.tsValCount.Size = new System.Drawing.Size(208, 25);
            this.tsValCount.Text = "No PI values on memory";
            // 
            // tbTag1
            // 
            this.tbTag1.Location = new System.Drawing.Point(106, 138);
            this.tbTag1.Name = "tbTag1";
            this.tbTag1.Size = new System.Drawing.Size(262, 20);
            this.tbTag1.TabIndex = 7;
            // 
            // lbTag1
            // 
            this.lbTag1.AutoSize = true;
            this.lbTag1.Location = new System.Drawing.Point(33, 141);
            this.lbTag1.Name = "lbTag1";
            this.lbTag1.Size = new System.Drawing.Size(66, 13);
            this.lbTag1.TabIndex = 1;
            this.lbTag1.Text = "Tag Name 5";
            // 
            // btGetPIData
            // 
            this.btGetPIData.Location = new System.Drawing.Point(267, 314);
            this.btGetPIData.Name = "btGetPIData";
            this.btGetPIData.Size = new System.Drawing.Size(75, 23);
            this.btGetPIData.TabIndex = 0;
            this.btGetPIData.Text = "Get PI Data!";
            this.btGetPIData.UseVisualStyleBackColor = true;
            this.btGetPIData.Click += new System.EventHandler(this.bGetData_Click);
            // 
            // tbStartTime
            // 
            this.tbStartTime.Location = new System.Drawing.Point(106, 164);
            this.tbStartTime.Name = "tbStartTime";
            this.tbStartTime.Size = new System.Drawing.Size(262, 20);
            this.tbStartTime.TabIndex = 7;
            // 
            // tbEndTime
            // 
            this.tbEndTime.Location = new System.Drawing.Point(106, 191);
            this.tbEndTime.Name = "tbEndTime";
            this.tbEndTime.Size = new System.Drawing.Size(262, 20);
            this.tbEndTime.TabIndex = 7;
            // 
            // lbStartTime
            // 
            this.lbStartTime.AutoSize = true;
            this.lbStartTime.Location = new System.Drawing.Point(33, 167);
            this.lbStartTime.Name = "lbStartTime";
            this.lbStartTime.Size = new System.Drawing.Size(55, 13);
            this.lbStartTime.TabIndex = 1;
            this.lbStartTime.Text = "Start Time";
            // 
            // lbEndtime
            // 
            this.lbEndtime.AutoSize = true;
            this.lbEndtime.Location = new System.Drawing.Point(33, 194);
            this.lbEndtime.Name = "lbEndtime";
            this.lbEndtime.Size = new System.Drawing.Size(48, 13);
            this.lbEndtime.TabIndex = 1;
            this.lbEndtime.Text = "End time";
            // 
            // gbGetData
            // 
            this.gbGetData.Controls.Add(this.cbShowAdvSet);
            this.gbGetData.Controls.Add(this.comboBox1);
            this.gbGetData.Controls.Add(this.rbnInterp);
            this.gbGetData.Controls.Add(this.rbnRecorded);
            this.gbGetData.Controls.Add(this.tbStartTime);
            this.gbGetData.Controls.Add(this.tbInterval);
            this.gbGetData.Controls.Add(this.tbEndTime);
            this.gbGetData.Controls.Add(this.UseDefault);
            this.gbGetData.Controls.Add(this.btGetPIData);
            this.gbGetData.Controls.Add(this.lbTag5);
            this.gbGetData.Controls.Add(this.lbTag3);
            this.gbGetData.Controls.Add(this.lbTag4);
            this.gbGetData.Controls.Add(this.lbTag2);
            this.gbGetData.Controls.Add(this.lbTag1);
            this.gbGetData.Controls.Add(this.tbTag5);
            this.gbGetData.Controls.Add(this.tbTag4);
            this.gbGetData.Controls.Add(this.tbTag3);
            this.gbGetData.Controls.Add(this.tbTag2);
            this.gbGetData.Controls.Add(this.tbTag1);
            this.gbGetData.Controls.Add(this.lbStartTime);
            this.gbGetData.Controls.Add(this.lbMode);
            this.gbGetData.Controls.Add(this.lbInterval);
            this.gbGetData.Controls.Add(this.lbEndtime);
            this.gbGetData.Location = new System.Drawing.Point(298, 134);
            this.gbGetData.Name = "gbGetData";
            this.gbGetData.Size = new System.Drawing.Size(399, 355);
            this.gbGetData.TabIndex = 8;
            this.gbGetData.TabStop = false;
            this.gbGetData.Text = "PI Tags information";
            this.gbGetData.Visible = false;
            // 
            // cbShowAdvSet
            // 
            this.cbShowAdvSet.AutoSize = true;
            this.cbShowAdvSet.Location = new System.Drawing.Point(164, 273);
            this.cbShowAdvSet.Name = "cbShowAdvSet";
            this.cbShowAdvSet.Size = new System.Drawing.Size(153, 21);
            this.cbShowAdvSet.TabIndex = 9;
            this.cbShowAdvSet.Text = "Show Advanced Settings";
            this.cbShowAdvSet.UseVisualStyleBackColor = true;
            this.cbShowAdvSet.CheckedChanged += new System.EventHandler(this.cbShowAdvSet_CheckedChanged);
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Location = new System.Drawing.Point(-147, 226);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(112, 21);
            this.comboBox1.TabIndex = 9;
            this.comboBox1.SelectedValueChanged += new System.EventHandler(this.bConfirmRFunction_Click);
            // 
            // rbnInterp
            // 
            this.rbnInterp.AutoSize = true;
            this.rbnInterp.Location = new System.Drawing.Point(261, 250);
            this.rbnInterp.Name = "rbnInterp";
            this.rbnInterp.Size = new System.Drawing.Size(88, 20);
            this.rbnInterp.TabIndex = 8;
            this.rbnInterp.TabStop = true;
            this.rbnInterp.Text = "Interpolated";
            this.rbnInterp.UseVisualStyleBackColor = true;
            this.rbnInterp.Click += new System.EventHandler(this.rbnInterp_Click);
            // 
            // rbnRecorded
            // 
            this.rbnRecorded.AutoSize = true;
            this.rbnRecorded.Location = new System.Drawing.Point(137, 250);
            this.rbnRecorded.Name = "rbnRecorded";
            this.rbnRecorded.Size = new System.Drawing.Size(79, 20);
            this.rbnRecorded.TabIndex = 8;
            this.rbnRecorded.TabStop = true;
            this.rbnRecorded.Text = "Recorded";
            this.rbnRecorded.UseVisualStyleBackColor = true;
            this.rbnRecorded.Click += new System.EventHandler(this.rbnRecorded_Click);
            // 
            // tbInterval
            // 
            this.tbInterval.Location = new System.Drawing.Point(106, 219);
            this.tbInterval.Name = "tbInterval";
            this.tbInterval.Size = new System.Drawing.Size(262, 20);
            this.tbInterval.TabIndex = 7;
            this.tbInterval.Visible = false;
            // 
            // UseDefault
            // 
            this.UseDefault.Location = new System.Drawing.Point(134, 314);
            this.UseDefault.Name = "UseDefault";
            this.UseDefault.Size = new System.Drawing.Size(75, 23);
            this.UseDefault.TabIndex = 0;
            this.UseDefault.Text = "Use Default";
            this.UseDefault.UseVisualStyleBackColor = true;
            this.UseDefault.Click += new System.EventHandler(this.btUseDefault_Click);
            // 
            // lbTag5
            // 
            this.lbTag5.AutoSize = true;
            this.lbTag5.Location = new System.Drawing.Point(33, 33);
            this.lbTag5.Name = "lbTag5";
            this.lbTag5.Size = new System.Drawing.Size(66, 13);
            this.lbTag5.TabIndex = 1;
            this.lbTag5.Text = "Tag Name 1";
            // 
            // lbTag3
            // 
            this.lbTag3.AutoSize = true;
            this.lbTag3.Location = new System.Drawing.Point(33, 85);
            this.lbTag3.Name = "lbTag3";
            this.lbTag3.Size = new System.Drawing.Size(66, 13);
            this.lbTag3.TabIndex = 1;
            this.lbTag3.Text = "Tag Name 3";
            // 
            // lbTag4
            // 
            this.lbTag4.AutoSize = true;
            this.lbTag4.Location = new System.Drawing.Point(33, 60);
            this.lbTag4.Name = "lbTag4";
            this.lbTag4.Size = new System.Drawing.Size(66, 13);
            this.lbTag4.TabIndex = 1;
            this.lbTag4.Text = "Tag Name 2";
            // 
            // lbTag2
            // 
            this.lbTag2.AutoSize = true;
            this.lbTag2.Location = new System.Drawing.Point(33, 112);
            this.lbTag2.Name = "lbTag2";
            this.lbTag2.Size = new System.Drawing.Size(66, 13);
            this.lbTag2.TabIndex = 1;
            this.lbTag2.Text = "Tag Name 4";
            // 
            // tbTag5
            // 
            this.tbTag5.Location = new System.Drawing.Point(106, 33);
            this.tbTag5.Name = "tbTag5";
            this.tbTag5.Size = new System.Drawing.Size(262, 20);
            this.tbTag5.TabIndex = 7;
            // 
            // tbTag4
            // 
            this.tbTag4.Location = new System.Drawing.Point(106, 60);
            this.tbTag4.Name = "tbTag4";
            this.tbTag4.Size = new System.Drawing.Size(262, 20);
            this.tbTag4.TabIndex = 7;
            // 
            // tbTag3
            // 
            this.tbTag3.Location = new System.Drawing.Point(106, 85);
            this.tbTag3.Name = "tbTag3";
            this.tbTag3.Size = new System.Drawing.Size(262, 20);
            this.tbTag3.TabIndex = 7;
            // 
            // tbTag2
            // 
            this.tbTag2.Location = new System.Drawing.Point(106, 112);
            this.tbTag2.Name = "tbTag2";
            this.tbTag2.Size = new System.Drawing.Size(262, 20);
            this.tbTag2.TabIndex = 7;
            // 
            // lbMode
            // 
            this.lbMode.AutoSize = true;
            this.lbMode.Location = new System.Drawing.Point(33, 250);
            this.lbMode.Name = "lbMode";
            this.lbMode.Size = new System.Drawing.Size(34, 13);
            this.lbMode.TabIndex = 1;
            this.lbMode.Text = "Mode";
            // 
            // lbInterval
            // 
            this.lbInterval.AutoSize = true;
            this.lbInterval.Location = new System.Drawing.Point(33, 222);
            this.lbInterval.Name = "lbInterval";
            this.lbInterval.Size = new System.Drawing.Size(42, 13);
            this.lbInterval.TabIndex = 1;
            this.lbInterval.Text = "Interval";
            this.lbInterval.Visible = false;
            // 
            // cbRFunctions
            // 
            this.cbRFunctions.FormattingEnabled = true;
            this.cbRFunctions.Location = new System.Drawing.Point(20, 67);
            this.cbRFunctions.Name = "cbRFunctions";
            this.cbRFunctions.Size = new System.Drawing.Size(210, 21);
            this.cbRFunctions.TabIndex = 9;
            this.cbRFunctions.SelectedValueChanged += new System.EventHandler(this.bConfirmRFunction_Click);
            // 
            // lbRfunction
            // 
            this.lbRfunction.AutoSize = true;
            this.lbRfunction.Location = new System.Drawing.Point(17, 33);
            this.lbRfunction.Name = "lbRfunction";
            this.lbRfunction.Size = new System.Drawing.Size(125, 13);
            this.lbRfunction.TabIndex = 1;
            this.lbRfunction.Text = "Please select R function:";
            // 
            // gbR
            // 
            this.gbR.Controls.Add(this.lbNumTags);
            this.gbR.Controls.Add(this.lbRfunction);
            this.gbR.Controls.Add(this.cbNumTags);
            this.gbR.Controls.Add(this.cbRFunctions);
            this.gbR.Location = new System.Drawing.Point(33, 134);
            this.gbR.Name = "gbR";
            this.gbR.Size = new System.Drawing.Size(248, 196);
            this.gbR.TabIndex = 10;
            this.gbR.TabStop = false;
            this.gbR.Text = "R information";
            this.gbR.Visible = false;
            // 
            // lbNumTags
            // 
            this.lbNumTags.AutoSize = true;
            this.lbNumTags.Location = new System.Drawing.Point(17, 115);
            this.lbNumTags.Name = "lbNumTags";
            this.lbNumTags.Size = new System.Drawing.Size(91, 13);
            this.lbNumTags.TabIndex = 1;
            this.lbNumTags.Text = "Numbers of Tags:";
            this.lbNumTags.Visible = false;
            // 
            // cbNumTags
            // 
            this.cbNumTags.FormattingEnabled = true;
            this.cbNumTags.Location = new System.Drawing.Point(20, 141);
            this.cbNumTags.Name = "cbNumTags";
            this.cbNumTags.Size = new System.Drawing.Size(210, 21);
            this.cbNumTags.TabIndex = 9;
            this.cbNumTags.Visible = false;
            this.cbNumTags.SelectedValueChanged += new System.EventHandler(this.cbNumTags_SelectedValueChanged);
            // 
            // btGraphic
            // 
            this.btGraphic.Location = new System.Drawing.Point(481, 550);
            this.btGraphic.Name = "btGraphic";
            this.btGraphic.Size = new System.Drawing.Size(125, 39);
            this.btGraphic.TabIndex = 0;
            this.btGraphic.Text = "Generate Graphic!";
            this.btGraphic.UseVisualStyleBackColor = true;
            this.btGraphic.Visible = false;
            this.btGraphic.Click += new System.EventHandler(this.btGenGraph);
            // 
            // lbUsingRFunction
            // 
            this.lbUsingRFunction.AutoSize = true;
            this.lbUsingRFunction.Location = new System.Drawing.Point(459, 514);
            this.lbUsingRFunction.Name = "lbUsingRFunction";
            this.lbUsingRFunction.Size = new System.Drawing.Size(92, 13);
            this.lbUsingRFunction.TabIndex = 1;
            this.lbUsingRFunction.Text = "Using R Function:";
            this.lbUsingRFunction.Visible = false;
            // 
            // lbCurrRFunction
            // 
            this.lbCurrRFunction.AutoSize = true;
            this.lbCurrRFunction.Location = new System.Drawing.Point(560, 514);
            this.lbCurrRFunction.Name = "lbCurrRFunction";
            this.lbCurrRFunction.Size = new System.Drawing.Size(105, 13);
            this.lbCurrRFunction.TabIndex = 1;
            this.lbCurrRFunction.Text = "No function selected";
            this.lbCurrRFunction.Visible = false;
            // 
            // gbRFunctionInfo
            // 
            this.gbRFunctionInfo.Controls.Add(this.lbParam4);
            this.gbRFunctionInfo.Controls.Add(this.cbParam4);
            this.gbRFunctionInfo.Controls.Add(this.cbParam3);
            this.gbRFunctionInfo.Controls.Add(this.cbParam2);
            this.gbRFunctionInfo.Controls.Add(this.cbParam1);
            this.gbRFunctionInfo.Controls.Add(this.lbParam3);
            this.gbRFunctionInfo.Controls.Add(this.lbParam2);
            this.gbRFunctionInfo.Controls.Add(this.lbParam1);
            this.gbRFunctionInfo.Location = new System.Drawing.Point(33, 353);
            this.gbRFunctionInfo.Name = "gbRFunctionInfo";
            this.gbRFunctionInfo.Size = new System.Drawing.Size(248, 216);
            this.gbRFunctionInfo.TabIndex = 11;
            this.gbRFunctionInfo.TabStop = false;
            this.gbRFunctionInfo.Text = "R Function Information";
            this.gbRFunctionInfo.Visible = false;
            // 
            // lbParam4
            // 
            this.lbParam4.AutoSize = true;
            this.lbParam4.Location = new System.Drawing.Point(20, 165);
            this.lbParam4.Name = "lbParam4";
            this.lbParam4.Size = new System.Drawing.Size(35, 13);
            this.lbParam4.TabIndex = 0;
            this.lbParam4.Text = "label1";
            // 
            // cbParam4
            // 
            this.cbParam4.FormattingEnabled = true;
            this.cbParam4.Location = new System.Drawing.Point(116, 161);
            this.cbParam4.Name = "cbParam4";
            this.cbParam4.Size = new System.Drawing.Size(114, 21);
            this.cbParam4.TabIndex = 9;
            // 
            // cbParam3
            // 
            this.cbParam3.FormattingEnabled = true;
            this.cbParam3.Location = new System.Drawing.Point(116, 122);
            this.cbParam3.Name = "cbParam3";
            this.cbParam3.Size = new System.Drawing.Size(114, 21);
            this.cbParam3.TabIndex = 9;
            // 
            // cbParam2
            // 
            this.cbParam2.FormattingEnabled = true;
            this.cbParam2.Location = new System.Drawing.Point(116, 82);
            this.cbParam2.Name = "cbParam2";
            this.cbParam2.Size = new System.Drawing.Size(114, 21);
            this.cbParam2.TabIndex = 9;
            // 
            // cbParam1
            // 
            this.cbParam1.FormattingEnabled = true;
            this.cbParam1.Location = new System.Drawing.Point(116, 39);
            this.cbParam1.Name = "cbParam1";
            this.cbParam1.Size = new System.Drawing.Size(114, 21);
            this.cbParam1.TabIndex = 9;
            // 
            // lbParam3
            // 
            this.lbParam3.AutoSize = true;
            this.lbParam3.Location = new System.Drawing.Point(17, 130);
            this.lbParam3.Name = "lbParam3";
            this.lbParam3.Size = new System.Drawing.Size(35, 13);
            this.lbParam3.TabIndex = 0;
            this.lbParam3.Text = "label1";
            // 
            // lbParam2
            // 
            this.lbParam2.AutoSize = true;
            this.lbParam2.Location = new System.Drawing.Point(20, 90);
            this.lbParam2.Name = "lbParam2";
            this.lbParam2.Size = new System.Drawing.Size(35, 13);
            this.lbParam2.TabIndex = 0;
            this.lbParam2.Text = "label1";
            // 
            // lbParam1
            // 
            this.lbParam1.AutoSize = true;
            this.lbParam1.Location = new System.Drawing.Point(20, 42);
            this.lbParam1.Name = "lbParam1";
            this.lbParam1.Size = new System.Drawing.Size(35, 13);
            this.lbParam1.TabIndex = 0;
            this.lbParam1.Text = "label1";
            // 
            // gbPIDA
            // 
            this.gbPIDA.Controls.Add(this.rbPIWA);
            this.gbPIDA.Controls.Add(this.rbPIAFSDK);
            this.gbPIDA.Location = new System.Drawing.Point(476, 12);
            this.gbPIDA.Name = "gbPIDA";
            this.gbPIDA.Size = new System.Drawing.Size(221, 81);
            this.gbPIDA.TabIndex = 12;
            this.gbPIDA.TabStop = false;
            this.gbPIDA.Text = "PI Data Access Method";
            // 
            // rbPIWA
            // 
            this.rbPIWA.AutoSize = true;
            this.rbPIWA.Location = new System.Drawing.Point(39, 45);
            this.rbPIWA.Name = "rbPIWA";
            this.rbPIWA.Size = new System.Drawing.Size(88, 20);
            this.rbPIWA.TabIndex = 2;
            this.rbPIWA.Text = "PI Web API";
            this.rbPIWA.UseVisualStyleBackColor = true;
            this.rbPIWA.CheckedChanged += new System.EventHandler(this.PIDataAccessMethodChanged);
            // 
            // rbPIAFSDK
            // 
            this.rbPIAFSDK.AutoSize = true;
            this.rbPIAFSDK.Checked = true;
            this.rbPIAFSDK.Location = new System.Drawing.Point(39, 19);
            this.rbPIAFSDK.Name = "rbPIAFSDK";
            this.rbPIAFSDK.Size = new System.Drawing.Size(83, 20);
            this.rbPIAFSDK.TabIndex = 0;
            this.rbPIAFSDK.TabStop = true;
            this.rbPIAFSDK.Text = "PI AF SDK";
            this.rbPIAFSDK.UseVisualStyleBackColor = true;
            this.rbPIAFSDK.CheckedChanged += new System.EventHandler(this.PIDataAccessMethodChanged);
            // 
            // WinForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(739, 648);
            this.Controls.Add(this.gbPIDA);
            this.Controls.Add(this.gbRFunctionInfo);
            this.Controls.Add(this.gbR);
            this.Controls.Add(this.gbGetData);
            this.Controls.Add(this.btGraphic);
            this.Controls.Add(this.lbCurrRFunction);
            this.Controls.Add(this.lbUsingRFunction);
            this.Controls.Add(this.StatusStrip);
            this.Controls.Add(this.cbPIServersList);
            this.Controls.Add(this.lbPIServerName);
            this.Controls.Add(this.btConnect);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "WinForm";
            this.Text = "Integrating PI System and R";
            this.StatusStrip.ResumeLayout(false);
            this.StatusStrip.PerformLayout();
            this.gbGetData.ResumeLayout(false);
            this.gbGetData.PerformLayout();
            this.gbR.ResumeLayout(false);
            this.gbR.PerformLayout();
            this.gbRFunctionInfo.ResumeLayout(false);
            this.gbRFunctionInfo.PerformLayout();
            this.gbPIDA.ResumeLayout(false);
            this.gbPIDA.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btConnect;
        private System.Windows.Forms.Label lbPIServerName;
        private System.Windows.Forms.ComboBox cbPIServersList;
        private System.Windows.Forms.ToolStripStatusLabel tsPISDKVersion;
        private System.Windows.Forms.StatusStrip StatusStrip;
        private System.Windows.Forms.ToolStripStatusLabel tsConnected;
        private System.Windows.Forms.TextBox tbTag1;
        private System.Windows.Forms.Label lbTag1;
        private System.Windows.Forms.Button btGetPIData;
        private System.Windows.Forms.TextBox tbStartTime;
        private System.Windows.Forms.TextBox tbEndTime;
        private System.Windows.Forms.Label lbStartTime;
        private System.Windows.Forms.Label lbEndtime;
        private System.Windows.Forms.GroupBox gbGetData;
        private System.Windows.Forms.ToolStripStatusLabel tsValCount;
        private System.Windows.Forms.ToolStripProgressBar tsProgressBar;
        private System.Windows.Forms.ComboBox cbRFunctions;
        private System.Windows.Forms.Label lbRfunction;
        private System.Windows.Forms.GroupBox gbR;
        private System.Windows.Forms.Button btGraphic;
        private System.Windows.Forms.Label lbTag2;
        private System.Windows.Forms.TextBox tbTag2;
        private System.Windows.Forms.Label lbUsingRFunction;
        private System.Windows.Forms.Label lbCurrRFunction;
        private System.Windows.Forms.Button UseDefault;
        private System.Windows.Forms.Label lbMode;
        private System.Windows.Forms.Label lbTag3;
        private System.Windows.Forms.TextBox tbTag3;
        private System.Windows.Forms.RadioButton rbnInterp;
        private System.Windows.Forms.RadioButton rbnRecorded;
        private System.Windows.Forms.Label lbInterval;
        private System.Windows.Forms.TextBox tbInterval;
        private System.Windows.Forms.GroupBox gbRFunctionInfo;
        private System.Windows.Forms.Label lbParam4;
        private System.Windows.Forms.Label lbParam3;
        private System.Windows.Forms.Label lbParam2;
        private System.Windows.Forms.Label lbParam1;
        private System.Windows.Forms.CheckBox cbShowAdvSet;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.ComboBox cbParam4;
        private System.Windows.Forms.ComboBox cbParam3;
        private System.Windows.Forms.ComboBox cbParam2;
        private System.Windows.Forms.ComboBox cbParam1;
        private System.Windows.Forms.Label lbTag5;
        private System.Windows.Forms.Label lbTag4;
        private System.Windows.Forms.TextBox tbTag5;
        private System.Windows.Forms.TextBox tbTag4;
        private System.Windows.Forms.Label lbNumTags;
        private System.Windows.Forms.ComboBox cbNumTags;
        private System.Windows.Forms.GroupBox gbPIDA;
        private System.Windows.Forms.RadioButton rbPIWA;
        private System.Windows.Forms.RadioButton rbPIAFSDK;
    }
}


