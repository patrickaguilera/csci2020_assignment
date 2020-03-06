/*
CSCI 2020U Assignment 1
@author: Patrick Aguilera
@student_id: 100673285
Completed on 3/5/2020
*/

package application;
	
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class question2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Initialize variables */
			GridPane pane = new GridPane();
			TextField investmentAmount = new TextField();
			TextField years = new TextField();
			TextField annualRate = new TextField();
			Text futureValue = new Text();
			Node [] fields = {investmentAmount, years, annualRate, futureValue};
			String [] labels = {"Investment Amount", "Years", "Annual Interest Rate", "Future Value"};
			
			/* set labels */
			for(int i = 0; i < labels.length; i++) {
				Label label = new Label();
				label.setText(labels[i]);
				pane.add(label, 0, i);
				pane.add(fields[i], 1, i);
			}
			
			/* organize Nodes */
			double padding = 10;
			pane.setPadding(new Insets(padding,padding,padding,padding));
			pane.setHgap(padding);
			pane.setVgap(padding);
			
			/* control button function */
			Button button = new Button();
			button.setText("Calculate");
			button.setOnAction(e->
				futureValue.setText(Double.toString(
					calculateInvestmentValue(
						Double.parseDouble(investmentAmount.getText()),
						Double.parseDouble(annualRate.getText()), 
						Integer.parseInt(years.getText())
					)
				))
			);
			pane.add(button, 1, 5);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setHalignment(HPos.RIGHT);
			
			/* display result */
	        Scene scene = new Scene(pane);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Calculate Future Interest Value");
	        primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* formula for calculating investment amount */
	private double calculateInvestmentValue(double investmentAmount, double monthlyRate, int years) {
		monthlyRate /= 100;
		double futureValue = investmentAmount * Math.pow(1 + monthlyRate/12, years*12);
		return futureValue;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
