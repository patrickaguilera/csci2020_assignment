/*
CSCI 2020U Assignment 1
@author: Patrick Aguilera
@student_id: 100673285
Completed on 3/5/2020
*/

package application;
	
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class question4 extends Application {
	char [] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	@Override
	public void start(Stage primaryStage) {
		try {
			/* Initialize variables */
			GridPane pane = new GridPane();
			pane.setVgap(10);
			pane.setHgap(5);
			pane.setPadding(new Insets(10,10,10,10));
			
			Label label = new Label("FileName: ");
			TextField file = new TextField();
			Button submit = new Button("Submit");
			
			/* Utilize the submit button */
			submit.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent action) {
					int maxLetter = 0; /* determines size Y of the pane */
					for(int i = 0; i < alphabet.length; i++) {
						String contents;
						try {
							
							/* Read file into string contents */
							contents = new String(Files.readAllBytes(Paths.get("src/" + file.getText())));
							int count = 0;
							
							/* Count letters in string contents */
							for(char letter : (contents.toCharArray())) {
								if(Character.toLowerCase(letter) == alphabet[i]) {
									count++;
								}
							}
							
							Rectangle bar = new Rectangle(5, count); /* create bars for histogram */
							if(count > maxLetter) maxLetter = count;
							bar.setStroke(Color.BLACK);
							
							/* add bars to histogram */
							GridPane.setValignment(bar, VPos.BOTTOM);
							pane.add(bar, i, 0);
							pane.setMinHeight(maxLetter);
							pane.setMinWidth(400);
							
							/* add letter labels */
							pane.add(new Label(Character.toString(Character.toUpperCase(alphabet[i]))), i, 1);
						
						/* catch invalid file */
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			/* add form to pane */
			pane.setMinHeight(100);
			pane.add(label, 0, 2, 6, 1);
			pane.add(file, 6, 2, 16, 1);
			pane.add(submit, 22, 2, 4, 1);
			
			/* show result */
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
