package com.main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.controller.PlaylistController;
import com.main.logic.dts.UserDT;
import com.main.logic.interfaces.IUserController;

public class ModifyPlaylistJInternalFrame extends JInternalFrame {

    private static final String EMPTY_COMBO_BOX_ITEM = "-";
    private static final UserDT EMPTY_COMBO_BOX_USER = new UserDT("-");

    public ModifyPlaylistJInternalFrame() {
        setTitle("Modificar lista de reproducción");
        setBounds(100, 100, 450, 210);

        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addContainerGap())
                );

        JPanel panel_1 = new JPanel();

        JPanel panel_2 = new JPanel();

        JPanel panel_3 = new JPanel();

        JPanel panel_4 = new JPanel();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(121, Short.MAX_VALUE))
                );

        JCheckBox isPrivateCheckbox = new JCheckBox("Privado");
        panel_3.add(isPrivateCheckbox);
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel playlistLabel = new JLabel("Playlist:");
        playlistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(playlistLabel);

        JComboBox<UserDT> userComboBox = new JComboBox<UserDT>();
        userComboBox.addItem(EMPTY_COMBO_BOX_USER);

        JComboBox<String> playlistComboBox = new JComboBox<String>();
        playlistComboBox.addItem(EMPTY_COMBO_BOX_ITEM);

        panel_2.add(playlistComboBox);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(usernameLabel);
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> userComboBox.addItem(userDT));

        userComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                for (int i = 1; i < playlistComboBox.getItemCount(); ) {
                    playlistComboBox.removeItemAt(i);
                }
                if (!(EMPTY_COMBO_BOX_ITEM).equals(userComboBox.getSelectedItem().toString())) {
                    UserDT user = (UserDT) userComboBox.getSelectedItem();
                    user.getChannel().getPlaylists().forEach(pl -> playlistComboBox.addItem(pl));
                }


            }
        });
        panel_1.add(userComboBox);
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!EMPTY_COMBO_BOX_ITEM.equals(playlistComboBox.getSelectedItem())) {
                    UserDT user = (UserDT) userComboBox.getSelectedItem();
                    String playlist = (String) playlistComboBox.getSelectedItem();
                    PlaylistController playlistController = PlaylistController.getInstance();
                    try {
                        playlistController.modifyPlaylist(user.getNickname(), playlist, isPrivateCheckbox.isSelected());
                        JOptionPane.showMessageDialog(getParent(), "Playlist modificada con éxito");

                        System.out.println("Exito");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(getParent(), e1.getMessage());
                    }
                }
            }
        });
        confirmButton.setBackground(UIManager.getColor("Focus.color"));
        panel_4.add(confirmButton);


    }

    public ModifyPlaylistJInternalFrame(UserDT userDTInput, String playlistName) {
        setTitle("Modificar lista de reproducción");
        setBounds(100, 100, 450, 210);

        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addContainerGap())
                );

        JPanel panel_1 = new JPanel();

        JPanel panel_2 = new JPanel();

        JPanel panel_3 = new JPanel();

        JPanel panel_4 = new JPanel();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(121, Short.MAX_VALUE))
                );

        JCheckBox isPrivateCheckbox = new JCheckBox("Privado");
        panel_3.add(isPrivateCheckbox);
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel playlistLabel = new JLabel("Playlist:");
        playlistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(playlistLabel);

        JComboBox<UserDT> userComboBox = new JComboBox<UserDT>();
        userComboBox.addItem(EMPTY_COMBO_BOX_USER);

        JComboBox<String> playlistComboBox = new JComboBox<String>();
        playlistComboBox.addItem(EMPTY_COMBO_BOX_ITEM);

        panel_2.add(playlistComboBox);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(usernameLabel);
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> userComboBox.addItem(userDT));

        userComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                for (int i = 1; i < playlistComboBox.getItemCount(); ) {
                    playlistComboBox.removeItemAt(i);
                }
                if (!(EMPTY_COMBO_BOX_ITEM).equals(userComboBox.getSelectedItem().toString())) {
                    UserDT user = (UserDT) userComboBox.getSelectedItem();
                    user.getChannel().getPlaylists().forEach(pl -> playlistComboBox.addItem(pl));
                }


            }
        });
        panel_1.add(userComboBox);
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!EMPTY_COMBO_BOX_ITEM.equals(playlistComboBox.getSelectedItem())) {
                    UserDT user = (UserDT) userComboBox.getSelectedItem();
                    String playlist = (String) playlistComboBox.getSelectedItem();
                    PlaylistController playlistController = PlaylistController.getInstance();
                    try {
                        playlistController.modifyPlaylist(user.getNickname(), playlist, isPrivateCheckbox.isSelected());
                        JOptionPane.showMessageDialog(getParent(), "Playlist modificada con éxito");

                        System.out.println("Exito");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(getParent(), e1.getMessage());
                    }
                }
            }
        });
        confirmButton.setBackground(UIManager.getColor("Focus.color"));
        panel_4.add(confirmButton);

        UserDT actualUserDT = null;
        for(UserDT userDT : userDTs) {
            if (userDT.getNickname() == userDTInput.getNickname()) {
                actualUserDT = userDT;
            }
        }

        if (actualUserDT != null) {
            userComboBox.setSelectedItem(actualUserDT);
            playlistComboBox.setSelectedItem(playlistName);

            userComboBox.setEnabled(false);
            playlistComboBox.setEnabled(false);
        }
    }

}
