package com.main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.ChannelDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.interfaces.IUserController;

public class UserInfoJInternalFrame extends JInternalFrame {

    private List<VideoDT> userVideos;
    private JComboBox comboBoxUsers;

    public UserInfoJInternalFrame(JFrame parent) {

        setTitle("Consulta de Usuario");
        setBounds(100, 100, 1050, 670);

        JPanel panelUsersExt = new JPanel();
        panelUsersExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        JPanel panelUserInfoExt = new JPanel();
        panelUserInfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        JPanel panelChannelInfoExt = new JPanel();
        panelChannelInfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        JPanel panelChannelInfoLabel = new JPanel();
        panelChannelInfoLabel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblChannelInfo = new JLabel("Información del canal:");
        lblChannelInfo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelChannelInfoLabel.add(lblChannelInfo);

        JPanel panelChannelInfo = new JPanel();

        JLabel lblChannelIdHeader = new JLabel("Id:");

        JLabel lblChannelId = new JLabel("");

        JLabel lblChannelNameHeader = new JLabel("Nombre:");

        JLabel lblChannelName = new JLabel("");

        JLabel lblChannelDescriptionHeader = new JLabel("Descripción:");

        JLabel lblChannelDescription = new JLabel("");

        JLabel lblChannelPrivateHeader = new JLabel("Privado:");

        JLabel lblChannelPrivate = new JLabel("");
        GroupLayout gl_panelChannelInfo = new GroupLayout(panelChannelInfo);
        gl_panelChannelInfo.setHorizontalGroup(
                gl_panelChannelInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelChannelInfo.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                                        .addComponent(lblChannelIdHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblChannelId))
                                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                                        .addComponent(lblChannelNameHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblChannelName))
                                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                                        .addComponent(lblChannelDescriptionHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblChannelDescription))
                                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                                        .addComponent(lblChannelPrivateHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblChannelPrivate)))
                        .addContainerGap(241, Short.MAX_VALUE))
                );
        gl_panelChannelInfo.setVerticalGroup(
                gl_panelChannelInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelChannelInfo.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelChannelInfo.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblChannelIdHeader)
                                .addComponent(lblChannelId))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_panelChannelInfo.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblChannelNameHeader)
                                .addComponent(lblChannelName))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_panelChannelInfo.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblChannelDescriptionHeader)
                                .addComponent(lblChannelDescription))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_panelChannelInfo.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblChannelPrivateHeader)
                                .addComponent(lblChannelPrivate))
                        .addContainerGap(24, Short.MAX_VALUE))
                );
        panelChannelInfo.setLayout(gl_panelChannelInfo);
        GroupLayout gl_panelChannelInfoExt = new GroupLayout(panelChannelInfoExt);
        gl_panelChannelInfoExt.setHorizontalGroup(
                gl_panelChannelInfoExt.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelChannelInfoExt.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelChannelInfoExt.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panelChannelInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                                .addComponent(panelChannelInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelChannelInfoExt.setVerticalGroup(
                gl_panelChannelInfoExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelChannelInfoExt.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelChannelInfoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelChannelInfo, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addContainerGap())
                );
        panelChannelInfoExt.setLayout(gl_panelChannelInfoExt);

        JPanel panelVideosAndPlaylistsExt = new JPanel();
        panelVideosAndPlaylistsExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        JPanel panelFollowersExt = new JPanel();
        panelFollowersExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

        JPanel panelFollowingAndFollowers = new JPanel();
        panelFollowingAndFollowers.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel panelFollowersAndFollowingHeaders = new JPanel();
        panelFollowersAndFollowingHeaders.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblFollowing = new JLabel("Siguiendo:");
        lblFollowing.setHorizontalAlignment(SwingConstants.CENTER);
        lblFollowing.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        panelFollowersAndFollowingHeaders.add(lblFollowing);

        JLabel lblFollowers = new JLabel("Seguidores:");
        lblFollowers.setHorizontalAlignment(SwingConstants.CENTER);
        lblFollowers.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        panelFollowersAndFollowingHeaders.add(lblFollowers);
        GroupLayout gl_panelFollowersExt = new GroupLayout(panelFollowersExt);
        gl_panelFollowersExt.setHorizontalGroup(
                gl_panelFollowersExt.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelFollowersExt.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelFollowersExt.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panelFollowingAndFollowers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                                .addComponent(panelFollowersAndFollowingHeaders, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelFollowersExt.setVerticalGroup(
                gl_panelFollowersExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelFollowersExt.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelFollowersAndFollowingHeaders, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelFollowingAndFollowers, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addContainerGap())
                );

        JScrollPane followingScrollPane = new JScrollPane();
        panelFollowingAndFollowers.add(followingScrollPane);

        JList<String> listFollowing = new JList<String>();
        followingScrollPane.setViewportView(listFollowing);

        JScrollPane followersScrollPane = new JScrollPane();
        panelFollowingAndFollowers.add(followersScrollPane);

        JList<String> listFollowers = new JList<String>();
        followersScrollPane.setViewportView(listFollowers);
        panelFollowersExt.setLayout(gl_panelFollowersExt);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(panelVideosAndPlaylistsExt, GroupLayout.PREFERRED_SIZE, 1016, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelFollowersExt, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1016, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelUsersExt, GroupLayout.PREFERRED_SIZE, 1016, GroupLayout.PREFERRED_SIZE)
                                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                        .addComponent(panelUserInfoExt, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(panelChannelInfoExt, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelUsersExt, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelUserInfoExt, GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE)
                                .addComponent(panelChannelInfoExt, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelVideosAndPlaylistsExt, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelFollowersExt, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

        JPanel panelVideosAndPlaylists = new JPanel();
        panelVideosAndPlaylists.setLayout(new GridLayout(1, 0, 0, 0));

        JScrollPane videosScrollPane = new JScrollPane();
        panelVideosAndPlaylists.add(videosScrollPane);

        JList<String> listVideos = new JList<String>();
        videosScrollPane.setViewportView(listVideos);

        JScrollPane playlistsScrollPane = new JScrollPane();
        panelVideosAndPlaylists.add(playlistsScrollPane);

        JList<String> listPlaylists = new JList<String>();
        playlistsScrollPane.setViewportView(listPlaylists);

        JPanel panelVideosAndPlaylistHeaders = new JPanel();

        JPanel panelVideosAndPlaylistButtons = new JPanel();
        GroupLayout gl_panelVideosAndPlaylistsExt = new GroupLayout(panelVideosAndPlaylistsExt);
        gl_panelVideosAndPlaylistsExt.setHorizontalGroup(
                gl_panelVideosAndPlaylistsExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelVideosAndPlaylistsExt.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelVideosAndPlaylistsExt.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelVideosAndPlaylists, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                                .addComponent(panelVideosAndPlaylistHeaders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                                .addComponent(panelVideosAndPlaylistButtons, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelVideosAndPlaylistsExt.setVerticalGroup(
                gl_panelVideosAndPlaylistsExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelVideosAndPlaylistsExt.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelVideosAndPlaylistHeaders, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelVideosAndPlaylists, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelVideosAndPlaylistButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
        panelVideosAndPlaylistButtons.setLayout(new GridLayout(1, 0, 0, 0));


        JButton buttonPlaylist = new JButton("Ver información de playlist");
        buttonPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlaylist = listPlaylists.getSelectedValue();
                if (selectedPlaylist != null) {
                    UserDT selectedUserDT = (UserDT) comboBoxUsers.getSelectedItem();

                    InfoPlaylistJInternalFrame internalJF = new InfoPlaylistJInternalFrame(selectedUserDT, selectedPlaylist, parent);

                    internalJF.setResizable(false);
                    internalJF.setMinimumSize(new Dimension(480, 295));
                    internalJF.setMaximumSize(new Dimension(480, 295));
                    internalJF.setPreferredSize(new Dimension(480, 295));
                    internalJF.setSize(new Dimension(480, 295));
                    internalJF.setBounds(0, 0, 100, 100);
                    internalJF.setVisible(true);
                    internalJF.setClosable(true);
                    parent.getContentPane().add(internalJF);
                    internalJF.toFront();
                    internalJF.pack();

                } else {
                    JOptionPane.showMessageDialog(null, "No hay ninguna playlist seleccionada", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonVideo = new JButton("Ver información de video");
        buttonVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVideo = listVideos.getSelectedValue();
                if (selectedVideo != null) {

                    int selectedIndex = listVideos.getSelectedIndex();
                    VideoDT selectedVideoDT = userVideos.get(selectedIndex);
                    UserDT selectedUserDT = (UserDT) comboBoxUsers.getSelectedItem();

                    VideoInfoJInternalFrame internalJF = new VideoInfoJInternalFrame(selectedVideoDT, selectedUserDT);

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
                    JOptionPane.showMessageDialog(null, "No hay ningun video seleccionado", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelVideosAndPlaylistButtons.add(buttonVideo);
        panelVideosAndPlaylistButtons.add(buttonPlaylist);
        panelVideosAndPlaylistHeaders.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblVideosHeaders = new JLabel("Videos:");
        lblVideosHeaders.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblVideosHeaders.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideosAndPlaylistHeaders.add(lblVideosHeaders);

        JLabel lblPlaylistHeader = new JLabel("Playlists:");
        lblPlaylistHeader.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblPlaylistHeader.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideosAndPlaylistHeaders.add(lblPlaylistHeader);
        panelVideosAndPlaylistsExt.setLayout(gl_panelVideosAndPlaylistsExt);

        JPanel panelUserInfoLabel = new JPanel();

        JPanel panelUserInfo = new JPanel();
        GroupLayout gl_panelUserInfoExt = new GroupLayout(panelUserInfoExt);
        gl_panelUserInfoExt.setHorizontalGroup(
                gl_panelUserInfoExt.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelUserInfoExt.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelUserInfoExt.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panelUserInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panelUserInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelUserInfoExt.setVerticalGroup(
                gl_panelUserInfoExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelUserInfoExt.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelUserInfoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelUserInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(316, Short.MAX_VALUE))
                );
        JLabel lblAvatar = new JLabel("");
        try {
            InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
            byte[] defAvatarBytes = new byte[defAvatarStream.available()];
            defAvatarStream.read(defAvatarBytes);
            ImageIcon imgIcon = new ImageIcon(defAvatarBytes);
            lblAvatar.setIcon(imgIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panelUserInfoLabels = new JPanel();
        GroupLayout gl_panelUserInfo = new GroupLayout(panelUserInfo);
        gl_panelUserInfo.setHorizontalGroup(
                gl_panelUserInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelUserInfo.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAvatar)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelUserInfoLabels, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addContainerGap())
                );
        gl_panelUserInfo.setVerticalGroup(
                gl_panelUserInfo.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panelUserInfo.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelUserInfoLabels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAvatar, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addContainerGap())
                );

        JLabel lblNicknameHeader = new JLabel("Nickname:");

        JLabel lblNameHeader = new JLabel("Nombre:");

        JLabel lblSurnameHeader = new JLabel("Apellido:");

        JLabel lblEmailHeader = new JLabel("E-mail:");

        JLabel lblBirthDateHeader = new JLabel("Fecha de nacimiento:");

        JLabel lblNickname = new JLabel("");

        JLabel lblName = new JLabel("");

        JLabel lblSurname = new JLabel("");

        JLabel lblEmail = new JLabel("");

        JLabel lblBirthDate = new JLabel("");
        GroupLayout gl_panelUserInfoLabels = new GroupLayout(panelUserInfoLabels);
        gl_panelUserInfoLabels.setHorizontalGroup(
                gl_panelUserInfoLabels.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                                        .addComponent(lblNicknameHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblNickname))
                                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                                        .addComponent(lblNameHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblName))
                                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                                        .addComponent(lblSurnameHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblSurname))
                                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                                        .addComponent(lblEmailHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblEmail))
                                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                                        .addComponent(lblBirthDateHeader)
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(lblBirthDate)))
                        .addContainerGap(150, Short.MAX_VALUE))
                );
        gl_panelUserInfoLabels.setVerticalGroup(
                gl_panelUserInfoLabels.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelUserInfoLabels.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNicknameHeader)
                                .addComponent(lblNickname))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNameHeader)
                                .addComponent(lblName))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblSurnameHeader)
                                .addComponent(lblSurname))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblEmailHeader)
                                .addComponent(lblEmail))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panelUserInfoLabels.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblBirthDateHeader)
                                .addComponent(lblBirthDate))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        panelUserInfoLabels.setLayout(gl_panelUserInfoLabels);
        panelUserInfo.setLayout(gl_panelUserInfo);
        panelUserInfoLabel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblUserInfo = new JLabel("Información de usuario:");
        lblUserInfo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelUserInfoLabel.add(lblUserInfo);
        panelUserInfoExt.setLayout(gl_panelUserInfoExt);

        JPanel panelUsersTitle = new JPanel();

        JPanel panelUsersCombo = new JPanel();
        GroupLayout gl_panelUsersExt = new GroupLayout(panelUsersExt);
        gl_panelUsersExt.setHorizontalGroup(
                gl_panelUsersExt.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panelUsersExt.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelUsersExt.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panelUsersCombo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(panelUsersTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelUsersExt.setVerticalGroup(
                gl_panelUsersExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelUsersExt.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelUsersTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelUsersCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(79))
                );
        panelUsersCombo.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblUsers = new JLabel("Seleccione un usuario:");
        lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
        panelUsersCombo.add(lblUsers);

        comboBoxUsers = new JComboBox();
        comboBoxUsers.addItem("-");
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> comboBoxUsers.addItem(userDT));

        comboBoxUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = comboBoxUsers.getSelectedItem().toString();
                if (!nickname.equals("-")) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    UserDT userDT = (UserDT) comboBoxUsers.getSelectedItem();
                    lblNickname.setText(userDT.getNickname());
                    lblName.setText(userDT.getName());
                    lblSurname.setText(userDT.getSurname());
                    lblBirthDate.setText(userDT.getBirthdate().format(dateTimeFormatter));
                    lblEmail.setText(userDT.getMail());

                    ImageIcon imgIcon = new ImageIcon(userDT.getAvatar());
                    Image image = imgIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    imgIcon = new ImageIcon(image);
                    lblAvatar.setIcon(imgIcon);

                    String[] followedNames = userDT.getFollowed()
                            .toArray(new String[0]);
                    listFollowing.setListData(followedNames);


                    String[] followersNames = userDT.getFollowers()
                            .toArray(new String[0]);
                    listFollowers.setListData(followersNames);

                    ChannelDT channelDT = userDT.getChannel();
                    lblChannelId.setText(String.valueOf(channelDT.getChannelId()));
                    lblChannelName.setText(channelDT.getName());
                    lblChannelDescription.setText(channelDT.getDescription());
                    if (channelDT.isPrivateChannel()) {
                        lblChannelPrivate.setText("Si");
                    } else {
                        lblChannelPrivate.setText("No");
                    }

                    userVideos = channelDT.getVideos();
                    if (userVideos != null) {
                        String[] videoNames = userVideos.stream()
                                .map(videoDT -> videoDT.getTitle())
                                .collect(Collectors.toList())
                                .toArray(new String[0]);

                        listVideos.setListData(videoNames);
                    }

                    List<String> playlistDTs = channelDT.getPlaylists();
                    if (playlistDTs != null) {
                        String[] playlistNames = playlistDTs.toArray(new String[0]);

                        listPlaylists.setListData(playlistNames);
                    }
                } else {
                    lblNickname.setText("");
                    lblName.setText("");
                    lblSurname.setText("");
                    lblBirthDate.setText("");
                    lblEmail.setText("");
                    try {
                        InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
                        byte[] defAvatarBytes = new byte[defAvatarStream.available()];
                        defAvatarStream.read(defAvatarBytes);
                        ImageIcon imgIcon = new ImageIcon(defAvatarBytes);
                        lblAvatar.setIcon(imgIcon);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    lblChannelId.setText("");
                    lblChannelName.setText("");
                    lblChannelDescription.setText("");
                    lblChannelPrivate.setText("");
                }
            }
        });
        panelUsersCombo.add(comboBoxUsers);
        panelUsersTitle.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblUsersTitle = new JLabel("Usuarios existentes:");
        lblUsersTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsersTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelUsersTitle.add(lblUsersTitle);
        panelUsersExt.setLayout(gl_panelUsersExt);
        getContentPane().setLayout(groupLayout);
    }
}
