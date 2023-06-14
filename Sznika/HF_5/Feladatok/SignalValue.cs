using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Signals
{
    public class SignalValue
    {
        private readonly double Value;
        private readonly DateTime TimeStamp;

        public double getValue() { return Value; }
        public DateTime getTimeStamp() { return TimeStamp; }

        public SignalValue(double v, DateTime time)
        {
            Value = v;
            TimeStamp = time;
        }

        public override string ToString()
        {
            return $"Value: {Value}, TimeStamp: {TimeStamp}";
        }

    }
}
