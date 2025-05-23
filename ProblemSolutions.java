
/******************************************************************
 *
 *   Malia Kuykendall / COMP 272/400C-001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

 import java.util.*;
 import java.util.PriorityQueue;
 
 public class ProblemSolutions {
 
     /**
      * Priority Queue (PQ) Game
      *
      * PQ1 Problem Statement:
      * -----------------------
      *
      * You are given an array of integers of boulders where boulders[i] is the
      * weight of the ith boulder.
      *
      * We are playing a game with the boulders. On each turn, we choose the heaviest
      * two boulders and smash them together. Suppose the heaviest two boulders have
      * weights x and y. The result of this smash is:
      *
      *    If x == y, both boulders are destroyed, and
      *    If x != y, the boulder of weight x is destroyed, and the boulder of
      *               weight y has new weight y - x.
      *
      * At the end of the game, there is at most one boulder left.
      *
      * Return the weight of the last remaining boulder. If there are no boulders
      * left, return 0.
      *
      *
      * Example 1:
      *
      * Input: boulders = [2,7,4,1,8,1]
      * Output: 1
      * Explanation:
      * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
      * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
      * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
      * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
      * value of the last stone.
      *
      * Example 2:
      *
      * Input: boulders = [1]
      * Output: 1
      *
      *
      *
      * RECOMMENDED APPROACH
      *
      * Initializing Priority Queue in reverse order, so that it gives
      * max element at the top. Taking top Elements and performing the
      * given operations in the question as long as 2 or more boulders;
      * returning the 0 if queue is empty else return pq.peek().
      */
 
   public static int lastBoulder(int[] boulders) {
 
    int finalBoulder = 0;
    PriorityQueue<Integer> orderBoulders = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < boulders.length; i++) {
        orderBoulders.add(boulders[i]);
    } // create priority queue of all items in the boulders array

    while (orderBoulders.size() > 1) {
        int firstBoulder = orderBoulders.poll();
        int secondBoulder = orderBoulders.poll();

        if (firstBoulder != secondBoulder) {
            firstBoulder -= secondBoulder;
            orderBoulders.offer(firstBoulder);
        }
    }
    // process repeats until either 1 or fewer elements are left in the orderBoulders queue
        // take top 2 priority elements with poll, compare weights
        // if the weights aren't equal, subtract weight of second-priority boulder from first-priority
        // then re-add the resulting weight into the queue
        // if they are equal, nothing additional needs to happen

    if (orderBoulders.size() > 0) {
        finalBoulder = orderBoulders.peek();
    }
    // at this point, the two possibilities are that there are no boulders in the queue, or there is one in the queue
    // if there's a boulder left in the queue, this is the final boulder, so return the weight, otherwise 0 is returned

       //
       // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
       //

       return finalBoulder;
   }
 
 
     /**
      * Method showDuplicates
      *
      * This method identifies duplicate strings in an array list. The list
      * is passed as an ArrayList<String> and the method returns an ArrayList<String>
      * containing only unique strings that appear more than once in the input list.
      * This returned array list is returned in sorted ascending order. Note that
      * this method should consider case (strings are case-sensitive).
      *
      * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
      * the method would return an ArrayList<String> containing: "Dog", "Lion"
      *
      * @param  input an ArrayList<String>
      * @return       an ArrayList<String> containing only unique strings that appear
      *               more than once in the input list. They will be in ascending order.
      */
 
     public static ArrayList<String> showDuplicates(ArrayList<String> input) {
        PriorityQueue<String> duplicateQueue = new PriorityQueue<>();
        Set<String> duplicateSet = new HashSet<>();
        ArrayList<String> finalArray = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            duplicateQueue.add(input.get(i));
        }
        // populate queue with input array
        for (int i = 0; i < input.size() - 1; i++) {
            String tempString = duplicateQueue.poll();
            if (duplicateQueue.peek().equals(tempString)) {
                duplicateSet.add(tempString);
            }
        }
        // go through entire queue (except last element), and compare current element to next priority element
            //  since identical elements will have the same priority, then they should appear right next to eachother
            // therefore, you can just check if the next element in the queue is equal to the current element
        // if so, then add the current element to a set
            // since sets don't allow for duplicate elements, there isn't a need to worry about duplicates being added

        duplicateQueue.clear();
        duplicateQueue.addAll(duplicateSet);
        // refill the queue with just the elements in the set, these are the duplicate elements that we just found
        for (int i = 0; i < duplicateSet.size(); i++) {
            finalArray.add(duplicateQueue.poll());
        }
        // insert resulting queue into arrayList based on priority

        return finalArray; // return the final, sorted array of duplicate elements
        
        // Make sure result is sorted in ascending order
         //
         //  YOUR CODE GOES HERE
         //
     }
 
 
     /**
      * Finds pairs in the input array that add up to k.
      *
      * @param input   Array of integers
      * @param k       The sum to find pairs for
 
      * @return an ArrayList<String> containing a list of strings. The ArrayList
      *        of strings needs to be ordered both within a pair, and
      *        between pairs in ascending order. E.g.,
      *
      *         - Ordering within a pair:
      *            A string is a pair in the format "(a, b)", where a and b are
      *            ordered lowest to highest, e.g., if a pair was the numbers
      *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
      *         - Ordering between pairs:
      *            The ordering of strings of pairs should be sorted in lowest to
      *            highest pairs. E.g., if the following two string pairs within
      *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
      *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
      *
      *         Example output:
      *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
      *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
      *
      *  HINT: Considering using any Java Collection Framework ADT that we have used
      *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
      *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
      *  Java Framework documentation in its use.
      */
 
     public static ArrayList<String> pair(int[] input, int k) {
        HashSet<Integer> orderingSet = new HashSet<>();
        PriorityQueue<Integer> orderingQueue = new PriorityQueue<>();
        PriorityQueue<Integer> reversedQueue = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<String> pairArray = new ArrayList<>();
        // initializing data structures needed for problem

        for (int i = 0; i < input.length; i++) {
            orderingSet.add(input[i]);
        }

        for (int i : orderingSet) {
            if (orderingSet.contains(k - i)) {
                orderingQueue.offer(i);
                reversedQueue.offer(i);
            }
        }
        // using hashSet to get all pair combinations that add up to k
        // then store these values in two priority queues
            // one is in reversed order, so that last item of the first queue can be accessed more easily

        int pairNumber = orderingQueue.size()/2;
        if (orderingQueue.size() % 2 == 1) {pairNumber++;}
        // the amount of pairs needed is half the length of the whole queue
        // if there's an odd number of items in the queue, that means that one of the pairs is the same value twice
            // (ex: k = 10, pair of 5 and 5), so we add one to the total pair count because of how rounding works in java

        for (int i = 0; i < pairNumber; i++) {
            int firstNum = orderingQueue.poll();
            int secondNum = reversedQueue.poll();

            pairArray.add("(" + firstNum + ", " + secondNum + ")");
        }
        // take first item from queue 1, and the first item from queue 2 using poll,
            // (equivalent to taking the first and last items in the first queue)
        // this is a pair, so add it to the array

         //
         //  YOUR CODE GOES HERE
         //
         return pairArray; // returning the final, sorted array of pairs
         
         // Make sure returned lists is sorted as indicated above
     }


 }