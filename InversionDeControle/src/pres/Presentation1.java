package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation1 {

	public static void main(String[] args) {
		/*
		 * Injection des dépendances par
		 * instanciation statique => new
		 * en utilisant un setter (ou via un constructeur)
		 */
		MetierImpl metier = new MetierImpl();
		DaoImpl dao = new DaoImpl();
		metier.setDao(dao);
		System.out.println("resultat=" + metier.calcul());
	}

}
