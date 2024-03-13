
import DAO.UsuarioDAO;
import entity.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {
    JButton cadastrarButton, procurarButton, removerButton;
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

        removerButton = new JButton("Remover Usuário");
        removerButton.setBackground(Color.RED);
        removerButton.setForeground(Color.WHITE); 
        removerButton.setPreferredSize(new Dimension(350, 50));
        removerButton.addActionListener(this);

        add(cadastrarButton);
        add(procurarButton);
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
}
