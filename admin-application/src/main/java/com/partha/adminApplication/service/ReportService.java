package com.partha.adminApplication.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.partha.adminApplication.dto.MonthlySalesReport;
import com.partha.adminApplication.entities.Book;
import com.partha.adminApplication.repositories.BookRepository;

@Service
public class ReportService {

	@Autowired
	private BookRepository bookRepository;

	public static final Logger logger= LoggerFactory.getLogger(ReportService.class);




	public 	ModelAndView getInventoryReport(ModelAndView mv) throws IOException {
		logger.info("ReportService.getInventoryReport() :: start");
		JFreeChart chart = ChartFactory.createBarChart3D(
				"Inventory Details",           
				"books",            
				"Quantity",            
				getInventoryDataset(),          
				PlotOrientation.VERTICAL,           
				true, true, false);
		//chart.getPlot().setBackgroundPaint(Color.WHITE);
		int width = 800;   /* Width of the image */
		int height = 480;  /* Height of the image */
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(stream, chart, width, height);
		mv.addObject("chart", Base64.getEncoder().encodeToString(stream.toByteArray()));
		logger.info("ReportService.getInventoryReport() :: end");
		return mv;
	}
	
	private CategoryDataset getInventoryDataset( ) {	
		Iterable<Book> books = bookRepository.findAll();
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
		for(Book book : books) {
			dataset.addValue(book.getAvailableUnits(), book.getTitle(), "");
		}
		return dataset; 
	}
	
	
	public ModelAndView getMonthlySalesReport(ModelAndView mv) throws IOException {
		logger.info("ReportService.getMonthlySalesReport() :: start");
		
		int year = mv.getModelMap().get("reportYear")==null ? 
				Year.now().getValue() : ((Integer)mv.getModelMap().get("reportYear")).intValue();
		
		mv.addObject("reportYear",year);
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		List<MonthlySalesReport> monthlySalesReport = bookRepository.getMonthlySalesReport(year);
		for(MonthlySalesReport month : monthlySalesReport) {
			dataset.setValue(month.getMonthName(), month.getSaleCount().intValue());
		}
		
		JFreeChart chart = ChartFactory.createPieChart3D(
								"Monthly-sales-figure",   // chart title
								dataset, // data
								true,             // include legend
								true,
								false);
		chart.getPlot().setBackgroundPaint(Color.WHITE);
		
		int width = 800;   /* Width of the image */
		int height = 480;  /* Height of the image */
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsJPEG(stream, chart, width, height);
		mv.addObject("chart", Base64.getEncoder().encodeToString(stream.toByteArray()));
		mv.addObject("monthlySales",monthlySalesReport);
		logger.info("ReportService.getMonthlySalesReport() :: start");
		return mv;
	}
	
	

	

	//	public 	ModelAndView getInventoryReport(ModelAndView mv) throws IOException {
	//		logger.info("ReportService.getInventoryReport() :: start");
	//		
	//		Iterable<Book> books = bookRepository.findAll();
	//		DefaultPieDataset dataset = new DefaultPieDataset( );
	//		for(Book book : books) {
	//			dataset.setValue(book.getId(), book.getAvailableUnits());
	//		}
	//		JFreeChart chart = ChartFactory.createPieChart(
	//				"Books in stock",   // chart title
	//				dataset,          // data
	//				true,             // include legend
	//				true,
	//				false);
	//		
	////		dataset.setValue("IPhone 5s", new Double( 20 ) );
	////		dataset.setValue("SamSung Grand", new Double( 20 ) );
	////		dataset.setValue("MotoG", new Double( 40 ) );
	////		dataset.setValue("Nokia Lumia", new Double( 10 ));
	////		JFreeChart chart = ChartFactory.createPieChart(
	////				"Mobile Sales",   // chart title
	////				dataset,          // data
	////				true,             // include legend
	////				true,
	////				false);
	//
	//		int width = 800;   /* Width of the image */
	//		int height = 480;  /* Height of the image */
	//		/* to store chart as file*/
	//		//File file = new File( "PieChart.jpeg" );
	//		//ChartUtilities.saveChartAsJPEG( file , chart , width , height );
	//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
	//		ChartUtilities.writeChartAsJPEG(stream, chart, width, height);
	//		//logger.info(new String(stream.toByteArray()));
	//		mv.addObject("chart", Base64.getEncoder().encodeToString(stream.toByteArray()));
	//		logger.info("ReportService.getInventoryReport() :: end");
	//		return mv;
	//	}

}
