# Springies
#### Compsci 308 - Spring 2013

Code for the Springies project for CompSci 308 Spring 2013.
Group members:
- Jonathan Schmidt jas138
- Yang Yang yy96

#### General Info
We started working on this project on 1/26/2013 and finished part 2 on 1/28/2013
We then change the factory to have a better implementation over the next week and then started working on Part 3 on 2/7/2013
Part 3 was mostly completed by 2/8/2013 but we made one or two changes after that.
Total Time spent on this assignment was around 10 hours when we worked together and about 10 hours apart.
	A substantial amount of time was spent on trying to make a better implementation for the factory

We worked on the majority of the 2nd part together, often coding on one computer at a time.
Jonathan then spent some time trying to figure out the factory a better way and we met and refined it from there.
For part 3 Jonathan implemented the initial code (because it was similar to the factory) and then Yang fixed up the problems and extended it.
Then both Jonathan and Yang made some efforts on fixing checkstyle problems and avoid duplicated code.

#### Repository
[GitHub Repository](https://github.com/Tadimsky/Springies)

#### Resources
The external resources we used:
- Various sites for trying to figure out a better factory
- Jimmy Mu (our TA)

#### Extra Stuff
We made the factory an abstract class that uses a map of String to Interface that allows the factory to be used to create anything it wants to.
The factory is then made concrete in two seperate classes which register the environment and simulation data respectively.
We feel that this way makes it really easy for someone to add a new class to the simulation and let the Factory create it for them.
