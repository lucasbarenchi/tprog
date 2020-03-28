package com.main.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;

public class CreatePlaylistJInternalFrame extends JInternalFrame {
    private JTextField nameTextField;

    /**
     * Create the frame.
     */
    public CreatePlaylistJInternalFrame() {
        setTitle("Crear lista de reproducción");

        ButtonGroup btnGroup = new ButtonGroup();
        JPanel playlistNamePanel = new JPanel();
        JPanel playlistTypePanel = new JPanel();
        JPanel particularOptionsPanel = new JPanel();

        JPanel confirmBtnPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(confirmBtnPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                .addComponent(playlistTypePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                .addComponent(particularOptionsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                .addComponent(playlistNamePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playlistTypePanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(playlistNamePanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(particularOptionsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(confirmBtnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(155, Short.MAX_VALUE))
                );
        confirmBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnConfirmar = new JButton("Confirmar");
        confirmBtnPanel.add(btnConfirmar);

        JPanel playlistUserPanel = new JPanel();
        playlistUserPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        playlistUserPanel.add(lblUsuario);

        JComboBox<String> comboBox = new JComboBox<>();
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> comboBox.addItem(userDT.getNickname()));

        playlistUserPanel.add(comboBox);

        JPanel isPrivatePanel = new JPanel();

        JPanel panel = new JPanel();
        GroupLayout gl_particularOptionsPanel = new GroupLayout(particularOptionsPanel);
        gl_particularOptionsPanel.setHorizontalGroup(
                gl_particularOptionsPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(playlistUserPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addComponent(isPrivatePanel, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                );
        gl_particularOptionsPanel.setVerticalGroup(
                gl_particularOptionsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_particularOptionsPanel.createSequentialGroup()
                        .addGap(5)
                        .addComponent(playlistUserPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(isPrivatePanel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                );
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        isPrivatePanel.setLayout(new GridLayout(0, 1, 0, 0));

        JCheckBox chckbxPrivada = new JCheckBox("Privada");
        chckbxPrivada.setHorizontalAlignment(SwingConstants.CENTER);
        isPrivatePanel.add(chckbxPrivada);
        particularOptionsPanel.setLayout(gl_particularOptionsPanel);

        JLabel lblTipo = new JLabel("Tipo:");

        JRadioButton rdbtnPorDefecto = new JRadioButton("Por defecto");
        rdbtnPorDefecto.setSelected(true);
        btnGroup.add(rdbtnPorDefecto);
        JRadioButton rdbtnParticular = new JRadioButton("Particular");
        rdbtnParticular.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    particularOptionsPanel.setVisible(true);

                    //pack();
                }
            }
        });

        rdbtnPorDefecto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    particularOptionsPanel.setVisible(false);
                    //pack();
                }
            }
        });
        btnGroup.add(rdbtnParticular);

        particularOptionsPanel.setVisible(false);

        JSeparator separator = new JSeparator();
        playlistTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        playlistTypePanel.add(lblTipo);
        playlistTypePanel.add(rdbtnPorDefecto);
        playlistTypePanel.add(rdbtnParticular);
        playlistTypePanel.add(separator);
        playlistNamePanel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNombreDeLa = new JLabel("Nombre de la lista:");
        lblNombreDeLa.setHorizontalAlignment(SwingConstants.CENTER);
        playlistNamePanel.add(lblNombreDeLa);

        nameTextField = new JTextField();
        playlistNamePanel.add(nameTextField);
        nameTextField.setColumns(10);
        getContentPane().setLayout(groupLayout);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();
                String name = nameTextField.getText();
                if (rdbtnPorDefecto.isSelected()) {
                    try {
                        playlistController.createDefaultPlaylist(name);
                        System.out.println("Se creo con éxito una playlist por defecto de nombre " + name);
                        JOptionPane.showMessageDialog(getParent(), "La playlist por defecto se ha creado con éxito!");
                    } catch (KeyAlreadyInUseException exception) {
                        System.out.println(exception.getMessage());
                        JOptionPane.showMessageDialog(getParent(), exception.getMessage());
                    }
                } else if (rdbtnParticular.isSelected()) {
                    try {
                        String nickname = (String) comboBox.getSelectedItem();
                        boolean isPrivate = chckbxPrivada.isSelected();
                        playlistController.createParticularPlaylist(name, nickname, isPrivate);
                        System.out.println("Se creo con éxito una playlist particular de nombre " + name + " para el usuario " + nickname);

                        JOptionPane.showMessageDialog(getParent(), "La playlist particular se ha creado con éxito!");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        JOptionPane.showMessageDialog(getParent(), exception.getMessage());
                    }
                }
            }
        });

        pack();
    }
}
