package ability.createDB;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends JFrame {
	ArrayList<String> dateArray  = new ArrayList<String>();
	ArrayList<Double> percentArray = new ArrayList<Double>();
	private  String chartTitle,applicationTitle; 
	protected JPanel chartPanel;
    public LineChart(String applicationTitle , String chartTitle , ArrayList<String> DateArray, ArrayList<Double> Percent,String y) {
    	super(applicationTitle);
    	this.chartTitle = chartTitle;
    	this.applicationTitle = applicationTitle;
        setPercentArray(Percent);
        setDatetArray(DateArray);
        chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(16, 9);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
    }
 
    private JPanel createChartPanel() {
    	 CategoryDataset dataset = createDataset();
    	 
    	 JFreeChart chart = ChartFactory.createLineChart(applicationTitle,"Date", chartTitle, dataset);
    	 
    	 return new ChartPanel(chart);
    }
 
    private CategoryDataset createDataset() {
    	    DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
    	      for(int i=0;i<dateArray.size();i++) {
    	    	  dataset.addValue( percentArray.get(i) , "Date" , String.valueOf(dateArray.get(i)) );
    	      }
    	      return dataset;
    	   
    }
    public void setPercentArray(ArrayList<Double> percent) {
  	  this.percentArray = percent;
  	
     }
     
     public void setDatetArray(ArrayList<String> dateArray) {
  	  
  	   this.dateArray = dateArray;
     }
    public JPanel getJPanel() { 
    	return chartPanel;
    }
}