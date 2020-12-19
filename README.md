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
