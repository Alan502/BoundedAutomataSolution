import java.util.Scanner;

import javax.annotation.Generated;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner("3 WBWBWBWBW 1000 WBWBWBWBBBW 5235 WBWWBWWBBBBWWBBWWWWWWBBWWWBBWWWWWWBBWWBBBBWWBWWBW");
		long lineNumber = 0;
		while(sc.hasNext()){
			lineNumber++;
			System.out.print("LINE "+lineNumber);
			
			final long maxStep = sc.nextInt();
			String line = sc.next();
			final long lineLength = line.length();
			line = line.replace("W", "0");
			line = line.replace("B", "1");
			long targetLine = Long.parseLong(line, 2);
			long step = 1;
			for(long rule = 1; rule < 256; rule++){
				step = 1;
				long currentStep = 1 << (lineLength/2);
				long previousStep = 0;
				while(step < maxStep && previousStep != currentStep){
//					System.out.println("Step "+step+" MaxStep "+maxStep+" Rule "+rule+" Prev step: "+previousStep+" Current step: "+currentStep);
					previousStep = currentStep;
					currentStep = nextStep(rule, currentStep, lineLength);
					if(currentStep == targetLine){
						System.out.print(" ("+rule+","+(step+1)+")");
						break;
					}
					step++;
				}
			}
			System.out.println();
		}
		
	}
	public static long nextStep(long rule, long line, long lineLength){
		long nextLine = 0;
		for(long i = 0; i <= lineLength-3; i++){
			long eval = (line >>> i)&7;
			nextLine += ((rule >>> eval)&1)<<(i+1);
		}
		return nextLine;
	}

}
