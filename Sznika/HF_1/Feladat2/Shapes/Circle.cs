using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shapes
{
    internal class Circle : Shape
    {
        private int radius;

        public Circle(int x, int y, int radius) : base(x, y)
        {
            this.radius = radius;
        }

        public override double getArea()
        {
            return radius*radius*Math.PI;
        }

        public override string getType()
        {
            return "Circle";
        }
    }
}
