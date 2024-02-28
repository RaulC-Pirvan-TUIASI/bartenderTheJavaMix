package clienti;
import barul.*;

public class Student {
	public Student()
	{
		System.out.println("o oferta !"); //welcome message
	}
	
	public void beaBautura(Bautura bautura)
	{
		if(bautura!=null)
		{
			bautura.bea();
		}
		else
		{
			System.out.println("studentul nu bea nimic"); //la apasare buton bea fara nimic selectat
		}
	}
}
