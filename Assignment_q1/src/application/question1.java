/*
CSCI 2020U Assignment 1
@author: Patrick Aguilera
@student_id: 100673285
Completed on 3/5/2020
*/

package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class question1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Set up project */
			Pane pane = new HBox(10);
	        pane.setPadding(new Insets(5,5,5,5));
	        String [] img = {"","",""}; 
	        
	        /* display cards */
	        for(int i = 0; i < 3; i++) {
	        	img[i] = "Cards\\" + Integer.toString(getRandint(1,54)) + ".png";
	        	/* handle repetitions */
        		if(i > 0) {
        			while(img[i] == img[i-1]) {
        				img[i] = "Cards\\" + Integer.toString(getRandint(1,54)) + ".png";
        			}
        		}
        		Image card = new Image(img[i]);
        		ImageView imageView = new ImageView(card);
    	        pane.getChildren().add(imageView);
	        }
	        
	        /* show result */
	        Scene scene = new Scene(pane);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Question 1");
	        primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* formula for generating random number in range */
	static int getRandint(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
