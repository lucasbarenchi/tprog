package com.main.gui;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;

public class RemoveVideoFromPlaylistJInternalFrame extends JInternalFrame {
    //private String contentUserCB = "";
    //private String contentPlayListCB = "";

    List<UserDT> userDTs = null;
    List<PlaylistDT> playlistDTs = null;
    List<VideoDT> videoDTs = null;
    private JComboBox videoComboBox;
    private JComboBox playlistsComboBox;
    private JComboBox<String> usersComboBox;

    public RemoveVideoFromPlaylistJInternalFrame() {
        setTitle("Remover video de lista de reproducción");
        setBounds(100, 100, 520, 210);

        JPanel panelUser = new JPanel();

        JPanel panelPlaylist = new JPanel();

        JPanel panelVideo = new JPanel();

        JPanel panelButtons = new JPanel();

        JLabel videoLbl = new JLabel("Video:");
        videoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideo.add(videoLbl);
        videoComboBox = new JComboBox();
        panelVideo.add(videoComboBox);
        videoComboBox.addItem("-");
        panelPlaylist.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel playlistLbl = new JLabel("Playlist:");
        playlistLbl.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlaylist.add(playlistLbl);
        playlistsComboBox = new JComboBox();
        panelPlaylist.add(playlistsComboBox);
        playlistsComboBox.addItem("-");

        JLabel nicknameLbl = new JLabel("Usuario:");
        nicknameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        panelUser.add(nicknameLbl);
        usersComboBox = new JComboBox<>();
        panelUser.add(usersComboBox);        
        usersComboBox.addItem("-");

        IUserController userController = ControllerFactory.getInstance().getUserController();
        IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();

        userDTs = userController.listUsers();

        userDTs.forEach(userDT -> usersComboBox.addItem(userDT.getNickname()));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                                .addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelPlaylist, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelVideo, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(panelPlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(panelVideo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                );
        panelButtons.setLayout(new GridLayout(0, 2, 0, 0));

        JButton btnConfirmar = new JButton("Confirmar");
        panelButtons.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        panelButtons.add(btnCancelar);

        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        btnConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!usersComboBox.getSelectedItem().toString().equals("-") &&
                        !playlistsComboBox.getSelectedItem().toString().equals("-") &&
                        !videoComboBox.getSelectedItem().toString().equals("-")) {

                    //REMOVE VIDEO FROM PLAYLIST
                    String userNick = usersComboBox.getSelectedItem().toString();
                    String playlist = playlistsComboBox.getSelectedItem().toString();
                    String video = videoComboBox.getSelectedItem().toString();

                    boolean hasBeenRemoved = playlistController.removeFromPlaylist(userNick, playlist, video);
                    if (hasBeenRemoved) {
                        JOptionPane.showMessageDialog(getParent(), "El video ha sido removido de la playlist exitosamente!");
                        // CLEAN DATA
                        usersComboBox.setSelectedIndex(0);
                        playlistsComboBox.removeAllItems();
                        videoComboBox.removeAllItems();

                        playlistsComboBox.addItem("-");
                        videoComboBox.addItem("-");
                    }
                } else {
                    JOptionPane.showMessageDialog(getParent(), "No han sido seleccionados todos los datos requeridos!");
                }
            }
        });
        panelVideo.setLayout(new GridLayout(0, 2, 0, 0));



        playlistsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent eventData) {
                if (eventData.getStateChange() == ItemEvent.SELECTED && !playlistsComboBox.getSelectedItem().toString().equals("-")) {

                    videoComboBox.removeAllItems();
                    videoComboBox.addItem("-");

                    // UPDATE DATA
                    videoDTs = playlistDTs.get(playlistsComboBox.getSelectedIndex() - 1).getVideos();
                    videoDTs.forEach(videoDT -> videoComboBox.addItem(videoDT.getTitle().toString()));
                }
            }
        });
        panelUser.setLayout(new GridLayout(1, 0, 0, 0));

        usersComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent eventData) {
                if (eventData.getStateChange() == ItemEvent.SELECTED && !usersComboBox.getSelectedItem().toString().equals("-")) {

                    playlistsComboBox.removeAllItems();
                    videoComboBox.removeAllItems();

                    playlistsComboBox.addItem("-");
                    videoComboBox.addItem("-");

                    String nick = usersComboBox.getSelectedItem().toString();

                    playlistDTs = playlistController.listPlaylistByUser(nick);
                    playlistDTs.forEach(playlistDT -> playlistsComboBox.addItem(playlistDT.getName().toString()));
                }
            }
        });
        getContentPane().setLayout(groupLayout);
    }
}
