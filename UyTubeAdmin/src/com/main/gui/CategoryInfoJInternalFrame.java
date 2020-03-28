package com.main.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.CategoryDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IVideoController;

public class CategoryInfoJInternalFrame extends JInternalFrame {

    public CategoryInfoJInternalFrame() {

        setTitle("Consulta de categoría");
        setBounds(100, 100, 545, 510);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel categoriesPanel = new JPanel();

        JPanel videosPanel = new JPanel();

        JPanel playlistsPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addComponent(playlistsPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                                .addComponent(videosPanel, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                                .addComponent(categoriesPanel, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                        .addContainerGap())
                );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(categoriesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(videosPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(playlistsPanel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))
                );
        playlistsPanel.setLayout(new BorderLayout(0, 0));

        JLabel playlistsLabel = new JLabel("Listas de reproducción:");
        playlistsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playlistsPanel.add(playlistsLabel, BorderLayout.NORTH);

        JTable playlistsTable = new JTable();
        String[] playlistsTableHead = {"Lista de reproducción", "Dueño"};
        DefaultTableModel playlistsModel = new DefaultTableModel(playlistsTableHead, 0);
        playlistsTable.setModel(playlistsModel);
        playlistsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistsPanel.add(new JScrollPane(playlistsTable));


        videosPanel.setLayout(new BorderLayout(0, 0));

        JTable videosTable = new JTable();
        String[] videosTableHead = {"Título", "Dueño"};
        DefaultTableModel videosModel = new DefaultTableModel(videosTableHead, 0);
        videosTable.setModel(videosModel);
        videosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        videosPanel.add(new JScrollPane(videosTable));

        JLabel videosLabel = new JLabel("Videos:");
        videosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        videosPanel.add(videosLabel, BorderLayout.NORTH);

        categoriesPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel categoryLabel = new JLabel("Categoría:");
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoriesPanel.add(categoryLabel);

        JComboBox categoryComboBox = new JComboBox();
        categoryComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                for (int row = 0; row < playlistsTable.getRowCount(); ) {
                    playlistsModel.removeRow(row);
                }
                for (int row = 0; row < videosTable.getRowCount(); ) {
                    videosModel.removeRow(row);
                }

                if (!"-".equals(categoryComboBox.getSelectedItem())) {
                    CategoryDT category = (CategoryDT) categoryComboBox.getSelectedItem();
                    IVideoController videoController = ControllerFactory.getInstance().getVideoController();
                    IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();
                    try {
                        List<PlaylistDT> playlists = playlistController.listPlaylistByCategory(category.getName());
                        playlists.forEach(pl -> {
                            String[] row = {pl.getName(), pl.getOwner().getOwner()};
                            playlistsModel.addRow(row);
                        });
                    } catch (EntityNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    List<VideoDT> videos = videoController.listVideosByCategory(category.getName());
                    videos.forEach(video -> {
                        String[] row = {video.getTitle(), video.getUploader()};
                        videosModel.addRow(row);
                    });
                }
            }
        });

        categoriesPanel.add(categoryComboBox);

        ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();
        List<CategoryDT> categories = categoryController.listCategories();
        categoryComboBox.addItem("-");
        categories.forEach(category -> categoryComboBox.addItem(category));
        getContentPane().setLayout(groupLayout);


    }
}
