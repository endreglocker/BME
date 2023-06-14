using System.Diagnostics.SymbolStore;
using System.IO;

namespace WinFormExpl
{
    public partial class MainForm : Form
    {
        private FileInfo loadedFile = null;
        int counter;
        readonly int counterInitialValue;

        string directory;

        public MainForm()
        {
            InitializeComponent();
            counterInitialValue = 60;
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void miExit_Click(object sender, EventArgs e)
        {
            // Close on click
            Close();
        }

        private void miOpen_Click(object sender, EventArgs e)
        {
            // Opening the selected directories & files
            var dlg = new InputDialog();
            if (dlg.ShowDialog() == DialogResult.OK)
            {
                // MessageBox.Show(result);

                // setting the path
                directory = dlg.Path;
                /// loading to the files and directories to listView
                loadData();

            }
        }

        private void loadData()
        {
            /// creating the container directory
            /// if it fails return
            var containerDirectory = new DirectoryInfo(directory);
            if (containerDirectory == null) return;

            /// clearing listView
            listView1.Items.Clear();
            try
            {
                /// If the container does have a parent directory 
                if (containerDirectory.Parent != null)
                {
                    /// Adding a way to switch to the parent directory
                    listView1.Items.Add(new ListViewItem(new string[] { "..", " " }) { Tag = containerDirectory.Parent });
                }

                /// Loading directories
                foreach (var di in containerDirectory.GetDirectories())
                {
                    listView1.Items.Add(new ListViewItem(new string[] { di.Name, " " }) { Tag = di });
                }

                /// Loading files
                foreach (FileInfo fi in containerDirectory.GetFiles())
                {
                    listView1.Items.Add(
                        new ListViewItem(
                            new string[]{
                                fi.Name, fi.Length.ToString()
                            })
                        { Tag = fi });
                }
            }
            catch (UnauthorizedAccessException ex)
            {
                /// if something is not OK
            }

        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count != 1) return;

            /// Casting to directory / file depending on what will you click on
            FileInfo selectedFile = listView1.SelectedItems[0].Tag as FileInfo;
            DirectoryInfo selectedDirectory = listView1.SelectedItems[0].Tag as DirectoryInfo;
            
            /// Either clicking on a directory or a file
            /// Writing the name & time of creation
            
            if (selectedDirectory != null)
            {
                lName.Text = selectedDirectory.Name;
                lCreated.Text = selectedDirectory.CreationTime.ToString();
            }

            if (selectedFile != null)
            {
                lName.Text = selectedFile.Name;
                lCreated.Text = selectedFile.CreationTime.ToString();
            }

        }

        private void listView1_DoubleClick(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count != 1) return;

            /// Casting to directory / file depending on what will you click on
            FileInfo selectedFile = listView1.SelectedItems[0].Tag as FileInfo;
            DirectoryInfo selectedDirectory = listView1.SelectedItems[0].Tag as DirectoryInfo;


            /// If you double click on a directory it will open it, and reload the listView
            if (selectedDirectory != null)
            {
                //miOpen_Click(sender, new EventArgs());
                directory = selectedDirectory.FullName;
                loadData();
            }

            /// If you double click on a file this will show the file's content
            if (selectedFile != null)
            {
                reloadTimer.Start();
                counter = counterInitialValue;
                loadedFile = selectedFile;
                tContent.Text = File.ReadAllText(selectedFile.FullName);
            }

        }

        private void reloadTimer_Tick(object sender, EventArgs e)
        {
            /// Decrease timer
            counter--;

            // Fontos! Ez váltja ki a Paint eseményt
            // és ezzel a téglalap újrarajzolását
            detailsPanel.Invalidate();

            /// Set timer & reload content
            if (counter <= 0)
            {
                counter = counterInitialValue;
                tContent.Text = File.ReadAllText(loadedFile.FullName);
            }
        }

        private void detailsPanel_Paint(object sender, PaintEventArgs e)
        {
            if (loadedFile != null)
            {
                // A téglalap szélessége a téglalap kezdõhosszúságából (adott a feladatkiírásban) számítható,
                // szorozva a számláló aktuális és max értékének arányával
                e.Graphics.FillRectangle(Brushes.Brown, 0, 0, 125 * counter / 60, 5);
            }
        }
    }
}