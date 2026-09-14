package com.mycompany._14_26;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    private TextField numDaysOfTrip;
    private TextField airfare;
    private TextField carRentalFees;
    private TextField numMilesDriven;
    private TextField parkingFees;
    private TextField taxiCharges;
    private TextField conferenceFees;
    private TextField lodgingFeesPerNight;
    
    private double mealExpense = 37.00;
    private double parkingFeeExpense = 10.00;
    private double taxiChargesExpense = 20.00;
    private double logingChargesExpense = 95.00;
    private double drivingExpense = 0.27;
    
    private Label buisnessTripExpenses;
    private Label totalAllowableExpenses;
    private Label excessExpenses;
    private Label amountSaved;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(12);
        Insets insets = new Insets(15);
        grid.setPadding(insets);
        
        grid.add(new Label("Number of days on the trip:"), 0, 0);
        grid.add(numDaysOfTrip, 1, 0);
        grid.add(new Label("Airfair price:"), 0, 1);
        grid.add(airfare, 1, 1);
        grid.add(new Label("Car rental fees:"), 0, 2);
        grid.add(carRentalFees, 1, 2);
        grid.add(new Label("Was a private vehicule used:"), 0, 3);
        grid.add(numDaysOfTrip, 1, 3);//
        grid.add(new Label("Miles driven with private vehicule:"), 0, 4);
        grid.add(numMilesDriven, 1, 4);
        grid.add(new Label("Parking fees:"), 0, 5);
        grid.add(parkingFees, 1, 5);
        grid.add(new Label("Taxi charges:"), 0, 6);
        grid.add(taxiCharges, 1, 6);
        grid.add(new Label("Conference registration fees:"), 0, 7);
        grid.add(conferenceFees, 1, 7);
        grid.add(new Label("Lodging charges per night"), 0, 8);
        grid.add(lodgingFeesPerNight, 1, 8);
        
        Button calculateExpenses = new Button("Calculate Expenses");
        calculateExpenses.setOnAction(e -> calculateExpenses());
        
        Label resultTitle = new Label("Results:");
      
        Label title = new Label("Buisness Expense Calculations");
        
        VBox resultBox = new VBox(
                5,
                resultTitle,
                buisnessTripExpenses,
                totalAllowableExpenses,
                excessExpenses,
                amountSaved
        );
        
        VBox root = new VBox(
            15,
            title,
            grid,
            calculateExpenses);
        
        var scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.show();
    }
    
    private void calculateExpenses() {
        //what the buisness person spends
        int days = Integer.parseInt(numDaysOfTrip.getText());
        if (numDaysOfTrip.getText().isEmpty()){
            days = 0;
        }
        double airfareDouble = Double.parseDouble(airfare.getText());
        if (airfare.getText().isEmpty()){
            airfareDouble = 0;
        }
        double carRental = Double.parseDouble(carRentalFees.getText());
        if (carRentalFees.getText().isEmpty()){
            carRental = 0;
        }
        double milesDriven = Double.parseDouble(numMilesDriven.getText());
        if (numMilesDriven.getText().isEmpty()){
            milesDriven = 0;
        }
        double parkingPrice = Double.parseDouble(parkingFees.getText());
        if (parkingFees.getText().isEmpty()){
            parkingPrice = 0;
        }
        double taxiprice = Double.parseDouble(taxiCharges.getText());
        if (taxiCharges.getText().isEmpty()){
            taxiprice = 0;
        }
        double lodgingPricePerNight = Double.parseDouble(lodgingFeesPerNight.getText());
        if (lodgingFeesPerNight.getText().isEmpty()){
            lodgingPricePerNight = 0;
        }
        
        double lodging = lodgingPricePerNight * days;
        
        double totalExpenses = airfareDouble + carRental + parkingPrice
                + taxiprice + lodging;
        
        //calculating total allowable expenses
        double allowableExpenses = (days * (mealExpense + logingChargesExpense +
                parkingFeeExpense + taxiChargesExpense)) + (milesDriven * drivingExpense);
        
        //calculating the excess that must be paid by the buisness person
        double excessPayment = 0;
        double amountSavedFromCompensation = 0;
        
        if (totalExpenses > allowableExpenses){
            excessPayment = totalExpenses - allowableExpenses;
        } else {
            amountSavedFromCompensation = allowableExpenses - totalExpenses;
        }
        
        //display
        buisnessTripExpenses.setText(String.format("Total expenses incured: %f",totalExpenses));
        totalAllowableExpenses.setText(String.format("Total allowable expenses: %f", allowableExpenses));
        excessExpenses.setText(String.format("Excess payment due: %f", excessPayment));
        amountSaved.setText(String.format("Amount saved: %f", amountSavedFromCompensation));
    }

    public static void main(String[] args) {
        launch();
    }

}