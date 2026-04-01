using System.Xml.Linq;

Console.WriteLine("Hello, World!");

XDocument dokumentum = XDocument.Load("F023QC_XML.xml");
XElement  gyoker     = dokumentum.Descendants("vendeglatas").First();

Console.WriteLine("(0.) A teljes dokumentum: \n\n" + gyoker);

Console.WriteLine("(1.) Az ötcsillagos éttermek: \n");
var otCsillagosEttermek = gyoker.Descendants("etterem")
    .Where(elem => elem.Descendants("csillag").First().Value == "5")
    .ToList();
otCsillagosEttermek.ForEach(elem =>
  Console.WriteLine(" - " + elem.Descendants("nev").First().Value)
);



Console.WriteLine("(2.) Melyik vendég, melyik étteremben, mit rendelt, mennyiért: \n");


var harmasJoin = gyoker.Descendants("rendeles")
    .Select(elem => {
        var vendegID = elem.Attribute("E_V_V").Value;
        var vendeg   = gyoker.Descendants("vendeg").Where(vendegElem => vendegElem.Attribute("Vkod").Value == vendegID).First().Descendants("nev").FirstOrDefault().Value;
        var etteremID = elem.Attribute("E_V_E").Value;

        var etterem   = gyoker.Descendants("etterem").First(etteremElem => etteremElem.Attribute("ekod").Value == etteremID).Descendants("nev").FirstOrDefault().Value;
        var rendeltEtel = elem.Descendants("etel").First().Value;
        var osszeg= elem.Descendants("osszeg").First().Value;

        return new
        {
            Vendeg = vendeg,
            Etterem = etterem,
            Etel = rendeltEtel,
            Osszeg = osszeg
        };
    })
    .ToList();
    var atlagKoltes = gyoker.Descendants("rendeles")
    .Select(rendeles => rendeles.Descendants("osszeg").First().Value)
    .Average(osszeg => double.Parse(osszeg));


Console.WriteLine($"(3.) Az átlagos költés: {atlagKoltes}");


Console.WriteLine("(4.) Minden rendelés összegét megduplázom, majd elmentem egy új fájlba: \n");
gyoker.Descendants("rendeles")
    .ToList()
    .ForEach(rendeles => {
        var osszegElem = rendeles.Descendants("osszeg").First();
        var osszeg = double.Parse(osszegElem.Value);
        osszeg *= 2;
        osszegElem.Value = osszeg.ToString();
    });

XDocument modositottDokumentum = new XDocument(gyoker);
modositottDokumentum.Save("etterem_modositott.xml");
Console.WriteLine("Az új fájl neve: \"etterem_modositott.xml\"");



Console.WriteLine("(5.) Törlöm az összes 3 csillagos éttermet, majd elmentem egy új fájlba: \n");
gyoker.Descendants("etterem")
    .Where(elem => elem.Descendants("csillag").First().Value == "3")
    .ToList()
    .ForEach(elem => {
        elem.Remove();
    });

XDocument toroltDokumentum = new XDocument(gyoker);
toroltDokumentum.Save("etterem_torolt.xml");
Console.WriteLine("Az új fájl neve: \"etterem_torolt.xml\"");