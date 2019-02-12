package org.but4reuse.adapters.proteins.utils;
import org.but4reuse.adapters.proteins.ProteinElement;
public class ProteinUtils {
	/**
	 * Color similarity based on euclidean distance
	 * 
	 * @param letter
	 * @param letter2
	 * @return a range from 0 to 1: 0 for proteins's frequency are diffrents, 1 for completely equal
	 */
	public static double getProteinSimilarity(ProteinElement proteinElement,ProteinElement proteinElement2){
		/*System.out.println("protein Util"+proteinElement.letter);
		System.out.println("fre 1 :"+proteinElement.frequency);
		System.out.println("protein Util"+proteinElement2.letter);
		System.out.println("fre 2 :"+proteinElement2.frequency);
		System.out.println("-------------------------------");*/
		int p1 = proteinElement.frequency;
		int p2 = proteinElement2.frequency;
		if(p1==p2){
			System.out.print("yes");
			return 1;
		} 
		else{
			return 0;
		}
	}	
}
