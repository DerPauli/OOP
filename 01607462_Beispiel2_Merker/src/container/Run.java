package container;

public class Run {

	public Run() {
	}

	public static void main(String[] args) {

		Container cont = new Container();
		System.out.println(cont.add(12));
		System.out.println(cont.add(13));
		System.out.println(cont.add(14));
		System.out.println(cont.add("Hallo"));
		System.out.println(cont.add("Hallo"));
		
	}

}
