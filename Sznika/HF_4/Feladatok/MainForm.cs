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
                IsBackground = true, // Ne blokkolja a szál a processz megszûnését
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

            // Ha még nem indítottuk ez a szálat, ez null.
            if (thread == null)
                return;

            // Megszakítjuk a szál várakozását,
            // ez az adott szálban egy ThreadInterruptedException-t fog kiváltani
            // A függvény leírásáról részleteket az elõadás anyagaiban találsz
            thread.Interrupt();

            // Megvárjuk, amíg a szál leáll
            thread.Join();

            // Megjegyzés:  Az 5. feladat csak opcióként vetette fel, hogy az elsõ újrakezdés után tilcsuk le az opciót.
            //              (én jobban ragaszkodtam a többszöri újraindítás lehetõségéhez, ezért nem tíltottam le azt)
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