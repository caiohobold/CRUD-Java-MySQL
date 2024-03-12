import java.util.Scanner;

import DAO.UsuarioDAO;
import entity.Usuario;

public class App {

    public static void main(String[] args) throws Exception {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario u = new Usuario();
    
        System.out.println("Bem-vindo ao sistema!\n\nEscolha entre as opções:");
        System.out.println("\n1 - Cadastrar usuário.\n2 - Deletar usuário.\n3 - Procurar usuário por ID");
        System.out.println("Escolha: ");
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        
        if (escolha == 1){
            System.out.println("Digite seu nome: ");
            String nome = scanner.next();  
            System.out.println("Digite o nome de usuário: ");
            String login = scanner.next(); 
            System.out.println("Digite sua senha: ");
            String senha = scanner.next();
            System.out.println("Digite seu e-mail: ");
            String email = scanner.next(); 
            u.setNome(nome);
            u.setLogin(login);
            u.setSenha(senha);
            u.setEmail(email);
            new UsuarioDAO().cadastrarUsuario(u);
        }
        else if (escolha == 2){
            System.out.println("Informe o ID do usuário a ser excluído: ");
            int id = scanner.nextInt();
            usuarioDAO.deletarUsuario(id);
        }
        else if (escolha == 3){
            System.out.println("Informe o ID do usuário a ser procurado: ");
            int id = scanner.nextInt();
            Usuario usuarioEncontrado = usuarioDAO.buscaUsuario(id);

            if(usuarioEncontrado != null){
                System.out.println("Usuário encontrado:\n");
                System.out.println("ID: " + usuarioEncontrado.getCodigo());
                System.out.println("Nome: " + usuarioEncontrado.getNome());
                System.out.println("Login: " + usuarioEncontrado.getLogin());
                System.out.println("Senha: " + usuarioEncontrado.getSenha());
                System.out.println("Email: " + usuarioEncontrado.getEmail());
            } else {
                System.out.println("Nenhum usuário encontrado com o ID: " + id);
            }
        }


        scanner.close();
    }
}
