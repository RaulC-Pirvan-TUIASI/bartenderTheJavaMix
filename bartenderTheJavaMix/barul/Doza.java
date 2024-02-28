package barul;

//adaptor de tip clasa pentru doza
//! nu strica putin documentare, imi cam da cu virgula 
public class Doza extends Pahar
{
	public Doza(String tip)
	{
		super(tip);
		setDeschis(false); //fortat cream obiectul sa fie incompatibil
	}
	
	public void bea()
	{
		desfaceDoza();
		super.bea();
	}
	
	public void desfaceDoza()
	{
		setDeschis(true);
		System.out.println("Desface doza pentru a deveni ca un pahar");
	}
}
