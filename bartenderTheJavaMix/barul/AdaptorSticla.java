package barul;

//adaptor de tip obiect penntru sticla
public class AdaptorSticla implements Bautura {
	private Sticla sticla;
	public AdaptorSticla(Sticla sticla)
	{
		this.sticla=sticla;
	}
	
	public void bea() {
		desfaceSticla();
		sticla.bea();
	}
	
	private void desfaceSticla()
	{
		sticla.deschis=true;
		System.out.println("Desfacem sticla cu un desfacator !");
	}
}
