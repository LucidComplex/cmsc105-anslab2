package anslab2;

import java.util.Scanner;

import org.jfree.chart.plot.PlotOrientation;


public class AnsLab2 {
	private Scanner mScanner;
	private Data mData;
	private TableMode mTableMode;
	private Graph mGraph;
	
	public AnsLab2() {
		mScanner = new Scanner(System.in);
		mData = new Data();
	}
	
	public void start() {
		
		while(true) {
			setupData();
			chooseTableMode();
			System.out.println(mData.asRawTable());
			
			String table = "";
			switch(mTableMode) {
			case BOTH:
				table = mData.asBothTable();
				break;
			case FDT:
				table = mData.asFDTable();
				break;
			case RFT:
				table = mData.asRFTable();
				break;
			default:
				break;
			}
			System.out.println(table);
			
			setupGraph();
			showGraph();
			
			askForContinue();
		}
	}
	
	private void askForContinue() {
		System.out.print("Restart program? (y/N): ");
		String input = mScanner.nextLine();
		input = input.trim();
		if(!input.matches("^[yY]$"))
			System.exit(0);
	}
	
	private void showGraph() {
		mGraph.setVisible(true);
	}
	
	private void setupGraph() {
		PlotOrientation orientation = null;
		System.out.print("Choose graph orientation:"
				+ "\n\t1. Horizontal"
				+ "\n\t2. Vertical"
				+ "\n\nSelection: ");
		do {
			String input = mScanner.nextLine();
			switch(input) {
			case "1":
				orientation = PlotOrientation.HORIZONTAL;
				break;
			case "2":
				orientation = PlotOrientation.VERTICAL;
				break;
			}
		} while(orientation == null);
		mGraph = new Graph(mData.getDataName() + " Graph", mTableMode, mData,
				orientation);
		System.out.println("Graph setup complete.");
	}
	
	private void setupData() {
		System.out.print("Enter name of data: ");
		mData.setDataName(mScanner.nextLine());
		System.out.print("\nEnter comma separated data: ");
		mData.setData(mScanner.nextLine());
		System.out.println("\nData setup finished.");
	}
	
	private void chooseTableMode() {
		boolean set = true;
		do {
			System.out.println("Choose table type:"
					+ "\n\t1. Frequency Distribution"
					+ "\n\t2. Relational Frequency"
					+ "\n\t3. Frequency Distribution and Relational Frequency");
			String input = mScanner.nextLine();
			System.out.println(input);
			switch(input) {
			case "1":
				mTableMode = TableMode.FDT;
				break;
			case "2":
				mTableMode = TableMode.RFT;
				break;
			case "3":
				mTableMode = TableMode.BOTH;
				break;
			default:
				set = false;
				break;
			}
		} while(!set);
	}

}

enum TableMode {
	FDT, RFT, BOTH
}
