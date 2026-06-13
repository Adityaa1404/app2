public class Stack {
    Data top, bottom;
    int jumlah;
    Stack(){
        top=bottom=null;
        jumlah=0;
    }
    void push(Data baru){
        if(top==null){
            top=bottom=baru;
        }else {
            baru.next=top;
            top=baru;
        }
        jumlah++;
    }
    Data pop(){
        Data temp=null;
        if(top==null){
            System.out.println("Stack kosong");
        }else if (jumlah==1) {
            temp=top;
            top=bottom=null;
        } else{
            temp=top;
            top=top.next;
        }
        return temp;
    }
    void show(){
        System.out.println("isi stack:");
        if (top==null) {
            System.out.println("Stack kosong");
            
        } else {
            for (Data t=top; t!=null; t=t.next) {
                System.out.println(t.getJudul());
            }
            System.out.println("");
        }
    }

}
