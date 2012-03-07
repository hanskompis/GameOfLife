package gol.testi;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class FileHandler {

    private int[][] grid;
    private String inputFileName;
    private String outputFileName;
    private int steps;
    private int n;

    public FileHandler(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }
    
    // Avaa tiedoston ja lukee datan int-taulukkoon
    private void openFileToRead() throws IOException {
        
        // Lukee ensimmäiseltä riviltä matriisin koon ja sukupolvien määrän
        Scanner in = new Scanner(new FileReader(inputFileName));
        String line;
        line = in.nextLine();
        StringTokenizer init = new StringTokenizer(line);
        String s = init.nextToken(" ");
        n = Integer.parseInt(s);
        s = init.nextToken();
        steps = Integer.parseInt(s);
        grid = new int[n][n];
        
        // Lukee solumatriisin
        for (int i = 0; i < n; i++) {
            if (in.hasNext()) {
                line = in.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                for (int j = 0; j < n; j++) {
                    if (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        int temp = Integer.parseInt(token);
                        if (temp == 0 || temp == 1) {
                            grid[i][j] = temp;
                        } else {
                            System.out.println("Virheellinen symboli rivillä " + i + ", sarakkeessa " + j + ": " + temp);
                        }

                    } else {
                        System.out.println("Matriisissa on alle " + n + " saraketta!");
                        return;
                    }
                }

            } else {
                System.out.println("Matriisissa on alle " + n + " riviä!");
                return;
            }
        }
        in.close();
    }

    // Luo uuden tiedoston ja kirjoittaa sinne lopputilanteen samassa formaatissa kuin alkuperäinen tiedosto
    private void openFileToWrite(int[][] grid) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName));
        out.write(n + " " + steps);
        out.write("\r\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                out.write("" + grid[i][j]);
                if (j < grid[i].length) {
                    out.write(" ");
                }
            }
            if (i < grid.length) {
                out.write("\r\n");
            }
        }
        out.close();
    }
    
    public int[][] readGrid() {
        try {
            openFileToRead();
        } catch (Exception e) {
            System.out.println("Cannot open file to read: " + e.getMessage());
        }
        return grid;
    }

    public void writeGrid(int[][] grid) {
        try {
            openFileToWrite(grid);
        } catch (Exception e) {
            System.out.println("Cannot open file to write: " + e.getMessage());
        }
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
