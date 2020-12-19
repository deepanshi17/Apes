# Dynamic programming solution to Apes vs Humans

## Problem Description
Implement a dynamic programming solution to the following problem.

### Deforestation: An Inevitable Fate
In quarantine, you’ve found yourself playing many games. As a fanatic of Civilization and
Catan, you were excited when a new game was released.
Here, you are the leader of a community of Apes. For many years, your tribe has lived in
peace in the tropical rain forests of South East Asia. However, recently, another species known
as humans have begun claiming land in the forest. Moreover, you noticed that these humans are
doing something horrid – they are cutting down the trees, and your tribe will no longer have access
to the tree’s fruit!
You have never encountered an enemy as fierce and powerful as them – they will stop at nothing
until to gain control of the land. You have come to realize that no matter what, your tribe will lose
all of its land in the coming days. However, for whatever time remains, you would like to maximize
the livelihood of your community.
The forest is divided up into N sections. Initially, you control all N sections. The forest is long
and thus the sections can be represented as N squares on a line as depicted in Figure 1. In total,
you collect pi fruit from each section i, each day.
The humans will eventually conquer the entire forest; thus, your objective is to maximize the
amount of fruit you collect before all the sections are overtaken.
At the beginning of each day, you can build a stick wall between two neighboring sections you
control. Subsequently, the humans observe the position of the wall and choose to overtake the
remaining forest either from east or west – conquering all the sections before the wall. At the
end of the day, you count the total amount of fruit you collected from the side of the wall that
remained under your control, and the wall is destroyed. The same process is repeated every
day until the entire forest is under human control.
You should be particularly careful when choosing the location of the wall. It is known that the
humans are super smart and always decide to attack from the direction that minimizes the number
of fruits apes can collect. That is, you may assume that the humans minimize the amount
of fruits apes can receive (after she/he has chosen the location for the wall) in the
CURRENT AND ALL FUTURE ROUNDS.

## Solution
The dynamic subproblems are the smaller sets of the total array of sections that result from partitioning the array at each placement of the wall. The solution to the larger problem is then the maximum of the minimum of approaching from the left and approaching from the right of each subproblem.

### Recurive Formula
The recursive formula for the optimal solution is given by:
![](eqn.png)
where i and j are the start and end points of the sections array, w is the placement of the wall, and n is the total size of the array.

### Argument for Correctness
By recording the value of the OPT for the dynamic subproblems and the wall placements for
each of the subproblems, my algorithm is able to reduce the complexity of the problem to
O(n^2). My algorithm for finding the maximum amount of fruit that the apes can collect is as
follows:

* Create an n x n x 3 matrix for storing the OPT values, the sums of the subarrays, and
the wall placements. The x-axis corresponds to the start index in the array and the
y-axis corresponds to the end index in the array.
* Initialize the diagonal of the OPT matrix with 0, since the max number of fruit received by the apes in an array of size 1 is 0 (the humans will take all the fruit). Initialize the diagonal of the sum array with each corresponding item in the sections array. Finally, the wall placement array should be initialized along the diagonal with a value of 1, since the wall must be placed at least after the first element.
* Now, loop through the memoized table diagonally starting from the initialized diagonal,
filling in entries in the memoized table. For each subproblem, the initial wall placement
is equal to the wall placement of the subproblem that is one element smaller than the
current problem.
* Loop through all the wall placements starting at the initial placement calculated in the step before. Calculate the minimum of the subproblems created by partitioning the array at the current wall placement.
* Stop looping through the wall placements when the value of the OPT given by changing
the placement of the wall no longer increases.
* Fill the cell in the OPT matrix with the maximum OPT of all the minimum OPT values
calculated. Also store the sum of the subarray and the wall placement that gave the
maximum OPT. The sum of each subproblem is calculated by summing the values of the
sums stored in the smaller subproblems.
* Keep looping through the array diagonally until the upper triangle of the matrix is
populated.
* The maximum fruit count is the value of the OPT in the top right corner.
Without optimizing the placement of the wall, the recursive solution gives a runtime of
O(n
3
). Each start and end point must be considered, except for the cases where the
end point is less than the start point, so there are n
2
2
subproblems to solve. For each
subproblem, we must consider every possible wall placement, which gives rise to an inner
loop of size n − 1. The actual comparisons and matrix accesses are done in constant
time. This gives a total runtime of O(n^3). However, my algorithm is
optimized by taking advantage of two key trends:
(a) The OPT values given by moving the placement of the wall to the right will only
ever monotonically increase and then monotonically decrease. This means that there
is only one maximum value, and once it is found we can stop considering further
wall placements.
(b) The wall placement of an array of size k will always been at least the same as the
wall placement in an array of the first k − 1 elements.

