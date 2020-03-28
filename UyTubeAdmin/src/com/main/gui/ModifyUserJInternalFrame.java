package com.main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.ChannelDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.interfaces.IUserController;
import com.main.logic.utils.DateUtils;

public class ModifyUserJInternalFrame extends JInternalFrame {
    private JTextField NameTextField;
    private JTextField LastNameTextField;
    private JTextField DescriptionTextField;
    private File file;
    private JTextField ChannelNameTextField;
    private JButton confirmButton;
    private JComboBox<UserDT> comboBoxUser;
    private JComboBox<VideoDT> comboBoxVideos;
    private JComboBox<String> comboBoxPlaylists;
    private JButton btnModifyPlaylist;
    private JButton btnModifyVideo;
    private JCheckBox PrivateCheckBox;
    private JSpinner YearSpinner;
    private JSpinner MonthSpinner;
    private JSpinner DaySpinner;
    private JLabel actualEmail;
    private JLabel ShowAvatarLabel;
    final JInternalFrame fileChooserFrame = new JInternalFrame("Hello");


    public ModifyUserJInternalFrame(JFrame frame) {
        IUserController userController = ControllerFactory.getInstance().getUserController();

        initialize();
        cleanAllFields();
        List<UserDT> users = userController.listUsers();
        UserDT empty = new UserDT();
        comboBoxUser.addItem(empty);
        users.forEach(u -> comboBoxUser.addItem(u));

        comboBoxUser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    UserDT selected = (UserDT) comboBoxUser.getSelectedItem();

                    if (selected == empty) {
                        cleanAllFields();
                    } else {
                        loadUserInfo(selected);
                    }
                }
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDT selected = (UserDT) comboBoxUser.getSelectedItem();

