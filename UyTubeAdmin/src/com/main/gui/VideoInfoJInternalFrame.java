package com.main.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.main.gui.utils.TreeUtils;
import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.IUserController;

public class VideoInfoJInternalFrame extends JInternalFrame {

    static final String EMPTY_COMBO_BOX_ITEM = "-";
    static final VideoDT EMPTY_VIDEO = new VideoDT();

    private JComboBox videosComboBox;
    private JComboBox usersComboBox;
    private JPanel usernamePanel;
    private GroupLayout groupLayout;
    private JPanel idPanel;
    private JPanel titlePanel;
    private JPanel urlPanel;
    private JPanel isPrivatePanel;
    private JPanel descriptionPanel;
    private JPanel uploadDatePanel;
    private JPanel durationPanel;
    private JPanel categoryPanel;
    private JPanel commentsPanel;
    private GroupLayout gl_videoInfoPanel;
    private JLabel commentsLabel;
    private JLabel videoId;
    private JLabel usernameLabel;
    private JLabel videosLabel;
    private JLabel idLabel;
    private JLabel videoTitle;
    private JLabel titleLabel;
    private JLabel videoUrl;
    private JLabel urlLabel;
    private JCheckBox isPrivateCheckBox;
    private JLabel videoDescription;
    private JLabel descriptionLabel;
    private JLabel videoUploadDate;
    private JLabel uploadDateLabel;
    private JLabel videoDuration;
    private JLabel lengthLabel;
    private JLabel videoCategory;
    private JLabel categoryLabel;
    private JScrollPane treeScrollPane;
    private JPanel videosPanel;
    private JPanel videoInfoPanel;

    /**
     * Create the frame.
     */
    public VideoInfoJInternalFrame() {
        initiGUI();
        videosComboBox.addItem(EMPTY_VIDEO);
        loadUsersToComboBox();
        addVideosComboBoxListener();
        addUsersComboBoxListener();
    }

    public VideoInfoJInternalFrame(VideoDT videoDT, UserDT userDT) {
        initiGUI();
        usersComboBox.addItem(userDT);
        videosComboBox.addItem(videoDT);

        usersComboBox.setEnabled(false);
        videosComboBox.setEnabled(false);

        loadVideoInfo();
    }

