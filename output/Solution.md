Large Scale Optimization
Vehicle Routing Problem 
Submitted by: Koh Miao Ling

Large Scale Optimization Assignment Solution 
Here I am reporting the output of the java code that was implemented regarding the VRP.
The examined Capacitated Vehicle Routing Problem implemented with the following Greedy Computational Strategy method. The proposed solution to this problem is the sequence of customers within routes. The constraints are the capacity of the vehicles and customers being serve exactly once.
Within component 3, the selection criteria is defined as following:
1)	Rank all feasible arc in ascending order. 
2)	Starting from deport, at each iteration select the feasible arc with the minimum cost and add to the partial solution until vehicle capacity constraint is satisfied.
3)	When all nodes are added to the solution exactly once, end route back to depot.

* * * * * * * * * * * * * * *
Output from Component3
* * * * * * * * * * * * * * *

Assignment to Vehicle 1: 0  26  19  7  11  16  30  18  4  0  
Route Cost: 105.0 - Route Load: 48

Assignment to Vehicle 2: 0  2  12  23  20  21  3  9  0  
Route Cost: 138.0 - Route Load: 50

Assignment to Vehicle 3: 0  6  15  1  17  14  28  5  22  0  
Route Cost: 181.0 - Route Load: 50

Assignment to Vehicle 4: 0  25  8  24  29  27  13  10  0  
Route Cost: 210.0 - Route Load: 46

Assignment to Vehicle 5: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 6: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 7: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 8: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 9: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 10: 0  0  
Route Cost: 0.0 - Route Load: 0

Total Cost: 634.0

* * * * * * * * * * * * * * *
Output from Component4
* * * * * * * * * * * * * * *

In this component, I have performed the local search algorithm and consider intra-route relocations. At each iteration, the method explore all potential relocations of the customers within their routes. Our final solution has two iterations with final total cost of 622.00.


Assignment to Vehicle 1: 0  26  19  7  11  16  30  18  4  0  
Route Cost: 105.0 - Route Load: 48

Assignment to Vehicle 2: 0  2  12  23  20  21  3  9  0  
Route Cost: 138.0 - Route Load: 50

Assignment to Vehicle 3: 0  6  15  1  17  14  28  5  22  0  
Route Cost: 181.0 - Route Load: 50

Assignment to Vehicle 4: 0  25  8  24  29  27  13  10  0  
Route Cost: 210.0 - Route Load: 46

Assignment to Vehicle 5: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 6: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 7: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 8: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 9: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 10: 0  0  
Route Cost: 0.0 - Route Load: 0

Total Cost: 634.0
 
* * * * * * * * * * * * * * *
Local Search Starts (intra) !
* * * * * * * * * * * * * * *
 
New Assignment to Vehicle 1: 0  19  7  16  30  18  4  11  26  0  
Route Cost: 93.0 - Route Load: 48
 
New Assignment to Vehicle 2: 0  2  12  23  20  21  3  9  0  
Route Cost: 138.0 - Route Load: 50
 
New Assignment to Vehicle 3: 0  6  15  1  17  14  28  5  22  0  
Route Cost: 181.0 - Route Load: 50
 
New Assignment to Vehicle 4: 0  25  8  24  29  27  13  10  0  
Route Cost: 210.0 - Route Load: 46
 
New Assignment to Vehicle 5: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 6: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 7: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 8: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 9: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 10: 0  0  
Route Cost: 0.0 - Route Load: 0
 
Total Cost: 622.0
Total Iterations: 2

* * * * * * * * * * * * * * *
Output from Component5
* * * * * * * * * * * * * * *

In the last component, I have performed the local search algorithm and consider intra-route relocations and inter-route relocations. At each iteration, the method explore all potential relocations of the customers at any points of the existing solution. Our final solution has two iterations with final total cost of 622.00.

Assignment to Vehicle 1: 0  26  19  7  11  16  30  18  4  0  
Route Cost: 105.0 - Route Load: 48

Assignment to Vehicle 2: 0  2  12  23  20  21  3  9  0  
Route Cost: 138.0 - Route Load: 50

Assignment to Vehicle 3: 0  6  15  1  17  14  28  5  22  0  
Route Cost: 181.0 - Route Load: 50

Assignment to Vehicle 4: 0  25  8  24  29  27  13  10  0  
Route Cost: 210.0 - Route Load: 46

Assignment to Vehicle 5: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 6: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 7: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 8: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 9: 0  0  
Route Cost: 0.0 - Route Load: 0

Assignment to Vehicle 10: 0  0  
Route Cost: 0.0 - Route Load: 0

Total Cost: 634.0
 
* * * * * * * * * * * * * * * * *
Local Search Starts (intra+inter) !
* * * * * * * * * * * * * * * * *
 
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
From route: 0, To Route: 0, New Load From:48, New Load To:48
New Assignment to Vehicle 1: 0  19  7  16  30  18  4  11  26  0  
Route Cost: 93.0 - Route Load: 48
 
New Assignment to Vehicle 2: 0  2  12  23  20  21  3  9  0  
Route Cost: 138.0 - Route Load: 50
 
New Assignment to Vehicle 3: 0  6  15  1  17  14  28  5  22  0  
Route Cost: 181.0 - Route Load: 50
 
New Assignment to Vehicle 4: 0  25  8  24  29  27  13  10  0  
Route Cost: 210.0 - Route Load: 46
 
New Assignment to Vehicle 5: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 6: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 7: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 8: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 9: 0  0  
Route Cost: 0.0 - Route Load: 0
 
New Assignment to Vehicle 10: 0  0  
Route Cost: 0.0 - Route Load: 0
 
Total Cost: 622.0
Total iterations: 2
