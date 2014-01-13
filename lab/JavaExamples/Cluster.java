import java.util.*;

public class Cluster <AnyType extends Comparable<AnyType>> {

	private ArrayList<AnyType> myList;

	Cluster(int initCapacity) {
		myList = new ArrayList<AnyType>(initCapacity);
	}

	public void put(AnyType myObject) {
		myList.add(myObject);
	}

	public void sort() {
		Collections.sort(myList);
	}

	void print() {
		for (AnyType a : myList)
			System.out.println(a);
	}

	public static void main(String [] args) {
		Cluster<Person> myCluster = new Cluster<Person>(4);

		// throw some stuff into the Cluster
		myCluster.put(new Person("Cada St-Merrein", "04/22/1961"));
		myCluster.put(new Person("Regulus Arcturus Black", "01/30/1961"));
		myCluster.put(new Person("Perceval Thoreau", "08/08/1450"));
		myCluster.put(new Person("Magdeleine Corriander Grabb", "05/19/1960"));

		// print the Cluster's contents
		System.out.println("Unsorted Cluster:");
		System.out.println("=================");
		myCluster.print();

		// sort the Cluster; it's a good thing Person objects are Comparable ;)
		myCluster.sort();

		// print the Cluster's contents (now sorted)
		System.out.println();
		System.out.println("Sorted Cluster:");
		System.out.println("===============");
		myCluster.print();
	}
}