    private void initiGUI() {
        setTitle("Consulta de Video");
        setBounds(100, 100, 620, 450);

        usernamePanel = new JPanel();

        videosPanel = new JPanel();

        videoInfoPanel = new JPanel();
        videoInfoPanel.setVisible(false);
        groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(videosPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
                                .addComponent(usernamePanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
                                .addComponent(videoInfoPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usernamePanel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(videosPanel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(videoInfoPanel, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addGap(116))
                );

        idPanel = new JPanel();

        titlePanel = new JPanel();

        urlPanel = new JPanel();

        isPrivatePanel = new JPanel();

        descriptionPanel = new JPanel();

        uploadDatePanel = new JPanel();

        durationPanel = new JPanel();

        categoryPanel = new JPanel();

        commentsPanel = new JPanel();
        gl_videoInfoPanel = new GroupLayout(videoInfoPanel);
        gl_videoInfoPanel.setHorizontalGroup(
                gl_videoInfoPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_videoInfoPanel.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_videoInfoPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(idPanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(isPrivatePanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(urlPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(descriptionPanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(uploadDatePanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(durationPanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(categoryPanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                .addComponent(commentsPanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_videoInfoPanel.setVerticalGroup(
                gl_videoInfoPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_videoInfoPanel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(idPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(urlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(isPrivatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(descriptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(uploadDatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(durationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(categoryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(commentsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(101, Short.MAX_VALUE))
                );
        commentsPanel.setLayout(new GridLayout(0, 2, 0, 0));

        commentsLabel = new JLabel("Comentarios:");
        commentsLabel.setVerticalAlignment(SwingConstants.TOP);
        commentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        commentsPanel.add(commentsLabel);

        treeScrollPane = new JScrollPane();
        commentsPanel.add(treeScrollPane);
        categoryPanel.setLayout(new GridLayout(0, 2, 0, 0));

        categoryLabel = new JLabel("Categoría:");
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryPanel.add(categoryLabel);

        videoCategory = new JLabel();
        categoryPanel.add(videoCategory);
        durationPanel.setLayout(new GridLayout(0, 2, 0, 0));

        lengthLabel = new JLabel("Duración:");
        lengthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        durationPanel.add(lengthLabel);

        videoDuration = new JLabel();
        durationPanel.add(videoDuration);
        uploadDatePanel.setLayout(new GridLayout(0, 2, 0, 0));

        uploadDateLabel = new JLabel("Fecha de subida:");
        uploadDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        uploadDatePanel.add(uploadDateLabel);

        videoUploadDate = new JLabel();
        uploadDatePanel.add(videoUploadDate);
        descriptionPanel.setLayout(new GridLayout(0, 2, 0, 0));

        descriptionLabel = new JLabel("Descripción:");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionPanel.add(descriptionLabel);

        videoDescription = new JLabel();
        descriptionPanel.add(videoDescription);

        isPrivateCheckBox = new JCheckBox("Privado");
        isPrivateCheckBox.setEnabled(false);
        isPrivatePanel.add(isPrivateCheckBox);
        urlPanel.setLayout(new GridLayout(0, 2, 0, 0));

        urlLabel = new JLabel("Url:");
        urlLabel.setHorizontalAlignment(SwingConstants.CENTER);
        urlPanel.add(urlLabel);

        videoUrl = new JLabel();
        urlPanel.add(videoUrl);
        titlePanel.setLayout(new GridLayout(0, 2, 0, 0));

        titleLabel = new JLabel("Título:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);

        videoTitle = new JLabel();
        titlePanel.add(videoTitle);
        idPanel.setLayout(new GridLayout(0, 2, 0, 0));

        idLabel = new JLabel("ID:");
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idPanel.add(idLabel);

        videoId = new JLabel();
        idPanel.add(videoId);
        videoInfoPanel.setLayout(gl_videoInfoPanel);
        videosPanel.setLayout(new GridLayout(0, 2, 0, 0));

        videosLabel = new JLabel("Videos:");
        videosPanel.add(videosLabel);
        videosLabel.setHorizontalAlignment(SwingConstants.CENTER);

        usernameLabel = new JLabel("Usuario:");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernamePanel.add(usernameLabel);

        videosComboBox = new JComboBox();

        usersComboBox = new JComboBox();
        usernamePanel.add(usersComboBox);
        getContentPane().setLayout(groupLayout);

        videosPanel.add(videosComboBox);
        usernamePanel.setLayout(new GridLayout(0, 2, 0, 0));
    }

    private void loadUsersToComboBox() {
        usersComboBox.addItem(EMPTY_COMBO_BOX_ITEM);
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> usersComboBox.addItem(userDT));
    }

    private void addUsersComboBoxListener() {
        usersComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                for (int i = 1; i < videosComboBox.getItemCount(); ) {
                    videosComboBox.removeItemAt(i);
                }
                if (!(EMPTY_COMBO_BOX_ITEM).equals(usersComboBox.getSelectedItem().toString())) {
                    String nick = usersComboBox.getSelectedItem().toString();
                    IUserController userController = ControllerFactory.getInstance().getUserController();
                    try {
                        UserDT user = userController.getUserData(nick);
                        List<VideoDT> videos = user.getChannel().getVideos();

                        videos.forEach(video -> videosComboBox.addItem(video));

                    } catch (EntityNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    private void addVideosComboBoxListener() {
        videosComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!Objects.equals(EMPTY_COMBO_BOX_ITEM, ((VideoDT) videosComboBox.getSelectedItem()).toString())) {
                    loadVideoInfo();
                } else {
                    setSize(new Dimension(620, 150));
                    videoInfoPanel.setVisible(false);
                }
            }
        });
    }

    private void loadVideoInfo() {
        VideoDT selectedVideo = (VideoDT) videosComboBox.getSelectedItem();

        videoId.setText(Long.toString(selectedVideo.getVideoId()));
        videoTitle.setText(selectedVideo.getTitle());
        videoUrl.setText(selectedVideo.getUrl());
        isPrivateCheckBox.setSelected(selectedVideo.isPrivate());
        videoDescription.setText(selectedVideo.getDescription());
        videoUploadDate.setText(selectedVideo.getUploadDate().toString());
        videoDuration.setText(Integer.toString(selectedVideo.getLength()));
        if (selectedVideo.getCategory() != null) {
            videoCategory.setText(selectedVideo.getCategory().getName());
        } else {
            videoCategory.setText("Sin categoria");
        }
        JTree commentScroll = TreeUtils.createScrollableTree(selectedVideo);
        treeScrollPane.setViewportView(commentScroll);
        treeScrollPane.setSize(new Dimension(100, 100));
        treeScrollPane.setMinimumSize(new Dimension(100, 100));
        treeScrollPane.setMaximumSize(new Dimension(100, 100));
        treeScrollPane.setPreferredSize(new Dimension(100, 100));
        setSize(new Dimension(620, 450));
        videoInfoPanel.setVisible(true);
    }
}
