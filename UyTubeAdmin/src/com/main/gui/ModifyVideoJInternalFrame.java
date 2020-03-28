package com.main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.CategoryDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;
import com.main.logic.utils.DateUtils;

public class ModifyVideoJInternalFrame extends JInternalFrame {

    private GroupLayout groupLayout;
    private JTextField textFieldTitulo;
    private JTextField textFieldUrl;
    private JPanel panelGeneral;
    private JPanel panelDueno;
    private JLabel lblDueno;
    private JComboBox<String> comboBoxUser;
    private JPanel panelVideo;
    private JLabel lblVideo;
    private JComboBox<VideoDT> comboBoxVideo;
    private JPanel panelTitulo;
    private JLabel lblTitulo;
    private JPanel panelDuracion;
    private JLabel lblDuracion;
    private JFormattedTextField textFieldDuracion;
    private JLabel lblInformacion;
    private JPanel panelUrl;
    private JLabel lblUrl;
    private JPanel panelFechaSubida;
    private JLabel lblFechaSubida;
    private JSpinner DaySpinner;
    private JSpinner MonthSpinner;
    private JSpinner YearSpinner;
    private JPanel panelCategoria;
    private JLabel lblCategoria;
    private JComboBox<String> comboBoxCategoria;
    private JPanel panelDescripcion;
    private JLabel lblDescripcion;
    private JTextPane textPaneDescripcion;
    private JPanel panelConfirmar;
    private JButton btnConfirmar;
    private JCheckBox checkboxPrivate;

    private IUserController userController = ControllerFactory.getInstance().getUserController();
    private ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();

    private List<VideoDT> videosFromUser = new ArrayList<>();

