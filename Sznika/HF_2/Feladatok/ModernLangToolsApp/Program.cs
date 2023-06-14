using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace ModernLangToolsApp;

public class Program
{
    [Description("Feladat2")]
    public static void SaveAndLoad(Jedi JediToFile)
    {
        /// Write
        var serializer = new XmlSerializer(typeof(Jedi));
        var stream = new StreamWriter(File.Open("jedi.txt", FileMode.Create));
        serializer.Serialize(stream, JediToFile);

        Process.Start(new ProcessStartInfo
        {
            FileName = "jedi.txt",
            UseShellExecute = true,
        });
        stream.Close();

        /// Read
        var serializer2 = new XmlSerializer(typeof(Jedi));
        var stream2 = new StreamReader(File.Open("jedi.txt", FileMode.Open));
        var JediFromFile = serializer2.Deserialize(stream2) as Jedi;
        Console.WriteLine($"{JediFromFile.Name} & {JediFromFile.MidiChlorianCount}");
        stream2.Close();

    }

    private static void MessageReceived(string message)
    {
        Console.WriteLine(message);
    }
    [Description("Feladat3")]
    public static void DeathInTheFamily(Jedi j)
    {
        var council = new JediCouncil();

        council.Announcment += new Warning(MessageReceived);
        council.Remove();
        council.Add(j);
        council.Remove();
    }

    [Description("Feladat4")]
    public static void ShowMeTheWeaklings()
    {
        var jedi1 = new Jedi()
        {
            Name = "Test",
            MidiChlorianCount = 100,
        };

        var jedi2 = new Jedi()
        {
            Name = "Test2",
            MidiChlorianCount = 110,
        };
        var council = new JediCouncil();
        council.Add(jedi1);
        council.Add(jedi2);
        var switchcouncil = council.WeakJedi_Delegate();

        foreach (Jedi j in switchcouncil)
        {
            Console.WriteLine($"{j.Name},\t{j.MidiChlorianCount}");
        }
    }

    [Description("Feladat5")]
    public static void ShowMeTheLessWeaklings()
    {
        var jedi1 = new Jedi()
        {
            Name = "Test",
            MidiChlorianCount = 999,
        };

        var jedi2 = new Jedi()
        {
            Name = "Test2",
            MidiChlorianCount = 998,
        };
        var council = new JediCouncil();
        council.Add(jedi1);
        council.Add(jedi2);
        var switchcouncil = council.WeakJedi_Lamda();

        foreach (Jedi j in switchcouncil)
        {
            Console.WriteLine($"{j.Name},\t{j.MidiChlorianCount}");
        }
    }

    public static bool Hmm(Jedi j)
    {
        return j.MidiChlorianCount > 250;
    }

    [Description("Feladat6")]
    public static void IMSC()
    {
        var jedi1 = new Jedi()
        {
            Name = "Test",
            MidiChlorianCount = 100,
        };

        var jedi2 = new Jedi()
        {
            Name = "Test2",
            MidiChlorianCount = 300,
        };
        var council = new JediCouncil();
        council.Add(jedi1);
        council.Add(jedi2);

        Console.WriteLine(council.CountIf(Hmm));
    }

    public static void Main(string[] args)
    {
        /// Create
        var j = new Jedi()
        {
            MidiChlorianCount = 100,
            Name = "Jesus",
        };

        SaveAndLoad(j);

        DeathInTheFamily(j);

        ShowMeTheWeaklings();

        ShowMeTheLessWeaklings();

        IMSC();


    }
}
