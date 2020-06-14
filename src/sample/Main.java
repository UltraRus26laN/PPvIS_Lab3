package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import view.*;

public class Main extends Application
{
    private Scene scene;
    private ChartGroup graphicGroup;

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = getWindow();
        primaryStage.setTitle("Plotter");
        scene = new Scene(root, 1250, 620);
        setSceneKeyPressEvents();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane getWindow()
    {
        BorderPane window = new BorderPane();
        Table table = new Table();
        Group tableGroup = table.getGroup();
        HBox tableBox = new HBox(new Label("        "), tableGroup);
        graphicGroup = new ChartGroup();
        Group chartGroup = graphicGroup.getChartGroup();
        Plotter parametersForm = new Plotter(graphicGroup, table);
        Group parametersFormGroup = parametersForm.getGroup();
        VBox generalGroup = new VBox();
        generalGroup.setSpacing(20);
        generalGroup.getChildren().addAll(tableBox, parametersFormGroup);
        window.setLeft(generalGroup);
        window.setRight(chartGroup);
        return window;
    }

    public void setSceneKeyPressEvents()
    {
        scene.setOnKeyPressed(ke ->
        {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.CONTROL))
            {
                graphicGroup.setChartScroll();
            }
        });
        scene.setOnKeyReleased(ke ->
        {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.CONTROL))
            {
                graphicGroup.clearChartScroll();
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}