//MainTest Class 
/**
 *
 * @author Sawaira Rafi
 */

import java.util.*;

public class TestGame {

    /**
     * @param args the command line arguments
     */
  
  
    static int n; //number of vertices
    static Vertix[] vertix = new Vertix[16];
    static Canvas board = new Canvas();
    
    public static void main(String[] args) {
        String move; //userMove
        int timer=0; //timer
        int turns=3; //number of tries in case of no path
        
        Scanner input=new Scanner(System.in);
        Scanner inputs=new Scanner(System.in);
        
        Pacman source= new Pacman();
        Food  destination = new Food();
        
       
        System.out.println("_____<Pacman>____");
        System.out.print("Enter your name:");
        String playerName=input.nextLine();
        
        System.out.println("Welcome "+playerName+" !");
           System.out.println("_____Game Begins!____");
      

        for (int i = 0; i < board.canvas.length; i++) {
            for (int j = 0; j < board.canvas.length; j++) {
                board.canvas[i][j]=0;
            }
        }
        addVertix("A","white");
        addVertix("B","white");
        addVertix("C","white");
        addVertix("D","black");//barrier
        addVertix("E","white");
        addVertix("F","black"); //barrier
        addVertix("G","white");
        addVertix("H","white");
        addVertix("I","white");
        addVertix("J","white");
        addVertix("K","white");
        addVertix("L","black");//barrier
        addVertix("M","white");
        addVertix("N","black");//barrier
        addVertix("O","white");
        addVertix("P","white");
       
        
        addEdge("A", "B");
        addEdge("A", "E");
        addEdge("B", "C");
        addEdge("C", "G");
        addEdge("G", "H");
        addEdge("G", "K");
        addEdge("E", "I");
        addEdge("I", "J");
        addEdge("J", "K");
        addEdge("K", "O");
        addEdge("O", "P");
        addEdge("I", "M");
    
     
        addBarriers("C", "D");
        addBarriers("D", "H");
        addBarriers("H", "L");
        addBarriers("K", "L");
        addBarriers("P", "L");
        addBarriers("M", "N");
        addBarriers("N", "O");
        addBarriers("N", "J");
        addBarriers("F", "B");
        addBarriers("F", "J");
        addBarriers("F", "E");
        addBarriers("F", "G");
            
         System.out.print("\nIf you want to play Enter 0 else 1 for autoplay mode >");
         int autoPlay=inputs.nextInt();
         if(autoPlay==1){
             //Best First Search
             System.out.println("\nAutoplay Mode:");
             System.out.println("In Autoplay Mode you just have to press enter after each move");
            int h[]= new int[]{1,1,1,2,1,2,1,1,1,1,1,2,1,2,1,1}; //heuristic values
            BestFirstSearch bestFirstSearch = new BestFirstSearch(15);
            bestFirstSearch.bestFirstSearch(board, h,getIndex("A"),vertix);
            System.out.println("\nCongratulation You Won! Pacman reaches the goal");
            System.out.println("Shortest Path :A B C G H");
            
    }   else{
        System.out.println("Are you excited Lets set our game board , Press Enter to see further instructions>");
        board.displayBoard();
        System.out.println("\nENTER PACMAN LOCATION");
        System.out.print("Enter x coordinate>");
        int x=inputs.nextInt();
        System.out.print("Enter y coordinate>");
        int y=inputs.nextInt();
        source.setX(x);
        source.setY(y);

        System.out.println("\nENTER FOOD LOCATION");
        System.out.print("Enter x coordinate>");
        int fx=inputs.nextInt();
        System.out.print("Enter y coordinate>");
        int fy=inputs.nextInt();
        destination.setfX(fx);
        destination.setfY(fy);
        
        board.assignValues(x, y, fx, fy,source,destination);
        //canvas=board.canvas;
        board.displayMatrix();
 
        System.out.println("\nHint For Shortest Path ");
        board.displayKeyBoard();
        System.out.print("\nEnter the source :Use Keys (Alphabets)>");
        String start=input.nextLine();
        System.out.print("\nEnter the destination :Use Keys (Alphabets)>");
        String end=input.nextLine();
        Dij d= new Dij();
        int time=d.Dijkstra(board.canvas,vertix,getIndex(start),getIndex(end));
        
        String prevMove=start;
        while(source.power!=-1 && source.power !=50 && timer<=time)
        {
            System.out.println("\nEnter Your next Move: ");
            move=input.nextLine();
            timer++;
            if(isAdjacent(move,prevMove))
            {
                board.ReassignValues(fx,fy,move,source,destination);
                System.out.println("\n Score= "+source.power);
                prevMove=move;
                board.displayMatrix(); 
            }
            else{
                  board.ReassignValues(fx,fy,move,source,destination);
                  if(source.power!=-1){
                      if(turns!=1){
                      turns--;
                  System.out.println("\nTry Again No Path Exist Check Hint!");
                  System.out.println("You have "+turns+" turns left");
            }else{
                          System.out.println("You are out of turns");
                          turns=-1;
                          break;
                      }
            }
        
        } }         
        
        //Timer if user choses a longer path that is path length greater than shortest path length
        
        if(source.power!=50 && source.power!=-1 && timer>time )
        {
            System.out.println("\nTime Over ! Better luck next Time");
        }
        
        //win case:when pacman reaches the food in shortest time 
         if (source.power==50 && turns!=-1)
         {
            System.out.println("\nCongratulations you won !");
            System.out.println("Your Score= "+source.power);
         }
         //GameOver case:When collided with Barrier
          if (source.power==-1)
          {
           
            System.out.println("\nYou collided with a barrier! Game Over");
        }
          if(turns==-1){
              System.out.println("Game Over!");
          }
    }}

