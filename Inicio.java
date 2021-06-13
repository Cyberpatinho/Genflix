package gen;

import java.util.ArrayList;
import java.util.Scanner;

public class Inicio {

	Scanner read = new Scanner(System.in);

	ArrayList<Perfil> userDB;

	Perfil user = null;

	public Inicio (ArrayList<Perfil> userDB) {
		setUserDB(userDB);
	}

	
	public Perfil IniciarSessao()
	{

		System.out.println("\n\n-----INICIAR SESSÃO-----");
        System.out.print("\nLogin ou Email: ");
        String x = read.next();
        
        System.out.print("Senha: ");
        String y = read.next();

        boolean done = false;

		for(int i = 0; i<userDB.size(); i++){
			if(userDB.get(i).getSenha().equals(y) && (userDB.get(i).getLogin().equals(x) || userDB.get(i).getEmail().equals(x))){
				done = true;
				user = userDB.get(i);
			}
		}


        while(!done) {
            System.out.println("Combinção inválida! ");
            System.out.print("\nLogin ou Email: ");
            x = read.next();
            System.out.print("Senha: ");
            y = read.next();
			for(int i = 0; i<userDB.size(); i++){
				if(userDB.get(i).getSenha().equals(y) && (userDB.get(i).getLogin().equals(x) || userDB.get(i).getEmail().equals(x))){
					done = true;
					user = userDB.get(i);
				}
			}
         }
        
        
        System.out.println(""
        		+ ".______     ______         ___           _______.    ____    ____   __  .__   __.  _______        ___            _______.\r\n"
        		+ "|   _  \\  /  __  \\      /   \\        /       |    \\   \\  /  / |  | |  \\ |  | |       \\    /   \\         /       |\r\n"
        		+ "|  |_)  | |  |  |  |     /  ^  \\       |   (----`    \\   \\/  /  |  | |   \\|  | |  .--.  |   /  ^  \\       |   (----`\r\n"
        		+ "|   _  <  |  |  |  |    /  /_\\ \\      \\   \\        \\      /   |  | |  . `   | |  |  |  |  /  /_\\  \\     \\   \\    \r\n"
        		+ "|  |_)  | |  `--'  |   /  _____  \\  .----)   |         \\    /    |  | |  |\\   | |  '--'  | /  _____  \\  .----)   |   \r\n"
        		+ "|______/  \\______/   /__/     \\_\\ |_______/           \\__/     |__| |__| \\__| |_______/ /__/     \\__\\ |_______/    \r\n"
        		+ "                                                                                                                    ");

        return user;
	}


	public void setUserDB(ArrayList<Perfil> userDB){ this.userDB = userDB; }
	
}