#### Proof of (a)
Given any array of size n, place a wall w somewhere after the first element of the array.
Now we have two subsets, sL and sR of the original array, where sL = 1, w and sR =
w + 1, n. Now, since we know that the array only contains nonnegative integers, moving
the placement of the wall w to the right will increase the value of the left array and
decrease the value of the right array, since we are effectively adding an element to the
left array while substracting that value from the right array. The value of the OPT is
always the minimum of these two subsets, since the humans always pick the optimal side.
Now there are two cases:

*case 1:
The value of the left array is initially less than the value of the right array. In this
case, moving the wall to the right will keep increasing the value of the left array until
it is equal to the right array and then eventually exceeds it. In this case the humans
always pick the optimal side, so they will continue picking the right side until the
two sides are equal. Then when the left side surpasses the right side, the humans
will pick the left side. In this way, the apes will reach a maximum value when the
left side is still slightly less than the right side. Then the humans will switch to
picking the left side when the value of the left surpasses the value of the right side.
At this point, moving the wall placement further to the right will only decrease the
profits of the apes.

*case 2:
The value of the left array is initially greater than the value of the right array. In
this case, the humans will approach from the left, and the apes will receive the value of the right side. Now, moving the wall to the right will only further increase the
value of the left array whille decreasing the profit of the apes.
In both cases, the OPT increases to a certain point, then ceases to increase for any
further placement of the wall.

#### Proof of (b)
Proof by contradiction:
Suppose that an array of size k − 1 has an optimal wall placement of w, and that array plus one more element of size k has an optimal wall placement of w − 1. Consider the subarrays created by partitioning the array of size k at w − 1. sL : {1, w − 1} and sR : {w, k}. The wall placement is optimal, so that means that the maximum amount of fruit is the minimum of these two arrays. Now consider two cases:
*case 1: the humans approach from the left.
In this case, the optimal fruit the apes can get is equal to OPT( sR : {w, k}). However,
the wall placement at w − 1 was available to the apes even in the smaller array of size
k − 1. If the apes had placed the wall at w − 1 in the smaller problem, sL : {1, w − 1}
and sR : {w, k − 1}. Since we know the humans approached from the left in the larger
problem, we know that the apes would have gotten more fruit by approaching from the
left, but now they will get the fruit on the right side. Therefore, adding an element to the left side of the array would only further increase the OPT of the left side while further decreasing the apes’ profits. This is a contradiction to the optimal wall placement in the smaller problem being at w, which is greater than w − 1.
*case 2: the humans approach from the right
In this case, the optimal fruit the apes can get is equal to OPT( sL : {1, w − 1}). The
humans approach from the side the minimizes the apes’ profit, so the apes would have
earned more fruit if they could approach from the right, but instead will receive the fruit that they can get by approaching from the left. However, sL : {1, w − 1} was still an available option for the smaller array of size k −1, but in that problem, adding one more element to the left side was optimal, meaning that the apes were able to receive more fruit. This means that adding one more element to the left side increased the yield of the apes without making the OPT of the left side greater than the right. However, the smaller array has one less element on the right side than the larger problem, so if the
wall placement at w could increase the ape profit without surpassing the OPT of the right, then the apes would have also chosen to place the wall at w in the larger problem. This is a contradiction to w − 1 being the optimal wall placement in the larger problem. 

From these two cases, we have shown that the wall placement of an array of size k will always have been at least the same as the wall placement in an array of the first k − 1 elements. Now, we have an upper and a lower bound on the amount of times we need to loop through the wall placements to find the optimal solution. The initial wall placement of any subproblem of size k must be at least greater than or equal to the wall placement of the subproblem of size k − 1. Also, we can stop looping when the value of the OPT stops increasing. Considering these two constraints, the worst case would be when the optimal wall placement moves one place to the right after each round of the game. Now, in the worst case scenario, every time we increase the length of the array, the average walls we check is equal to 1. This is because if the average was greater than 1, this implies that the total amount of wall checks was more
than n times, which is not possible. Therefore, in the worst case, the average amount of times
that the wall placement is checked is 1. This gives a runtime of O(n^2).

