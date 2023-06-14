namespace MultiThreadedApp
{
    public partial class MainForm : Form
    {
        ManualResetEvent m_event = new ManualResetEvent(false);
        AutoResetEvent m_autoReset = new AutoResetEvent(false);

        long length = 0;
        Object m_object = new Object();

        int startLeft;

        public MainForm()
        {
            InitializeComponent();
        }

        public void BikeThreadFunction(object param)
        {
            try
            {
                var bike = (Button)param;
                bike.Tag = Thread.CurrentThread;

                while (bike.Left < sTarget.Left)
                {
                    MoveBike(bike);
                    stopBikeEvent.Set();
                    Thread.Sleep(100);
                }
                m_event.WaitOne();
                while (bike.Left < sTarget2.Left)
                {
                    MoveBike(bike);
                    stopBikeEvent.Set();
                    Thread.Sleep(100);
                }

                m_autoReset.WaitOne();
                while (bike.Left < pTarget.Left)
                {
                    MoveBike(bike);
                    stopBikeEvent.Set();
                    Thread.Sleep(100);
                }
            }
            catch { }

        }

        Random random = new Random();

        public void MoveBike(Button bike)
        {
            if (!stopBikes)
            {
                if (InvokeRequired)
                {
                    Invoke(MoveBike, bike);
                }
                else
                {
                    int temp = random.Next(2, 8);
                    bike.Left += temp;
                    IncreasePixels(temp);
                }
            }
            else { return; }
        }

        private void bStart_Click(object sender, EventArgs e)
        {
            startLeft = bBike1.Left;
            StartBike(bBike1);
            StartBike(bBike2);
            StartBike(bBike3);
        }

        private void StartBike(Button bBike)
        {
            var t = new Thread(BikeThreadFunction)
            {
                IsBackground = true, // Ne blokkolja a sz�l a processz megsz�n�s�t
            };

            bBike.Tag = t;
            t.Start(bBike);
        }

        private void bStep1_Click(object sender, EventArgs e)
        {
            m_event.Set();
        }

        private void bStep2_Click(object sender, EventArgs e)
        {
            m_autoReset.Set();
        }

        void IncreasePixels(long step) { lock (m_object) { length += step; } }

        long GetPixels()
        {
            lock (m_object) { return length; }
        }

        private void countingButton_Click(object sender, EventArgs e)
        {
            countingButton.Text = GetPixels().ToString();
        }

        private void bike_Click(object sender, EventArgs e)
        {
            Button bike = (Button)sender;
            Thread thread = (Thread)bike.Tag;

            // Ha m�g nem ind�tottuk ez a sz�lat, ez null.
            if (thread == null)
                return;

            // Megszak�tjuk a sz�l v�rakoz�s�t,
            // ez az adott sz�lban egy ThreadInterruptedException-t fog kiv�ltani
            // A f�ggv�ny le�r�s�r�l r�szleteket az el�ad�s anyagaiban tal�lsz
            thread.Interrupt();

            // Megv�rjuk, am�g a sz�l le�ll
            thread.Join();

            // Megjegyz�s:  Az 5. feladat csak opci�k�nt vetette fel, hogy az els� �jrakezd�s ut�n tilcsuk le az opci�t.
            //              (�n jobban ragaszkodtam a t�bbsz�ri �jraind�t�s lehet�s�g�hez, ez�rt nem t�ltottam le azt)
            bike.Left = startLeft;
            StartBike(bike);
            bStep1_Click(bike, new EventArgs());

        }

        bool stopBikes = false;
        ManualResetEvent stopBikeEvent = new ManualResetEvent(false);

        private void bStop_Click(object sender, EventArgs e)
        {
            stopBikeEvent.WaitOne();
            stopBikes = true;
        }
    }
}