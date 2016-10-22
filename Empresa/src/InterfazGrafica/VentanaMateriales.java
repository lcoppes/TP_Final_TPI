
package InterfazGrafica;

import Controladora.Controladora;
import Controladora.InterfazException;
import Controladora.InterfazMaquina;

import empresa.Maquina;

import empresa.Material;

import java.util.HashMap;

import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class VentanaMateriales
  extends javax.swing.JFrame implements InterfazMaquina
{
    HashMap<Integer, Maquina> maquinas;
    HashMap<Integer, Material> inventario;

  /** Creates new form VentanaMateriales */
  public VentanaMateriales(HashMap<Integer, Maquina> maquinas, HashMap<Integer, Material> inventario)
  {
    initComponents();
    inicializarComponentes();
    this.maquinas = maquinas;
    this.inventario = inventario;
  }
  
    private void inicializarComponentes(){
        jComboBox1.removeAllItems();
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setEditable(false);
        agregar.setActionCommand(InterfazMaquina.AGREGAR);
        eliminar.setActionCommand(InterfazMaquina.ELIMINAR);
        mod.setActionCommand(InterfazMaquina.MODIFICAR);
        volver.setActionCommand(InterfazMaquina.VOLVER);
    }
  
    @Override
    public void mostrar(){
        this.setVisible(true);
    }
    
    @Override
    public void ocultar(){
        this.setVisible(false);
    }
    
    @Override
    public void cerrar(){
        this.dispose();
    }
    
    @Override
    public void setControlador(Controladora c){
        agregar.addActionListener(c);
        eliminar.addActionListener(c);
        mod.addActionListener(c);
        volver.addActionListener(c);
    }
    
    @Override
    public void refresh(){
        cargarCombo();
        jTextArea1.setText(maquinas.get(Integer.parseInt((String) jComboBox1.getSelectedItem())).getDescripcion());
        listadoMaterialesMaquina();
        listadoMaterialesInventario();
    }
    
    @Override
    public int getCodigoMaquina(){
        return Integer.parseInt((String) jComboBox1.getSelectedItem());
    }
    
    @Override
     public Material getMatStockSeleccionado()
        throws InterfazException
    {
        int row = jTable2.getSelectedRow();
        if(row == -1)
            throw new InterfazException("No se ha seleccionado material del inventario.");
        return (Material) jTable2.getValueAt(row, 0);
    }
    
    @Override
    public Material getMatProdSeleccionado()
        throws InterfazException
    {
        int row = jTable1.getSelectedRow();
        if(row == -1)
            throw new InterfazException("No se ha seleccionado material de la receta.");
        return (Material) jTable1.getValueAt(row, 0);    
    }
    
    @Override
    public double getCantidad()
        throws InterfazException, NumberFormatException
    {
        String strNumber = JOptionPane.showInputDialog(null, "Ingrese la cantidad.",
                                                       "GuiLeoCrisAl S.A.", JOptionPane.INFORMATION_MESSAGE);
        if(strNumber == null || strNumber.isEmpty())
            throw new InterfazException("No se ha ingresado la cantidad.");
        return Double.parseDouble(strNumber);
    }
    
    private void cargarCombo(){
        jComboBox1.removeAllItems();
        Iterator<Maquina> itMaq = maquinas.values().iterator();
        while(itMaq.hasNext())
            jComboBox1.addItem("" + itMaq.next().getCodigo());
        
    }
    
    private void listadoMaterialesMaquina(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Iterator<Material> itMat = maquinas.get(Integer.parseInt((String) jComboBox1.getSelectedItem())).getListadoMateriales().values().iterator();
        while(itMat.hasNext()){
            Object row[] = new Object[4];
            Material auxMat = itMat.next();
            row[0] = auxMat;
            row[1] = String.format("MAT%06d", auxMat.getCodigoMaterial());
            row[2] = auxMat.getDescripcion();
            row[3] = String.format("%4.3f", auxMat.getCantidad());
            model.addRow(row);
        }
    }
    
    private void listadoMaterialesInventario(){
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Iterator<Material> itMat = inventario.values().iterator();
        while(itMat.hasNext()){
            Object row[] = new Object[4];
            Material auxMat = itMat.next();
            row[0] = auxMat;
            row[1] = String.format("MAT%06d", auxMat.getCodigoMaterial());
            row[2] = auxMat.getDescripcion();
            row[3] = String.format("%4.3f", auxMat.getCantidad());
            model.addRow(row);
        }
    }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        mod = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        volver = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuiLeoCrisAl S.A.");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Maquinas y Materiales"));

        jLabel1.setText("Codigo de maquina:");

        jLabel2.setText("Descripcion:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Receta");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sel", "Codigo", "Descripcion", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Sel");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Codigo");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Cantidad");
        }

        agregar.setText("Agregar");

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        mod.setText("Modificar cantidad");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock de Materiales"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sel", "Codigo", "Descripcion", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setHeaderValue("Sel");
            jTable2.getColumnModel().getColumn(1).setHeaderValue("Codigo");
            jTable2.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            jTable2.getColumnModel().getColumn(3).setHeaderValue("Cantidad");
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(agregar)
                .addGap(11, 11, 11)
                .addComponent(eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        volver.setText("Volver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(volver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        jTextArea1.setText(maquinas.get(Integer.parseInt((String) jComboBox1.getSelectedItem())).getDescripcion());
        listadoMaterialesMaquina();
        listadoMaterialesInventario();
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

  /**
   * @param args the command line arguments
   */
  public static void main(String args[])
  {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException ex)
    {
      java.util.logging.Logger.getLogger(VentanaMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (InstantiationException ex)
    {
      java.util.logging.Logger.getLogger(VentanaMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (IllegalAccessException ex)
    {
      java.util.logging.Logger.getLogger(VentanaMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util.logging.Logger.getLogger(VentanaMateriales.class.getName()).log(java.util.logging.Level.SEVERE, null,
                                                                                ex);
    }
    //</editor-fold>


  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton mod;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

}
