/* prepisuje iz stare baze u novu */
INSERT INTO filmovi_bp2_2019.drzava( DrzavaId, Naziv)
SELECT DrzavaId, Naziv FROM var_filmovi.drzava;

INSERT INTO filmovi_bp2_2019.zanr( ZanrId, Naziv)
SELECT ZanrId, Naziv FROM var_filmovi.zanr;

INSERT INTO filmovi_bp2_2019.osoba( Ime, Prezime, Nadimak, DrzavaId)
select Ime, Prezime, Nadimak, DrzavaId
from
((select Ime, Prezime, Nadimak, DrzavaId
from glumac)
UNION ALL
(select Ime, Prezime, NULL as Nadimak, DrzavaId
from reziser)
UNION ALL
(select Ime, Prezime, NULL as Nadimak, DrzavaId
from scenarista)) as svi
group by Ime, Prezime, Nadimak, DrzavaId;

insert into filmovi_bp2_2019.glumac(GlumacId)
select o.OsobaId as GlumacId
from filmovi_bp2_2019.osoba as o
where exists
( select gs.GlumacId
  from var_filmovi.glumac as gs
  where gs.Ime = o.Ime 
  and gs.Prezime = o.Prezime 
  and gs.DrzavaId = o.DrzavaId 
  and ((gs.Nadimak is null and o.Nadimak is null ) 
        or (gs.Nadimak = o.Nadimak))
);

INSERT INTO filmovi_bp2_2019.film(
    FilmId,
    Naziv,
    ZanrId,
    Trajanje,
    Godina,
    Ocena,
    Opis,
    Zarada) 
SELECT FilmId,
    Naziv,
    ZanrId,
    Trajanje,
    Godina,
    Ocena,
    Opis,
    Zarada
FROM var_filmovi.film;
  
INSERT INTO `filmovi_bp2_2019`.`glumio`
(`FilmId`,
`GlumacId`,
`Honorar`)
select upit.FilmId, upit.OsobaId, 1 as Honorar
from  
(select gms.FilmId, gms.GlumacId, gs.Ime, gs.Prezime, 
       gs.Nadimak, gs.DrzavaId, osn.OsobaId
from var_filmovi.glumio as gms join var_filmovi.glumac as gs
on (gs.Glumacid = gms.GlumacId), filmovi_bp2_2019.osoba as osn
where gs.Ime = osn.Ime 
  and gs.Prezime = osn.Prezime 
  and gs.DrzavaId = osn.DrzavaId 
  and ((gs.Nadimak is null and osn.Nadimak is null ) 
        or (gs.Nadimak = osn.Nadimak))) as upit;
  
