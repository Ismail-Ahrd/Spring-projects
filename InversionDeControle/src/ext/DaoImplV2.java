package ext;

import dao.IDao;

public class DaoImplV2 implements IDao {

	@Override
	public double getData() {
		System.out.println("Version capteur....");
		double temp = Math.random() * 40;
		return temp;
	}

}
