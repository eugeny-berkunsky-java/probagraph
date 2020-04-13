package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.model.Model;
import main.model.Point;
import main.model.Window;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML private TextField x1Field;
    @FXML private TextField x2Field;
    @FXML private TextField y1Field;
    @FXML private TextField y2Field;
    @FXML private Pane centerPane;
    @FXML private Canvas canvas;


    Window.Builder builder;
    Model model;

    public void initialize() {
        canvas.widthProperty().bind(centerPane.widthProperty());
        canvas.heightProperty().bind(centerPane.heightProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
        builder = new Window.Builder().x1(-50).x2(50).y1(-50).y2(50);
        model = new Model();
    }

    void draw() {
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        g2.setFill(Color.BEIGE);
        g2.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        Window window = builder.xs1(0).xs2(centerPane.getWidth()).ys1(0).ys2(centerPane.getHeight()).build();
        drawGraphic(window);
    }

    private void drawGraphic(Window window) {
        List<Point> points = new ArrayList<>();
        for (int i = (int)window.getXs1(); i < window.getXs2(); i++) {
            double x = window.toWorldX(i);
            double y = model.f(x);
            points.add(new Point(x,y));
        }
        drawGraphic(window, points);
    }

    private void drawGraphic(Window window, List<Point> points) {
        GraphicsContext g2 = canvas.getGraphicsContext2D();
        g2.setStroke(Color.BLACK);
        g2.strokeLine(window.toScreenX(0), window.toScreenY(window.getY1()), window.toScreenX(0), window.toScreenY(window.getY2()));
        g2.strokeLine(window.toScreenX(window.getX1()), window.toScreenY(0), window.toScreenX(window.getX2()), window.toScreenY(0));
        g2.setStroke(Color.BLUE);
        for (int i = 0; i < points.size() - 1; i++) {
            g2.strokeLine(
                    window.toScreenX(points.get(i).getX()), window.toScreenY(points.get(i).getY()),
                    window.toScreenX(points.get(i+1).getX()), window.toScreenY(points.get(i+1).getY())
                    );
        }
    }


    public void update() {
        double v = Double.parseDouble(x1Field.getText());
        builder.x1(v);
        v = Double.parseDouble(x2Field.getText());
        builder.x2(v);
        v = Double.parseDouble(y1Field.getText());
        builder.y1(v);
        v = Double.parseDouble(y2Field.getText());
        builder.y2(v);
        draw();
    }
}
