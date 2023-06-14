using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shapes
{
    internal class ShapeContainer
    {
        private List<ShapeInterface> shapeStorage;
        public ShapeContainer()
        {
            shapeStorage = new List<ShapeInterface>();
        }

        public void ListAll()
        {
            foreach (ShapeInterface shape in shapeStorage)
            {
                Console.WriteLine($"Leírás: {shape.getType()}\tKoordinata: {shape.getX()} {shape.getY()}\tTerület: {shape.getArea()}");
            }
        }

        public void AddShape(ShapeInterface s)
        {
            shapeStorage.Add(s);
        }
    }
}
