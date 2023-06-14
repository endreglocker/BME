namespace WinFormExpl
{
    partial class InputDialog
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
            tPath = new TextBox();
            bOk = new Button();
            bCancel = new Button();
            label1 = new Label();
            SuspendLayout();
            // 
            // tPath
            // 
            tPath.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            tPath.Location = new Point(53, 183);
            tPath.Name = "tPath";
            tPath.Size = new Size(704, 23);
            tPath.TabIndex = 0;
            // 
            // bOk
            // 
            bOk.Anchor = AnchorStyles.Bottom | AnchorStyles.Left;
            bOk.DialogResult = DialogResult.OK;
            bOk.Location = new Point(53, 264);
            bOk.Name = "bOk";
            bOk.Size = new Size(75, 23);
            bOk.TabIndex = 2;
            bOk.Text = "Ok";
            bOk.UseVisualStyleBackColor = true;
            // 
            // bCancel
            // 
            bCancel.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            bCancel.DialogResult = DialogResult.Cancel;
            bCancel.Location = new Point(682, 264);
            bCancel.Name = "bCancel";
            bCancel.Size = new Size(75, 23);
            bCancel.TabIndex = 3;
            bCancel.Text = "Cancel";
            bCancel.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            label1.Anchor = AnchorStyles.Top | AnchorStyles.Right;
            label1.AutoSize = true;
            label1.Location = new Point(384, 123);
            label1.Name = "label1";
            label1.Size = new Size(31, 15);
            label1.TabIndex = 4;
            label1.Text = "Path";
            // 
            // InputDialog
            // 
            AcceptButton = bOk;
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            CancelButton = bCancel;
            ClientSize = new Size(800, 450);
            Controls.Add(label1);
            Controls.Add(bCancel);
            Controls.Add(bOk);
            Controls.Add(tPath);
            Name = "InputDialog";
            Text = "InputDialog";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox tPath;
        private Button bOk;
        private Button bCancel;
        private Label label1;
    }
}