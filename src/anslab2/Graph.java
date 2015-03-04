package anslab2;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JFrame {
	private static final long serialVersionUID = 1L;
	private TableMode mTableMode;
	private Data mData;
	private PlotOrientation mOrientation;
	
	public Graph(String title, TableMode mode, Data data,
			PlotOrientation orientation) {
		super(title);
		mTableMode = mode;
		mData = data;
		mOrientation = orientation;
		// create and set chart
		CategoryDataset dataSet = createDataset();
		JFreeChart chart = createChart(dataSet);
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(500, 270));
		setContentPane(panel);
		this.pack();
	}
	
	private JFreeChart createChart(CategoryDataset dataSet) {
		String yLabel = null;
		switch(mTableMode) {
		case FDT:
			yLabel = "Frequency";
			break;
		case RFT:
			yLabel = "Percentage";
			break;
		case BOTH:
			yLabel = "Frequency and Percentage";
		default:
			break;
		}
		return ChartFactory.createBarChart(null, mData.getDataName(), yLabel,
				dataSet, mOrientation, true, false, false);
		
	}
	
	private CategoryDataset createDataset() {
		String rowText = null;
		double modifier = 1;
		switch(mTableMode){
		case FDT:
			rowText = "Frequency";
			break;
		case RFT:
			rowText = "Percentage";
			modifier = 100 / mData.size();
			break;
		default:
			break;
		}
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Integer> dataMap = mData.getDataTree();
		for(String key : dataMap.keySet()) {
			double value = dataMap.get(key) * modifier;
			
			// null means BOTH
			if(rowText == null) {
				dataSet.addValue(value, "Frequency", key);
				dataSet.addValue(value * 100 / mData.size(), "Percentage", key);
			}
			else {
				dataSet.addValue(value, rowText, key);
			}
		}
		return dataSet;
	}
}
