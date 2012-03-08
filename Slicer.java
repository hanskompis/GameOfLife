/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

/**
 *
 * @author hansi
 */
public class Gol {

     private static int[] calculateSlices(int length, int divider)
         {
             int[] slices = new int[divider];
             int baseSlice = (int)length / divider;
             int modulus = length % divider;
             for (int i = 0; i < divider; i++)
             {
                 if (modulus > 0)
                 {
                     slices[i] = baseSlice + 1;
                     modulus--;
                 }
                 else
                     slices[i] = baseSlice;
             }
             return slices;
         }
    public static void main(String[] args) {
        int[] slices = calculateSlices(813, 16);
             for(int slice : slices)
                 System.out.println("Slice size is: " + slice);
             
    }
}
