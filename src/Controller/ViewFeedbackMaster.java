package Controller;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.MasterDetailPane;

import Database.Connection.DBViewFeeback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ViewFeedbackMaster {
	
	//Classes
	private DBViewFeeback viewDB = new DBViewFeeback();
	
	//Lists
	private ObservableList<Label> labelList = FXCollections.observableArrayList();
	private List<Integer> q_ans = new ArrayList<>(); 
	
	//Bar Chart
	private GridPane grid = new GridPane();
	private CategoryAxis x = new CategoryAxis();
	private NumberAxis y = new NumberAxis();
	private BarChart<String, Number> feedback = new BarChart<String, Number>(x,y);
	
	//MasterDetailPane
	private MasterDetailPane centerQuestion_Chart;
	
	public MasterDetailPane createMasterGrid(String eventName){
		centerQuestion_Chart = new MasterDetailPane();
		List<String> questionStatement = viewDB.retrieveQuestions(eventName);
		
		//get Question from database
		grid = new GridPane();
		// Display Questions 
		
		grid.setPrefSize(600, 100);
		grid.setMaxSize(600, 100);
		//grid.setStyle("-fx-background-color: slateblue;");
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		
		for(int i = 0; i < questionStatement.size(); i++)
		{
			Label q1 = new Label(questionStatement.get(i));
			q1.setMinSize(600, 67);
			q1.setMaxSize(600, 67);
			GridPane.setConstraints(q1, 0, i);
			q1.setAccessibleText(String.valueOf(i+1));
			labelList.add(q1);		
			grid.getChildren().add(q1);
		}
		
		centerQuestion_Chart.setMasterNode(grid);
		centerQuestion_Chart.setDividerPosition(0.30);
		centerQuestion_Chart.setShowDetailNode(false);
		//int index = 0;
		for(int i = 0; i < labelList.size(); i++){
			int index = i;
			labelList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent arg0) {
					viewDB.setQuesNo((String.valueOf(labelList.get(index).getAccessibleText())));
					centerQuestion_Chart.setShowDetailNode(true);
					//q_ans = viewDB.retrieveAnswer();
					barChartDisplay(eventName);
				}	
			});
			}
		
		return centerQuestion_Chart;
	}
	
	public BarChart<String, Number> barChartDisplay(String eventName)
	{
		feedback = new BarChart<String, Number>(x,y);
		y.setLabel("No. of Volunteers");
		
		centerQuestion_Chart.setDetailNode(feedback);
		centerQuestion_Chart.setDetailSide(Side.RIGHT);
		centerQuestion_Chart.setShowDetailNode(true);
		centerQuestion_Chart.setMaxSize(centerQuestion_Chart.getMaxWidth(), centerQuestion_Chart.getMaxHeight());
		// Get Data from database 

		q_ans = viewDB.retrieveAnswer(eventName);
		int ans1 = q_ans.get(0);
		int ans2 = q_ans.get(1);
		int ans3 = q_ans.get(2);
		int ans4 = q_ans.get(3);
		int ans5 = q_ans.get(4);
		// Get Answer from entity
		//Feedback answerEntity = new Feedback();
		
//		XYChart.Series set1 = new XYChart.Series();
        
        XYChart.Series set2 = new XYChart.Series();
        set2.getData().add(new XYChart.Data("Strongly Disagree",ans1));
		set2.getData().add(new XYChart.Data("Disagree",ans2));
		set2.getData().add(new XYChart.Data("Neutral",ans3));
		set2.getData().add(new XYChart.Data("Agree",ans4));
		set2.getData().add(new XYChart.Data("Strongly Agree",ans5));

		feedback.getData().addAll(set2);
		return feedback;
	}
}
