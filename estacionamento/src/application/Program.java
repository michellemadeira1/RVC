package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
		
		System.out.print("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy  hh:mm: )");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy  hh:mm: )");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
        
        
        CarRental cr = new CarRental(start,finish, new Vehicle (carModel));
        
        
        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();
        
        
        RentalServices rentalServices = new RentalServices (pricePerHour,pricePerDay,new BrazilTaxService());
   
        rentalServices.processInvoice(cr);
        
        System.out.println("Fatura: ");
        System.out.println("Pagamento basico: " + String.format("%2.f", cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%2.f",cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%2.f",cr.getInvoice().getTotalPayment()));
        
        sc.close();
        
	}

}
