import java.util.ArrayList;
import java.util.Random;



public class component5 {


	public static void main(String[] args) {
		Random ran = new Random(231287);
		//Set up Input for VRP
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
		System.out.println("* * * * * * * * * * * * *");
		System.out.println("VRP Starts !");
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" ");
		// Distance Matrix
		double [][] distanceMatrix = new double [customers.size()][customers.size()];
		for (int i = 0 ; i < customers.size(); i++)
		{
			Node from = customers.get(i);

			for (int j = 0 ; j < customers.size(); j++)
			{
				Node to = customers.get(j);

				double Delta_x = (from.x - to.x);
				double Delta_y = (from.y - to.y);
				double distance = Math.sqrt((Delta_x * Delta_x) + (Delta_y * Delta_y));

				distance = Math.round(distance);

				distanceMatrix[i][j] = distance;

			}
		}

		// VRP heuristic
		//initialize solution
		Solution s = new Solution();

		// array list of ALL routes
		ArrayList<Route> routes = s.rt;

		// loop for vehicles
		for (int i=1; i<=numberOfVehicles; i++)
		{
			Route route_nodes = new Route();
			route_nodes.ID = i;
			routes.add(route_nodes);
		}

		int toRoute = numberOfCustomers;
		for (int j = 1; j<=numberOfVehicles; j++)
		{
			ArrayList <Node> nodeSequence = routes.get(j-1).nodes;
			int remaining = routes.get(j-1).capacity;
			int load = routes.get(j-1).load;
			nodeSequence.add(depot);

			boolean finalized = false;
			if (toRoute == 0) {
				finalized = true;
				nodeSequence.add(depot);
				
			}
			while (finalized == false )			{
				//this will be the position of the nearest neighbor customer -- initialization to -1
				int positionOfTheNextOne = -1;
				// This will hold the minimal cost for moving to the next customer - initialized to something very large
				double bestCostForTheNextOne = Double.MAX_VALUE;
				//This is the last customer of the route (or the depot if the route is empty)
				Node lastInTheRoute = nodeSequence.get(nodeSequence.size() - 1);
				//identify nearest non-routed customer
				for (int k = 1 ; k < customers.size(); k++)
				{
					// The examined node is called candidate
					Node candidate = customers.get(k);
					// if this candidate has not been pushed in the solution
					if (candidate.isRouted == false)					{
						//This is the cost for moving from the last to the candidate one
						double trialCost = distanceMatrix[lastInTheRoute.ID][candidate.ID];
						//If this is the minimal cost found so far -> store this cost and the position of this best candidate
						if (trialCost < bestCostForTheNextOne && candidate.demand<= remaining)
						{
							positionOfTheNextOne = k;
							bestCostForTheNextOne = trialCost;
						}
					}
				}//end of for
				

				if (positionOfTheNextOne != -1 )
				{
					Node insertedNode = customers.get(positionOfTheNextOne);
					//Push him
					nodeSequence.add(insertedNode);
					s.cost = s.cost + bestCostForTheNextOne;
					routes.get(j-1).cost = routes.get(j-1).cost + bestCostForTheNextOne;
					//routes.get(j-1).load = load ;
					insertedNode.isRouted = true;
					remaining = remaining - insertedNode.demand;
					load = load + insertedNode.demand;
					routes.get(j-1).load = load;
					toRoute = toRoute - 1;
					//System.out.println(j + " / " + insertedNode.demand + " / " + load);
				} else { //this adds the depot at the end of every route when it is full and cannot take up more customers
					nodeSequence.add(depot);
					s.cost = s.cost + distanceMatrix[lastInTheRoute.ID][0];
					routes.get(j-1).cost = routes.get(j-1).cost + distanceMatrix[lastInTheRoute.ID][0];
					finalized = true;

				}//end of if
				//this adds the depot as the end point in case when although the vehicle is not completely full yet, there are no more customers to serve

			}//end of while loop


		}//end of for loop
		for (int j = 0; j<numberOfVehicles; j++)
		{
			int vehicle = j+1;
			System.out.print("Assignment to Vehicle " + vehicle + ": ");
			for (int k=0; k<s.rt.get(j).nodes.size(); k++) 
			{
				System.out.print(s.rt.get(j).nodes.get(k).ID + "  ");
			}
			System.out.println("");
			System.out.println("Route Cost: " + s.rt.get(j).cost + " - Route Load: " + s.rt.get(j).load);
			System.out.println("");
		

		}		
		System.out.println("Total Cost: " + s.cost);

