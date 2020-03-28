package com.main.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.main.logic.dao.ChannelDAO;
import com.main.logic.dao.PlaylistDAO;
import com.main.logic.dao.UserDAO;
import com.main.logic.dao.VideoDAO;
import com.main.logic.dts.ChannelDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeletedUsersInfoJInternalFrame extends JInternalFrame {

    public DeletedUsersInfoJInternalFrame() {
        setBounds(100, 100, 1050, 581);
        
        JPanel panelUserInfoExt = new JPanel();
        panelUserInfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        
        JPanel panelUserInfoWithAvatar = new JPanel();
        
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
        
        JPanel panelUserInfo = new JPanel();
        
        JLabel lblNicknameHeader = new JLabel("Nickname:");
        
        JLabel lblNickname = new JLabel("");
        
        JLabel lblNameHeader = new JLabel("Nombre:");
        
        JLabel lblName = new JLabel("");
        
        JLabel lblSurnameHeader = new JLabel("Apellido:");
        
        JLabel lblSurname = new JLabel("");
        
        JLabel lblEmailHeader = new JLabel("E-mail:");
        
        JLabel lblEmail = new JLabel("");
        
        JLabel lblBirthdateHeader = new JLabel("Fecha de nacimiento:");
        
        JLabel lblBirthdate = new JLabel("");
        GroupLayout gl_panelUserInfo = new GroupLayout(panelUserInfo);
        gl_panelUserInfo.setHorizontalGroup(
            gl_panelUserInfo.createParallelGroup(Alignment.LEADING)
                .addGap(0, 471, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelUserInfo.createSequentialGroup()
                            .addComponent(lblNicknameHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblNickname))
                        .addGroup(gl_panelUserInfo.createSequentialGroup()
                            .addComponent(lblNameHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblName))
                        .addGroup(gl_panelUserInfo.createSequentialGroup()
                            .addComponent(lblSurnameHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblSurname))
                        .addGroup(gl_panelUserInfo.createSequentialGroup()
                            .addComponent(lblEmailHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblEmail))
                        .addGroup(gl_panelUserInfo.createSequentialGroup()
                            .addComponent(lblBirthdateHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblBirthdate)))
                    .addContainerGap(326, Short.MAX_VALUE))
        );
        gl_panelUserInfo.setVerticalGroup(
            gl_panelUserInfo.createParallelGroup(Alignment.LEADING)
                .addGap(0, 116, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNicknameHeader)
                        .addComponent(lblNickname))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNameHeader)
                        .addComponent(lblName))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSurnameHeader)
                        .addComponent(lblSurname))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEmailHeader)
                        .addComponent(lblEmail))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelUserInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBirthdateHeader)
                        .addComponent(lblBirthdate))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUserInfo.setLayout(gl_panelUserInfo);
        GroupLayout gl_panelUserInfoWithAvatar = new GroupLayout(panelUserInfoWithAvatar);
        gl_panelUserInfoWithAvatar.setHorizontalGroup(
            gl_panelUserInfoWithAvatar.createParallelGroup(Alignment.LEADING)
                .addGap(0, 489, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfoWithAvatar.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAvatar)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelUserInfo, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panelUserInfoWithAvatar.setVerticalGroup(
            gl_panelUserInfoWithAvatar.createParallelGroup(Alignment.LEADING)
                .addGap(0, 128, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfoWithAvatar.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelUserInfoWithAvatar.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelUserInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAvatar, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addContainerGap())
        );
        panelUserInfoWithAvatar.setLayout(gl_panelUserInfoWithAvatar);
        
        JPanel panelUserInfoLabel = new JPanel();
        panelUserInfoLabel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblUserInfo = new JLabel("Información de usuario:");
        lblUserInfo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelUserInfoLabel.add(lblUserInfo);
        GroupLayout gl_panelUserInfoExt = new GroupLayout(panelUserInfoExt);
        gl_panelUserInfoExt.setHorizontalGroup(
            gl_panelUserInfoExt.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 503, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelUserInfoExt.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelUserInfoWithAvatar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(panelUserInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelUserInfoExt.setVerticalGroup(
            gl_panelUserInfoExt.createParallelGroup(Alignment.LEADING)
                .addGap(0, 195, Short.MAX_VALUE)
                .addGroup(gl_panelUserInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelUserInfoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelUserInfoWithAvatar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        panelUserInfoExt.setLayout(gl_panelUserInfoExt);
        
        JPanel panelChannelnfoExt = new JPanel();
        panelChannelnfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        
        JPanel panelChannelInfo = new JPanel();
        
        JLabel lblChannelIdHeader = new JLabel("Id:");
        
        JLabel lblChannelId = new JLabel("");
        
        JLabel lblChannelNameHeader = new JLabel("Nombre:");
        
        JLabel lblChannelName = new JLabel("");
        
        JLabel lblChannelDescriptionHeader = new JLabel("Descripción:");
        
        JLabel lblChannelDescription = new JLabel("");
        
        JLabel lblPrivateHeader = new JLabel("Privado:");
        
        JLabel lblChannelPrivate = new JLabel("");
        GroupLayout gl_panelChannelInfo = new GroupLayout(panelChannelInfo);
        gl_panelChannelInfo.setHorizontalGroup(
            gl_panelChannelInfo.createParallelGroup(Alignment.LEADING)
                .addGap(0, 493, Short.MAX_VALUE)
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
                            .addComponent(lblPrivateHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblChannelPrivate)))
                    .addContainerGap(402, Short.MAX_VALUE))
        );
        gl_panelChannelInfo.setVerticalGroup(
            gl_panelChannelInfo.createParallelGroup(Alignment.LEADING)
                .addGap(0, 156, Short.MAX_VALUE)
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
                        .addComponent(lblPrivateHeader)
                        .addComponent(lblChannelPrivate))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        panelChannelInfo.setLayout(gl_panelChannelInfo);
        
        JPanel panelChannelInfoLabel = new JPanel();
        panelChannelInfoLabel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblChannelinfo = new JLabel("Información del canal:");
        lblChannelinfo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelChannelInfoLabel.add(lblChannelinfo);
        GroupLayout gl_panelChannelnfoExt = new GroupLayout(panelChannelnfoExt);
        gl_panelChannelnfoExt.setHorizontalGroup(
            gl_panelChannelnfoExt.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 507, Short.MAX_VALUE)
                .addGroup(gl_panelChannelnfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelChannelnfoExt.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelChannelInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                        .addComponent(panelChannelInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelChannelnfoExt.setVerticalGroup(
            gl_panelChannelnfoExt.createParallelGroup(Alignment.LEADING)
                .addGap(0, 195, Short.MAX_VALUE)
                .addGroup(gl_panelChannelnfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelChannelInfoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelChannelInfo, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panelChannelnfoExt.setLayout(gl_panelChannelnfoExt);
        
        JPanel panelDeletedUsersExt = new JPanel();
        panelDeletedUsersExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        
        JPanel panelDeletedUsersCombo = new JPanel();
        panelDeletedUsersCombo.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblDeletedUsersCombo = new JLabel("Seleccione un usuario:");
        lblDeletedUsersCombo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDeletedUsersCombo.add(lblDeletedUsersCombo);
        
        JPanel panelDeletedUsersLabel = new JPanel();
        panelDeletedUsersLabel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblDeletedUsers = new JLabel("Usuarios eliminados:");
        lblDeletedUsers.setHorizontalAlignment(SwingConstants.LEFT);
        lblDeletedUsers.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelDeletedUsersLabel.add(lblDeletedUsers);
        GroupLayout gl_panelDeletedUsersExt = new GroupLayout(panelDeletedUsersExt);
        gl_panelDeletedUsersExt.setHorizontalGroup(
            gl_panelDeletedUsersExt.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 1016, Short.MAX_VALUE)
                .addGroup(gl_panelDeletedUsersExt.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelDeletedUsersExt.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelDeletedUsersCombo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                        .addComponent(panelDeletedUsersLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelDeletedUsersExt.setVerticalGroup(
            gl_panelDeletedUsersExt.createParallelGroup(Alignment.LEADING)
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(gl_panelDeletedUsersExt.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDeletedUsersLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panelDeletedUsersCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(79))
        );
        panelDeletedUsersExt.setLayout(gl_panelDeletedUsersExt);
        
        JPanel panelVideoInfoExt = new JPanel();
        panelVideoInfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        
        JPanel panelVideoInfo = new JPanel();
        
        JLabel lblVideoIdHeader = new JLabel("Id:");
        
        JLabel lblVideoId = new JLabel("");
        
        JLabel lblVideoNameHeader = new JLabel("Nombre:");
        
        JLabel lblVideoName = new JLabel("");
        
        JLabel lblVideoDescriptionHeader = new JLabel("Descripción:");
        
        JLabel lblVideoDescription = new JLabel("");
        
        JLabel lblVideoDurationHeader = new JLabel("Duración:");
        
        JLabel lblVideoDuration = new JLabel("");
        
        JLabel lblVideoDateHeader = new JLabel("Fecha publicación:");
        
        JLabel lblVideoDate = new JLabel("");
        
        JLabel lblVideoUrlHeader = new JLabel("URL:");
        
        JLabel lblVideoUrl = new JLabel("");
        
        JLabel lblVideoPrivateHeader = new JLabel("Privado:");
        
        JLabel lblVideoPrivate = new JLabel("");
        GroupLayout gl_panelVideoInfo = new GroupLayout(panelVideoInfo);
        gl_panelVideoInfo.setHorizontalGroup(
            gl_panelVideoInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelVideoInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoIdHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoId))
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoNameHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoName))
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoDescriptionHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoDescription))
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addGap(67)
                            .addComponent(lblVideoDuration))
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoDateHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoDate))
                        .addComponent(lblVideoDurationHeader)
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoUrlHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoUrl))
                        .addGroup(gl_panelVideoInfo.createSequentialGroup()
                            .addComponent(lblVideoPrivateHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblVideoPrivate)))
                    .addContainerGap(338, Short.MAX_VALUE))
        );
        gl_panelVideoInfo.setVerticalGroup(
            gl_panelVideoInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelVideoInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblVideoIdHeader)
                        .addComponent(lblVideoId))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblVideoNameHeader)
                        .addComponent(lblVideoName))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblVideoDescriptionHeader)
                        .addComponent(lblVideoDescription))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblVideoDuration)
                        .addComponent(lblVideoDurationHeader))
                    .addGap(12)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblVideoDate)
                        .addComponent(lblVideoDateHeader))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblVideoUrlHeader)
                        .addComponent(lblVideoUrl))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelVideoInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblVideoPrivateHeader)
                        .addComponent(lblVideoPrivate))
                    .addContainerGap(98, Short.MAX_VALUE))
        );
        panelVideoInfo.setLayout(gl_panelVideoInfo);
        
        JPanel panelVideosInfoHeader = new JPanel();
        panelVideosInfoHeader.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblVideosInfoHeader = new JLabel("Información de videos:");
        lblVideosInfoHeader.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelVideosInfoHeader.add(lblVideosInfoHeader);
        
        JPanel panelVideosCombo = new JPanel();
        panelVideosCombo.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblSelectVideo = new JLabel("Seleccione un video:");
        lblSelectVideo.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideosCombo.add(lblSelectVideo);
        
        JComboBox comboBoxDeletedVideos = new JComboBox();
        comboBoxDeletedVideos.addItem("-");
        panelVideosCombo.add(comboBoxDeletedVideos);
        GroupLayout gl_panelVideoInfoExt = new GroupLayout(panelVideoInfoExt);
        gl_panelVideoInfoExt.setHorizontalGroup(
            gl_panelVideoInfoExt.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelVideoInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelVideoInfoExt.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelVideosInfoHeader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(panelVideosCombo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(panelVideoInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelVideoInfoExt.setVerticalGroup(
            gl_panelVideoInfoExt.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelVideoInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelVideosInfoHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelVideosCombo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelVideoInfo, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panelVideoInfoExt.setLayout(gl_panelVideoInfoExt);
        
        JPanel panelPlaylistInfoExt = new JPanel();
        panelPlaylistInfoExt.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        
        JPanel panelPlaylistInfo = new JPanel();
        
        JLabel lblPlaylistIdHeader = new JLabel("Id:");
        
        JLabel lblPlaylistNameHeader = new JLabel("Nombre:");
        
        JLabel lblPlaylistDefaultHeader = new JLabel("Por defecto:");
        
        JLabel lblPlaylistPrivateHeader = new JLabel("Privado:");
        
        JLabel lblPlaylistId = new JLabel("");
        
        JLabel lblPlaylistName = new JLabel("");
        
        JLabel lblPlaylistDefault = new JLabel("");
        
        JLabel lblPlaylistPrivate = new JLabel("");

        GroupLayout gl_panelPlaylistInfo = new GroupLayout(panelPlaylistInfo);
        gl_panelPlaylistInfo.setHorizontalGroup(
            gl_panelPlaylistInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelPlaylistInfo.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                            .addComponent(lblPlaylistIdHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblPlaylistId))
                        .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                            .addComponent(lblPlaylistNameHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblPlaylistName))
                        .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                            .addComponent(lblPlaylistPrivateHeader, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblPlaylistPrivate))
                        .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                            .addComponent(lblPlaylistDefaultHeader)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblPlaylistDefault)))
                    .addContainerGap(364, Short.MAX_VALUE))
        );
        gl_panelPlaylistInfo.setVerticalGroup(
            gl_panelPlaylistInfo.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelPlaylistInfo.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelPlaylistInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPlaylistIdHeader)
                        .addComponent(lblPlaylistId))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelPlaylistInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPlaylistNameHeader)
                        .addComponent(lblPlaylistName))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelPlaylistInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPlaylistDefaultHeader)
                        .addComponent(lblPlaylistDefault))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelPlaylistInfo.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPlaylistPrivateHeader)
                        .addComponent(lblPlaylistPrivate))
                    .addContainerGap(93, Short.MAX_VALUE))
        );
        panelPlaylistInfo.setLayout(gl_panelPlaylistInfo);
        
        JPanel panelPlaylistInfoHeader = new JPanel();
        panelPlaylistInfoHeader.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lblPanelInfoHeader = new JLabel("Información de playlists:");
        lblPanelInfoHeader.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        panelPlaylistInfoHeader.add(lblPanelInfoHeader);
        
        JPanel panelPlaylistsCombo = new JPanel();
        panelPlaylistsCombo.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblSelectPlaylist = new JLabel("Seleccione una playlist:");
        lblSelectPlaylist.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlaylistsCombo.add(lblSelectPlaylist);
        
        GroupLayout gl_panelPlaylistInfoExt = new GroupLayout(panelPlaylistInfoExt);
        gl_panelPlaylistInfoExt.setHorizontalGroup(
            gl_panelPlaylistInfoExt.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 503, Short.MAX_VALUE)
                .addGroup(gl_panelPlaylistInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelPlaylistInfoExt.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panelPlaylistInfoHeader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(panelPlaylistsCombo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                        .addComponent(panelPlaylistInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelPlaylistInfoExt.setVerticalGroup(
            gl_panelPlaylistInfoExt.createParallelGroup(Alignment.LEADING)
                .addGap(0, 249, Short.MAX_VALUE)
                .addGroup(gl_panelPlaylistInfoExt.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPlaylistInfoHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelPlaylistsCombo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelPlaylistInfo, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panelPlaylistInfoExt.setLayout(gl_panelPlaylistInfoExt);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(panelDeletedUsersExt, GroupLayout.PREFERRED_SIZE, 1016, GroupLayout.PREFERRED_SIZE)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(panelUserInfoExt, 0, 0, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(panelChannelnfoExt, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
                            .addGap(4))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(panelVideoInfoExt, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(panelPlaylistInfoExt, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelDeletedUsersExt, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(panelChannelnfoExt, 0, 0, Short.MAX_VALUE)
                        .addComponent(panelUserInfoExt, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelPlaylistInfoExt, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                        .addComponent(panelVideoInfoExt, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                    .addContainerGap())
        );
        getContentPane().setLayout(groupLayout);

        JComboBox comboBoxDeletedPlaylists = new JComboBox();
        
        comboBoxDeletedPlaylists.addItem("-");
        panelPlaylistsCombo.add(comboBoxDeletedPlaylists);
        
        JComboBox comboBoxDeletedUsers = new JComboBox();
        
        comboBoxDeletedUsers.addItem("-");
        List<UserDT> deletedUsers = UserDAO.getDeletedUsersNicknames();
        deletedUsers.forEach(deletedUser -> comboBoxDeletedUsers.addItem(String.format("%s-%s", deletedUser.getNickname(), deletedUser.getId())));

        comboBoxDeletedUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickId = comboBoxDeletedUsers.getSelectedItem().toString();
                if (!nickId.equals("-")) {
                    Long id = Long.valueOf(nickId.substring(nickId.lastIndexOf('-') + 1));
                    String nickname = nickId.substring(0, nickId.lastIndexOf('-'));
                    
                    UserDT deletedUser = UserDAO.getDeletedUserInfo(nickname, id);

                    ImageIcon imgIcon = new ImageIcon(deletedUser.getAvatar());
                    lblAvatar.setIcon(imgIcon);

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    lblNickname.setText(deletedUser.getNickname());
                    lblName.setText(deletedUser.getName());
                    lblSurname.setText(deletedUser.getSurname());
                    lblBirthdate.setText(deletedUser.getBirthdate().format(dateTimeFormatter));
                    lblEmail.setText(deletedUser.getMail());

                    ChannelDT channelDT = ChannelDAO.getDeletedChannelInfo(id);
                    lblChannelId.setText(channelDT.getChannelId().toString());
                    lblChannelName.setText(channelDT.getName());
                    lblChannelDescription.setText(channelDT.getDescription());
                    if (channelDT.isPrivateChannel()) {
                        lblChannelPrivate.setText("Si");
                    } else {
                        lblChannelPrivate.setText("No");
                    }
                    
                    comboBoxDeletedVideos.removeAllItems();
                    comboBoxDeletedVideos.addItem("-");
                    
                    List<VideoDT> videos = ChannelDAO.getDeletedVideosFromChannel(channelDT.getChannelId());
                    videos.forEach(video -> {comboBoxDeletedVideos.addItem(String.format("%s-%s", video.getTitle(), video.getVideoId()));});
                    
                    lblVideosInfoHeader.setText("Información de videos (del canal):");
                    
                    comboBoxDeletedPlaylists.removeAllItems();
                    comboBoxDeletedPlaylists.addItem("-");
                    
                    List<PlaylistDT> playlists = ChannelDAO.getDeletedPlaylistFromChannel(channelDT.getChannelId());
                    playlists.forEach(playlist -> {comboBoxDeletedPlaylists.addItem(String.format("%s-%s", playlist.getName(), playlist.getPlaylistId()));});
                } else {
                    lblNickname.setText("");
                    lblName.setText("");
                    lblSurname.setText("");
                    lblBirthdate.setText("");
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
                    
                    lblVideoDate.setText("");
                    lblVideoDescription.setText("");
                    lblVideoDuration.setText("");
                    lblVideoId.setText("");
                    lblVideoName.setText("");
                    lblVideoPrivate.setText("");
                    lblVideoUrl.setText("");
                    
                    lblPlaylistDefault.setText("");
                    lblPlaylistId.setText("");
                    lblPlaylistName.setText("");
                    lblPlaylistPrivate.setText("");
                    
                    comboBoxDeletedVideos.removeAllItems();
                    comboBoxDeletedVideos.addItem("-");
                    
                    comboBoxDeletedPlaylists.removeAllItems();
                    comboBoxDeletedPlaylists.addItem("-");
                    
                    lblVideosInfoHeader.setText("Información de videos:");
                }
            }
        });
        panelDeletedUsersCombo.add(comboBoxDeletedUsers);
        
        comboBoxDeletedVideos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxDeletedVideos.getItemCount() > 0) {
                    String nameId = comboBoxDeletedVideos.getSelectedItem().toString();
                    if (!nameId.equals("-")) {
                        Long id = Long.valueOf(nameId.substring(nameId.lastIndexOf('-') + 1));
                        
                        VideoDT video = VideoDAO.getDeletedVideoInfo(id);

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        lblVideoDate.setText(video.getUploadDate().format(dateTimeFormatter));
                        lblVideoDescription.setText(
                                video.getDescription()
                                .substring(0, video.getDescription().length() > 45 ? 45 : video.getDescription().length()) 
                                + (video.getDescription().length() > 45 ? "..." : "")
                                );
                        lblVideoDuration.setText(String.valueOf(video.getLength()));
                        lblVideoId.setText(video.getVideoId().toString());
                        lblVideoName.setText(
                                video.getTitle()
                                .substring(0, video.getTitle().length() - 1 > 45 ? 45 : video.getTitle().length()) 
                                + (video.getTitle().length() > 45 ? "..." : "")
                                );
                        lblVideoUrl.setText(video.getUrl());

                        if (video.isPrivate()) {
                            lblVideoPrivate.setText("Si");
                        } else {
                            lblVideoPrivate.setText("No");
                        }
                        
                    } else {
                        lblVideoDate.setText("");
                        lblVideoDescription.setText("");
                        lblVideoDuration.setText("");
                        lblVideoId.setText("");
                        lblVideoName.setText("");
                        lblVideoPrivate.setText("");
                        lblVideoUrl.setText("");
                    }
                }
            }
        });
        
        comboBoxDeletedPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxDeletedPlaylists.getItemCount() > 0) {
                    String nameId = comboBoxDeletedPlaylists.getSelectedItem().toString();
                    if (!nameId.equals("-")) {
                        Long id = Long.valueOf(nameId.substring(nameId.lastIndexOf('-') + 1));

                        PlaylistDT playlistDT = PlaylistDAO.getDeletedPlaylistInfo(id);
                        
                        lblPlaylistId.setText(playlistDT.getPlaylistId().toString());
                        lblPlaylistName.setText(playlistDT.getName());
                        
                        if (playlistDT.isPrivate()) {
                            lblPlaylistPrivate.setText("Si");
                        } else {
                            lblPlaylistPrivate.setText("No");
                        }
                        
                        if (playlistDT.isDefaultPlaylist()) {
                            lblPlaylistDefault.setText("Si");
                        } else {
                            lblPlaylistDefault.setText("No");
                        }
                        
                        comboBoxDeletedVideos.removeAllItems();
                        comboBoxDeletedVideos.addItem("-");
                        
                        List<VideoDT> videos = PlaylistDAO.getDeletedVideosFromPlaylist(playlistDT.getPlaylistId());
                        videos.forEach(video -> {comboBoxDeletedVideos.addItem(String.format("%s-%s", video.getTitle(), video.getVideoId()));});
                        
                        lblVideosInfoHeader.setText("Información de videos (de la playlist):");
                        
                    } else {
                        lblVideoDate.setText("");
                        lblVideoDescription.setText("");
                        lblVideoDuration.setText("");
                        lblVideoId.setText("");
                        lblVideoName.setText("");
                        lblVideoPrivate.setText("");
                        lblVideoUrl.setText("");
                        
                        lblPlaylistDefault.setText("");
                        lblPlaylistId.setText("");
                        lblPlaylistName.setText("");
                        lblPlaylistPrivate.setText("");
                        
                        String nickId = comboBoxDeletedUsers.getSelectedItem().toString();
                        if (!nickId.equals("-")) {
                            Long id = Long.valueOf(nickId.substring(nickId.lastIndexOf('-') + 1));
                            
                            comboBoxDeletedVideos.removeAllItems();
                            comboBoxDeletedVideos.addItem("-");
                            
                            List<VideoDT> videos = ChannelDAO.getDeletedVideosFromChannel(id);
                            videos.forEach(video -> {comboBoxDeletedVideos.addItem(String.format("%s-%s", video.getTitle(), video.getVideoId()));});
                            
                            lblVideosInfoHeader.setText("Información de videos (del canal):");
                        } else {
                            comboBoxDeletedVideos.removeAllItems();
                            comboBoxDeletedVideos.addItem("-");
                            
                            lblVideosInfoHeader.setText("Información de videos:");
                        }
                    }
                }
            }
        });
    }
}
