//BFS
/**
 *
 * @author Sawaira Rafi
 */
import java.util.*;
public class BestFirstSearch {
     private PriorityQueue<Vertex> priorityQueue;
    private int heuristicvalues[];
    private int numberOfVertices;
    
    public static final int MAX_VALUE = Integer.MAX_VALUE;
 
    public BestFirstSearch(int numberOfNodes)
    {
        this.numberOfVertices = numberOfNodes;
        this.priorityQueue = new PriorityQueue<Vertex>();
    }
 
    public void bestFirstSearch(Canvas board, int[] heuristicvalues,int source,Vertix[] v)
    {  
        int sourceNode;
        int destinationNode;
        int visited[] = new int [numberOfVertices + 1];
        this.heuristicvalues = heuristicvalues;
        int parent=source;
      
        Food destination = new Food();
        destination.fx=1;
        destination.fy=3;
        
        Pacman player = new Pacman();
        player.x=0;
        player.y=0;
                
        priorityQueue.add(new Vertex(source, this.heuristicvalues[source]));
        visited[source] = 1;
        Scanner input = new Scanner(System.in);
        
        while (!priorityQueue.isEmpty())
        {
            sourceNode = getMinimumHeuristicValue();
            destinationNode = 1;
 
            System.out.println(v[sourceNode].label+ "\t");	
             board.ReassignValues(1,3,v[sourceNode].label,player,destination);
             board.displayMatrix();
             String enter=input.nextLine();
            while (destinationNode <= numberOfVertices)
            { 
                Vertex vertex = new Vertex(destinationNode,this.heuristicvalues[destinationNode]);
            
                if ((board.canvas[sourceNode][destinationNode] != MAX_VALUE && sourceNode != destinationNode)&& visited[destinationNode] == 0)
                { 
                    if(board.canvas[sourceNode][destinationNode] ==1){

                    if(vertex.isAdjacent(parent,destinationNode,board.canvas) || source==parent){
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                    parent=destinationNode;
                
                    }                    
                }
                 
                }
                
                destinationNode++;
               
            }
        }        
    }
 
    private int getMinimumHeuristicValue()
    {
        Vertex vertex = priorityQueue.remove();
        return vertex.node;
    }
 
    
 
class Vertex 
{
    public int heuristicvalue;
    public int node;
  
 
    public Vertex(int node, int heuristicvalue)
    {
        this.heuristicvalue = heuristicvalue;
        this.node = node;
    }
 
    public Vertex()
    {
 
    } 

    public boolean isAdjacent(int v1, int v2,int[][] canvas){
     if (canvas[v1][v2] == 1){
            return true;
        }
        return false;
    }
}}