    public ModifyVideoJInternalFrame() {
        getContentPane().setPreferredSize(new Dimension(150, 65));
        setTitle("Modificar Video");
        setBounds(100, 100, 470, 631);

        // Todo el codigo feo generado por eclipse para inicializar cosas
        initializeFields();

        // Cargar Usuarios en comboBox
        loadUserField();

        // Cargar Categor�as en comboBox
        loadCategoryField();

        // Cuando cambia el usuario seleccionado
        comboBoxUser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        loadVideoField((String) comboBoxUser.getSelectedItem());
                    } catch (EntityNotFoundException excep) {
                        System.out.println("Usuario no encontrado");
                        JOptionPane.showMessageDialog(getParent(), "Usuario no encontrado");
                    } finally {
                        cleanVideoDataFields();
                    }
                }
            }
        });

        // Cuando cambia el video elegido
        comboBoxVideo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    loadVideoInfo((VideoDT) comboBoxVideo.getSelectedItem());
                }
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IVideoController videoController = ControllerFactory.getInstance().getVideoController();
                try {
                    VideoDT selected = (VideoDT) comboBoxVideo.getSelectedItem();
                    videoController.modifyVideo(getVideoInfo(), selected.getVideoId());
                    JOptionPane.showMessageDialog(getParent(), "Video modificado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(getParent(), exc.getMessage());
                }
            }
        });
    }

    public ModifyVideoJInternalFrame(String nickname, VideoDT videoDT) {
        getContentPane().setPreferredSize(new Dimension(150, 65));
        setTitle("Modificar Video");
        setBounds(100, 100, 470, 631);

        // Todo el codigo feo generado por eclipse para inicializar cosas
        initializeFields();

        // Cargar Usuarios en comboBox
        loadUserField();

        // Cargar Categor�as en comboBox
        loadCategoryField();

        // Cuando cambia el usuario seleccionado
        comboBoxUser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        loadVideoField((String) comboBoxUser.getSelectedItem());
                    } catch (EntityNotFoundException excep) {
                        System.out.println("Usuario no encontrado");
                        JOptionPane.showMessageDialog(getParent(), "Usuario no encontrado");
                    } finally {
                        cleanVideoDataFields();
                    }
                }
            }
        });

        // Cuando cambia el video elegido
        comboBoxVideo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED) {
                    loadVideoInfo((VideoDT) comboBoxVideo.getSelectedItem());
                }
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IVideoController videoController = ControllerFactory.getInstance().getVideoController();
                try {
                    VideoDT selected = (VideoDT) comboBoxVideo.getSelectedItem();
                    videoController.modifyVideo(getVideoInfo(), selected.getVideoId());
                    JOptionPane.showMessageDialog(getParent(), "Video modificado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(getParent(), exc.getMessage());
                }
            }
        });

        comboBoxUser.setSelectedItem(nickname);
        try {
            loadVideoField(nickname);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        VideoDT actualVideoDT = null;

        for(VideoDT v : videosFromUser) {
            if(videoDT.getVideoId() == v.getVideoId()) {
                actualVideoDT = v;
            }
        }
        System.out.println(actualVideoDT);

        if (actualVideoDT != null) {
            comboBoxVideo.setSelectedItem(actualVideoDT);
            comboBoxUser.setEnabled(false);
            comboBoxVideo.setEnabled(false);
        }
    }

    private void cleanVideoDataFields() {
        textFieldTitulo.setText("");
        textFieldDuracion.setValue(new Long(0));
        textFieldUrl.setText("");
        DaySpinner.setValue(1);
        MonthSpinner.setValue(1);
        YearSpinner.setValue(2018);
        comboBoxCategoria.setSelectedItem("-");
        textPaneDescripcion.setText("");
        checkboxPrivate.setSelected(false);
    }

    private VideoDT getVideoInfo() throws Exception {
        String title = textFieldTitulo.getText();
        if (title == null || title.equals("")) {
            throw new Exception("Titulo invalido");
        }
        Long length = (Long) textFieldDuracion.getValue();
        String url = textFieldUrl.getText();
        if (url == null || url.equals("")) {
            throw new Exception("URL invalida");
        }

        String description = textPaneDescripcion.getText();

        VideoDT videoDT = new VideoDT();
        videoDT.setTitle(title);
        videoDT.setLength(length.intValue());
        videoDT.setUrl(url);
        LocalDate date = LocalDate.of((Integer) YearSpinner.getValue(), (Integer) MonthSpinner.getValue(), (Integer) DaySpinner.getValue());
        videoDT.setUploadDate(date);
        videoDT.setPrivate(checkboxPrivate.isSelected());

        String category = (String) comboBoxCategoria.getSelectedItem();
        if (!category.equals("-")) {
            videoDT.setCategory(categoryController.getCategory(category));
        }

        videoDT.setDescription(description);
        return videoDT;
    }

    private void loadVideoInfo(VideoDT video) {
        textFieldTitulo.setText(video.getTitle());
        textFieldDuracion.setValue(new Long(video.getLength()));
        textFieldUrl.setText(video.getUrl());

        if (video.getUploadDate() != null) {
            DaySpinner.setValue(video.getUploadDate().getDayOfMonth());
            MonthSpinner.setValue(video.getUploadDate().getMonthValue());
            YearSpinner.setValue(video.getUploadDate().getYear());
        }

        if (video.getCategory() != null) {
            comboBoxCategoria.setSelectedItem(video.getCategory().getName());
        }

        checkboxPrivate.setSelected(video.isPrivate());
        textPaneDescripcion.setText(video.getDescription());
    }

    private void loadUserField() {
        comboBoxUser.removeAllItems();
        comboBoxUser.addItem("-");
        List<UserDT> users = userController.listUsers();
        users.forEach(user -> comboBoxUser.addItem(user.getNickname()));
    }

    private void loadVideoField(String userNickname) throws EntityNotFoundException {
        comboBoxVideo.removeAllItems();
        comboBoxVideo.addItem(new VideoDT());
        if (!userNickname.equals("-")) {
            IVideoController videoController = ControllerFactory.getInstance().getVideoController();
            videosFromUser = videoController.listVideosByUser(userNickname);
            videosFromUser.forEach(v -> comboBoxVideo.addItem(v));
        }
    }

    private void loadCategoryField() {
        comboBoxCategoria.removeAllItems();
        comboBoxCategoria.addItem("-");
        List<CategoryDT> categoryDTs = categoryController.listCategories();
        categoryDTs.forEach(categoryDT -> comboBoxCategoria.addItem(categoryDT.getName()));
    }

    private void initializeFields() {
        panelGeneral = new JPanel();

        groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGeneral, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGeneral, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                        .addContainerGap())
                );

        panelDueno = new JPanel();
        panelDueno.setLayout(new GridLayout(0, 2, 0, 0));

        lblDueno = new JLabel("Due\u00F1o:");
        lblDueno.setHorizontalAlignment(SwingConstants.CENTER);
        panelDueno.add(lblDueno);

        comboBoxUser = new JComboBox<String>();
        panelDueno.add(comboBoxUser);

        panelVideo = new JPanel();
        panelVideo.setLayout(new GridLayout(0, 2, 0, 0));

        lblVideo = new JLabel("Video:");
        lblVideo.setHorizontalAlignment(SwingConstants.CENTER);
        panelVideo.add(lblVideo);

        comboBoxVideo = new JComboBox<VideoDT>();
        panelVideo.add(comboBoxVideo);

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(0, 2, 0, 0));

        lblTitulo = new JLabel("T\u00EDtulo:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(lblTitulo);

        textFieldTitulo = new JTextField();
        panelTitulo.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        panelDuracion = new JPanel();
        panelDuracion.setLayout(new GridLayout(0, 2, 0, 0));

        lblDuracion = new JLabel("Duraci\u00F3n (segundos):");
        lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDuracion.add(lblDuracion);

        NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance();
        integerFieldFormatter.setGroupingUsed(false);
        textFieldDuracion = new JFormattedTextField(new NumberFormatter(integerFieldFormatter));
        panelDuracion.add(textFieldDuracion);

        lblInformacion = new JLabel("Informaci\u00F3n del video:");
        lblInformacion.setFont(new Font("Lucida Grande", Font.BOLD, 15));

        panelUrl = new JPanel();
        panelUrl.setLayout(new GridLayout(0, 2, 0, 0));

        lblUrl = new JLabel("URL:");
        lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
        panelUrl.add(lblUrl);

        textFieldUrl = new JTextField();
        panelUrl.add(textFieldUrl);
        textFieldUrl.setColumns(10);

        panelFechaSubida = new JPanel();
        panelFechaSubida.setMaximumSize(new Dimension(300, 32767));
        panelFechaSubida.setLayout(new GridLayout(0, 2, 0, 0));

        lblFechaSubida = new JLabel("Fecha de subida:");
        lblFechaSubida.setHorizontalAlignment(SwingConstants.CENTER);
        panelFechaSubida.add(lblFechaSubida);

        JPanel panel_1 = new JPanel();
        panel_1.setAlignmentX(0.0f);
        panelFechaSubida.add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        DaySpinner = new JSpinner();
        panel_1.add(DaySpinner);

        MonthSpinner = new JSpinner();
        panel_1.add(MonthSpinner);

        YearSpinner = new JSpinner();
        panel_1.add(YearSpinner);

        DaySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        JComponent daySpinnerEditor = DaySpinner.getEditor();

        YearSpinner.setModel(new SpinnerNumberModel(LocalDate.now().getYear(), 1, LocalDate.now().getYear(), 1));
        JComponent yearSpinnerEditor = YearSpinner.getEditor();
        YearSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int monthValue = (Integer) MonthSpinner.getValue();
                int daylValue = (Integer) DaySpinner.getValue();
                int yearValue = (Integer) YearSpinner.getValue();
                int leapDayValue = DateUtils.isLeapYear(yearValue) ? 29 : 28;

                switch (monthValue) {
                case 2:
                    DaySpinner.setModel(new SpinnerNumberModel(daylValue < leapDayValue ? daylValue : leapDayValue, 1,
                            leapDayValue, 1));
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
        MonthSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                int monthValue = (Integer) MonthSpinner.getValue();
                int daylValue = (Integer) DaySpinner.getValue();
                int yearValue = (Integer) YearSpinner.getValue();
                int leapDayValue = DateUtils.isLeapYear(yearValue) ? 29 : 28;

                switch (monthValue) {
                case 2:
                    DaySpinner.setModel(new SpinnerNumberModel(daylValue < leapDayValue ? daylValue : leapDayValue, 1,
                            leapDayValue, 1));
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

        panelCategoria = new JPanel();
        panelCategoria.setLayout(new GridLayout(1, 0, 0, 0));

        lblCategoria = new JLabel("Categor\u00EDa:");
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        panelCategoria.add(lblCategoria);

        comboBoxCategoria = new JComboBox<>();
        panelCategoria.add(comboBoxCategoria);

        panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new GridLayout(0, 2, 0, 0));

        lblDescripcion = new JLabel("Descripci\u00F3n:");
        lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDescripcion.add(lblDescripcion);

        textPaneDescripcion = new JTextPane();
        panelDescripcion.add(textPaneDescripcion);
        textPaneDescripcion.setBorder(UIManager.getBorder("TextField.border"));
        textPaneDescripcion.setMinimumSize(new Dimension(6, 32));
        textPaneDescripcion.setMaximumSize(new Dimension(6, 32));

        panelConfirmar = new JPanel();
        btnConfirmar = new JButton("Confirmar");

        panelConfirmar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelConfirmar.add(btnConfirmar);

        JPanel panel = new JPanel();

        GroupLayout gl_panelGeneral = new GroupLayout(panelGeneral);
        gl_panelGeneral.setHorizontalGroup(
                gl_panelGeneral.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelGeneral.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelGeneral.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelVideo, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelFechaSubida, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panelTitulo, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panelDuracion, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panelCategoria, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelDescripcion, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(lblInformacion, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panelUrl, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panelDueno, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelConfirmar, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );
        gl_panelGeneral.setVerticalGroup(
                gl_panelGeneral.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelGeneral.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInformacion)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelDueno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelVideo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelFechaSubida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelDescripcion, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                        .addGap(7)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panelConfirmar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE))
                );

        checkboxPrivate = new JCheckBox("Privado");
        panel.add(checkboxPrivate);

        panelGeneral.setLayout(gl_panelGeneral);
        getContentPane().setLayout(groupLayout);
    }
}
