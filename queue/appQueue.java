import java.util.Scanner;
public class appQueue{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue antrian = new Queue();
        int pilih1 = 0, pilih2 = 0;

        do {
            System.out.println("\nAplikasi Antrian Order");
            System.out.println("1. Order\n2. Proses\n3. Lihat");
            System.out.println("4.Selesai");
            System.out.print("Pilih: ");
            pilih1=sc.nextInt();
            switch (pilih1) {
                case 1:
                    String brg=null;
                    String nama=null;
                    double harga=1, jumlah=0;
                    int pilih3;
                    System.out.print("Nama Pembeli: ");
                    nama=sc.next();
                    do {
                    System.out.println("Daftar Barang");
                    System.out.println("1. Beras\n2. Telur\n3. Minyak");
                    System.out.print("Pilih: ");
                    pilih2=sc.nextInt();
                    switch (pilih2) {
                        case 1:
                            brg="Beras"; harga=18000; jumlah=0;
                            

                            break;
                        case 2:
                            brg="Telur"; harga=25000; jumlah=0;
                            
                            break;
                        case 3:
                            brg="Minyak"; harga=20000; jumlah=0;
                            
                            break;
                    
                        default:
                            break;
                    }
                    System.out.println("Jumlah ");
                    jumlah=sc.nextInt();
                    Order order = new Order(nama, brg, harga, jumlah);
                    antrian.enqueue(order);
                    System.out.print("Apakah Anda ingin menambahkan pesanan lain? (1. Ya, 0. Tidak): ");
                    pilih3 = sc.nextInt();
                    } while (pilih3 == 1);
                    break;  

                case 2:
                    antrian.dequeue();
                    break;  
                case 3:
                    antrian.view();
                    break;  
                case 4:
                    System.out.println("Terimakasih");
                    break;  
            
                default:
                    break;
            }
        } while (pilih1!=4);
        sc.close();
    }
}

class Order {
    String nama; //pembeli
    String barang;
    double harga;
    double jumlah;
    Order next;
    Order(){}
    Order(String nama, String barang, double harga, double jumlah) {
        this.nama = nama;
        this.barang = barang;
        this.harga = harga;
        this.jumlah = jumlah;
    }
    String getNama(){
        return nama;
    }
    String getBarang() {
        return barang;
    }
    double getHarga() {
        return harga;
    }
    double getJumlah() {
        return jumlah;
    }
}

class Queue {
    Order front, rear;
    double totalHarga;
    Queue() {}
    void enqueue(Order newOrder) {
        if (rear == null) {
            front = rear = newOrder;
        } else {
            rear.next = newOrder;
            rear = newOrder;
        }
        totalHarga += newOrder.getHarga() * newOrder.getJumlah();
    }
    void dequeue() {
        Order temp = front;
        if (front == null) {
            System.out.println("Antrian kosong");
            return;
        }else if(front == rear) {
            System.out.println("Pesanan " + temp.getBarang() + " dengan jumlah " + temp.getJumlah() + " telah diproses. Total harga: " + (temp.getHarga() * temp.getJumlah()));
            front = rear = null;
        } else {
            front = front.next;
            temp.next = null;
        }
    }
    void view(){
        System.out.println("Daftar Pesanan:");
        if (front==null) {
            System.out.println("Antrian kosong");
        } else {
            for(Order temp = front; temp != null; temp = temp.next) {
                System.out.println(temp.getNama());
                System.out.println("Barang: " + temp.getBarang() + ", Jumlah: " + temp.getJumlah()+", Harga Satuan: " + temp.getHarga());
                System.out.println("Total Harga: " + (temp.getHarga() * temp.getJumlah()));
            }
            System.out.println(" ");
        }
    }
}