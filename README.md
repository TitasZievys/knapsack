# knapsack
This is a knapsack problem program that was used for my Extended Essay which is attached below. It was created to see how elitism could reduce the generation count needed to solve the problem while using roulette wheel selection.

The program generates a knapsack of a weight capacity and the number of available items you can choose from:
```
Knapsack knapsack = new Knapsack(100, 20); // 100 - weight limit, 20 - amount of items generated
```
Then it generates the items with random values and weights:
```
Item 1	 Weight 29	 Value 26
Item 2	 Weight 35	 Value 5
Item 3	 Weight 28	 Value 12
Item 4	 Weight 4	 Value 15
Item 5	 Weight 21	 Value 27
Item 6	 Weight 44	 Value 32
Item 7	 Weight 12	 Value 4
Item 8	 Weight 34	 Value 8
Item 9	 Weight 44	 Value 41
Item 10	 Weight 29	 Value 1
Item 11	 Weight 44	 Value 29
Item 12	 Weight 10	 Value 7
Item 13	 Weight 41	 Value 9
Item 14	 Weight 38	 Value 19
Item 15	 Weight 36	 Value 50
Item 16	 Weight 39	 Value 29
Item 17	 Weight 13	 Value 33
Item 18	 Weight 25	 Value 30
Item 19	 Weight 1	 Value 36
Item 20	 Weight 3	 Value 17

```
The program returns the optimal (or nearly optimal) solution. The optimal solution is the combination of items which do not exceed the weight limit and have the highest value. It prints out the values and weights of the items as well as the generation count it took to find the optimum. In this case the answer would be:

```
Value 27    Weight 21
Value 50    Weight 36
Value 33    Weight 13
Value 30    Weight 25
Value 36    Weight 1
Value 17    Weight 3


Total weight = 99
Total value = 193
generation 124
```

You choose the parameters of the knapsack, population and the various implementation techniques in the Main class - the inline comments tell you your available choices. There is also one available choice in the Evolution class on line 120.

Everything else should stay as it is.



# **For those who are reading and trying to evaluate my code quality:**

Now with having more experience, I can see what the project lacks: comments, more commits, etc.

I choose to keep it this way so it fully represents the program that I did for the time, I was not expecting anyone else to see or use it (in the future I may create another repository with the updated version).
The only thing I've changed is I added some comments in the Main class so if anyone were to use or read the program, they can try to adapt it or understand it better.

The latest commits were pushed much later than the code was actually written since begginer me didn't understand the importance of version control. And so it turns out I didn't even push the final version when I completed my project.

Some commit messages were in Lithuanian, so I changed them to be as accurate as possible with the small ammount of commits that I did at the time.

Extended Essay:
[May22_jrz071_EE.pdf](https://github.com/TitasZievys/knapsack/files/10051828/May22_jrz071_EE.pdf)
