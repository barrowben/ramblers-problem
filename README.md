# ramblers-problem
The problem is to work out the best walking route from a start point to a goal point, given a terrain map for the walking area.

We’ll store our terrain maps in Portable Grey Map (pgm1) format.
In this format each cell is represented by an int from 0 to 255.

The Rambler steps one pixel at a time North, South, East or West (not diagonally). An upward step is more costly.

The local cost c(y, x, y′, x′) of a step from (y, x) to (y′, x′) is:

`if (h(y′,x′) ≤ h(y,x)):`<br>
`c(y,x,y′,x′) = 1`<br>
`else:`<br>
`1 + |h(y′, x′) − h(y, x)|`<br>

where h(y, x) is the height in the terrain map at point (y, x).

We will implement both branch-and-bound search and A* search and then compare results.
