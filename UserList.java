package gen;

import java.util.ArrayList;

public class UserList {

    static Perfil Bruno = new Perfil("Bruno", 25, "bruno@bruno", "Rua 1", 987654321, "12345678910", "Mastercard",
            "1234 4321 6789 9876", "Individual", "brunologin", "brunosenha");

    static Perfil Ed = new Perfil("Ed", 100, "cyberpatinho@github.com", "Rua dos Patos 99", 444444444, "99999999999", "Quack",
            "9999 9999 9999 9999", "Individual", "ed", "123");

    static Perfil Esdras = new Perfil("Esdras", 28, "esdras@filmes", "Rua Javinha 76", 777777777, "11111111111", "Visa",
            "2222 2222 2222 2222", "Individual", "esdras@filmes", "filmes0011");

    ArrayList<Perfil> userDB = new ArrayList<Perfil>();


}