                if (selected == empty) {
                    JOptionPane.showMessageDialog(getParent(), "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        userController.modifyUser(getUserInfo(selected));
                        JOptionPane.showMessageDialog(getParent(), "Usuario modificado con éxito", "Exito", JOptionPane.PLAIN_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(getParent(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnModifyPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDT userDT = (UserDT) comboBoxUser.getSelectedItem();
                if (userDT == empty) {
                    JOptionPane.showMessageDialog(getParent(), "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String playlistName = (String) comboBoxPlaylists.getSelectedItem();
                if (playlistName == "-") {
                    JOptionPane.showMessageDialog(getParent(), "Debe seleccionar un video", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                openModifyPlaylist(frame, userDT, playlistName);
            }
        });

        btnModifyVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDT userDT = (UserDT) comboBoxUser.getSelectedItem();
                if (userDT == empty) {
                    JOptionPane.showMessageDialog(getParent(), "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                VideoDT videoDT = (VideoDT) comboBoxVideos.getSelectedItem();
                if (videoDT.getTitle() == null) {
                    JOptionPane.showMessageDialog(getParent(), "Debe seleccionar una playlist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                openModifyVideo(frame, userDT.getNickname(), videoDT);
            }
        });
    }

    public void openModifyPlaylist(JFrame jFrame, UserDT userDT, String playlist) {
        ModifyPlaylistJInternalFrame internalJF = new ModifyPlaylistJInternalFrame(userDT, playlist);

        internalJF.setResizable(true);
        internalJF.setMinimumSize(new Dimension(450, 210));
        internalJF.setMaximumSize(new Dimension(470, 210));
        internalJF.setPreferredSize(new Dimension(470, 210));
        internalJF.setSize(new Dimension(470, 210));
        internalJF.setBounds(0, 0, 470, 544);
        internalJF.setVisible(true);
        internalJF.setClosable(true);
        jFrame.getContentPane().add(internalJF);
        internalJF.toFront();
        internalJF.pack();
    }

    public void openModifyVideo(JFrame jFrame, String nickname, VideoDT videoDT) {
        ModifyVideoJInternalFrame internalJF = new ModifyVideoJInternalFrame(nickname, videoDT);

        internalJF.setResizable(true);
        internalJF.setMinimumSize(new Dimension(470, 544));
        internalJF.setMaximumSize(new Dimension(470, 544));
        internalJF.setPreferredSize(new Dimension(470, 544));
        internalJF.setSize(new Dimension(470, 544));
        internalJF.setBounds(0, 0, 470, 544);
        internalJF.setVisible(true);
        internalJF.setClosable(true);
        jFrame.getContentPane().add(internalJF);
        internalJF.toFront();
        internalJF.pack();
	}
	
	public void cleanAllFields() {
	    try {
	        NameTextField.setText("");
	        LastNameTextField.setText("");
	        actualEmail.setText("");
	        ChannelNameTextField.setText("");
	        DescriptionTextField.setText("");
	        PrivateCheckBox.setSelected(false);
	        
	        YearSpinner.setValue(2018);
	        MonthSpinner.setValue(1);
	        DaySpinner.setValue(1);
	        
	        InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
	        byte[] defAvatarBytes = new byte[defAvatarStream.available()];
	        defAvatarStream.read(defAvatarBytes);
	        
	        ImageIcon imgIcon = new ImageIcon(defAvatarBytes);
	        ShowAvatarLabel.setIcon(imgIcon);
	        
	        comboBoxVideos.removeAllItems();
	        comboBoxVideos.addItem(new VideoDT());
	        comboBoxPlaylists.removeAllItems();
	        comboBoxPlaylists.addItem("-");
	    } catch (IOException e) {
            e.printStackTrace();
	    }
		
	}
	
	public void loadUserInfo(UserDT userDT) {
		NameTextField.setText(userDT.getName());
		LastNameTextField.setText(userDT.getSurname());
		actualEmail.setText(userDT.getMail());
		ChannelNameTextField.setText(userDT.getChannel().getName());
		DescriptionTextField.setText(userDT.getChannel().getDescription());
		PrivateCheckBox.setSelected(userDT.getChannel().isPrivateChannel());
		
		YearSpinner.setValue(userDT.getBirthdate().getYear());
		MonthSpinner.setValue(userDT.getBirthdate().getMonthValue());
		DaySpinner.setValue(userDT.getBirthdate().getDayOfMonth());
		
		ImageIcon imgIcon = new ImageIcon(userDT.getAvatar());
		Image image = imgIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(image);
		ShowAvatarLabel.setIcon(imgIcon);
		
		comboBoxVideos.removeAllItems();
		comboBoxVideos.addItem(new VideoDT());
		userDT.getChannel().getVideos().forEach(v -> comboBoxVideos.addItem(v));
		
		comboBoxPlaylists.removeAllItems();
		comboBoxPlaylists.addItem("-");
		userDT.getChannel().getPlaylists().forEach(pl -> comboBoxPlaylists.addItem(pl));
	}
	
	public UserDT getUserInfo(UserDT selected) throws Exception {
		UserDT userDT = new UserDT();
		userDT.setNickname(selected.getNickname());
		userDT.setMail(selected.getMail());
		
		String name = NameTextField.getText();
		String lastName = LastNameTextField.getText();
		String channelName = ChannelNameTextField.getText();
		String channelDescription = DescriptionTextField.getText();
		boolean isPrivate = PrivateCheckBox.isSelected();
		
		byte[] avatar = file != null ? Files.readAllBytes(file.toPath()) : selected.getAvatar();
		
		LocalDate birthDate = LocalDate.of((Integer) YearSpinner.getValue(), (Integer) MonthSpinner.getValue(), (Integer) DaySpinner.getValue());

		if(!name.isEmpty() && !lastName.isEmpty() && !channelDescription.isEmpty() && !channelName.isEmpty()) {
			userDT.setName(name);
			userDT.setSurname(lastName);
			userDT.setAvatar(avatar);
			userDT.setBirthdate(birthDate);
			ChannelDT channelDT = new ChannelDT();
			channelDT.setName(channelName);
			channelDT.setDescription(channelDescription);
			channelDT.setPrivateChannel(isPrivate);
			userDT.setChannel(channelDT);
		} else {
			throw new Exception("Ingrese todos los campos obligatorios");
		}
		
		return userDT;
	}
	
	public void initialize() {
		setTitle("Modificar usuario");
		getContentPane().setMaximumSize(new Dimension(300, 600));
		setBounds(100, 100, 745, 505);
		
		JPanel ConfirmBtnPanel = new JPanel();

		JPanel UserInfoPanel = new JPanel();

		JPanel ChannelInfoPanel = new JPanel();

		JPanel ShowAvatarPanel = new JPanel();
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(ChannelInfoPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
						.addComponent(ConfirmBtnPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 711, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(UserInfoPanel, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ShowAvatarPanel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(ShowAvatarPanel, 0, 0, Short.MAX_VALUE)
						.addComponent(UserInfoPanel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ChannelInfoPanel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ConfirmBtnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Videos:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		comboBoxVideos = new JComboBox<VideoDT>();
		panel_1.add(comboBoxVideos);
		
		btnModifyVideo = new JButton("Modificar");
		panel_1.add(btnModifyVideo);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Playlists:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		comboBoxPlaylists = new JComboBox<String>();
		panel.add(comboBoxPlaylists);
		
		btnModifyPlaylist = new JButton("Modificar");
		panel.add(btnModifyPlaylist);

		ShowAvatarLabel = new JLabel("");
		ShowAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgIcon = new ImageIcon(getClass().getResource("utils/emptyAvatar.png"));
		ShowAvatarLabel.setIcon(imgIcon);

		JButton UploadPictureBtn = new JButton("Subir imagen");
		GroupLayout gl_ShowAvatarPanel = new GroupLayout(ShowAvatarPanel);
		gl_ShowAvatarPanel.setHorizontalGroup(gl_ShowAvatarPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(UploadPictureBtn, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)
				.addComponent(ShowAvatarLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE));
		gl_ShowAvatarPanel.setVerticalGroup(gl_ShowAvatarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ShowAvatarPanel.createSequentialGroup()
						.addGap(29)
						.addComponent(ShowAvatarLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(UploadPictureBtn)
						.addGap(25)));
		ShowAvatarPanel.setLayout(gl_ShowAvatarPanel);

		UploadPictureBtn.addActionListener(new ActionListener() {
			// Handle open button action.
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
				int returnVal = fc.showOpenDialog(fileChooserFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();

					BufferedImage img = null;
					try {
						img = ImageIO.read(file);
					} catch (IOException e2) {
						System.out.println("Error reading image");
					}
					ImageIcon imgIcon = new ImageIcon(file.getAbsolutePath());
					Image image = imgIcon.getImage();
					Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					imgIcon = new ImageIcon(newimg);
					ShowAvatarLabel.setIcon(imgIcon);
					System.out.println(img.getHeight());
					
				} else {
					System.out.println("Open command cancelled by user.");
				}
				System.out.println(returnVal);
			}
		});

		JLabel ChannelInfoLabel = new JLabel("Información del canal:");
		ChannelInfoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		ChannelInfoLabel.setVerticalAlignment(SwingConstants.TOP);

		JPanel ChannelNamePanel = new JPanel();
		ChannelNamePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel ChannelNameLabel = new JLabel("Nombre del canal:");
		ChannelNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ChannelNamePanel.add(ChannelNameLabel);

		ChannelNameTextField = new JTextField();
		ChannelNameTextField.setColumns(10);
		ChannelNamePanel.add(ChannelNameTextField);

		JPanel DescriptionPanel = new JPanel();
		DescriptionPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel DescriptionLabel = new JLabel("Descripción:");
		DescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DescriptionPanel.add(DescriptionLabel);

		DescriptionTextField = new JTextField();
		DescriptionTextField.setColumns(10);
		DescriptionPanel.add(DescriptionTextField);

		JPanel PrivateCheckBoxPanel = new JPanel();

		PrivateCheckBox = new JCheckBox("Privado");
		PrivateCheckBoxPanel.add(PrivateCheckBox);
		PrivateCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_ChannelInfoPanel = new GroupLayout(ChannelInfoPanel);
		gl_ChannelInfoPanel.setHorizontalGroup(gl_ChannelInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChannelInfoPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_ChannelInfoPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(ChannelInfoLabel, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
								.addComponent(ChannelNamePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
								.addComponent(DescriptionPanel, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
								.addComponent(PrivateCheckBoxPanel, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
						.addContainerGap()));
		gl_ChannelInfoPanel.setVerticalGroup(gl_ChannelInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChannelInfoPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(ChannelInfoLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ChannelNamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(DescriptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(PrivateCheckBoxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));
		ChannelInfoPanel.setLayout(gl_ChannelInfoPanel);

		JPanel NicknamePanel = new JPanel();
		NicknamePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel NicknameLabel = new JLabel("Usuario:");
		NicknameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NicknamePanel.add(NicknameLabel);

		JLabel UserInfoLabel = new JLabel("Información del usuario:");
		UserInfoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		UserInfoLabel.setVerticalAlignment(SwingConstants.TOP);
		UserInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel NameLabel = new JLabel("Nombre:");
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NamePanel.add(NameLabel);

		NameTextField = new JTextField();
		NamePanel.add(NameTextField);
		NameTextField.setColumns(10);

		JPanel LastNamePanel = new JPanel();
		LastNamePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel LastNameLabel = new JLabel("Apellido:");
		LastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LastNamePanel.add(LastNameLabel);

		LastNameTextField = new JTextField();
		LastNamePanel.add(LastNameTextField);
		LastNameTextField.setColumns(10);

		JPanel EmailPanel = new JPanel();
		EmailPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel EmailLabel = new JLabel("Email:");
		EmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EmailPanel.add(EmailLabel);

		JPanel BirthdatePanel = new JPanel();
		BirthdatePanel.setMaximumSize(new Dimension(300, 32767));
		BirthdatePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel BirthDateLabel = new JLabel("Fecha de nacimiento:");
		BirthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BirthdatePanel.add(BirthDateLabel);

		JPanel BirthDateInputPanel = new JPanel();
		BirthDateInputPanel.setAlignmentX(0.0f);
		BirthdatePanel.add(BirthDateInputPanel);
		BirthDateInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		MonthSpinner = new JSpinner();
		YearSpinner = new JSpinner();
		DaySpinner = new JSpinner();

		DaySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		JComponent daySpinnerEditor = DaySpinner.getEditor();
		JFormattedTextField dayTf = ((JSpinner.DefaultEditor) daySpinnerEditor).getTextField();
		dayTf.setColumns(2);

		YearSpinner.setModel(new SpinnerNumberModel(Year.now().getValue(), 1, Year.now().getValue(), 1));
		YearSpinner.setEditor(new JSpinner.NumberEditor(YearSpinner, "#"));
		
		YearSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int monthValue = (Integer) MonthSpinner.getValue();
				int daylValue = (Integer) DaySpinner.getValue();
				int yearValue = (Integer) YearSpinner.getValue();
				int leapDayValue = DateUtils.isLeapYear(yearValue) ? 29 : 28;

				switch (monthValue) {
				case 2:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < leapDayValue ? daylValue : leapDayValue, 1, leapDayValue, 1));
					break;
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < 30 ? daylValue : 30, 1, 30, 1));
					break;
				default:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < 31 ? daylValue : 31, 1, 31, 1));
				}
			}
		});

		MonthSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		JComponent MonthSpinnerEditor = MonthSpinner.getEditor();
		JFormattedTextField monthTf = ((JSpinner.DefaultEditor) MonthSpinnerEditor).getTextField();
		monthTf.setColumns(2);
		MonthSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				int monthValue = (Integer) MonthSpinner.getValue();
				int daylValue = (Integer) DaySpinner.getValue();
				int yearValue = (Integer) YearSpinner.getValue();
				int leapDayValue = DateUtils.isLeapYear(yearValue) ? 29 : 28;

				switch (monthValue) {
				case 2:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < leapDayValue ? daylValue : leapDayValue, 1, leapDayValue, 1));
					break;
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < 30 ? daylValue : 30, 1, 30, 1));
					break;
				default:
					DaySpinner.setModel(new SpinnerNumberModel(daylValue < 31 ? daylValue : 31, 1, 31, 1));
				}
			}
		});

		BirthDateInputPanel.add(DaySpinner);
		BirthDateInputPanel.add(MonthSpinner);
		BirthDateInputPanel.add(YearSpinner);

		GroupLayout gl_UserInfoPanel = new GroupLayout(UserInfoPanel);
		gl_UserInfoPanel.setHorizontalGroup(
			gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UserInfoPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(NicknamePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(NamePanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(LastNamePanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(EmailPanel, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)
						.addComponent(BirthdatePanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(UserInfoLabel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_UserInfoPanel.setVerticalGroup(
			gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UserInfoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(UserInfoLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NicknamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LastNamePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmailPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BirthdatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		actualEmail = new JLabel("email.com");
		actualEmail.setHorizontalAlignment(SwingConstants.CENTER);
		EmailPanel.add(actualEmail);
		
		comboBoxUser = new JComboBox<UserDT>();
		NicknamePanel.add(comboBoxUser);

		UserInfoPanel.setLayout(gl_UserInfoPanel);
		ConfirmBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		ConfirmBtnPanel.add(btnCancelar);

		confirmButton = new JButton("Confirmar");
		
		ConfirmBtnPanel.add(confirmButton);

		getContentPane().setLayout(groupLayout);
	}
}
