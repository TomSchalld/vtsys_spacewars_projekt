package logik;

public class Demo {

	public static void main(String[] args) {
		Spaceship viper = new Fighter(0);
		Spaceship galactica = new Battlestar(1);
		System.out.println(viper.getClass()+" "+galactica.getClass());
		
	}

}
