package modelo;


public class NutrientesPorAlimento{
	private Nutrientes codigoNutrientes;
	private Alimentos codigoAlimento;

	public NutrientesPorAlimento(Nutrientes codigoNutrientes, Alimentos codigoAlimento) {
		this.codigoNutrientes = codigoNutrientes;
		this.codigoAlimento = codigoAlimento;
	}

	//Metodos atributo: codigoNutrientes
	public Nutrientes getCodigoNutrientes() {
		return codigoNutrientes;
	}
	public void setCodigoNutrientes(Nutrientes codigoNutrientes) {
		this.codigoNutrientes = codigoNutrientes;
	}
	//Metodos atributo: codigoAlimento
	public Alimentos getCodigoAlimento() {
		return codigoAlimento;
	//MENSAJE PRUEBA
	}
	public void setCodigoAlimento(Alimentos codigoAlimento) {
		this.codigoAlimento = codigoAlimento;
	}
}