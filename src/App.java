import com.view.AuthView;
import com.view.PrintDelay;

public class App {
    public static void main(String[] args){
        
        
        AuthView initialDisplay = new AuthView();
        initialDisplay.displayMenu(); 
        PrintDelay.print("\n\n===== code by: Kelompok 1 TID 23 =====\n");
        PrintDelay.print(
            """

            Anggota :\n
            | Nama                     | NIM         | 
            |--------------------------|-------------| 
            | Galang Krisna Pramudya   | 23051204134 | 
            | Alif Rasyid Febriansyah  | 23051204131 | 
            | Mirza Faris Al Arifin    | 23051204142 | 
            | Alvin Aulia Rahman Sh    | 23051204117 | 
            | Mohammad Eksa Hardian    | 23051204115 | 
            | Neng Jihan               | 23051204114 | 
            """);
    }
}
