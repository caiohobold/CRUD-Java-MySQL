
import DAO.UsuarioDAO;
import entity.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {
    JButton cadastrarButton, procurarButton, removerButton, alterarButton, alterarNome, alterarLogin, alterarSenha, alterarEmail;
    UsuarioDAO usuarioDAO;

    public UserInterface() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Gestão de Usuários");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Cadastro de Usuários", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(400, 150));

        cadastrarButton = new JButton("Cadastrar Usuário");
        cadastrarButton.setBackground(Color.GREEN);
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setPreferredSize(new Dimension(350, 50));
        cadastrarButton.addActionListener(this);

        procurarButton = new JButton("Procurar Usuário por ID");
        procurarButton.setPreferredSize(new Dimension(350, 50));
        procurarButton.addActionListener(this);

        alterarButton = new JButton("Alterar Usuário");
        alterarButton.setPreferredSize(new Dimension(350, 50));
        alterarButton.addActionListener(this);

        removerButton = new JButton("Remover Usuário");
        removerButton.setBackground(Color.RED);
        removerButton.setForeground(Color.WHITE); 
        removerButton.setPreferredSize(new Dimension(350, 50));
        removerButton.addActionListener(this);

        add(cadastrarButton);
        add(procurarButton);
        add(alterarButton);
        add(removerButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrarButton) {
            cadastrarUsuario();
        } else if (e.getSource() == procurarButton) {
            procurarUsuarioPorId();
        } else if (e.getSource() == removerButton) {
            removerUsuario();
        } else if (e.getSource() == alterarButton){
            alterarUsuario();
        }
    }

    private void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do usuário:");
        String login = JOptionPane.showInputDialog(this, "Digite o login do usuário:");
        String senha = JOptionPane.showInputDialog(this, "Digite a senha do usuário:");
        String email = JOptionPane.showInputDialog(this, "Digite o email do usuário:");

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setLogin(login);
        novoUsuario.setSenha(senha);
        novoUsuario.setEmail(email);

        usuarioDAO.cadastrarUsuario(novoUsuario);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!\nID:  "+ novoUsuario.getCodigo());
    }

    private void procurarUsuarioPorId() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o ID do usuário:"));
        Usuario usuario = usuarioDAO.buscaUsuario(id);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Usuário encontrado:\n" +
                    "ID: " + usuario.getCodigo() + "\n" +
                    "Nome: " + usuario.getNome() + "\n" +
                    "Login: " + usuario.getLogin() + "\n" +
                    "Senha: " + usuario.getSenha() + "\n" +
                    "Email: " + usuario.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado com o ID: " + id);
        }
    }

    private void removerUsuario() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o ID do usuário a ser removido:"));
        Usuario usuario = usuarioDAO.buscaUsuario(id);

        if (usuario != null) {
            usuarioDAO.deletarUsuario(id);
            JOptionPane.showMessageDialog(this, "Usuário removido com sucesso: " + usuario.getNome());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado com o ID: " + id);
        }
    }

    private void alterarUsuario(){
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o ID do usuário:"));
        Usuario usuario = usuarioDAO.alteraUsuario(id);
        if (usuario != null) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));

            panel.add(new JLabel("ID: " + usuario.getCodigo()));
            panel.add(new JLabel("Nome: " + usuario.getNome()));
            panel.add(new JLabel("Login: " + usuario.getLogin()));
            panel.add(new JLabel("Senha: " + usuario.getSenha()));
            panel.add(new JLabel("Email: " + usuario.getEmail()));

            alterarNome = new JButton("Alterar Nome");
            alterarNome.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String novoNome = JOptionPane.showInputDialog(UserInterface.this, "Digite o novo nome:");
                    if(novoNome != null){
                        usuario.setNome(novoNome);
                        usuarioDAO.atualizarUsuario(usuario);
                        JOptionPane.showMessageDialog(UserInterface.this, "Nome alterado com sucesso!");
                    }
                }
            });
            alterarLogin = new JButton("Alterar Login");
            alterarLogin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String novoLogin = JOptionPane.showInputDialog(UserInterface.this, "Digite o novo login:");
                    if(novoLogin != null){
                        usuario.setLogin(novoLogin);
                        usuarioDAO.atualizarUsuario(usuario);
                        JOptionPane.showMessageDialog(UserInterface.this, "Login alterado com sucesso!");
                    }
                }
            });
            alterarSenha = new JButton("Alterar Senha");
            alterarSenha.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String novaSenha = JOptionPane.showInputDialog(UserInterface.this, "Digite a nova senha:");
                    if(novaSenha != null){
                        usuario.setSenha(novaSenha);
                        usuarioDAO.atualizarUsuario(usuario);
                        JOptionPane.showMessageDialog(UserInterface.this, "Senha alterada com sucesso!");
                    }
                }
            });
            alterarEmail = new JButton("Alterar E-mail");
            alterarEmail.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String novoEmail = JOptionPane.showInputDialog(UserInterface.this, "Digite o novo email:");
                    if(novoEmail != null){
                        usuario.setEmail(novoEmail);
                        usuarioDAO.atualizarUsuario(usuario);
                        JOptionPane.showMessageDialog(UserInterface.this, "Email alterado com sucesso!");
                    }
                }
            });
            
            panel.add(alterarNome);
            panel.add(alterarLogin);
            panel.add(alterarSenha);
            panel.add(alterarEmail);
            JOptionPane.showMessageDialog(this, panel, "Informações do Usuário", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado com o ID: " + id);
        }
    }

}
