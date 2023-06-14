using Controls;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shapes
{
    internal class TextArea: Textbox, ShapeInterface
    {
        public TextArea(int x, int y, int w, int h) : base(x, y, w, h)
        {
        }


        public /*override*/ double getArea()
        {
            return GetHeight() * GetWidth();
        }

        public /*override*/ string getType()
        {
            return "TextArea";
        }

        public int getX() { return GetX(); }

        public int getY() { return GetY(); }
    }
}
