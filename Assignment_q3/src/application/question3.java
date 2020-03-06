/*
CSCI 2020U Assignment 1
@author: Patrick Aguilera
@student_id: 100673285
Completed on 3/5/2020
*/

package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class question3 extends Application {
	
	/* Initialize global variables */
	final double CircleSize = 150;
	final double PointSize = 30;
	Circle circle = new Circle(CircleSize);
	Circle [] points = {new Circle(PointSize), new Circle(PointSize), new Circle(PointSize)};
	Text [] texts = {new Text(), new Text(), new Text()};
	Line [] lines = {new Line(), new Line(), new Line()};
	Line xAxis = new Line(0,circle.getCenterY(),CircleSize * 4, circle.getCenterY());
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/* create scene with objects */
			Pane pane = new Pane();
			Scene scene = new Scene(pane, CircleSize * 4, CircleSize * 4);
			drawCircle(100, pane);
			drawTriangle(pane);
			
			/* show result */
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* draw main circle */
	void drawCircle(double size, Pane pane) {
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
		circle.setCenterX(CircleSize*(2));
		circle.setCenterY(CircleSize*(2));
		for(Circle point : points) movePoint(point);
		pane.getChildren().add(circle);
	}
	
	/* draw each edge of triangle */
	void drawTriangle(Pane pane) {
		generatePoints(pane);
		for(int i = 0; i < lines.length; i++) {
			/* bind each line to point (vertice) */
			lines[i].setStroke(Color.BLACK);
			lines[i].startXProperty().bind(points[i].centerXProperty());
			lines[i].startYProperty().bind(points[i].centerYProperty());
			lines[i].endXProperty().bind(points[(i+1)%3].centerXProperty());
			lines[i].endYProperty().bind(points[(i+1)%3].centerYProperty());
			pane.getChildren().add(lines[i]);
		}
	}
	
	/* generate each movable point (vertice) of the triangle */ 
	void generatePoints(Pane pane) {
		/* initialize local variables */
		double rad = 5;
		Color color = Color.DARKRED;
		double h, k, r;
		h = pane.getWidth()/2;
		k = pane.getHeight()/2;
		r = CircleSize;
		
		for(int i = 0; i < points.length; i++) {
			/* draw each point */
			double theta = Math.random() * 2 * Math.PI;
			points[i].setRadius(rad);
			points[i].setFill(color);
			points[i].setCenterX(h + Math.cos(theta) * r);
			points[i].setCenterY(k + Math.sin(theta) * r);
			points[i].toFront();
			pane.getChildren().add(points[i]);
			
			/* display each angle */
			texts[i].setText(Double.toString(round(cosineLaw(i), 1)));
			texts[i].xProperty().bind(points[i].centerXProperty());
			texts[i].yProperty().bind(points[i].centerYProperty()); 
			pane.getChildren().add(texts[i]);
		}
		
	}
	
	/* control handler for moving each point */
	void movePoint(Circle point) {
		point.onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				double pointCond = Math.sqrt(
					Math.pow(event.getX() - circle.getCenterX(), 2) + 
					Math.pow(event.getY() - circle.getCenterY(), 2)
				);
				
				/* must have cursor almost directly on the circle in order to move */
				if((circle.getRadius() + 2 > pointCond) && (circle.getRadius() - 2 < pointCond)) {
					/* move points */
					point.setCenterX(event.getX());
					point.setCenterY(event.getY());	
					
					/* update angle texts as points move */
					for(int i = 0; i < points.length; i++) {
						texts[i].setText(Double.toString(round(cosineLaw(i), 1)));
					}
				}
			}
		});	
	}
	
	/*---------Formulas-----------*/
	
	double cosineLaw(int i) {
		double a = distBetweenPoints(
			points[i].getCenterX(),
			points[i].getCenterY(),
			points[(i+1)%3].getCenterX(),
			points[(i+1)%3].getCenterY()
		);
		double b = distBetweenPoints(
			points[i].getCenterX(),
			points[i].getCenterY(),
			points[(i+2)%3].getCenterX(),
			points[(i+2)%3].getCenterY()
		);
		double c = distBetweenPoints(
			points[(i+1)%3].getCenterX(),
			points[(i+1)%3].getCenterY(),
			points[(i+2)%3].getCenterX(),
			points[(i+2)%3].getCenterY()
		);
		return Math.acos((a*a + b*b - c*c)/(2*a*b)) * (180/Math.PI);
	}
	
	static double distBetweenPoints(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(y2-y1,2)+Math.pow(x2-x1,2));
	}
	
	static double round(double x, int position){
        double a = x;
        double temp = Math.pow(10.0, position);
        a *= temp;
        a = Math.round(a);
        return (a/(float)temp);
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