   static void index(){
       for (int i = 0; i < vertix.length; i++) {
          
               System.out.println(vertix[i].label+" index" );
               getIndex(vertix[i].label);
           
           
       }
   }
        
     static boolean isAdjacent(int v1, int v2){
     if (board.canvas[v1][v2] == 1){
            return true;
        }
        return false;
    }
   static boolean isReachable(int v1, int v2){
    
     if (isAdjacent(v1,v2)){
            return true;
        }
        return false;
    }
     static int getIndex(String label){//improve it
       
          for (int i = 0; i < vertix.length; i++) {
            if (label.equalsIgnoreCase(vertix[i].label)){
               
                return i;
               
            }
        }
        return -1;
     }




 static boolean addEdge(String v1,String v2){
        int ind1=getIndex(v1);//improve it
        int ind2=getIndex(v2);
       
        if (ind1 != -1 && ind2 != -1 ){
          
            board.canvas[ind1][ind2]=1;
            board.canvas[ind2][ind1]=1;

            return true;
        }//for undirected graph
        return false;
    }
  static boolean addBarriers(String v1,String v2){
        int ind1=getIndex(v1);//improve it
        int ind2=getIndex(v2);
       
        if (ind1 != -1 && ind2 != -1 ){
           
            board.canvas[ind1][ind2]=0;
            

            return true;
        }//for undirected graph
        return false;
    }
 
   static boolean addVertix(String label,String color){//improve it using hash
        //traverse the vertix list linearly and add the vertex with label on the provided space.
        
         n++;
         for (int i = 0; i < vertix.length; i++) {
          
         vertix[n-1] = new Vertix(label,color);
       
    }
        return true;
    }
   ////////////////
   static boolean isAdjacent(String v1, String v2){
     if (board.canvas[getIndex(v1)][getIndex(v2)] == 1){
            return true;
        }
        return false;
    }


}
   class Vertix{
    String label;
    String color;
    int pathLength;

    Vertix(String label,String color){
        this.label=label;
        this.color=color;
    }
    Vertix(){
        
    }
    
      

}

class Dij{
    static int Dijkstra(int adjMat[][],Vertix[] distance,int Start,int destination){
    int v=16;
       
    boolean visited[]= new boolean[v];
    Vertix path[]= new Vertix[v];
    path[0]=distance[Start];
    int count=1;
    int check=0;
    int[] parents = new int[v];
    parents[Start]=-1;
 
    
    distance[Start].pathLength=0;
        for (int i = 0; i < v; i++) {
            if(distance[i].label!=distance[Start].label)
            {
            distance[i].pathLength=Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < v-1; i++) {
            //find vertex with min distance
            int minVertex=findMinVertex(distance,visited);
          
            visited[minVertex]=true;
               
           
            for (int j = 0; j < v; j++) {
               
                if(adjMat[minVertex][j] !=0 && !visited[j] && distance[minVertex].pathLength!=Integer.MAX_VALUE){
                    int newDist = distance[minVertex].pathLength+adjMat[minVertex][j];
                  
                    if(newDist<distance[j].pathLength){
                       
                        parents[j] = minVertex;
                        distance[j].pathLength=newDist;
                  
                }                      
                    }
                }
            }
        
    //print

        for (int i = 0; i < v; i++) {
            if(distance[i].pathLength>20){
                distance[i].pathLength=0;
              
            }
            
            if(i==destination){
            check=i;
            System.out.println("\nPath length form "+distance[Start].label+" to "+ distance[i].label+" "+distance[i].pathLength);
            if(distance[i].pathLength==0){
                System.out.println(" \nGame Over! You colided with an obstacle ");
                return 0;
            }
        }
    }   System.out.println("Shortest path Hint");
        System.out.print(distance[Start].label);
        printPath(parents, destination, distance);
        
return distance[destination].pathLength;

    }
        
    
    
    private static int findMinVertex(Vertix[] distance, boolean[] visited){
        int minVertex=-1;
        for (int i = 0; i < distance.length; i++) {
            if(!visited[i] && (minVertex==-1 || distance[i].pathLength< distance[minVertex].pathLength))
                    minVertex=i;
           
          
        } 
        return minVertex;
    }
    static void printPath(int parent[], int j,Vertix[] distance)
{
    
    if (parent[j]==-1)
        return;

    printPath(parent, parent[j],distance);
    System.out.print(" "+distance[j].label);
 
}
   
    
}
