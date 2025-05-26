
package Labs.Lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Test{
    public static void main(String[] args){
        CitiesOfTurkiye myGraph = new CitiesOfTurkiye(81);
        
        try {
            File myObj = new File("Data\cities.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                myGraph.addVertex(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        try {
            File myObj = new File("Data\graph.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String temp[] = data.split(" ");
                for(int j=0; j<temp.length; j++){
                    myGraph.edges[i][j] = Integer.parseInt(temp[j]);                   
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("Please select a city to see the neighbourhood level: ");
        String city = s.nextLine();
        myGraph.bfs(city);
        System.out.println("");
    }
}

