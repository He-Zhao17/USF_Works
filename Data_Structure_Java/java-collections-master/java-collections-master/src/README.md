### Lab 6: Collections

##### 30 points

##### Due: Sunday, Oct 25, 11:59pm

For this exercise, I've provided some starter code. You should feel free to extend or modify it as necessary. You might want to add inner classes, additional helper methods, or instance variables. That's all fine.

1. (5 points) Set example - union, intersection, difference

    Implement a class SetOperators, with static methods Union, Intersection, and Difference. Use the Set collection (you can choose the implementation) to compute each of these.
    
   Use JUnit to implement tests for each method, plus a @BeforeAll to set things up.
   Use this on your ham and spam emails to find the words that occur in either document (union), in both documents (intersection), or in ham but not spam (set difference).

2. (5 points) Stack example.  
    
    Write a class ListReverser, with a static method reverseList. It should take as input a Linked List and use a Stack to reverse it.
    You may either use your linked list from assignment 4 or the built-in LinkedList. 
Use JUnit to implement a test for this, plus a Before to set things up. 

3. (10 points) Queue example.

    Suppose that we have been hired by Safeway to develop a model to help them understand how many checkout aisles they should have open at any given time. They want to be able to model how long it takes for each customer to get through the line.

We can model this with a queue. 

I’ve provided some starter code for you to see how to set this up. This is what’s called a discrete event simulator. We’re running it in a loop for n iterations. Each iteration, a random number of new customers (modeled as Tasks) get in line. Right now this is a random number between 0 and 2. Each customer has a duration that they spend in line. This is currently set to 5.

Each iteration we check the front of the queue to see if that task is completed. If it is, we dequeue it and check to see how long it took to get through the queue. This is called throughput. 

With only one queue and a duration of 5, the store quickly backs up. We either need faster customers or more checkers.

* Extend the model to make duration a random number, with range as a parameter you can set.
* Extend the model to add additional queues. You can decide how you would like to implement this. When a new Task arrives, place it in the shortest queue. 
* Rather than just printing out the throughputs, calculate the average, max and minimum throughputs and report these at the end. 
* Test your model with a range of parameters for duration, number of arrivals, and number of queues. What are your recommendations to Safeway?

4 (5 points) Priority Queue.

Extend the StoreSimulator to use a PriorityQueue. (You can either create a second class, or parameterize the Queue). Extend the Task to contain a priority from 1 to 5. It will need to implement the Comparable interface. Priority 1tasks are the most important and should be at the front of the queue. If two tasks have the same priority, the older one should go first.

Re-run your model. How does this change throughput? Do some priorities do better than others? Are there some tasks that never get through the queue? (We call this starvation)

(5 points) Prepare a short document that describes your results for the Queue and PriorityQueue experiments.

Note: there are lots of ways to extend this. You can decide to put customers in the longest queue, or choose randomly. You can increase customers’ priority over time, to avoid starvation. You can have some customers get frustrated and leave. You might have multiple levels of queues, as at an airport - one to check in and one to get through security. This same approach is also used to model packets in a network to identify points of congestion.
