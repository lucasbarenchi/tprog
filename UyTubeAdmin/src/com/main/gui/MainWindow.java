package com.main.gui;

import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame {

    public MainWindow() {

        JFrame jFrame = this;

        JDesktopPane jDesktopPane = new JDesktopPane(){
        	public void paintComponent(Graphics g) {
        		try {
        			InputStream backIs = getClass().getClassLoader().getResourceAsStream("back.jpg");
                    byte[] backBytes = new byte[backIs.available()];
                    backIs.read(backBytes);
            		Image backImage = new ImageIcon(backBytes).getImage();
            		Graphics2D g2d = (Graphics2D) g;
            		g2d.drawImage(backImage, 0, 0, this);
        		} catch (IOException e) {
        			e.printStackTrace();
				}
        	}
        };

        setContentPane(jDesktopPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu mAlta = new JMenu("Ingresar");
        menuBar.add(mAlta);

        JMenuItem mAltaUsuario = new JMenuItem("Usuario");
        mAlta.add(mAltaUsuario);
        // ALTA DE USUARIO
        mAltaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserJInternalFrame internalJF = new CreateUserJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(560, 480));
                internalJF.setMaximumSize(new Dimension(560, 480));
                internalJF.setPreferredSize(new Dimension(560, 480));
                internalJF.setSize(new Dimension(560, 480));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mAltaVideo = new JMenuItem("Video");
        mAlta.add(mAltaVideo);
        // CREAR VIDEO
        mAltaVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateVideoJInternalFrame internalJF = new CreateVideoJInternalFrame();

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
        });

        JMenuItem mAltaPlaylist = new JMenuItem("Lista de reproducción");
        mAlta.add(mAltaPlaylist);
        // CREAR LISTA DE REPRODUCCION
        mAltaPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePlaylistJInternalFrame internalJF = new CreatePlaylistJInternalFrame();

                internalJF.setResizable(true);
                internalJF.setMinimumSize(new Dimension(350, 280));
                internalJF.setMaximumSize(new Dimension(350, 280));
                internalJF.setPreferredSize(new Dimension(350, 280));
                internalJF.setSize(new Dimension(350, 280));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mAltaVideoEnPlaylist = new JMenuItem("Video a lista de reproducción");
        mAlta.add(mAltaVideoEnPlaylist);
        // AGREGAR VIDEO A LISTA DE REPRODUCCION
        mAltaVideoEnPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddVideoToPlaylistJInternalFrame internalJF = new AddVideoToPlaylistJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(450, 300));
                internalJF.setMaximumSize(new Dimension(450, 300));
                internalJF.setPreferredSize(new Dimension(450, 300));
                internalJF.setSize(new Dimension(450, 300));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mAltaCategoria = new JMenuItem("Categoría");
        mAlta.add(mAltaCategoria);
        // ALTA CATEGORIA
        mAltaCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCategoryJInternalFrame internalJF = new CreateCategoryJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(475, 145));
                internalJF.setMaximumSize(new Dimension(475, 145));
                internalJF.setPreferredSize(new Dimension(475, 145));
                internalJF.setSize(new Dimension(475, 145));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenu mBaja = new JMenu("Eliminar");
        menuBar.add(mBaja);

        JMenuItem mBajaVideoDePlaylist = new JMenuItem("Video de lista de reproducción");
        mBaja.add(mBajaVideoDePlaylist);
        // ELIMINAR VIDEO DE LISTA DE REPRODUCCION
        mBajaVideoDePlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveVideoFromPlaylistJInternalFrame internalJF = new RemoveVideoFromPlaylistJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setBounds(0, 0, 520, 210);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenu mConsulta = new JMenu("Consultar");
        menuBar.add(mConsulta);

        JMenuItem mConsultaUsuario = new JMenuItem("Usuarios");
        mConsulta.add(mConsultaUsuario);
        // CONSULTA DE USUARIO
        mConsultaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfoJInternalFrame internalJF = new UserInfoJInternalFrame(jFrame);

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(1050, 670));
                internalJF.setMaximumSize(new Dimension(1050, 670));
                internalJF.setPreferredSize(new Dimension(1050, 670));
                internalJF.setSize(new Dimension(1050, 670));
                internalJF.setBounds(0, 0, 1050, 670);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mConsultaVideo = new JMenuItem("Videos");
        mConsulta.add(mConsultaVideo);
        // CONSULTA DE VIDEO
        mConsultaVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoInfoJInternalFrame internalJF = new VideoInfoJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(620, 150));
                internalJF.setMaximumSize(new Dimension(620, 450));
                internalJF.setPreferredSize(new Dimension(620, 150));
                internalJF.setSize(new Dimension(620, 150));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mConsultaPlaylist = new JMenuItem("Listas de reproducción");
        mConsulta.add(mConsultaPlaylist);
        // 	CONSULTAR LISTA DE REPRODUCCION
        mConsultaPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoPlaylistJInternalFrame internalJF = new InfoPlaylistJInternalFrame(jFrame);

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(480, 295));
                internalJF.setMaximumSize(new Dimension(480, 295));
                internalJF.setPreferredSize(new Dimension(480, 295));
                internalJF.setSize(new Dimension(480, 295));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mConsultaCategoria = new JMenuItem("Categorias");
        mConsulta.add(mConsultaCategoria);
        
        //  CONSULTAR CATEGORIA
        mConsultaCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryInfoJInternalFrame internalJF = new CategoryInfoJInternalFrame();
                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(450, 510));
                internalJF.setMaximumSize(new Dimension(450, 510));
                internalJF.setPreferredSize(new Dimension(450, 510));
                internalJF.setSize(new Dimension(450, 510));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });
        
        JMenuItem mConsultaUsuarioEliminado = new JMenuItem("Usuarios Eliminados");
        mConsulta.add(mConsultaUsuarioEliminado);
        
        mConsultaUsuarioEliminado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletedUsersInfoJInternalFrame internalJF = new DeletedUsersInfoJInternalFrame();
                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(1050, 581));
                internalJF.setMaximumSize(new Dimension(1050, 581));
                internalJF.setPreferredSize(new Dimension(1050, 581));
                internalJF.setSize(new Dimension(1050, 581));
                internalJF.setBounds(0, 0, 1050, 581);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });
        
        JMenu mModific = new JMenu("Modificar");
        menuBar.add(mModific);

        JMenuItem mModificUsuario = new JMenuItem("Usuario");
        mModific.add(mModificUsuario);

        // MODIFICAR USUARIO
        mModificUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyUserJInternalFrame internalJF = new ModifyUserJInternalFrame(jFrame);

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(745, 505));
                internalJF.setMaximumSize(new Dimension(745, 505));
                internalJF.setPreferredSize(new Dimension(745, 505));
                internalJF.setSize(new Dimension(745, 505));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mModificVideo = new JMenuItem("Video");
        mModific.add(mModificVideo);

        // MODIFICAR VIDEO
        mModificVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyVideoJInternalFrame internalJF = new ModifyVideoJInternalFrame();

                internalJF.setResizable(true);
                internalJF.setMinimumSize(new Dimension(470, 631));
                internalJF.setMaximumSize(new Dimension(470, 631));
                internalJF.setPreferredSize(new Dimension(470, 631));
                internalJF.setSize(new Dimension(470, 631));
                internalJF.setBounds(0, 0, 470, 631);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mModificPlaylist = new JMenuItem("Lista de reproducción");
        mModific.add(mModificPlaylist);

        // MODIFICAR LISTAS DE REPRODUCCION
        mModificPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyPlaylistJInternalFrame internalJF = new ModifyPlaylistJInternalFrame();

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
        });

        JMenu mLista = new JMenu("Listar");
        menuBar.add(mLista);

        JMenuItem mListaUsuario = new JMenuItem("Usuarios");
        mLista.add(mListaUsuario);
        mListaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListUsersJInternalFrame internalJF = new ListUsersJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(450, 300));
                internalJF.setMaximumSize(new Dimension(450, 300));
                internalJF.setPreferredSize(new Dimension(450, 300));
                internalJF.setSize(new Dimension(450, 300));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();
            }
        });

        JMenuItem mListaCategorias = new JMenuItem("Categorias");
        mListaCategorias.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ListCategoriesJInternalFrame internalJF = new ListCategoriesJInternalFrame();

                internalJF.setResizable(false);
                internalJF.setMinimumSize(new Dimension(450, 300));
                internalJF.setMaximumSize(new Dimension(450, 300));
                internalJF.setPreferredSize(new Dimension(450, 300));
                internalJF.setSize(new Dimension(450, 300));
                internalJF.setBounds(0, 0, 100, 100);
                internalJF.setVisible(true);
                internalJF.setClosable(true);
                jFrame.getContentPane().add(internalJF);
                internalJF.toFront();
                internalJF.pack();

            }
        });
        mLista.add(mListaCategorias);

        JButton mCargarDatos = new JButton("Cargar datos de prueba");
        menuBar.add(mCargarDatos);
        mCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDataLoader dataLoaderController = new DataLoader();
                try {
                    dataLoaderController.cargarDatosDePrueba();
                } catch (KeyAlreadyInUseException ex) {
                    JOptionPane.showMessageDialog(getParent(), "No se pueden cargar los datos. Hay algunos elementos en uso.");
                } catch (EntityNotFoundException ex) {
                    JOptionPane.showMessageDialog(getParent(), "Esto no debería pasar!! Llamar al admin.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(getParent(), "Esto jamas!");
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(getParent(), "Los datos de prueba se han cargado con éxito.");
                mCargarDatos.setEnabled(false);
            }
        });

        jFrame.setTitle("UyTube - Admin");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setBounds(0, 0, screenSize.width, screenSize.height);

        jFrame.setJMenuBar(menuBar);
        jFrame.pack();

    }

}
