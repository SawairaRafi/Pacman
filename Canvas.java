//canvas class

/**
 *
 * @author Sawaira Rafi
 */
import java.util.*;
public class Canvas {
    int n;
     int[][] canvas=new int[16][16];
     String gameBoard[][]=new String[4][4];
    Scanner input = new Scanner(System.in);
   


void displayBoard(){
        
        System.out.println("These are the keys if your Pacman is at M location and Food is at C location");
        System.out.println("You will need to find a shortest Path from M to C so you will enter source as M");
        System.out.println("and Destination as C");
                
        System.out.println("  __________________________");
        System.out.println(" | A  |   B   |   C   |  D  |");
        System.out.println("  --------------------------");
        System.out.println(" | E  |   F   |   G   |  H  |");
         System.out.println("  --------------------------");
        System.out.println(" | I  |   J   |   K   |  L  |");
         System.out.println("  --------------------------");
        System.out.println(" | M  |   N   |   O   |  P  |");
        System.out.println("   __________________________");
        
        String enter= input.nextLine();
        System.out.println("These are the coordinates You cannot select The 'X' coordinates" );
        System.out.println("Enter x as 2 and y as 0 to place your pacman at 20 location or enter coordinates of your choice");
        System.out.println("x/y   0     1       2      3 ");
        System.out.println("  __________________________");
        System.out.println("0 | 00  |  01  |  02  |  X  |");
        System.out.println("  --------------------------");
        System.out.println("1 | 10  |  X   |  12  |  13 |");
         System.out.println("  --------------------------");
        System.out.println("2 | 20  |  21  |  22  |  X  |");
         System.out.println("  --------------------------");
        System.out.println("3 | 30  |  X   |  32  |  33 |");
        System.out.println("   __________________________");
        enter= input.nextLine();

}


void displayKeyBoard(){
    String enter= input.nextLine();
    System.out.println("This is our keyBoard");
        System.out.println("  __________________________");
        System.out.println(" | A  |   B   |   C   |  D  |");
        System.out.println("  --------------------------");
        System.out.println(" | E  |   F   |   G   |  H  |");
        System.out.println("  --------------------------");
        System.out.println(" | I  |   J   |   K   |  L  |");
        System.out.println("  --------------------------");
        System.out.println(" | M  |   N   |   O   |  P  |");
        System.out.println("   __________________________");
}

int[] AssignCoordinatesToKeys(String Key){
    int x[]= new int[3]; //x[0]-xCoorinate ,x[1]-yCoordinate ,x[2]-score
    if("A".equalsIgnoreCase(Key)) {
      x[0]=0; x[1]=0; x[2]=5;
      return x;
    }
    else if("B".equalsIgnoreCase(Key)) {
      x[0]=0; x[1]=1; x[2]=5;
      return x;
    }
    else if("C".equalsIgnoreCase(Key)) {
      x[0]=0; x[1]=2; x[2]=5;
      return x;
    }
    else if("D".equalsIgnoreCase(Key)) {
      x[0]=0; x[1]=3; x[2]=0;
      return x;
    }
    else if("E".equalsIgnoreCase(Key)) {
      x[0]=1; x[1]=0; x[2]=5;
      return x;
    }
    else if("F".equalsIgnoreCase(Key)) {
      x[0]=1; x[1]=1; x[2]=0;
      return x;
    }
    else if("G".equalsIgnoreCase(Key)) {
      x[0]=1; x[1]=2; x[2]=5;
      return x;
    }
    else if("H".equalsIgnoreCase(Key)) {
      x[0]=1; x[1]=3; x[2]=5;
      return x;
    }
    else if("I".equalsIgnoreCase(Key)) {
      x[0]=2; x[1]=0; x[2]=5;
      return x;
    }
    else if("J".equalsIgnoreCase(Key)) {
      x[0]=2; x[1]=1; x[2]=5;
      return x;
    }
    else if("K".equalsIgnoreCase(Key)) {
      x[0]=2; x[1]=2; x[2]=5;
      return x;
    }
    else if("L".equalsIgnoreCase(Key)) {
      x[0]=2; x[1]=3; x[2]=0;
      return x;
    }
    else if("M".equalsIgnoreCase(Key)) {
      x[0]=3; x[1]=0; x[2]=5;
      return x;
    }
    else if("N".equalsIgnoreCase(Key)) {
      x[0]=3; x[1]=1; x[2]=0;
      return x;
    }
    else if("O".equalsIgnoreCase(Key)) {
      x[0]=3; x[1]=2; x[2]=5;
      return x;
    }
    else if("P".equalsIgnoreCase(Key)) {
      x[0]=3; x[1]=3; x[2]=5;
      return x;
    }
    else{
        System.out.println("Invalid Key");
    }
    return x;
}
String[][] assignValues(int x ,int y , int fx, int fy,Pacman source,Food Destination){
    
    
     for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
             
                    gameBoard[i][j]=". ";
                
            }
        }
        //pacman location
        gameBoard[x][y]=source.label;
        //food location
        gameBoard[fx][fy]=Destination.label;
        //barriers location
        gameBoard[0][3]="X ";
        gameBoard[1][1]="X ";
        gameBoard[2][3]="X ";
        gameBoard[3][1]="X ";
        return gameBoard;
    
}


String[][] ReassignValues( int fx, int fy,String move,Pacman source,Food destination){
    //Resetting Board
     for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
             
                    gameBoard[i][j]=". "; 
            }
        }
    int[] value=AssignCoordinatesToKeys(move);
    //Pacman score
     if(value[2]!=0){
        source.power=value[2]+source.power;
     }
     if(value[2]==0){
        source.power=-1;
     }
     if(value[0]==destination.fx && value[1]==destination.fy ){ //pacman reaches food
         source.power=50;
     }
        gameBoard[fx][fy]=destination.label;
        //food location
        gameBoard[value[0]][value[1]]=source.label;
        
       
        return gameBoard;
    
}
 void displayMatrix()
{System.out.println("");
    System.out.print("Game Board");
   
    for (int i = 0; i < gameBoard.length; i++) {
        System.out.println("");
        for (int j = 0; j < gameBoard.length ;j++) {
            System.out.print("      " + gameBoard[i][j]);
        }
    }
}
 
  void displayAdjMatrix()
{System.out.println("");
    System.out.print("Game Board");
   
    for (int i = 0; i < canvas.length; i++) {
        System.out.println("");
        for (int j = 0; j < canvas.length ;j++) {
            System.out.print("      " + canvas[i][j]);
        }
    }
}
}
