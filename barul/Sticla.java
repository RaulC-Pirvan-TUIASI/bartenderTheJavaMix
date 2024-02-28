package barul;

public class Sticla implements Bautura {
	private String tip;
	public boolean deschis;
	public Sticla(String tip)
	{
		this.tip=tip;
		deschis=false;
	}
	public void bea() 
	{
		if(deschis)
			System.out.println("A bea din sticla de "+ tip);
		else
			System.out.println("Sticla este inchisa, nu se poate bea din ea !"); //asta e doar pentru prezentare
	}

}
