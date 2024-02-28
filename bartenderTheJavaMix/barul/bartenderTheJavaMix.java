package barul;

import clienti.Student;

public class bartenderTheJavaMix {

	public static void main(String[] args) {
		System.out.println("Bun venit la 1301Bar");
		// title screen
		// sub numele noastre si 3 sec sleep

		Student student = new Student(); // initializez clientul

		student.beaBautura(null);

		// bautura din pahar, practic cazul de baza
		Bautura pahar = new Pahar("suc");
		student.beaBautura(pahar);

		// Bearea din doza de bere (clasa adaptor)
		Bautura doza = new Doza("Doza de suc"); 
		student.beaBautura(doza);

		// Bearea din sticla de bere (obiect adaptor)
		Bautura sticla = new AdaptorSticla(new Sticla("suc"));
		student.beaBautura(sticla); //succes
		student.beaBautura(new Sticla("suc")); //de asta folosim adaptorul, practic nu se poate bea din sticla fara el
		
		
	}
}
