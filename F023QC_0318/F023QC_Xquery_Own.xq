xquery version "3.1";

doc("F023QC_rendelesek.xml")//Vevő

for $f in doc("F023QC_rendelesek.xml")//Futár
where $f/típus = "Autós"
return 
<futar_adatok>  
    <FutarID>{data($f/@futárID)}</FutarID>  
    <Nev>{data($f/név)}</Nev>  
    <Tipus>{data($f/típus)}</Tipus>
    <Telefon>{data($f/telefonszám)}</Telefon>
</futar_adatok>

for $r in doc("F023QC_rendelesek.xml")//Rendelés
where $r/teljes_ár > 5000
return
<kiemelt_rendeles>
    <RendelesID>{data($r/@rendelésID)}</RendelesID>
    <Ar>{data($r/teljes_ár)}</Ar>
    <Idopont>{data($r/rendelés_ideje)}</Idopont>
</kiemelt_rendeles>

for $f in doc("F023QC_rendelesek.xml")//Futár
where $f/elérhető = 1
return
<elerheto_futar>
    <Nev>{data($f/név)}</Nev>
    <Telefon>{data($f/telefonszám)}</Telefon>
</elerheto_futar>

for $v in doc("F023QC_rendelesek.xml")//Vevő
let $rendeles := doc("F023QC_rendelesek.xml")//Rendelés[@rendelésID = $v/@rendelésID]
return
<adatok>
    <vevo_neve>{$v/név/text()}</vevo_neve>
    <fizetett_osszeg>{$rendeles/teljes_ár/text()}</fizetett_osszeg>
</adatok>

for $r in doc("F023QC_rendelesek.xml")//Rendelés
return
update value $r/teljes_ár with $r/teljes_ár + 500

count(doc("F023QC_rendelesek.xml")//Rendelés)

sum(doc("F023QC_rendelesek.xml")//Rendelés/teljes_ár)

avg(doc("F023QC_rendelesek.xml")//Rendelés/teljes_ár)