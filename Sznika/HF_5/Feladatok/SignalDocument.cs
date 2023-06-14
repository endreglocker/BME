using Signals.DocView;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Signals
{
    public class SignalDocument : Document
    {
        private List<SignalValue> signals = new List<SignalValue>();

        private SignalValue[] testValues = new SignalValue[]
        {
        new SignalValue(1.0, new DateTime(2002, 6, 6, 6, 6, 6, 111)),
        new SignalValue(0.0, new DateTime(2002, 6, 6, 6, 6, 7, 111)),
        new SignalValue(3.0, new DateTime(2002, 6, 6, 6, 6, 8, 111)),
        new SignalValue(2.0, new DateTime(2002, 6, 6, 6, 6, 9, 111)),
        new SignalValue(-1.0, new DateTime(2002, 6, 6, 6, 6, 10, 111)),
        new SignalValue(5.0, new DateTime(2002, 6, 6, 6, 6, 11, 111)),
        new SignalValue(3.0, new DateTime(2002, 6, 6, 6, 6, 12, 111)),
        };

        public SignalDocument(string name)
            : base(name)
        {
            // Kezdetben dolgozzunk úgy, hogy a signals
            // jelérték listát a testValues alapján inicializáljuk.
            signals.AddRange(testValues);
        }

        public override void SaveDocument(string filePath)
        {
            using (StreamWriter sw = new StreamWriter(filePath))
            {
                foreach(SignalValue signs in  signals)
                {
                    var dt = signs.getTimeStamp().ToUniversalTime().ToString("o");
                    sw.WriteLine(signs.getValue().ToString() + "\t" + dt);
                }
                sw.Close();
            }
        }

        public override void LoadDocument(string filePath)
        {
            signals.Clear();
            using (StreamReader sr = new StreamReader(filePath))
            {
                string line;
                while ((line = sr.ReadLine()) != null)
                {
                    string[] columns = line.Trim().Split('\t');
                    double d = double.Parse(columns[0]);
                    DateTime dt = DateTime.Parse(columns[1]);
                    DateTime localDt = dt.ToLocalTime();
                    signals.Add(new SignalValue(d,dt));
                }
            }
            TraceValues();
            UpdateAllViews();
        }

        private void TraceValues()
        {
            foreach (var signal in signals)
                Trace.WriteLine(signal.ToString());
        }

        public IReadOnlyList<SignalValue> Signals
        {
            get { return signals; }
        }
    }
}
