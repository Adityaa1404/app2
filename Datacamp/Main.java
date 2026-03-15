package Datacamp;

import java.util.Scanner;

public class Main {

  public static boolean loop = false;
  public static int pilih;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Toyota toyota = new Toyota();
    Honda honda = new Honda();

    do {
      System.out.println("Data Mobil:");
      System.out.printf("Color: %s, Model: %s, Year: %d\n",
          toyota.getColor(), toyota.getModel(), toyota.getYear());
      System.out.printf("Color: %s, Model: %s, Year: %d\n",
          honda.getColor(), honda.getModel(), honda.getYear());
      System.out.println("..........................");
      System.out.println("Menu:");
      System.out.println("1. Create");
      System.out.println("2. Update");
      System.out.println("3. Delete");
      System.out.println("4. Exit");
      System.out.print("Pilih menu: ");
      pilih = scanner.nextInt();
      switch (pilih) {

        case 1:
          System.out.println("a");
          break;
        case 2:
          System.out.print("Masukkan tahun Toyota: ");
          int t = scanner.nextInt();
          toyota.setYear(t);

          break;
        case 3:
          System.out.println("c");
          break;
        case 4:
          loop = false;
          break;

        default:
          System.out.println("Pilihan tidak valid");
      }

    } while (loop == false);

    scanner.close();
  }
}