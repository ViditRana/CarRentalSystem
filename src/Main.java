import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Car {
    private String carId;
    private String brand ;          // these are  the some featurs of car class
    private String model;
    private double pricePerDay;
    private boolean isAvailable ;
    public Car(String carId , String brand ,String model ,double pricePerDay)       // constructor for car class it will st the vlaues of the car whlie adding the new car
    {
        this.carId= carId;
        this.brand= brand;          // this key word is used to eliminate the class atributes and the parameter with the same name
        this.model= model;
        this.pricePerDay= pricePerDay;
        this.isAvailable=true;               // by default the value of isAvailable will true
    }
    public String getCarId(){
        return carId;
    }
    public String getBrand(){       // these are getter methodes which are used to get the data from privete data member of the class
        return brand;
    }
    public String getModel(){
        return model ;
    }
    public double calculatePrice( int rentDays){
        return pricePerDay*rentDays;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void rent (){
        isAvailable =false;
    }
    public void returnCar(){
        isAvailable= true;
    }
}
class Customer {
    private String  customerId;
    private String name ;

    public Customer (String customerId,String name ){
        this.customerId= customerId;
        this .name = name ;
    }
    public String getCustomerId(){
        return customerId;

    }
    public String getName(){
        return name;
    }




}
class Rental{                       // this clas will contain the combination of car and customer class
    private Car car;
    private Customer customer;
    private int days;
    public Rental(Car car,Customer customer,int days){
        this .car= car;
        this.customer= customer;
        this. days= days;
    }
    public Car getCar(){
        return car;

    }
    public Customer getCustomer(){
        return customer;
    }
    public int getDays(){
        return days;
    }


}
class CarRentalSystem{                                              // hare we will store our data in the array lists at the  of database
    private List<Car> cars;
    private List<Customer> customers;
    private List< Rental> rentals ;

    // cunstructor for class carRentalSyatem
    public CarRentalSystem(){
        cars=new ArrayList<>();
        customers= new ArrayList<>();                               // this will create the arryList in the memory for store the data of cars and custemers and rentals
        rentals= new ArrayList<>();
    }
    public void addCar( Car car){                                   // whenever a new car comes we can add it with this method in the array list
        cars.add(car);
    }
    public void addCustomer( Customer customer){                    // to add any new customer in the array list
        customers.add(customer);
    }
                // method for rent the car
    public void rentCar( Car car, Customer customer, int days){
        if ( car.isAvailable()){                                     // hare isAvailable method will return true or false according to situtaion
            car.rent();                                              // this method will turn the true into the false if car rented

            rentals.add( new Rental( car ,customer, days));          // this will add all the details in the rental ArrayList
             }
        else {
            System.out.println( " Car is not available for the rent ");
        }
    }
    public void returnCar( Car car){
        car.returnCar();
        Rental rentalToRemove= null;
        for(Rental rental: rentals){
            if ( rental.getCar()==car){
                rentalToRemove =rental;
                break;
            }
        }
        if (rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println (" Car returned successfully");
        }
        else{
            System.out.println( " car was  not rented");
        }
    }
    public void menu(){
        Scanner scanner = new Scanner ( System.in );
        while( true ){
            System.out.println( "CAR RENTAL SYSTEM");
            System.out.println( "1. Rent the Car");
            System.out.println( "2. Return the Car");
            System.out.println( "3. Exit the menu ");
            System.out.println (" enter youe choice ");
            int choice = scanner.nextInt ();
            scanner. nextLine();        // consume new line

            if( choice == 1){
                System.out.println("\n== Rent a car== \n");
                System.out.println (" Enter you name ");
                String customerName= scanner.nextLine();

                System.out.println( " \n Available Cars");
                for ( Car car: cars){
                    if( car.isAvailable()){
                        System.out.println( car.getCarId()+" "+ car.getBrand() +" "+ car.getModel());
                    }
                }
                System.out.println( "\n enter the car id you want to rent");
                String carId= scanner.nextLine();

                System.out.println ("Eneter the no.of days for rent the Car");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();         // to consume the new line
                Customer newCustomer = new Customer( "c"+(customers.size()+1), customerName);

                addCustomer( newCustomer);

                Car selectedCar = null;
                for( Car car : cars){
                    if ( car.getCarId().equals(carId)&& car.isAvailable()){
                        selectedCar= car;

                        break;
                    }
                }
                if ( selectedCar != null){
                    double totalPrice = selectedCar.calculatePrice( rentalDays);
                    System.out.println( "\n == Rental information==\n");
                    System.out.println(" Customer Id :"+" " +newCustomer.getCustomerId());
                    System.out.println("Customer name:"+" " +newCustomer.getName());
                     System.out.println( "car"+selectedCar.getBrand()+" "+selectedCar.getModel());
                     System.out.println( " RentalDays"+ "" +rentalDays );
                     System.out.println ( " The total price"+ " " +totalPrice);

                     System.out.println ("\n== Confirm rental(Y/N): ");
                     String confirm = scanner.nextLine();
                     if  (confirm.equalsIgnoreCase("Y")){
                         rentCar( selectedCar, newCustomer,rentalDays);
                         System.out.println( " Car rented successfully ");
                     }
                     else{
                         System.out.println( "Car rental canceled");
                     }
                }
                else{
                    System.out.println (" Invalid car section or car not rented");
                }


            }
            else if (choice ==2)
            {
                System.out.println ("\n == Return a car==\n");
                System.out.println( " enter the car Id which you want to return");
                String carId = scanner.nextLine();

                Car ToReturn =null;
                for( Car car : cars){
                    if ( car.getCarId().equals(carId) && !car.isAvailable()){
                        ToReturn=car;
                        break;
                    }
                }
                if ( ToReturn!= null){
                    Customer customer= null;
                    for( Rental rental: rentals){
                        if( rental.getCar()== ToReturn){
                            customer= rental.getCustomer();
                            break;
                        }
                    }
                    if ( customer != null){
                        returnCar(ToReturn);
                        System.out.println ( "car return successfully by"+ customer.getName());
                    }
                    else{
                        System.out.println( " car was not rented or information missing");
                    }

                }
                else{
                    System.out.println (" invalid car is or car was no rented");
                }

            }
            else if( choice ==3){
                break;
            }
            else{
                System.out.println (" Invalid choice please enter valid option");
            }
        }
        System.out.println( " Thankyou for using CAR RENTAL SYSTEM");
    }
}


public class Main{
    public static void main( String[] args){
        CarRentalSystem rentalSystem= new CarRentalSystem();
        Car car1= new  Car( "c001","Toyota","camry",600);
        Car car2= new  Car( "c002","Honda","Accord",500);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.menu();
    }

}

