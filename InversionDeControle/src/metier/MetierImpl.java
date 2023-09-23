package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
	//Couplage Faible
	private IDao dao;
	
	public MetierImpl() {}
	
	public MetierImpl(IDao dao) {
		this.dao = dao;
	}

	@Override
	public double calcul() {
		double data = dao.getData();
		double res = data * 232;
		return res;
	}

	/*
	 * Pour injecter dans la variable dao un objet
	 * d'une classe qui implémente l'interface IDao
	 */
	public void setDao(IDao dao) {
		this.dao = dao;
	}

}
