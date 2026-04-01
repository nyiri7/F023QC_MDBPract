// See https://aka.ms/new-console-template for more information
using System.Globalization;
using System.Linq.Expressions;
using System.Xml.Linq;

Console.WriteLine("Hello, World!");


XDocument dokumentum = XDocument.Load("F023QC_XML1.xml");
XElement  gyoker     = dokumentum.Descendants("F023QC_rendelesek").First();


Console.WriteLine("(0.) A teljes dokumentum: \n\n" + gyoker);

Console.WriteLine("Milán nevű futár:   ");

var milanF = gyoker.Descendants("Futár").Where(elem => elem.Descendants("név").First().Value == "Nagy Milán").ToList();

milanF.ForEach(e => Console.WriteLine(e.Descendants("név").First().Value));


var harmasJoin = gyoker.Descendants("RT").Select(
    e =>
    {
        var rendelesId = e.Attribute("rendelésID").Value;
        var termekId = e.Attribute("termékID").Value;

        var termek = gyoker.Descendants("Termék").Where(v => v.Attribute("termékID").Value == termekId).ToList();
        var rendelesek = gyoker.Descendants("Rendelés").Where(r => r.Attribute("rendelésID").Value == rendelesId).ToList();

        return new
        {
            Termek= termek,
            Rendelesek = rendelesek,
            RT = e
        };
    }
).ToList();

harmasJoin.ForEach(j => Console.WriteLine(j));


Console.WriteLine("Aggregáció");


var atlagar = gyoker.Descendants("Termék").Select(t => t.Descendants("ár").First().Value).Average(ar => double.Parse(ar));

Console.WriteLine(atlagar);




Console.WriteLine("Módosítás \n");
gyoker.Descendants("Termék")
    .ToList()
    .ForEach(rendeles => {
        var osszegElem = rendeles.Descendants("ár").First();
        var osszeg = double.Parse(osszegElem.Value);
        osszeg *= 2;
        osszegElem.Value = osszeg.ToString();
    });

XDocument modositottDokumentum = new XDocument(gyoker);
modositottDokumentum.Save("F023QC_XML1_modositott.xml");
Console.WriteLine("Az új fájl neve: \"F023QC_XML1_modositott.xml\"");




Console.WriteLine("Törlés \n");
gyoker.Descendants("Termék")
    .Where(elem => elem.Descendants("név").First().Value == "Húsleves")
    .ToList()
    .ForEach(elem => {
        elem.Remove();
    });

XDocument toroltDokumentum = new XDocument(gyoker);
toroltDokumentum.Save("F023QC_XML1_torolt.xml");
Console.WriteLine("Az új fájl neve: \"F023QC_XML1_torolt.xml\"");