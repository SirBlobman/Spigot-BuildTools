package org.spigotmc.gui.modals;

import com.jeff_media.javafinder.JavaInstallation;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import org.spigotmc.gui.components.ColumnsAutoSizer;
import org.spigotmc.gui.data.JavaInstallationManager;
import org.spigotmc.gui.panels.options.OptionsPanel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import org.spigotmc.utils.SwingUtils;

public class SelectJavaModal extends JFrame {

    private JTable javaTable;

    private final OptionsPanel optionsPanel;
    private final JavaInstallationManager javaInstallationManager;
    private final Map<Integer, JavaInstallation> tableInstallationMap;

    public SelectJavaModal(final OptionsPanel optionsPanel, final JavaInstallationManager javaInstallationManager) {
        this.optionsPanel = optionsPanel;
        this.javaInstallationManager = javaInstallationManager;
        this.tableInstallationMap = new HashMap<>();

        SwingUtils.applyIcon(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select a Java Version");
        setSize(new Dimension(600, 500));

        initComponents();

        javaTable.getModel().addTableModelListener((TableModelEvent event) -> ColumnsAutoSizer.sizeColumnsToFit(javaTable));
        final DefaultTableModel model = (DefaultTableModel) javaTable.getModel();

        for (JavaInstallation installation : javaInstallationManager.getInstallations()) {
            tableInstallationMap.put(model.getRowCount(), installation);
            model.insertRow(model.getRowCount(), new Object[]{
                installation.isCurrentJavaVersion(),
                installation.getType() + " " + installation.getVersion().getMajor(),
                installation.getJavaExecutable().getAbsolutePath()
            });
        }
        javaTable.setColumnSelectionAllowed(false);
        javaTable.setRowSelectionAllowed(true);
        javaTable.changeSelection(0, 0, false, false);
    }

    private void initComponents() {
        javaTable = new JTable();
        javaTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Primary", "Version", "Path"}) {

            Class<?>[] types = new Class[]{String.class, String.class, String.class};

            boolean[] canEdit = new boolean[]{false, false, false};

            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        javaTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        javaTable.setColumnSelectionAllowed(true);
        javaTable.getTableHeader().setReorderingAllowed(false);
        javaTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (javaTable.getColumnModel().getColumnCount() > 0) {
            javaTable.getColumnModel().getColumn(0).setResizable(false);
            javaTable.getColumnModel().getColumn(1).setResizable(false);
            javaTable.getColumnModel().getColumn(2).setResizable(false);
        }

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(javaTable);

        final JLabel titleLabel = new JLabel("Select a Java Version");

        final JButton selectButton = new JButton("Select");
        selectButton.setMargin(new Insets(8, 16, 8, 16));
        selectButton.addActionListener(this::selectButtonActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(titleLabel)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGap(265, 265, 265)
                    .addComponent(selectButton)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titleLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(selectButton)
                    .addContainerGap())
        );

        pack();
    }

    private void selectButtonActionPerformed(ActionEvent event) {
        final JavaInstallation installation = tableInstallationMap.get(javaTable.getSelectedRow());
        javaInstallationManager.setSelectedInstallation(installation);
        optionsPanel.setJavaExecutable(installation);
        dispose();
    }

}
