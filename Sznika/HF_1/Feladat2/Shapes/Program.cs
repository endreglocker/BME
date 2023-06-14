namespace Shapes;

public class Program
{
    public static void Main(string[] args)
    {
        ShapeContainer storage = new ShapeContainer();

        storage.AddShape(new Circle(1, 2, 1));
        storage.AddShape(new Circle(3, 4, 2));
        storage.AddShape(new Circle(5, 6, 3));

        storage.AddShape(new TextArea(7, 8, 1, 1));
        storage.AddShape(new TextArea(9, 10, 1, 2));
        storage.AddShape(new TextArea(11, 12, 1, 3));
        
        storage.AddShape(new Square(13, 14, 1));
        storage.AddShape(new Square(15, 16, 2));
        storage.AddShape(new Square(17, 18, 3));

        storage.ListAll();
    }
}
