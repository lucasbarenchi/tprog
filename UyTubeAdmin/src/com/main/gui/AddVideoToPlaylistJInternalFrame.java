package com.main.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;

public class AddVideoToPlaylistJInternalFrame extends JInternalFrame {

    public AddVideoToPlaylistJInternalFrame() {
        setTitle("Añadir video a lista de reproducción");
        setClosable(true);
        getContentPane().setMaximumSize(new Dimension(400, 800));

        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JPanel panel_1 = new JPanel();

        JPanel panel_2 = new JPanel();

        JPanel panel_3 = new JPanel();

        JPanel panel_4 = new JPanel();

        JPanel panel_5 = new JPanel();

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addContainerGap())
                );

        JButton btnConfirmar = new JButton("Confirmar");
        panel_5.add(btnConfirmar);
        btnConfirmar.setEnabled(false);

        JButton btnCancelar = new JButton("Cancelar");
        panel_4.add(btnCancelar);
        btnCancelar.setEnabled(false);

        panel_3.setLayout(new GridLayout(0, 2, 0, 0));

        IUserController userController = ControllerFactory.getInstance().getUserController();

        List<UserDT> users = userController.listUsers();

        JLabel lblNewLabel = new JLabel("Usuario dueño del video:");

        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox();
        panel.add(comboBox);
        comboBox.addItem("-");
        for (UserDT user : users) {
            comboBox.addItem(user.getNickname());
        }

        getContentPane().setLayout(groupLayout);

        JLabel lblVideosDelUsuario = new JLabel("Videos del usuario:");
        lblVideosDelUsuario.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblVideosDelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblVideosDelUsuario);

        JComboBox<String> comboBoxVideos = new JComboBox();
        panel_1.add(comboBoxVideos);
        comboBoxVideos.addItem("-");
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        comboBoxVideos.setEnabled(false);

        JLabel lblUsuarioDueoDe = new JLabel("Usuario dueño de playlist:");
        lblUsuarioDueoDe.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblUsuarioDueoDe.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblUsuarioDueoDe);


        JComboBox<String> comboBoxUser2 = new JComboBox();
        panel_2.add(comboBoxUser2);
        comboBoxUser2.addItem("-");
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        comboBoxUser2.setEnabled(false);

        JLabel lblPlaylistDestino = new JLabel("Playlist destino:");
        lblPlaylistDestino.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblPlaylistDestino.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblPlaylistDestino);


        JComboBox<String> comboBoxPlaylist = new JComboBox();
        panel_3.add(comboBoxPlaylist);
        comboBoxPlaylist.addItem("-");
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));

        comboBoxPlaylist.setEnabled(false);

        // Cuando elija un Usuario en el primer comboBox

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    comboBoxVideos.removeAllItems();
                    comboBoxUser2.removeAllItems();
                    comboBoxPlaylist.removeAllItems();
                    comboBoxVideos.addItem("-");
                    try {
                        Object nickUser = comboBox.getSelectedItem();

                        IVideoController videoController = ControllerFactory.getInstance().getVideoController();
                        List<VideoDT> videosFromUser = videoController.listVideosByUser(nickUser.toString());
                        for (VideoDT video : videosFromUser) {
                            comboBoxVideos.addItem(video.getTitle());
                        }

                    } catch (EntityNotFoundException excep) {
                        System.out.println("Usuario no encontrado");
                    }

                    comboBoxUser2.setEnabled(false);
                    comboBoxPlaylist.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnConfirmar.setEnabled(false);
                    comboBoxVideos.setEnabled(true);
                    //pack();
                }
            }
        });

        // Cuando elija un Video

        comboBoxVideos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {

                    Object videoTitle = comboBoxVideos.getSelectedItem();
                    comboBoxUser2.removeAllItems();
                    comboBoxPlaylist.removeAllItems();
                    comboBoxUser2.addItem("-");
                    for (UserDT user : users) {
                        comboBoxUser2.addItem(user.getNickname());
                    }


                    comboBoxPlaylist.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnConfirmar.setEnabled(false);
                    comboBoxUser2.setEnabled(true);
                    //pack();
                }
            }
        });

        // Cuando elija un segundo usuario

        comboBoxUser2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        comboBoxPlaylist.removeAllItems();
                        comboBoxPlaylist.addItem("-");
                        Object user2 = comboBoxUser2.getSelectedItem();
                        List<PlaylistDT> playlists = userController.listUserPlaylist(user2.toString());
                        for (PlaylistDT playlist : playlists) {
                            comboBoxPlaylist.addItem(playlist.getName());
                        }

                    } catch (EntityNotFoundException excep) {
                        // NO DEBERIA PASAR NUNCA
                    }


                    btnCancelar.setEnabled(false);
                    btnConfirmar.setEnabled(false);
                    comboBoxPlaylist.setEnabled(true);
                    //pack();
                }
            }
        });

        // Cuando elija una playlist

        comboBoxPlaylist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {

                    Object playlist = comboBoxPlaylist.getSelectedItem();

                    btnCancelar.setEnabled(true);
                    btnConfirmar.setEnabled(true);
                    //pack();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playlistName = comboBoxPlaylist.getSelectedItem().toString();
                String nickOwnerVideo = comboBox.getSelectedItem().toString();
                String nickOwnerPlaylist = comboBoxUser2.getSelectedItem().toString();
                String videoName = comboBoxVideos.getSelectedItem().toString();

                IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();

                try {
                    playlistController.addVideoToPlaylist(playlistName, videoName, nickOwnerVideo, nickOwnerPlaylist);
                    JOptionPane.showMessageDialog(getParent(), "El video ha sido agregado a la playlist con éxito!");
                    System.out.println("El video se ha agregado con éxito a la playlist");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(getParent(), e1.getMessage());				
                }
            }
        });

    }
}
