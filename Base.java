package gen;


import java.util.*;

public class Base extends UserList{

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        //Inicialização do database de filmes
        ArrayList< ArrayList<Filme> > movieDB = new ArrayList< ArrayList<Filme> >(10);
        for(int i = 0; i < 10; i++){
            movieDB.add(new ArrayList<Filme>());
            for(int j = 0; j < 37; j++){
                //Consertar isso com aqui com todos os tipos de filmes
                movieDB.get(i).add(new Animacao("Filme " + j, "Animação", 120, "Livre", 0, "Descrição Teste"));
            }
        }

        //Inicialização do database de usuários
        ArrayList<Perfil> userDB = new ArrayList<Perfil>();
        userDB.add(Bruno);
        userDB.add(Ed);
        userDB.add(Esdras);

        Animacao filmeInit = new Animacao("TESTE", "TC", 233, "LIVRE", 0, "DESCRIÇÃO TESTE");

        // - Login
        Inicio init = new Inicio(userDB);
        Perfil profile = init.IniciarSessao();
        UserInterface user = new UserInterface(profile.getNome(), profile.getEmail(), profile.getPlano(), profile.getLogin(), profile.getSenha());

        // - Loop principal do menu de usuário
        while (true) {

            System.out.println("-----MENU DE USUÁRIO-----");
            System.out.println("Digite o que gostaria de fazer: ");
            System.out.println("1 - Iniciar filme atual");
            System.out.println("2 - Buscar Filmes");
            System.out.println("3 - Nossa indicação para você");
            System.out.println("4 - Pontos GenFlix");
            System.out.println("5 - Acessar configurações de usuário");
            System.out.println("6 - Sair");

            int x = user.checker(1, 6, "");

            // - Iniciar o filme atual
            if (x == 1) {

                user.start(user.getSelected());
                user.getSelected().mensagemInicio();
                user.getSelected().efeitoVisual();

                boolean playing = false;

                while(true) {

                    System.out.println("-----MENU DE FILME-----");
                    System.out.println("Digite o que gostaria de fazer: ");
                    System.out.println("1 - Continuar / Pausar");
                    System.out.println("2 - Parar");
                    System.out.println("3 - Favoritar");
                    System.out.println("4 - Deixar avaliação");
                    System.out.println("5 - Deixar comentário");
                    System.out.println("6 - Ver avaliações");

                    int y = user.checker(1, 6 , "");

                    if (y == 1) {
                        if(!playing) user.pause();
                        else user.play(user.getSelected());
                        playing =!playing;

                    }
                    if (y == 2) {
                        user.setTotalWatched(user.getTotalWatched()+1);
                        break;
                    }

                    if (y == 3) user.addFavs(user.getSelected());

                    if (y == 4) {
                        user.getSelected().setNota(user.getSelected().getNota() + user.rate(user.getSelected()));
                        user.getSelected().setContaNota(user.getSelected().getContaNota()+1);
                    }
                    if (y == 5) {
                        String s = user.comment();
                        user.getSelected().getComentario().add("\""+s+"\" —— "+ user.getNome());
                        System.out.println("Muito Obrigado! Sua opinião é muito importante para nós!");

                    }
                    if (y == 6) {
                        user.showRating(user.getSelected());
                        for(int j = 0;j < user.getSelected().getComentario().size();j++) {
                            System.out.println(user.getSelected().getComentario().get(j));

                        }

                    }

                }

            }

            // - Procurar por um filme
            else if (x == 2) {
                System.out.println("-----ESCOLHA UMA CATEGORIA-----");
                System.out.println("0 - Animação");
                System.out.println("1 - Ficção Científica");
                System.out.println("2 - Suspense");
                System.out.println("3 - Terror");
                System.out.println("4 - Aventura");
                System.out.println("5 - Comédia");
                System.out.println("6 - Romance");
                System.out.println("7 - Ação");
                System.out.println("8 - Drama");
                System.out.println("9 - Documentários");

                int y = user.checker(0, 9, "");

                int p = 0;
                // - Loop para iterar pelos filmes da categoria
                while (p < movieDB.get(y).size()) {
                    int cnt = 0;
                    // - Loop para printar os filmes da categoria
                    while (cnt++ < 6 && p < movieDB.get(y).size()) {
                        System.out.println(cnt + " - " + movieDB.get(y).get(p++).getNome());
                    }
                    System.out.println(cnt++ + " - Voltar");
                    System.out.println(cnt++ + " - Avançar");
                    System.out.println(cnt + " - Sair");
                    int z = user.checker(1, cnt, "");
                    if(z == cnt) break;
                    else if(z == cnt-1) p = Math.min(p, movieDB.get(y).size()-6);
                    else if(z == cnt-2) p = Math.max(0, p-12);
                    else{
                        user.setSelected(movieDB.get(y).get(p-cnt+1));
                        user.getSelected().apresentarInfo();
                        break;
                    }
                }

            }

            // - Assistir à recomendação
            else if (x == 3) {
                int cat = (int) (Math.random()*10);
                int mov = (int) (Math.random()*movieDB.get(cat).size());
                Filme rec = movieDB.get(cat).get(mov);
                rec.apresentarInfo();
                System.out.println("Gostaria de assistir? (1 - Sim | 2 - Não)");

                System.out.println(cat + " " + mov);
                int y = user.checker(1, 2, "(1 - Sim | 2 - Não)");

                user.setSelected(rec);

            }

            // - Sistema de Pontos
            else if (x == 4) {
                System.out.println("A GenFlix é uma empresa socialmente consciente e para cada filme assistido, ela te presenteia "
                        + "\ncom pontos que podem ser convertidos em uma contribuição monetária para ONGs da sua escolha, com projetos como:");
                System.out.println("- Proteção aos Jovens");
                System.out.println("- Assistência a Mulheres");
                System.out.println("- Reflorestamento");
                System.out.println("- Apoio hospitalar");
                System.out.println("- Auxílio a Idosos");
                System.out.println("- Resgate de animais abandonados");
                System.out.println("- Assessoria a defencientes");
                System.out.println("Número de moedas GenFlix: "+user.getTotalWatched());
            }

            // - Loop secundário das configurações de usuário
            else if (x == 5) {
                while (true) {
                    System.out.println("-----MENU DE CONFIGURAÇÕES-----");
                    System.out.println("1 - Dados do Usuário");
                    System.out.println("2 - Alterar Email");
                    System.out.println("3 - Alterar Senha");
                    System.out.println("4 - Alterar Plano");
                    System.out.println("5 - Verificar Dados");
                    System.out.println("6 - Voltar");

                    int y = user.checker(1, 6, "");

                    if (y == 1) profile.apresentarDados();
                    else if (y == 2) profile.alterarEmail();
                    else if (y == 3) profile.alterarSenha();
                    else if (y == 4) profile.alterarPlano();
                    else if (y == 5) profile.validarDados();
                    else break;
                }

            } else break;

        }

    }

}

