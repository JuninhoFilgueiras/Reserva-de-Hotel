package Hotel;

public interface Reserva {
	public Boolean verificarSituação(int quarto);
	public void listar_desocupados();
	public void listar_hospedes();
	void reservar(int quarto, String nome, int pessoas);
	void desocupar(int quarto);
}