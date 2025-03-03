import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      
      
      Random rand = new Random();
      Scanner scan = new Scanner(System.in);
      
      
      int size,
      min = 1, max = 100;
      int [] tallyA = new int [max+1];
      int [] tallyB = new int [max+1];
      //set both tally arrays to zero
       for (int i = 0; i < tallyA.length; i++) 
       {
          tallyA[i] = 0;
          tallyB[i] = 0;
       }
       
       
      /*
       //premade sets for task 4
        int [] setA = {2, 5, 9, 6, 3, 4, 7, 8, 10};
        int [] setB = {4, 9, 6, 5, 3, 7, 8, 10, 24, 25, 30};
       */
      
      //TASK 1 
     
      //take in both sizes for set A and set B from the user
      System.out.printf("Size of Set A: ");
      size = scan.nextInt(); 
      while (size < 0 || size > 50 )
        size = scan.nextInt();
      int [] setA = new int [size];
      
      System.out.printf("Size of Set B: ");
      size = scan.nextInt(); 
      while (size < 0 || size > 50 )    //validate it is correct size
        size = scan.nextInt();
      int [] setB = new int [size];
      
      
      //TASK ONE
      
       //populate both sets with random numbers
    
       for (int i = 0; i < setA.length; i++)
       {
         setA[i] = rand.nextInt(max - min + 1) + min;
         while (tallyA[setA[i]] != 0)  //randomly generate it again until the it generates a number that hasnt been used
          setA[i] = rand.nextInt(max - min + 1) + min; 
         tallyA[setA[i]]++; //up the tally for the number that has been "filled"   
       }

        for (int i = 0; i < setB.length; i++)
       {
        setB[i] = rand.nextInt(max - min + 1) + min; 
         while (tallyB[setB[i]] != 0)
          setB[i] = rand.nextInt(max - min + 1) + min; 
        tallyB[setB[i]]++;
       }
       
       
       //display both arrays
       System.out.println("\n\nSet A: ");
       for (int i = 0; i < setA.length; i++) 
        {
          System.out.printf( "%-5d" , setA[i] );
          if ( (i + 1) % 10 == 0 )
                System.out.println();
        }
        
        System.out.println("\n\nSet B: ");
         for (int i = 0; i < setB.length; i++) 
        {
         System.out.printf( "%-5d" , setB[i] );
         if ( (i + 1) % 10 == 0 )
                System.out.println();
        }
        
        
        
     //TASK 2
     int [] unionSet = new int [setA.length + setB.length];
     int unionCount = 0;
      //since both sets have different lengths: tally is garunteed to have same size AND still indicate which numbers are in the sets
       for (int i = 0; i < tallyA.length; i++)
       {
           if (tallyA[i] == 1 || tallyB[i] == 1) //even if its a duplicate, it will only count once in the union
           {
            unionSet[unionCount] = i;
            unionCount++;
           }
       }
      
       System.out.println("\n\nUnion Set: ");
      for (int i = 0; i < unionCount; i++) 
        {
         
          System.out.printf( "%-5d" , unionSet[i] );
           if ( (i + 1) % 10 == 0 )
                System.out.println();
 
        }
        
        
        //TASK 3
        int [] intersectionSet;
        
        //the largest intersection can only be as big as the smaller set
        if (setA.length <= setB.length)
             intersectionSet = new int [setA.length];
        else
            intersectionSet = new int [setB.length]; 

        int intersectionCount = 0;
        
          for (int i = 0; i < tallyA.length; i++)
       {
           if (tallyA[i] == 1 && tallyB[i] == 1) //only adds to the set if BOTH tally arrays hold that number
           {
            intersectionSet[intersectionCount] = i;
            intersectionCount++;
           }
       }
       
        System.out.println("\n\nIntersection Set: ");
      for (int i = 0; i < intersectionCount; i++) 
        {
       
          System.out.printf( "%-5d" , intersectionSet[i] );
           if ( (i + 1) % 10 == 0 )
                System.out.println();
 
        }
       
        
      
        //TASK 4
    System.out.println("\n\nCommon Sequences: ");
    boolean commonFound = false;
    int indexA, indexB, count;

    for (int i = 0; i < setA.length; i++)
    {
        for (int j = 0; j < setB.length; j++)
        {
            if (setA[i] == setB[j])
            {
                //mark where the two are the same in set a and b (need to be different from i and j)
                indexA = i;
                indexB = j;
                count = 0; //and reset count
                
                if (indexA < setA.length && indexB < setB.length && setA[indexA] == setB[indexB]) //fixed going out of bounds: had to be in same check
                 {
                         count++;
                        indexA++;
                        indexB++;
                 }
                
                if (count >= 2)
                {
                    //using set a, output the sequence with how long the count is, stoping at the index right after the sequence ends
                    for (int k = i; k < count + i; k++)
                        System.out.printf( "%-3d" , setA[k] );
                    System.out.println();
                    
                    //move after sequence in outer for loop: already checked the numbers in the sequence
                    i += count -1; //fixed displaying sequences > 2 as multiple sequences
                    commonFound = true;
                }
            }
        }
    }
    
    //display needed message to user since bool is still false
    if (commonFound == false)
        System.out.println("No common sequences between the sets.");

    //TASK 5
    
    int temp, index = 0; //set index to beginning of array for swap
    
    for (int i = 0; i < setA.length; i++)
    {
        for (int j = 0; j < intersectionSet.length; j++)
        {
            //found common number
            if (setA[i] == intersectionSet[j])
            {
            //move common number to first "next" available index
                temp = setA[i];
                setA[i] = setA[index];
                setA[index] = temp;
                index++; //move next available index
            }
        }
    }
    
       System.out.println("\n\nNew Set A: ");
       for (int i = 0; i < setA.length; i++) 
        {
          System.out.printf( "%-5d" , setA[i] );
          if ( (i + 1) % 10 == 0 )
                System.out.println();
        }
  }
}