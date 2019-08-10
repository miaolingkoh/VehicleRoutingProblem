import java.util.ArrayList;
import java.util.Random;



public class component2 {
	

	public static void main(String[] args) {
		
		// Define number of available customers and vehicles
		int numberOfCustomers  = 30;
		int numberOfVehicles = 10;

		//initialize depot
		Node depot = new Node();
		depot.x = 50;
		depot.y = 50;
		depot.ID = 0;
		depot.isRouted = true;

		//Create the list with customers and add depot into it as well
		ArrayList <Node> customers = new ArrayList<Node>();
		customers.add(depot);
		
		
		// Set seed
		Random ran = new Random(231287);
		//Create customers and add them to the list as well
		for(int i=1 ; i<=numberOfCustomers; i++)
		{
			Node cust = new Node();
			cust.x = ran.nextInt(100);
			cust.y = ran.nextInt(100);
			cust.demand = 4 + ran.nextInt(7);
			cust.ID = i;
			cust.isRouted = false;
			customers.add(cust);
		}
		
				
		
		// initialize solution
		Solution s = new Solution();
		// array list of ALL routes
		ArrayList<Route> routes = s.rt;
		// routes initialization - one for every vehicle
		

		

	}//end of main
	
}//end of VRP comp 2


// Representation for both customers and depot
class Node 
{
	int x;
	int y;
	int ID;
	int demand;

	// true/false flag indicating if a customer has been inserted in the solution
	boolean isRouted; 

	Node() 
	{
	}
}

class Solution 
{
	double cost;
	ArrayList<Route> rt;

	//This is the Solution constructor. It is executed every time a new Solution object is created (new Solution)
	Solution ()
	{
		// A new route object is created addressed by rt
		// The constructor of route is called
		rt = new ArrayList<Route>();
		cost = 0;
	}
}

class Route 
{
	ArrayList <Node> nodes;
	double cost;
	int ID;
	int load;
	int capacity;

	//This is the Route constructor. It is executed every time a new Route object is created (new Route)
	Route() 
	{
		cost = 0;
		ID = -1;
		capacity = 50;
		load = 0;

		// A new arraylist of nodes is created
		nodes = new ArrayList<Node>();
	}
}