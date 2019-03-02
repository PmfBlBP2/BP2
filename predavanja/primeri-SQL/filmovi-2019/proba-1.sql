select * from drzava;

select * from film;

select d.DrzavaId, d.Naziv, Count(*) as BrojFilmova
from drzava as d join film as f
on (d.DrzavaId=f.DrzavaId)
group by d.DrzavaId, d.Naziv;