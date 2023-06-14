using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Shapes
{
    public abstract class Shape: ShapeInterface
    {
        protected int x;
        protected int y;

        public Shape(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public abstract double getArea();

        public abstract string getType();

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }
    }
}