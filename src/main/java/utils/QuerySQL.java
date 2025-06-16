/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Carlos Ribera
 */
public class QuerySQL {

    public static final String SELECT_ALL_REPARACIONES = "SELECT r.id, " + 
            "r.fecha_entrada, " + 
            "r.fecha_salida, " + 
            "r.precio, " + 
            "r.garantia, " + 
            "r.comentarios, " + 
            "r.estado, " +
            "r.id_marca AS idMarca, " + 
            "r.id_modelo AS idModelo, " + 
            "r.id_tipo_reparacion AS idReparacion, " + 
            "r.id_cliente, " + 
            "m.marca, " + 
            "mo.modelo, " + 
            "t.reparacion, " + 
            "c.nombre, " + 
            "c.apellidos, " + 
            "c.telefono, " + 
            "c.direccion " + 
            "FROM reparacion r " + 
            "JOIN marca m ON r.id_marca = m.id " + 
            "JOIN modelo mo ON r.id_modelo = mo.id " + 
            "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " + 
            "JOIN cliente c ON r.id_cliente = c.id ";
    
    public static final String SELECT_REPARACIONES_BY_CLIENTE_ID = "SELECT r.id, " + 
            "r.fecha_entrada, " + 
            "r.fecha_salida, " + 
            "r.precio, " + 
            "r.garantia, " + 
            "r.comentarios, " +
            "r.estado, " +
            "r.id_marca AS idMarca, " + 
            "r.id_modelo AS idModelo, " +
            "r.id_tipo_reparacion AS idReparacion, " + 
            "r.id_cliente, " + 
            "m.marca, " + 
            "mo.modelo, " + 
            "t.reparacion, " + 
            "c.nombre, " + "c.apellidos, " + 
            "c.telefono, " + "c.direccion " + 
            "FROM reparacion r " + 
            "JOIN marca m ON r.id_marca = m.id " + 
            "JOIN modelo mo ON r.id_modelo = mo.id " + 
            "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " + 
            "JOIN cliente c ON r.id_cliente = c.id " + 
            "WHERE r.id_cliente = ?";
    
    public static final String SELECT_REPARACIONES = "SELECT r.id, " + "r.fecha_entrada, " + "r.fecha_salida, " + "r.precio, " + "r.garantia, " + "r.comentarios, " + "r.id_marca AS idMarca, " + "r.id_modelo AS idModelo, " + "r.id_tipo_reparacion AS idReparacion, " + "r.id_cliente, " + "m.marca, " + "mo.modelo, " + "t.reparacion, " + "c.nombre, " + "c.apellidos, " + "c.telefono, " + "c.direccion " + "FROM reparacion r " + "JOIN marca m ON r.id_marca = m.id " + "JOIN modelo mo ON r.id_modelo = mo.id " + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " + "JOIN cliente c ON r.id_cliente = c.id " + "WHERE c.telefono = ? ";
    public static final String MODIFICAR_REPARACION = "UPDATE reparacion SET " + 
            "fecha_entrada = ?, " + 
            "fecha_salida = ?, " + 
            "precio = ?, " +
            "garantia = ?, " +
            "comentarios = ?, " +
            "estado = ?, " +
            "id_marca = ?, " +
            "id_modelo = ?, " +
            "id_tipo_reparacion = ? " +
            "WHERE id = ?";
    public static final String DELETE_REPARACION = "DELETE FROM reparacion WHERE id = ?";
    public static final String INSERT_REPARACION = " INSERT INTO reparacion (fecha_entrada, fecha_salida, id_marca, id_modelo, id_tipo_reparacion, " + "precio, garantia, comentarios, id_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_REPARACION_BY_ID = "SELECT r.id, " + "r.fecha_entrada, " + "r.fecha_salida, " + "r.precio, " + "r.garantia, " + "r.comentarios, " + "r.id_marca AS idMarca, " + "r.id_modelo AS idModelo, " + "r.id_tipo_reparacion AS idReparacion, " + "r.id_cliente, " + "m.marca, " + "mo.modelo, " + "t.reparacion, " + "c.nombre, " + "c.apellidos, " + "c.telefono, " + "c.direccion " + "FROM reparacion r " + "JOIN marca m ON r.id_marca = m.id " + "JOIN modelo mo ON r.id_modelo = mo.id " + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " + "JOIN cliente c ON r.id_cliente = c.id " + "WHERE r.id = ?";
    public static final String SELECT_REPARACION_BY_DATE = "SELECT r.id AS idReparacion, " +
            "r.fecha_entrada, " +
            "r.fecha_salida, " +
            "r.precio, " +
            "r.garantia, " +
            "r.comentarios, " +
            "r.id_marca AS idMarca, " +
            "r.id_modelo AS idModelo, " +
            "r.id_tipo_reparacion AS idReparacionTipo, " +
            "r.id_cliente AS idCliente, " +
            "m.marca, " +
            "mo.modelo, " +
            "t.reparacion, " +
            "c.nombre, " +
            "c.apellidos, " +
            "c.telefono, " +
            "c.direccion " +
            "FROM reparacion r " +
            "JOIN marca m ON r.id_marca = m.id " +
            "JOIN modelo mo ON r.id_modelo = mo.id " +
            "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " +
            "JOIN cliente c ON r.id_cliente = c.id " +
            "WHERE r.fecha_entrada >= ? AND r.fecha_salida <= ?";
    
    public static final String SELECT_REPARACION_BY_PHONE_DATE = "SELECT r.id AS idReparacion, " +
            "r.fecha_entrada, " +
            "r.fecha_salida, " +
            "r.precio, " +
            "r.garantia, " +
            "r.comentarios, " +
            "r.id_marca AS idMarca, " +
            "r.id_modelo AS idModelo, " +
            "r.id_tipo_reparacion AS idReparacionTipo, " +
            "r.id_cliente AS idCliente, " +
            "m.marca, " +
            "mo.modelo, " +
            "t.reparacion, " +
            "c.nombre, " +
            "c.apellidos, " +
            "c.telefono, " +
            "c.direccion " +
            "FROM reparacion r " +
            "JOIN marca m ON r.id_marca = m.id " +
            "JOIN modelo mo ON r.id_modelo = mo.id " +
            "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id " +
            "JOIN cliente c ON r.id_cliente = c.id " +
            "WHERE c.telefono = ? AND r.fecha_entrada >= ? AND r.fecha_salida <= ?";
    
    public static final String UPDATE_GARANTIA_REPARACION = "UPDATE reparacion SET " + "garantia = ?" + " WHERE id = ?";
}
