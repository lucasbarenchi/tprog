package com.main.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.DefaultDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;

public class InfoPlaylistJInternalFrame extends JInternalFrame {

    private JPanel panelUser;
    private JPanel panelPlaylists;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panelVideos;
    private JPanel panelButton;
    private GroupLayout groupLayout;
    private JButton btnConsultarVideo;
    private JLabel lblTipo;
    private JLabel label_2;
    private JLabel lblVisibilidad;
    private JLabel label;
    private JLabel lblNombre;
    private JLabel label_1;
    private JLabel lblUsuario;
    private JComboBox comboBoxUsers;
    private JComboBox comboBoxVideos;
    private JComboBox comboBoxPlay;
    private IUserController userController;
    private IPlaylistController playlistController;
    private JFrame parent;

    public InfoPlaylistJInternalFrame(UserDT userDT, String playlist, JFrame parent) {
        initGUI();
        this.parent = parent;
        comboBoxUsers.addItem(userDT.getNickname());
        comboBoxUsers.setEnabled(false);
        comboBoxVideos.setEnabled(true);
        comboBoxPlay.addItem(playlist);
        loadPlaylistInfo();
        btnConsultarVideo.setEnabled(true);
        addListenerToVideoButton();
    }

    /**
     * @wbp.parser.constructor
     */
    public InfoPlaylistJInternalFrame(JFrame parent) {
        setTitle("Consulta de lista de reproducción");
        this.parent = parent;
        initGUI();
        comboBoxUsers.addItem("-");
        List<UserDT> users = userController.listUsers();

        for (UserDT user : users) {
            comboBoxUsers.addItem(user.getNickname());
        }
        addListenerToPlaylistCombo();
        addListenerToUsersCombo();
        addListenerToVideoButton();
        addListenerToVideoCombo();
    }

