import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        Data data;
        int pilih = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Show");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            switch (pilih) {
                case 1:
                    // Implementasi push
                    System.out.println("Masukkan judul: ");
                    sc.nextLine(); // Clear the buffer
                    String judul = sc.nextLine();
                    data = new Data();
                    data.setJudul(judul);
                    stack.push(data);
                    break;
                case 2:
                    // Implementasi pop
                    data = stack.pop();
                    if (data!=null) {
                        System.out.println(data.getJudul()+" telah diambil dari stack.");
                    } else {
                        System.out.println("Stack kosong.");
                    }
                    break;
                case 3:
                    // Implementasi show
                    stack.show();
                    break;
                case 4:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih!=4);
    }
}
