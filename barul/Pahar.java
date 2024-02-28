package barul;

public class Pahar implements Bautura {
	private String tip;
	private boolean deschis;
	
	public Pahar(String tip)
	{
		setDeschis(true);
		this.tip=tip;
	}
	public void bea() {
		if(isDeschis())
			System.out.println("A bea din paharul de " + tip); //C: paharul se bea fara nicio adaptare
		else
			System.out.println("Eroare pahar !!!"); 
	}
	public boolean isDeschis() {
		return deschis;
	}
	public void setDeschis(boolean deschis) {
		this.deschis = deschis;
	}

}