    public void initGUI() {
        setBounds(100, 100, 480, 295);

        panelUser = new JPanel();

        panelPlaylists = new JPanel();

        panel_2 = new JPanel();

        panel_3 = new JPanel();

        panel_4 = new JPanel();

        panelVideos = new JPanel();

        panelPlaylists.setEnabled(false);
        panelVideos.setEnabled(false);

        panelButton = new JPanel();

        btnConsultarVideo = new JButton("Consultar video");
        btnConsultarVideo.setEnabled(false);

        groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addComponent(panelVideos, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(panelUser, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(panelPlaylists, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                                        .addContainerGap())
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(btnConsultarVideo)
                                                .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(39))))
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelPlaylists, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelVideos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(btnConsultarVideo)
                        .addGap(22)
                        .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                );
        panelButton.setLayout(new GridLayout(1, 0, 0, 0));
        panelVideos.setLayout(new GridLayout(0, 2, 0, 0));


        panel_4.setLayout(new GridLayout(0, 2, 0, 0));

        lblTipo = new JLabel("Tipo:");
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        panel_4.add(lblTipo);

        label_2 = new JLabel("-");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        panel_4.add(label_2);
        panel_3.setLayout(new GridLayout(0, 2, 0, 0));

        lblVisibilidad = new JLabel("Visibilidad:");
        lblVisibilidad.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(lblVisibilidad);

        label = new JLabel("-");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(label);
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));

        lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblNombre);

        label_1 = new JLabel("-");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(label_1);
        panelPlaylists.setLayout(new GridLayout(0, 2, 0, 0));

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        panelUser.add(lblUsuario);

        comboBoxUsers = new JComboBox();
        comboBoxPlay = new JComboBox();
        comboBoxVideos = new JComboBox();

        comboBoxPlay.setEnabled(false);
        comboBoxVideos.setEnabled(false);

        userController = ControllerFactory.getInstance().getUserController();
        playlistController = ControllerFactory.getInstance().getPlaylistController();

        panelUser.add(comboBoxUsers);
        getContentPane().setLayout(groupLayout);

        // PLAYLISTS

        JLabel lblListasDeReproduccin = new JLabel("Listas de reproducción:");
        lblListasDeReproduccin.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlaylists.add(lblListasDeReproduccin);
        panelPlaylists.add(comboBoxPlay);
        panelUser.setLayout(new GridLayout(0, 2, 0, 0));

        // VIDEOS

        JLabel lblVideos = new JLabel("Videos:");
        lblVideos.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideos.add(lblVideos);
        panelVideos.add(comboBoxVideos);
    }

    public void addListenerToVideoButton() {
        btnConsultarVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedUser = comboBoxUsers.getSelectedItem();
                Object selectedVideo = comboBoxVideos.getSelectedItem();
                Object selectedPlaylist = comboBoxPlay.getSelectedItem();
                try {
                    UserDT user = userController.getUserData(selectedUser.toString());
                    Optional<PlaylistDT> playlistOpt = playlistController.listPlaylistByUser(selectedUser.toString())
                            .stream()
                            .filter(pl -> pl.getName().equals(selectedPlaylist.toString()))
                            .findFirst();

                    if (playlistOpt.isPresent()) {
                        PlaylistDT play = playlistOpt.get();

                        Optional<VideoDT> video = play.getVideos()
                                .stream()
                                .filter(v -> v.getTitle().equals(selectedVideo.toString()))
                                .findFirst();

                        if (video.isPresent()) {
                            VideoInfoJInternalFrame internalJF = new VideoInfoJInternalFrame(video.get(), user);
                            internalJF.setResizable(false);
                            internalJF.setMinimumSize(new Dimension(620, 450));
                            internalJF.setMaximumSize(new Dimension(620, 450));
                            internalJF.setPreferredSize(new Dimension(620, 450));
                            internalJF.setSize(new Dimension(620, 450));
                            internalJF.setBounds(0, 0, 100, 100);
                            internalJF.setVisible(true);
                            internalJF.setClosable(true);
                            parent.getContentPane().add(internalJF);
                            internalJF.toFront();
                            internalJF.pack();
                        } else {
                            JOptionPane.showMessageDialog(null, "Ese video no existe realmente. Reportar al admin.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                } catch (EntityNotFoundException exc) {
                    JOptionPane.showMessageDialog(null, "Ese usuario no existe realmente. Reportar al administrador!", "Alerta", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
    }

    public void addListenerToUsersCombo() {
        comboBoxUsers.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    comboBoxVideos.removeAllItems();
                    comboBoxPlay.removeAllItems();
                    comboBoxPlay.addItem("-");
                    label.setText("-");
                    label_1.setText("-");
                    label_2.setText("-");
                    comboBoxPlay.setEnabled(true);
                    comboBoxVideos.setEnabled(false);

                    Object user = comboBoxUsers.getSelectedItem();

                    if (!user.toString().equals("-")) {
                        List<PlaylistDT> playlists = playlistController.listPlaylistByUser(user.toString());

                        for (PlaylistDT play : playlists) {
                            comboBoxPlay.addItem(play.getName());
                        }
                        panelPlaylists.setEnabled(true);
                        panelVideos.setEnabled(false);
                    }
                }
            }
        });
    }

    public void addListenerToVideoCombo() {
        comboBoxVideos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBoxVideos.getSelectedItem().toString().equals("-") || comboBoxVideos.getItemCount() == 0) {
                        return;
                    }
                    btnConsultarVideo.setEnabled(true);
                }
            }
        });

    }

    public void addListenerToPlaylistCombo() {
        comboBoxPlay.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBoxPlay.getSelectedItem().toString().equals("-") || comboBoxPlay.getItemCount() == 0) {
                        label.setText("-");
                        label_1.setText("-");
                        label_2.setText("-");
                        return;
                    }
                    comboBoxVideos.removeAllItems();
                    comboBoxVideos.setEnabled(true);

                    loadPlaylistInfo();

                    panelVideos.setEnabled(true);
                    //pack();
                }
            }
        });
    }

    public void loadPlaylistInfo() {
        Object playlist = comboBoxPlay.getSelectedItem();
        Object userSelected = comboBoxUsers.getSelectedItem();

        try {
            List<VideoDT> videos = new ArrayList<>();

            Optional<PlaylistDT> playl = userController.listUserPlaylist(userSelected.toString())
                    .stream()
                    .filter(pl -> pl.getName().equals(playlist.toString()))
                    .findFirst();

            if (playl.isPresent()) {
                videos = playl.get().getVideos();
                label_1.setText(playl.get().getName());
                if (playl.get().isPrivate()) {
                    label.setText("Privada");
                } else {
                    label.setText("Pública");
                }
                if (playl.get() instanceof DefaultDT) {
                    label_2.setText("Default");
                } else {
                    label_2.setText("Particular");
                }
            }
            comboBoxVideos.addItem("-");
            for (VideoDT vid : videos) {
                comboBoxVideos.addItem(vid.getTitle());
            }

        } catch (EntityNotFoundException e) {
            System.out.println("Usuario no encontrado para esa playlist");
        }
    }
}
