using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ModernLangToolsApp;

[XmlRoot("Zsedi")]
public class Jedi
{
    int midi_chlorian_count;
    [XmlAttribute("MidiChlorianCount")]
    ///[WriteInToString(IsEnabled = true)]
    public int MidiChlorianCount
    {
        get { return midi_chlorian_count; }
        set
        {
            /*
            if (midi_chlorian_count == value)
                return;
            */
            if (value < 35)
                throw new ArgumentException("You are not a true jedi!");


            midi_chlorian_count = value;
        }
    }

    [XmlAttribute("derName")]
    public string Name { get; set; }
}

public delegate void Warning(string sign);
public class JediCouncil
{
    public event Warning Announcment;

    private List<Jedi> CouncilMembers = new List<Jedi>();

    [Description("Feladat3")]
    public void Add(Jedi jedi)
    {
        CouncilMembers.Add(jedi);

        Announcment?.Invoke($"{jedi.Name} csatlakozott a tanácshoz, ereje: {jedi.MidiChlorianCount}");
    }

    public void Remove()
    {


        if (CouncilMembers.Count == 0)
        {
            Announcment?.Invoke("A tanács kihalt!");
        }
        else
        {
            CouncilMembers.RemoveAt(CouncilMembers.Count - 1);
            Announcment?.Invoke($"Valaki távozott!");
        }
    }
    public List<Jedi> WeakJedi_Delegate()
    {
        var weak = CouncilMembers.FindAll(x => x.MidiChlorianCount < 1000);
        return weak;
    }

    public List<Jedi> WeakJedi_Lamda() => CouncilMembers.FindAll(j => j.MidiChlorianCount < 1000);

    public int Count
    {
        get { return CouncilMembers.Count; }
    }
    public int CountIf(Func<Jedi, bool> predicatum) => CouncilMembers.Count(j => predicatum(j));


}
