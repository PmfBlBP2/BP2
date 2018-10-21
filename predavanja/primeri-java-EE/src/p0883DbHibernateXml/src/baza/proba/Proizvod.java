package baza.proba;

public class Proizvod
{
	long proizvodId;
	String naziv;
	int kolicina;
	String jedinicaMere; 
	
	public long getProizvodId()
	{
		return proizvodId;
	}
	
	public void setProizvodId(long proizvodId)
	{
		this.proizvodId = proizvodId;
	}
	
	public String getNaziv()
	{
		return naziv;
	}
	
	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	public int getKolicina()
	{
		return kolicina;
	}
	
	public void setKolicina(int kolicina)
	{
		this.kolicina = kolicina;
	}
	
	public String getJedinicaMere()
	{
		return jedinicaMere;
	}
	
	public void setJedinicaMere(String jedinicaMere)
	{
		this.jedinicaMere = jedinicaMere;
	}
	
}
