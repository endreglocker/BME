using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shapes
{
    internal class Square : Shape
    {
        private int width;

        public Square(int x, int y, int width) : base(x, y)
        { 
           this.width = width;
        
        }

        public override double getArea()
        {
            return width*width;
        }

        public override string getType()
        {
            return "Square";
        }
    }
}
