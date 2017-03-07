/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Ship;

/**
 *
 * @author afpa
 */

public class ShipTable extends AbstractTableModel {
    private final String[] entetes = {"N° de voile", "Nom voilier", "Prenom", "Nom", "Classe"};
    private List<Ship> ships;

    public ShipTable(List<Ship> ships) {
        this.ships = ships;
    }
    
    public void addShip(Ship ship) {
        ships.add(ship);
        this.fireTableDataChanged();
    }
    public void removeShip(Ship ship) {
        ships.remove(ship);
        this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return ships.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    public Ship getShip(int rowIndex) {
        return ships.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
            return ships.get(rowIndex).getVoi_num_voile();

            case 1:
            return ships.get(rowIndex).getVoi_nom();
                
            case 2:
            return ships.get(rowIndex).getOwner().getPer_prenom();
            
            case 3:
            return ships.get(rowIndex).getOwner().getPer_nom();
            
            case 4:
            return ships.get(rowIndex).getShipClass().getCla_nom();
            
            
            default:
                throw new IllegalArgumentException();
        }

    }
}
