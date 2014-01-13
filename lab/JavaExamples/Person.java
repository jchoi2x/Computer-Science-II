import java.util.*;

/**/
public class Person implements Comparable<Person> {

	private String firstName, lastName;
	private int birthYear, birthMonth, birthDay;


	Person(String name, String birthday) {
		int splitPoint = name.lastIndexOf(' ');

		this.firstName = name.substring(0, splitPoint);
		this.lastName = name.substring(splitPoint + 1);

		String [] numbers = birthday.split("/");

		this.birthMonth = Integer.parseInt(numbers[0]);
		this.birthDay   = Integer.parseInt(numbers[1]);
		this.birthYear  = Integer.parseInt(numbers[2]);
	}
  @Override
	public int compareTo(Person rhs) {

		if (this.birthYear < rhs.birthYear)
			return -1;
		else if (this.birthYear > rhs.birthYear)
			return 1;
		else if (this.birthMonth < rhs.birthMonth)
			return -1;
		else if (this.birthMonth > rhs.birthMonth)
			return 1;
		else if (this.birthDay < rhs.birthDay)
			return -1;
		else if (this.birthDay > rhs.birthDay)
			return 1;
		else
			return 0;
	}

  @Override
	public String toString() {
		return "(" + String.format("%02d", birthMonth) +
		        "/" + String.format("%02d", birthDay) +
		        "/" + String.format("%02d", birthYear) + ")" +
		        " " + lastName + ", " + firstName;
	}

	public static void main(String [] args) {
		ArrayList<Person> list = new ArrayList<Person>();

		list.add(new Person("Cada St-Merrein", "04/22/1961"));
		list.add(new Person("Regulus Arcturus Black", "01/30/1961"));
		list.add(new Person("Perceval Thoreau", "08/08/1450"));
		list.add(new Person("Magdeleine Corriander Grabb", "05/19/1960"));

		System.out.println("Unsorted List");
		System.out.println("=============");
		for (Person p : list) System.out.println(p);

		Collections.sort(list);

		System.out.println();
		System.out.println("Sorted List");
		System.out.println("===========");
		for (Person p : list) System.out.println(p);

		Collections.shuffle(list);

		Person [] array = new Person[4];
		for (int i = 0; i < list.size(); i++) array[i] = list.get(i);

		System.out.println("");
		System.out.println("Unsorted array");
		System.out.println("==============");
		for (Person p : array) System.out.println(p);

		Arrays.sort(array);

		System.out.println("");
		System.out.println("Sorted array");
		System.out.println("============");
		for (Person p : array) System.out.println(p);

	}

}
