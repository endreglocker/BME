using Signals.DocView;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Signals
{
    public partial class GraphicsSignalView : UserControl, IView
    {
        private SignalDocument signaldocument;

        public GraphicsSignalView()
        {
            InitializeComponent();
        }

        public GraphicsSignalView(SignalDocument document) : this()
        {
            signaldocument = document;
        }

        public int ViewNumber { get; set; }

        public Document GetDocument()
        {
            return signaldocument;
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);

            /// kék ecset és ceruza beállítasa
            var brush = new SolidBrush(Color.Blue);
            var pen = new Pen(Color.Blue, 2)
            {
                DashStyle = DashStyle.Dot,
                EndCap = LineCap.ArrowAnchor,
            };

            /// ablak magassága és szélessége
            var h = ClientSize.Height;
            var w = ClientSize.Width;

            /// tengelyek kirajzolása
            e.Graphics.DrawLine(pen, 2, h, 2, 0);
            e.Graphics.DrawLine(pen, 2, h / 2, w, h / 2);

            IReadOnlyList<SignalValue> signals = signaldocument.Signals;

            var max = signals.Max(o => o.getValue());


            // idő szerint rendezem
            signals.OrderBy(o => o.getTimeStamp());

            // első és utolsó elem az összes eltelt idő kiszámításához
            var first = signals.First();
            var last = signals.Last();

            /// egyenletesen elosztjuk az értékeket az összes eltelt idő szerint
            var deltaTime = (last.getTimeStamp() - first.getTimeStamp()).Ticks;
            /// sebesség = összes út / összes idő
            /// a 'w-3' azért kellett, mert ha csak 'w'-t használnék, akkor az utolsó pont nem jelenne meg az ablakban
            double pixelPerSec = (double)(w - 3) / (double)deltaTime;

            /// mivel magasság/2 részen kell egyenletesen elosztani az értékeket
            var pixelPerValue = h / (2 * max);

            /// kezdőpont beállítása
            int? prevX = null;
            int? prevY = null;



            foreach (var s in signals)
            {
                /// eltelt idő
                var deltaTick = (s.getTimeStamp().Ticks - first.getTimeStamp().Ticks);

                /// eltelt idő * sebesség
                int currX = (int)(deltaTick * pixelPerSec * zoom);

                /// megfordítom az Y tengelyt, és arányosan kiszámolom, hogy az adott érték hol helyezkedik el a maximum értékhez képest.
                int currY = (int)(h - (zoom * s.getValue() * pixelPerValue + h / 2));

                /// pont kirajzolása
                e.Graphics.FillRectangle(brush, currX, currY, 3, 3);


                if (prevX != null && prevY != null)
                {
                    /// pontok összekötése
                    /// a jelenlegi pontot kötöm össze az előzővel
                    e.Graphics.DrawLine(new Pen(Color.Blue, 1), currX, currY, (int)prevX, (int)prevY);
                }

                /// előző pontok beállítása
                prevX = currX;
                prevY = currY;
            }                      
        }

        double zoom = 1;
        private void button1_Click(object sender, EventArgs e)
        {
            zoom *= 1.2;
            Invalidate();
        }
        
        private void button2_Click(object sender, EventArgs e)
        {
            zoom *= (1/1.2);
            Invalidate();
        }
    }
}
