package pres;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import dao.IDao;
import metier.IMetier;

public class Presentation2 {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		//Creation d'une instance d'une maniere dynamique
		Scanner scanner = new Scanner(new File("config.txt"));
		
		String daoClassName = scanner.nextLine();
		Class cDao = Class.forName(daoClassName);
		IDao dao = (IDao) cDao.newInstance();
		
		String metierClassName = scanner.nextLine();
		Class cMetier = Class.forName(metierClassName);
		IMetier metier = (IMetier) cMetier.newInstance();
		
		Method method = cMetier.getMethod("setDao", IDao.class);
		//metier.setDao(dao); => statique
		method.invoke(metier, dao);
		
		System.out.println("resultat: " + metier.calcul());
	}

}