		//START OF LOCAL SEARCH CODE///
		//
		//The NN Solution has been generated
		//
		//Local Search
		System.out.println(" ");
		System.out.println("* * * * * * * * * * * * * * * * *");
		System.out.println("Local Search Starts (intra+inter) !");
		System.out.println("* * * * * * * * * * * * * * * * *");
		System.out.println(" ");

		//this is a boolean flag (true/false) for terminating the local search procedure
		boolean terminationCondition = false;

		//this is a counter for holding the local search iterator
		int localSearchIterator = 0;

		//Here we apply the best relocation move local search scheme
		//This is an object for holding the best relocation move that can be applied to the candidate solution
		RelocationMove rm = new RelocationMove(); // in order to apply one relocation  move for all routes - dont want to lose previous if i change vehicle

		//Initialize the relocation move rm
		rm.positionOfRelocated = -1;
		rm.positionToBeInserted = -1;
		rm.fromRoute = 0;
		rm.toRoute = 0;
		rm.moveCostFrom = Double.MAX_VALUE;
		rm.moveCostTo = Double.MAX_VALUE;
		rm.moveCostFrom = Double.MAX_VALUE;
		// Until the termination condition is set to true repeat the following block of code
		while (terminationCondition == false)
		{
			//With this function we look for the best relocation move
			//the characteristics of this move will be stored in the object rm
			findBestRelocationMove(rm, s, distanceMatrix, numberOfVehicles);

			// If rm (the identified best relocation move) is a cost improving move, or in other words
			// if the current solution is not a local optimum
			if (rm.moveCost < 0)
			{
				//This is a function applying the relocation move rm to the candidate solution
				applyRelocationMove(rm, s, distanceMatrix);
				localSearchIterator = localSearchIterator + 1;

			}
			else
			{
				//if no cost improving relocation move was found,
				//or in other words if the current solution is a local optimum
				//terminate the local search algorithm
				terminationCondition = true;
			}

		}
		for (int j = 0; j<numberOfVehicles; j++)
		{
			int vehicle = j+1;
			System.out.print("New Assignment to Vehicle " + vehicle + ": ");
			for (int k=0; k<s.rt.get(j).nodes.size(); k++) 
			{
				System.out.print(s.rt.get(j).nodes.get(k).ID + "  ");
			}
			System.out.println("");
			System.out.println("Route Cost: " + s.rt.get(j).cost  + " - Route Load: " + s.rt.get(j).load);
			System.out.println(" ");

		}		
		System.out.println("Total Cost: " + s.cost);
		System.out.println("Total iterations: " + localSearchIterator);

	}//end of main

	private static void findBestRelocationMove(RelocationMove rm, Solution s, double [][] distanceMatrix, int numberOfVehicles) 
	{
		//This is a variable that will hold the cost of the best relocation move
		double bestMoveCost = Double.MAX_VALUE;

		//We will iterate through all available nodes to be relocated
		//from route for
		for (int from = 0; from<numberOfVehicles; from++)
		{
			//to route for
			for (int to = 0; to<numberOfVehicles; to++)
			{
				//for (int relToIndex = 1; relToIndex < s.rt.get(to).nodes.size() - 1; relToIndex++)
				//{
				for (int relFromIndex = 1; relFromIndex < s.rt.get(from).nodes.size() - 1; relFromIndex++)
				{
					//Node A is the predecessor of B
					Node A = s.rt.get(from).nodes.get(relFromIndex - 1);

					//Node B is the relocated node
					Node B = s.rt.get(from).nodes.get(relFromIndex);

					//Node C is the successor of B
					Node C = s.rt.get(from).nodes.get(relFromIndex + 1);

					//We will iterate through all possible re-insertion positions for B
					for (int afterToInd = 0; afterToInd < s.rt.get(to).nodes.size() -1; afterToInd ++)
					{
						// Why do we have to write this line?
						// This line has to do with the nature of the 1-0 relocation
						// If afterInd == relIndex -> this would mean the solution remains unaffected
						// If afterInd == relIndex - 1 -> this would mean the solution remains unaffected
						if ((afterToInd != relFromIndex && afterToInd != relFromIndex - 1)||from != to)
						{
							//Node F the node after which B is going to be reinserted
							Node F = s.rt.get(to).nodes.get(afterToInd);

							//Node G the successor of F
							Node G = s.rt.get(to).nodes.get(afterToInd + 1);

							//The arcs A-B, B-C, and F-G break
							double costRemovedFrom = distanceMatrix[A.ID][B.ID] + distanceMatrix[B.ID][C.ID];
							double costRemovedTo = distanceMatrix[F.ID][G.ID];
							//double costRemoved = costRemoved1 + costRemoved2;

							//The arcs A-C, F-B and B-G are created
							double costAddedFrom = distanceMatrix[A.ID][C.ID];
							double costAddedTo  = distanceMatrix[F.ID][B.ID] + distanceMatrix[B.ID][G.ID];
							//double costAdded = costAdded1 + costAdded2;

							//This is the cost of the move, or in other words
							//the change that this move will cause if applied to the current solution
							//double moveCost = costAdded - costRemoved;
							double moveCostFrom = costAddedFrom - costRemovedFrom;
							double moveCostTo = costAddedTo - costRemovedTo;
							
							//If this move is the best found so far
							double moveCost = moveCostFrom+moveCostTo;
							if ((moveCost < bestMoveCost)&&(from == to || (s.rt.get(to).load + s.rt.get(from).nodes.get(relFromIndex).demand<=s.rt.get(to).capacity)))
							{
								//set the best cost equal to the cost of this solution
								bestMoveCost = moveCost;

								//store its characteristics
								rm.positionOfRelocated = relFromIndex;
								rm.positionToBeInserted = afterToInd;
								rm.moveCostTo = moveCostTo;
								rm.moveCostFrom = moveCostFrom;
								rm.fromRoute = from;
								rm.toRoute = to;
								rm.moveCost = moveCost;
								if (from != to) {
									rm.newLoadFrom = s.rt.get(from).load - s.rt.get(from).nodes.get(relFromIndex).demand;
									rm.newLoadTo = s.rt.get(to).load + s.rt.get(from).nodes.get(relFromIndex).demand;
								}
								else {
									rm.newLoadFrom = s.rt.get(from).load;
									rm.newLoadTo = s.rt.get(to).load;
								}

								System.out.println("From route: " + rm.fromRoute + ", To Route: " + rm.toRoute + ", New Load From:" + rm.newLoadFrom + ", New Load To:" + rm.newLoadTo);
							}
						}
					}
				}
				//}
			}
		}
	}


	//This function applies the relocation move rm to solution s
	private static void applyRelocationMove(RelocationMove rm, Solution s, double[][] distanceMatrix) 
	{
		//This is the node to be relocated
		Node relocatedNode = s.rt.get(rm.fromRoute).nodes.get(rm.positionOfRelocated);

		//Take out the relocated node
		s.rt.get(rm.fromRoute).nodes.remove(rm.positionOfRelocated);

		//Reinsert the relocated node into the appropriate position
		//Where??? -> after the node that WAS (!!!!) located in the rm.positionToBeInserted of the route

		//Watch out!!! 
		//If the relocated customer is reinserted backwards we have to re-insert it in (rm.positionToBeInserted + 1)
		if (((rm.positionToBeInserted < rm.positionOfRelocated) && (rm.toRoute == rm.fromRoute))||(rm.toRoute!=rm.fromRoute))
		{
			s.rt.get(rm.toRoute).nodes.add(rm.positionToBeInserted + 1, relocatedNode);
		}
		////else (if it is reinserted forward) we have to re-insert it in (rm.positionToBeInserted)
		else
		{
			s.rt.get(rm.toRoute).nodes.add(rm.positionToBeInserted, relocatedNode);
		}

		//update the cost of the solution and the corresponding cost of the route object in the solution
		s.cost = s.cost + rm.moveCost;
		s.rt.get(rm.toRoute).cost = s.rt.get(rm.toRoute).cost + rm.moveCostTo;
		s.rt.get(rm.fromRoute).cost = s.rt.get(rm.fromRoute).cost + rm.moveCostFrom;
		if  (rm.toRoute != rm.fromRoute) {
			s.rt.get(rm.toRoute).load = rm.newLoadTo;
			s.rt.get(rm.fromRoute).load = rm.newLoadFrom;
		}
		else {
			s.rt.get(rm.toRoute).load = rm.newLoadTo;			
		}

	}


}//end of VRP comp 5


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

class RelocationMove 
{
	int positionOfRelocated;
	int positionToBeInserted;
	int fromRoute;
	int toRoute;
	double moveCostTo;
	double moveCostFrom;
	double moveCost;
	int newLoadFrom;
	int newLoadTo;

	RelocationMove() 
	{
	}
}