package com.main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.CategoryDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;
import com.main.logic.utils.DateUtils;

public class CreateVideoJInternalFrame extends JInternalFrame {
    private JTextField textFieldTitulo;
    private JTextField textFieldUrl;
    private JComboBox comboBoxUsers;
    private JPanel panelGeneral;
    private GroupLayout groupLayout;
    private JPanel panelDueno;
    private JLabel lblUser;
    private JPanel panelTitulo;
    private JLabel lblTitulo;
    private JPanel panelDuracion;
    private JLabel lblDuracion;
    private JTextField textFieldDuracion;
    private JLabel lblInformacion;
    private JPanel panelUrl;
    private JLabel lblUrl;
    private JPanel panelFechaSubida;
    private JLabel lblFechaSubida;
    private JPanel panelDescripcion;
    private JTextPane textPaneDescripcion;

    public CreateVideoJInternalFrame() {
        getContentPane().setPreferredSize(new Dimension(150, 65));
        setTitle("Crear video");
        setBounds(100, 100, 470, 544);

        panelGeneral = new JPanel();

        groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGeneral, GroupLayout.PREFERRED_SIZE, 409, Short.MAX_VALUE)
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGeneral, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                        .addContainerGap())
                );

        panelDueno = new JPanel();
        panelDueno.setLayout(new GridLayout(0, 2, 0, 0));

        lblUser = new JLabel("Usuario:");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        panelDueno.add(lblUser);

        panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(0, 2, 0, 0));

        lblTitulo = new JLabel("Título:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(lblTitulo);

        textFieldTitulo = new JTextField();
        panelTitulo.add(textFieldTitulo);
        textFieldTitulo.setColumns(10);

        panelDuracion = new JPanel();
        panelDuracion.setLayout(new GridLayout(0, 2, 0, 0));

        lblDuracion = new JLabel("Duración (segundos):");
        lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDuracion.add(lblDuracion);

        textFieldDuracion = new JTextField();
        panelDuracion.add(textFieldDuracion);
        textFieldDuracion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())
                        && !(e.getKeyChar() == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        lblInformacion = new JLabel("Información del video:");
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

        JSpinner DaySpinner = new JSpinner();
        panel_1.add(DaySpinner);

        JSpinner MonthSpinner = new JSpinner();
        panel_1.add(MonthSpinner);

        JSpinner YearSpinner = new JSpinner();
        YearSpinner.setModel(new SpinnerNumberModel(Year.now().getValue(), 1, Year.now().getValue(), 1));
        YearSpinner.setEditor(new JSpinner.NumberEditor(YearSpinner, "#"));
        panel_1.add(YearSpinner);

        DaySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        JComponent daySpinnerEditor = DaySpinner.getEditor();

        YearSpinner.setModel(new SpinnerNumberModel(LocalDate.now()
                .getYear(), 1, LocalDate.now()
                .getYear(), 1));
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
        MonthSpinner.addChangeListener(new ChangeListener() {
            @Override
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

        JPanel panelCategoria = new JPanel();
        panelCategoria.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblCategoria = new JLabel("Categoría (opcional):");
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        panelCategoria.add(lblCategoria);

        JComboBox<String> comboBoxCategoria = new JComboBox<>();
        panelCategoria.add(comboBoxCategoria);
        comboBoxCategoria.addItem("-");
        ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();
        List<CategoryDT> categoryDTs = categoryController.listCategories();
        categoryDTs.forEach(categoryDT -> comboBoxCategoria.addItem(categoryDT.getName()));

        panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
        lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDescripcion.add(lblDescripcion);

        textPaneDescripcion = new JTextPane();
        panelDescripcion.add(textPaneDescripcion);
        textPaneDescripcion.setBorder(UIManager.getBorder("TextField.border"));
        textPaneDescripcion.setMinimumSize(new Dimension(6, 32));
        textPaneDescripcion.setMaximumSize(new Dimension(6, 32));

        JPanel panelConfirmar = new JPanel();
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isCompletedInfo()) {
                    IVideoController videoController = ControllerFactory.getInstance().getVideoController();
                    String owner = comboBoxUsers.getSelectedItem().toString();
                    String title = textFieldTitulo.getText();
                    Integer length = Integer.valueOf(textFieldDuracion.getText());
                    String url = textFieldUrl.getText();
                    String description = textPaneDescripcion.getText();
                    LocalDate uploadDate = LocalDate.of((Integer) YearSpinner.getValue(),
                            (Integer) MonthSpinner.getValue(), (Integer) DaySpinner.getValue());
                    String category = (String) comboBoxCategoria.getSelectedItem();
                    try {
                        if (category == "-") {
                            videoController.createVideo(owner, title, length, url, description, uploadDate);
                        } else {
                            videoController.createVideo(owner, title, length, url, description, uploadDate, category);
                        }
                        JOptionPane.showMessageDialog(btnConfirmar, "Video creado existosamente", "Información",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Video creado con éxito!");
                    } catch (EntityNotFoundException e) {
                        JOptionPane.showMessageDialog(btnConfirmar, "El nombre de usuario ingresado no existe",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(btnConfirmar, "Por favor complete los campos requeridos antes de confirmar", "Alerta",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelConfirmar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelConfirmar.add(btnConfirmar);

        GroupLayout gl_panelGeneral = new GroupLayout(panelGeneral);
        gl_panelGeneral.setHorizontalGroup(
                gl_panelGeneral.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panelGeneral.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelGeneral.createParallelGroup(Alignment.LEADING)
                                .addComponent(panelFechaSubida, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelTitulo, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelDuracion, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelCategoria, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelDescripcion, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelDueno, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(lblInformacion, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelUrl, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addComponent(panelConfirmar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                        .addContainerGap())
                );
        gl_panelGeneral.setVerticalGroup(
                gl_panelGeneral.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelGeneral.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInformacion)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelDueno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(12)
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
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(panelConfirmar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))
                );

        comboBoxUsers = new JComboBox();
        panelDueno.add(comboBoxUsers);
        panelGeneral.setLayout(gl_panelGeneral);
        getContentPane().setLayout(groupLayout);

        comboBoxUsers.addItem("-");
        IUserController userController = ControllerFactory.getInstance().getUserController();
        List<UserDT> userDTs = userController.listUsers();
        userDTs.forEach(userDT -> comboBoxUsers.addItem(userDT));
    }

    private boolean isCompletedInfo() {
        return !comboBoxUsers.getSelectedItem().toString().equals("-")
                && !textFieldTitulo.getText().trim().equals("")
                && !textFieldDuracion.getText().trim().equals("")
                && !textFieldUrl.getText().trim().equals("")
                && !textPaneDescripcion.getText().trim().equals("");
    }
}
