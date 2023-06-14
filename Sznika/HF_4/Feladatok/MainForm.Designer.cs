namespace MultiThreadedApp
{
    partial class MainForm
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            pTarget = new Panel();
            bBike1 = new Button();
            bStart = new Button();
            bBike2 = new Button();
            bBike3 = new Button();
            sTarget = new Panel();
            bStep1 = new Button();
            sTarget2 = new Panel();
            bStep2 = new Button();
            countingButton = new Button();
            bStop = new Button();
            SuspendLayout();
            // 
            // pTarget
            // 
            pTarget.BackColor = Color.LightSteelBlue;
            pTarget.Location = new Point(677, 66);
            pTarget.Name = "pTarget";
            pTarget.Size = new Size(111, 211);
            pTarget.TabIndex = 0;
            // 
            // bBike1
            // 
            bBike1.Font = new Font("Webdings", 32F, FontStyle.Regular, GraphicsUnit.Point);
            bBike1.Location = new Point(25, 66);
            bBike1.Name = "bBike1";
            bBike1.Size = new Size(93, 50);
            bBike1.TabIndex = 1;
            bBike1.Text = "b";
            bBike1.UseVisualStyleBackColor = true;
            bBike1.Click += bike_Click;
            // 
            // bStart
            // 
            bStart.Location = new Point(25, 345);
            bStart.Name = "bStart";
            bStart.Size = new Size(93, 23);
            bStart.TabIndex = 2;
            bStart.Text = "Start";
            bStart.UseVisualStyleBackColor = true;
            bStart.Click += bStart_Click;
            // 
            // bBike2
            // 
            bBike2.Font = new Font("Webdings", 32F, FontStyle.Regular, GraphicsUnit.Point);
            bBike2.Location = new Point(25, 149);
            bBike2.Name = "bBike2";
            bBike2.Size = new Size(93, 50);
            bBike2.TabIndex = 3;
            bBike2.Text = "b";
            bBike2.UseVisualStyleBackColor = true;
            bBike2.Click += bike_Click;
            // 
            // bBike3
            // 
            bBike3.Font = new Font("Webdings", 32F, FontStyle.Regular, GraphicsUnit.Point);
            bBike3.Location = new Point(25, 227);
            bBike3.Name = "bBike3";
            bBike3.Size = new Size(93, 50);
            bBike3.TabIndex = 4;
            bBike3.Text = "b";
            bBike3.UseVisualStyleBackColor = true;
            bBike3.Click += bike_Click;
            // 
            // sTarget
            // 
            sTarget.BackColor = Color.LightSlateGray;
            sTarget.Location = new Point(240, 66);
            sTarget.Name = "sTarget";
            sTarget.Size = new Size(111, 211);
            sTarget.TabIndex = 1;
            // 
            // bStep1
            // 
            bStep1.Location = new Point(240, 386);
            bStep1.Name = "bStep1";
            bStep1.Size = new Size(111, 23);
            bStep1.TabIndex = 5;
            bStep1.Text = "Step1";
            bStep1.UseVisualStyleBackColor = true;
            bStep1.Click += bStep1_Click;
            // 
            // sTarget2
            // 
            sTarget2.BackColor = Color.CornflowerBlue;
            sTarget2.Location = new Point(457, 66);
            sTarget2.Name = "sTarget2";
            sTarget2.Size = new Size(111, 211);
            sTarget2.TabIndex = 2;
            // 
            // bStep2
            // 
            bStep2.Location = new Point(457, 386);
            bStep2.Name = "bStep2";
            bStep2.Size = new Size(111, 23);
            bStep2.TabIndex = 6;
            bStep2.Text = "Step2";
            bStep2.UseVisualStyleBackColor = true;
            bStep2.Click += bStep2_Click;
            // 
            // countingButton
            // 
            countingButton.Location = new Point(677, 386);
            countingButton.Name = "countingButton";
            countingButton.Size = new Size(111, 23);
            countingButton.TabIndex = 7;
            countingButton.Text = "Kilóméteróra";
            countingButton.UseVisualStyleBackColor = true;
            countingButton.Click += countingButton_Click;
            // 
            // bStop
            // 
            bStop.Location = new Point(25, 386);
            bStop.Name = "bStop";
            bStop.Size = new Size(93, 23);
            bStop.TabIndex = 8;
            bStop.Text = "Stop";
            bStop.UseVisualStyleBackColor = true;
            bStop.Click += bStop_Click;
            // 
            // MainForm
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 476);
            Controls.Add(bStop);
            Controls.Add(countingButton);
            Controls.Add(bStep2);
            Controls.Add(bStep1);
            Controls.Add(bBike3);
            Controls.Add(bBike2);
            Controls.Add(bStart);
            Controls.Add(bBike1);
            Controls.Add(pTarget);
            Controls.Add(sTarget);
            Controls.Add(sTarget2);
            Name = "MainForm";
            Text = "Tour de France - KBC838";
            ResumeLayout(false);
        }

        #endregion

        private Panel pTarget;
        private Button bBike1;
        private Button bStart;
        private Button bBike2;
        private Button bBike3;
        private Panel sTarget;
        private Button bStep1;
        private Panel sTarget2;
        private Button bStep2;
        private Button countingButton;
        private Button bStop;
    }
}