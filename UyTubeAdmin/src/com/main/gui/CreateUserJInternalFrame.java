package com.main.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
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
import com.main.logic.interfaces.IUserController;
import com.main.logic.utils.DateUtils;


public class CreateUserJInternalFrame extends JInternalFrame {
	private JTextField NicknameTextField;
	private JTextField NameTextField;
	private JTextField LastNameTextField;
	private JTextField EmailTextField;
	private JTextField DescriptionTextField;
	private File file;
	private JTextField ChannelNameTextField;
	final JInternalFrame fileChooserFrame = new JInternalFrame("Hello");


	public CreateUserJInternalFrame() {
		setTitle("Crear usuario");
		getContentPane().setMaximumSize(new Dimension(300, 600));
		setBounds(100, 100, 533, 416);

		JPanel ConfirmBtnPanel = new JPanel();

		JPanel UserInfoPanel = new JPanel();

		JPanel ChannelInfoPanel = new JPanel();

		JPanel ShowAvatarPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ConfirmBtnPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(ChannelInfoPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(UserInfoPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(ShowAvatarPanel, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ShowAvatarPanel, 0, 0, Short.MAX_VALUE)
								.addComponent(UserInfoPanel, GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ChannelInfoPanel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ConfirmBtnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(7, Short.MAX_VALUE)));

		JLabel ShowAvatarLabel = new JLabel("");
		ShowAvatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		try {
            InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
            byte[] defAvatarBytes = new byte[defAvatarStream.available()];
            defAvatarStream.read(defAvatarBytes);
            ImageIcon imgIcon = new ImageIcon(defAvatarBytes);
            ShowAvatarLabel.setIcon(imgIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

					ImageIcon imgIcon = new ImageIcon(file.getAbsolutePath());
					Image image = imgIcon.getImage();
					Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
					imgIcon = new ImageIcon(newimg);
					ShowAvatarLabel.setIcon(imgIcon);
					
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
		ChannelNameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(ChannelNameTextField.getText().isEmpty()) {
					ChannelNameTextField.setForeground(Color.LIGHT_GRAY);
					ChannelNameTextField.setText("(Opcional)");				
				}
			}
		});
		ChannelNameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(Objects.equals(ChannelNameTextField.getText(), "(Opcional)")) {
					ChannelNameTextField.setText("");				
					ChannelNameTextField.setForeground(Color.BLACK);
				}
			}
		});
		ChannelNameTextField.setForeground(Color.LIGHT_GRAY);
		ChannelNameTextField.setSelectedTextColor(Color.BLACK);
		ChannelNameTextField.setText("(Opcional)");
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

		JCheckBox PrivateCheckBox = new JCheckBox("Privado");
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

		NicknameTextField = new JTextField();
		NicknameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		NicknameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		NicknamePanel.add(NicknameTextField);
		NicknameTextField.setColumns(10);

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

		EmailTextField = new JTextField();
		EmailPanel.add(EmailTextField);
		EmailTextField.setColumns(10);

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

		JSpinner MonthSpinner = new JSpinner();
		JSpinner YearSpinner = new JSpinner();
		JSpinner DaySpinner = new JSpinner();

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
		gl_UserInfoPanel.setHorizontalGroup(gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UserInfoPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(NicknamePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
								.addComponent(NamePanel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
								.addComponent(LastNamePanel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
								.addComponent(EmailPanel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
								.addComponent(BirthdatePanel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
								.addComponent(UserInfoLabel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
						.addContainerGap()));
		gl_UserInfoPanel.setVerticalGroup(gl_UserInfoPanel.createParallelGroup(Alignment.LEADING)
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
						.addContainerGap(107, Short.MAX_VALUE)));

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

		JButton confirmButton = new JButton("Confirmar");
		
		ConfirmBtnPanel.add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					IUserController userController = ControllerFactory.getInstance().getUserController();

					String nickname = NicknameTextField.getText();
					String name = NameTextField.getText();
					String lastName = LastNameTextField.getText();
					String email = EmailTextField.getText();
					String channelName = ChannelNameTextField.getText().equals("") || ChannelNameTextField.getText().equals("(Opcional)") ? nickname : ChannelNameTextField.getText();
					String channelDescription = DescriptionTextField.getText();
					boolean isPrivate = PrivateCheckBox.isSelected();
					
		            InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
		            byte[] defAvatarBytes = new byte[defAvatarStream.available()];
		            defAvatarStream.read(defAvatarBytes);
					
					File avatarFile = file != null ? file : new File(getClass().getClassLoader().getResource("emptyAvatar.png").getFile());
					byte[] avatar = Files.readAllBytes(avatarFile.toPath());
					
					LocalDate birthDate = LocalDate.of((Integer) YearSpinner.getValue(), (Integer) MonthSpinner.getValue(), (Integer) DaySpinner.getValue());

					if(!nickname.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !email.isEmpty() &&
							!channelDescription.isEmpty()) {
						
						boolean userCreated = userController.createUser(nickname, name, lastName, email, birthDate, avatar, channelDescription, channelName, isPrivate);
		            	
		                if(userCreated) {
		                	JOptionPane.showMessageDialog(getParent(), "Usuario creado con éxito!");
		                	System.out.println("Usuario creado con éxito!");
		                	
		                } else {
		                	JOptionPane.showMessageDialog(getParent(), "El usuario ya existe");
		                	System.out.println("El usuario ya existe!");
		                }
					} else {
						JOptionPane.showMessageDialog(getParent(), "Ingrese todos los datos obligatorios");
	                	System.out.println("Ingrese todos los datos obligatorios");
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error leyendo la imagen", "Error", ERROR);
				}
			}
		});

		getContentPane().setLayout(groupLayout);

	}
}
